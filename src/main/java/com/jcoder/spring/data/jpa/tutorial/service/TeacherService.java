package com.jcoder.spring.data.jpa.tutorial.service;

import com.jcoder.spring.data.jpa.tutorial.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
}
