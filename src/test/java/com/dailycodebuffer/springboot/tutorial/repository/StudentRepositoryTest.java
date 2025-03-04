package com.dailycodebuffer.springboot.tutorial.repository;

import com.dailycodebuffer.springboot.tutorial.entity.Guardian;
import com.dailycodebuffer.springboot.tutorial.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .studentEmail("student@email.com")
                .firstName("John")
                .lastName("Doe")
                //.guardianEmail("john.doe@email.com")
                //.guardianName("John Doe")
                //.guardianMobile("2356469481")
                .build();
        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian() {
        Guardian guardian = Guardian.builder()
                .email("adi@email.com")
                .name("Adi")
                .mobile("123456790")
                .build();
        Student student = Student.builder()
                .studentEmail("dinesh@email.com")
                .firstName("Dinesh")
                .lastName("Adi")
                .guardian(guardian)
                //.guardianEmail("john.doe@email.com")
                //.guardianName("John Doe")
                //.guardianMobile("2356469481")
                .build();
        studentRepository.save(student);
    }

    @Test
    public void findAllStudents() {
        List<Student> students = studentRepository.findAll();
        System.out.println(students);
    }

    @Test
    public void findStudentByFirstName() {
        List<Student> students = studentRepository.findByFirstName("John");
        System.out.println(students);
    }

    @Test
    void findByFirstNameContaining() {
        List<Student> students = studentRepository.findByFirstNameContaining("John");
        System.out.println(students);
    }

    @Test
    void findByGuardianGuardianName() {
        List<Student> students = studentRepository.findByGuardianName("Adi");
        System.out.println(students);
    }

    @Test
    void getStudentByEmailAddress() {
        Student student = studentRepository.getStudentByEmailAddress("dinesh@email.com");
        System.out.println(student);
    }

    @Test
    void getStudentFirstNameByEmailAddress() {
        String studentName = studentRepository.getStudentFirstNameByEmailAddress("dinesh@email.com");
        System.out.println(studentName);
    }

    @Test
    void getStudentByEmailAddressNative() {
        Student student = studentRepository.getStudentByEmailAddressNative("dinesh@email.com");
        System.out.println(student);
    }

    @Test
    void getStudentByEmailNativeNamedParam() {
        Student student = studentRepository.getStudentByEmailNativeNamedParam("dinesh@email.com");
        System.out.println(student);
    }


    @Test
    void updateStudentLastnameByEmailAddressNative() {
        int result = studentRepository.updateStudentLastnameByEmailAddressNative("Adikesavan", "dinesh@email.com");
        System.out.println(result);
    }
}