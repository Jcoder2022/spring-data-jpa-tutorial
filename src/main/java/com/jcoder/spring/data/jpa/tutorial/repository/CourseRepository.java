package com.jcoder.spring.data.jpa.tutorial.repository;

import com.jcoder.spring.data.jpa.tutorial.entity.Course;
import com.jcoder.spring.data.jpa.tutorial.entity.Guardian;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CourseRepository  extends JpaRepository<Course,Long> {
    //custom sorting
    Page<Course> findByTitleContaining(String title, Pageable pageable );

}
