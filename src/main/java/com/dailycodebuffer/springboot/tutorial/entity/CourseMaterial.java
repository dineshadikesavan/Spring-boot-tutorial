package com.dailycodebuffer.springboot.tutorial.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name ="tbl_course_material", uniqueConstraints = @UniqueConstraint(name="url", columnNames = "url"))
public class CourseMaterial {
    @Id
    @SequenceGenerator(name = "course_material_sequence", sequenceName = "course_material_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_material_sequence")
    private Long courseMaterialId;
    private String url;

    @OneToOne
    @JoinColumn(
            name="course_id", referencedColumnName = "courseId"
    )
    private Course course;
}
