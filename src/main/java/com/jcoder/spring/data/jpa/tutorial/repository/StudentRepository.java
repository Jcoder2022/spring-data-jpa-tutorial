package com.jcoder.spring.data.jpa.tutorial.repository;

import com.jcoder.spring.data.jpa.tutorial.entity.Course;
import com.jcoder.spring.data.jpa.tutorial.entity.Student;
import com.jcoder.spring.data.jpa.tutorial.vo.CourseVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public List<Student> findByFirstName(String firstName);

    public List<Student> findByFirstNameAndEmail(String firstName, String email);

    //find students having particular characters
    public List<Student> findByFirstNameContaining(String name);

    //recond which are not null
    public List<Student> findByLastNameNotNull();

    //get data based on Guardian name
    public List<Student> findByGuardianName(String guardianName);

    public Student findByFirstNameAndLastName(String firstName, String lastName);

    //JPQL based on classes you created not based on tables, using @Query here we dont use table name and its properties but we use class and its attributes
    @Query("select s from Student s where s.email=?1")
    public Student findByEmailAddress(String emailId);

    // Let us say I only wanted to get student first name
    @Query("select s.firstName from Student s where s.email=?1")
    public String findByFirstNameEmailAddress(String emailId);


    //Native Query Language
    //Let us say you very complex query, you can execute with JPQL as well

    @Query(value = "SELECT * FROM student s where s.email_address=?1 ", nativeQuery = true)
    public Student findByEmailAddressNative(String emailAddress);

    //Native Named Parameter, providing parameter like ?1, ?2 is not better way
    @Query(value = "SELECT * FROM student s where s.email_address= :emailId ", nativeQuery = true)
    public Student findByEmailAddressNativeNamedParam(@Param("emailId") String emailAddress);


    //Native Named Parameter, providing parameter like ?1, ?2 is not better way
    @Query(value = " select c.course_id   \n" +
            "FROM course c \n" +
            "inner join  student_course sc \n" +
            "on c.course_id = sc.course_id\n" +
            "where sc.student_id = (select student_id from student where first_name= :firstName )",nativeQuery = true)
    public List<Long> findByCoursesNamedParam(@Param("firstName") String firstName);




    //So far, All methods were fetching data, next is updattion using modify annotation
    //Transactional and Modifying Annotation
    //As it is modifying query, we need to make it transaction (we neeed to make it as unit operation)

    @Modifying
    @Transactional
    @Query(
            value="update student s set s.first_Name= ?1  where  s.email_address= ?2  ",
            nativeQuery = true
    )
    int updateStudentNameByEmailwithQuestionMarks(String firstName,String email);


    @Modifying
    @Transactional
    @Query(
            value="update student s set s.first_Name= :firstName  where  s.email_address= :email  ",
            nativeQuery = true
    )
    int updateStudentNameByEmail(String firstName,String email);

    // for deletion, Again we will use @Modifying and @Transaction

    @Modifying
    @Transactional
    @Query(
            value="delete from student s  where  s.email_address= :email  ",
            nativeQuery = true
    )
    int deleteStudentNameByEmail(String email);




}
