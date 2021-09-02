package com.jcoder.spring.data.jpa.tutorial.repository;

import com.jcoder.spring.data.jpa.tutorial.entity.Course;
import com.jcoder.spring.data.jpa.tutorial.entity.Guardian;
import com.jcoder.spring.data.jpa.tutorial.entity.Student;
import com.jcoder.spring.data.jpa.tutorial.vo.CourseVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .firstName("Nawaz")
                .lastName("Sharif")
                .email("nawaz@gmail.com")
                //.guardianName("Ijaz Sharif")
                //.guardianMobile("4545454545454")
                //.guardianEmail("ijaz@yahoo.com")
                .build();

        studentRepository.save(student);
    }


    @Test
    public void saveStudentwithGuardian(){
        Guardian guardian = Guardian.builder()
                .mobile("3434343434")
                .name("Alpha")
                .email("Alpha@gmail.com")
                .build();

        Course course = Course.builder()
                .title("Algorithms")
                .credit(4)
                .build() ;

        Course course1  = Course.builder()
                .title("Algebra")
                .credit(4)
                .build() ;

        Student student = Student.builder()
                .guardian(guardian)
                .firstName("AlphaFather")
                .lastName("Rashid")
                .email("alphafather@gmail.com")
                //.courses(Arrays.asList(course,course1))
                .build();
       studentRepository.save(student);
    }


    @Test
    public void OneStudentTakingmultipleCourses(){


        Student student1 = Student.builder()
                .firstName("20000Michael")
                .lastName("Akram")

                .email("20000ike@gmail.com")
                .build();


        Course course = Course.builder()
                .title("20000Physics")
                .credit(4)
                .students(List.of(student1))
                .build() ;

        Course course1 = Course.builder()
                .title("20000Chem")
                .credit(4)
                .students(List.of(student1))
                .build() ;

       // student1.setCourses(Set.of(course,course1));

//        course.addStudent(student1);
//        course1.addStudent(student1);

        //student1.addCourses(course1);
        //student1.addCourses(course);

        studentRepository.save(student1);


    }


    @Test
    public void findStudentswithCourses(){

        List<Student> students= studentRepository.findByFirstName("AlphaFather");
        System.out.println(students);
    }



    @Test
    public void findStudentswithGuardian(){

        List<Student> students= studentRepository.findByGuardianName("Sadia");
        System.out.println(students);
    }

    @Test
    public void findStudentswithGuardianCourse(){
        Student s = studentRepository.findByFirstName("AlphaFather").get(0);
        System.out.println(s);
    }


    @Test
    public void findStudentsUsingJPQL(){

        Student student= studentRepository.findByEmailAddress("sabeen@gmail.com");
        System.out.println(student);
    }

    @Test
    public void findStudentsUsingNative(){

        Student student= studentRepository.findByEmailAddressNative("sabeen@gmail.com");
        System.out.println(student);
    }

    @Test
    public void findStudentsUsingNativeNamedParam(){

        Student student= studentRepository.findByEmailAddressNativeNamedParam("sabeen@gmail.com");
        System.out.println(student);
    }


    @Test
    public void updateStudentsUsingNativeNamedParam(){

        Integer status= studentRepository.updateStudentNameByEmail("Sarmad","sabeen@gmail.com");
        System.out.println(status);
    }


    @Test
    public void updateStudentsUsingNativeNamedParamWithQuestionMarks(){

        Integer status= studentRepository.updateStudentNameByEmail("Sohail","sabeen@gmail.com");
        System.out.println(status);
    }

    @Test
    public void deleteStudentsUsingNativeNamedParamWithQuestionMarks(){

        Integer status= studentRepository.deleteStudentNameByEmail("sabeen@gmail.com");
        System.out.println(status);
    }

    @Test

    public void saveStudentwithCourses(){

        // create a student
//        Student student = Student.builder()
//                .firstName("Dale123BarnJoe")
//                .lastName("Dale")
//                .email("Dale@gmail.com")
//                .build();
//
//        // save the student
//        //studentRepository.save(student);
//
//        // create three courses
//        Course course1 = Course.builder()
//                .credit(4)
//                .title("AI01")
//                .students(Arrays.asList(student))
//                .build();
//        Course course2 = Course.builder()
//                .credit(4)
//                .title("AI02")
//                .students(Arrays.asList(student))
//                .build();
//        Course course3 = Course.builder()
//                .credit(4)
//                .title("AI03")
//                .students(Arrays.asList(student))
//                .build();
//        // save courses
//        courseRepository.saveAll(Arrays.asList(course1, course2, course3));
//


        // add courses to the student
//        student.addCourses(course1);
//        student.addCourses(course2);
//        student.addCourses(course3);
//
        //student.setCourses(Arrays.asList(course1, course2, course3));
        // update the student

        Student s = studentRepository.findByFirstName("Dale123BarnJoe").get(0);
        System.out.println(s);

//        studentRepository.saveAndFlush(student);



    }
    @Test
    public void findCoursesNativeQueryfromFirstName(){
        List<Long> courseIds = studentRepository.findByCoursesNamedParam("Dale123BarnJoe");
        System.out.println(courseIds);

        Optional<Course> course = courseRepository.findById(courseIds.get(0));

        System.out.println(" Credit = " +course.get().getCredit()+ " Title = " + course.get().getTitle() + " Teacher = " +course.get().getTeacher().getFirstName());
        //System.out.println(course1);
        //System.out.println(course2);

    }

}

