package com.jcoder.spring.data.jpa.tutorial.service;

import com.jcoder.spring.data.jpa.tutorial.repository.CourseMaterialRepository;
import com.jcoder.spring.data.jpa.tutorial.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseMaterialService {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;


}
