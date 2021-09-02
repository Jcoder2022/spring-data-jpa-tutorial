package com.jcoder.spring.data.jpa.tutorial.repository;

import com.jcoder.spring.data.jpa.tutorial.entity.Course;
import com.jcoder.spring.data.jpa.tutorial.entity.CourseMaterial;
import com.jcoder.spring.data.jpa.tutorial.entity.Student;
import com.jcoder.spring.data.jpa.tutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.Access;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public  void saveCourse(){
        Course course = Course.builder()
                .credit(5)
                .title("DBA")
                .build();
        courseRepository.save(course);

    }

    @Test
    public  void saveCourseMaterial() {

//        Teacher teacherLW = Teacher.builder()
//                .firstName("Louise")
//                .lastName("White")
//                .build();


        Teacher teacherSW = Teacher.builder()
                .firstName("Smith")
                .lastName("White")
                .build();

        Course courseDA = Course.builder()
                .credit(32)
                .title("Data Analysis")
                .teacher(teacherSW)
                .build();



        Course courseMaths = Course.builder()
                .credit(32)
                .title("Maths")
                .teacher(teacherSW)
                .build();


//        CourseMaterial courseMaterial = CourseMaterial.builder()
//                .url("http://jcoder.com")
//                .course(List.of(courseDA,courseMaths))
//                .build();
        courseRepository.saveAll(List.of(courseDA,courseMaths));

    }


    //Call pagination and sort method
    @Test
    public void findAllpagination(){

        Pageable firstPagethreeRecords = PageRequest.of(0,3);

        List<Course> coursesFirstPageWithThree =
                courseRepository.findAll(firstPagethreeRecords).getContent()
                        ;

        System.out.println(coursesFirstPageWithThree);

        System.out.println("------------------------------------------------------------------------------------------------------");


        Pageable firstPagetwoRecords = PageRequest.of(0,2, Sort.by("title"));


        Long totalElements =courseRepository
                .findAll(firstPagetwoRecords).getTotalElements();

        System.out.println(" total Elements = "+totalElements);


        int totalPages =courseRepository
                .findAll(firstPagetwoRecords).getTotalPages();

        System.out.println("total Pages = " + totalPages);

        System.out.println("------------------------------------------------------------------------------------------------------");




    }

    @Test
    public void findAllWithSorting(){
        Pageable firstPagethreeRecordswithSorting = PageRequest.of(0,3, Sort.by("title"));

        List<Course> coursefirstPagethreeRecordswithSorting =courseRepository
                .findAll(firstPagethreeRecordswithSorting).getContent();

        System.out.println(coursefirstPagethreeRecordswithSorting);

        Pageable firstPagethreeRecordsSortBytitleDescendingandCredits  = PageRequest.of(0,3,
                Sort.by("title")
                        .descending()
                        .and(Sort.by("credit").ascending()));

        List<Course> courses = courseRepository.findAll(firstPagethreeRecordsSortBytitleDescendingandCredits).getContent();
        System.out.println(courses);

    }

    @Test
    public void findBytitleContaining(){

        Pageable pageable = PageRequest.of(0,5);

        Page<Course> course = courseRepository.findByTitleContaining("D", pageable);

        System.out.println(course.getContent());
    }

@Test
    public void twoStudentsTakingSameCourse(){


    Student student1 = Student.builder()
            .firstName("1234Tabish")
            .lastName("1234Aslam")

            .email("1234tabish@gmail.com")
            .build();

    Student student2 = Student.builder()
            .firstName("1234Khurram")
            .lastName("1234Aslam")
            .email("1234khurram@gmail.com")
            .build();
    Course course = Course.builder()
            .title("DBMS1234")
            .credit(4)
            //.students(List.of(student1,student2))
            .build() ;

    course.addStudent(student1);
    course.addStudent(student2);
    courseRepository.save(course);


}

        @Test
    public void fetchCourse(){
        List<Course> courseList = courseRepository.findAll();
        System.out.println(courseList);
    }

}