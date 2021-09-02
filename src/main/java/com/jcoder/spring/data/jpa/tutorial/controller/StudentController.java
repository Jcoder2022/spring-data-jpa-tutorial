package com.jcoder.spring.data.jpa.tutorial.controller;

import com.jcoder.spring.data.jpa.tutorial.entity.Department;
import com.jcoder.spring.data.jpa.tutorial.entity.Student;
import com.jcoder.spring.data.jpa.tutorial.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> students() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<List<Student>>( students,HttpStatus.OK);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> student(@PathVariable("id") Long id) {

        Student student = null;
        try {
            student   = studentService.getStudentById(id);
        }
        catch (Exception e){
            //need to return guest user
        }
        return new ResponseEntity<Student>(student,HttpStatus.OK);
    }

    @GetMapping("/student/Dept/{id}")
    public ResponseEntity<List<Department>> studentDepartment(@PathVariable("id") Long id) {

        Student student = null;
        try {
            student   = studentService.getStudentById(id);
        }
        catch (Exception e){
            return new ResponseEntity<List<Department>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Department>>(student.getDepartments(),HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}/Dept/{deptId}")
    public ResponseEntity<Department> studentWithDepartmentId(@PathVariable("studentId") Long studentId,
                                                              @PathVariable("deptId") Long deptId) {

        Student student = null;
        Department dept = null;
        try {
            student   = studentService.getStudentById(studentId);
            List<Department> departments = student.getDepartments();
            for(Department d:departments){
                if( d.getDepartmentId()==deptId)
                {dept = d;}
            }
        }
        catch (Exception e){
            return new ResponseEntity<Department>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Department>(dept,HttpStatus.OK);
    }

    @PostMapping("/student")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        boolean flag = studentService.saveStudent(student);
        if (flag == false) {
            return new ResponseEntity<Student>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Student>(student, HttpStatus.CREATED);
    }

    @PutMapping("/student")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        studentService.updateStudent(student);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    @DeleteMapping("student")
    public ResponseEntity<Void> deleteStudent(@RequestBody Student student) {
        studentService.deleteStudent(student);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
