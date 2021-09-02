package com.jcoder.spring.data.jpa.tutorial.repository;

import com.jcoder.spring.data.jpa.tutorial.entity.Course;
import com.jcoder.spring.data.jpa.tutorial.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Test
    public  void saveCourseMaterial(){

        Course course = Course.builder()
                .credit(32)
                .title("Data Structures")
                .build();


        CourseMaterial courseMaterial = CourseMaterial.builder()
                        .url("http://google.com")
                .course(course)
                .build();
        courseMaterialRepository.save(courseMaterial);

        //if we try to save it, following exception will occur
        /*  InvalidDataAccessApiUsageException: org.hibernate.TransientPropertyValueException: object references an unsaved transient instance -
        save the transient instance before flushing : com.jcoder.spring.data.jpa.tutorial.entity.CourseMaterial.course -> com.jcoder.spring.data.jpa.tutorial.entity.Course;
         nested exception is java.lang.IllegalStateException: org.hibernate.TransientPropertyValueException: object references an unsaved transient instance -
        save the transient instance before flushing : com.jcoder.spring.data.jpa.tutorial.entity.CourseMaterial.course -> com.jcoder.spring.data.jpa.tutorial.entity.Course */


        //Here comes the concept of cascading, there are different types of cascading ENUMS are available
        // @OneToOne(cascade = CascadeType.ALL), exception will be removed and it will save course
    }


    @Test
    public  void findByCourseMaterial(){

        List<CourseMaterial> cmList = courseMaterialRepository.findAll();

        System.out.println(cmList);
    }


}