package com.jcoder.spring.data.jpa.tutorial.service;

import com.jcoder.spring.data.jpa.tutorial.entity.Department;
import com.jcoder.spring.data.jpa.tutorial.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllStudents(){
        return departmentRepository.findAll();
    }
    public Department getDeptById(Long id){
        return departmentRepository.getById(id);
    }

    public Boolean saveDept(Department department){
        Department d = departmentRepository.save(department);

        return true;
    }

    public void updateDept(Department department){
        saveDept(department);
    }

    public void deleteDepartment(Department dept){
        if(departmentRepository.existsById(dept.getDepartmentId())){
            departmentRepository.delete(dept);
        }
    }


}
