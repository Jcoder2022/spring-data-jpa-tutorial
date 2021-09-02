package com.jcoder.spring.data.jpa.tutorial.service;

import com.jcoder.spring.data.jpa.tutorial.entity.Student;
import com.jcoder.spring.data.jpa.tutorial.exception.StudentNotFoundException;
import com.jcoder.spring.data.jpa.tutorial.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }
    public Student getStudentById(Long id) throws StudentNotFoundException {
            return  studentRepository.findById(id)
                    .orElseThrow(StudentNotFoundException::new);

    }
    @Transactional
    public Boolean saveStudent(Student student){
        Student s = studentRepository.save(student);

        return true;
    }

    public void updateStudent(Student student){
        saveStudent(student);
    }
    @Transactional
    public void deleteStudent(Student student){
        if(studentRepository.existsById(student.getStudentId())){
            studentRepository.delete(student);
        }
    }

}
