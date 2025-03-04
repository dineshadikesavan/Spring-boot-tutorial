package com.dailycodebuffer.springboot.tutorial.repository;

import com.dailycodebuffer.springboot.tutorial.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    public Department findByDepartmentName(String departmentName);

    public Department findByDepartmentNameIgnoreCase(String departmentName);

    @Query(value =  "SELECT * FROM DEPARTMENT WHERE DEPARTMENT_CODE = ?1"
            , countQuery = "SELECT COUNT(*) FROM DEPARTMENT WHERE DEPARTMENT_CODE = ?1"
            , nativeQuery = true)
    public Department findByDeptCode(String departmentCode);

}
