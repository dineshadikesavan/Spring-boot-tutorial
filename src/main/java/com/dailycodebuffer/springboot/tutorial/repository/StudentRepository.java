package com.dailycodebuffer.springboot.tutorial.repository;

import com.dailycodebuffer.springboot.tutorial.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public List<Student> findByFirstName(String firstName);

    public List<Student> findByFirstNameContaining(String firstName);

    public List<Student> findByLastNameNotNull();

    public List<Student> findByGuardianName(String guardianName);

    // JPQL
    @Query("select s from Student s where s.studentEmail = ?1")
    public Student getStudentByEmailAddress(String emailAddress);

    // JPQL
    @Query("select s.firstName from Student s where s.studentEmail = ?1")
    public String getStudentFirstNameByEmailAddress(String emailAddress);

    // Native query
    @Query(
            value ="select * from tbl_student s where s.email_address = ?1",
            nativeQuery = true
    )
    public Student getStudentByEmailAddressNative(String emailAddress);

    @Query(
            value ="select * from tbl_student s where s.email_address = :email",
            nativeQuery = true)
    public Student getStudentByEmailNativeNamedParam(String email);

    @Modifying
    @Transactional
    @Query(
            value="update tbl_student set last_name = ?1 where email_address = ?2",
            nativeQuery = true
    )
    public int updateStudentLastnameByEmailAddressNative(String lastName, String emailAddress);
}
