package com.jcoder.spring.data.jpa.tutorial.controller;

import com.jcoder.spring.data.jpa.tutorial.entity.Department;
import com.jcoder.spring.data.jpa.tutorial.entity.Student;
import com.jcoder.spring.data.jpa.tutorial.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(name="/api")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/department")
    public ResponseEntity<Void> addDepartment(@RequestBody Department dept, UriComponentsBuilder builder) {
        boolean flag = departmentService.saveDept(dept);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/student/{id}").buildAndExpand(dept.getDepartmentId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

}
