package com.jcoder.spring.data.jpa.tutorial.repository;

import com.jcoder.spring.data.jpa.tutorial.entity.CourseMaterial;
import com.jcoder.spring.data.jpa.tutorial.entity.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMaterialRepository  extends JpaRepository<CourseMaterial,Long> {

}
