package com.dailycodebuffer.springboot.tutorial.service;

import com.dailycodebuffer.springboot.tutorial.entity.Department;
import com.dailycodebuffer.springboot.tutorial.exception.DepartmentNotFoundException;
import com.dailycodebuffer.springboot.tutorial.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long id) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            return department.get();
        } else {
            throw new DepartmentNotFoundException("Department not available");
        }
    }

    @Override
    public void deleteDepartmentById(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Department updateDepartmentById(Department department, Long id) {
       Department dep = departmentRepository.findById(id).get();
       if(department.getDepartmentAddress() != null){
           dep.setDepartmentAddress(department.getDepartmentAddress());
       }
       if(department.getDepartmentName() != null) {
           dep.setDepartmentName(department.getDepartmentName());
       }
       if(department.getDepartmentCode() != null) {
           dep.setDepartmentCode(department.getDepartmentCode());
       }
       return departmentRepository.save(dep);
    }

    @Override
    public Department getDepartmentByName(String name) {
        return departmentRepository.findByDepartmentNameIgnoreCase(name);
    }

    @Override
    public Department getDepartmentByDepartmentCode(String departmentCode) {
        return departmentRepository.findByDeptCode(departmentCode);
    }


}
