package com.jcoder.spring.data.jpa.tutorial.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Data // will generate @ToString, hashcode, equals and getter setter method
@NoArgsConstructor
@AllArgsConstructor
@Builder // to add builder pattern for student entity
@Entity // will create table in db
@Table(name = "student",
        uniqueConstraints = @UniqueConstraint(
                name = "email_unique",
                columnNames = "email_address")) // Name of table will be
@ToString(exclude = "course")
public class Student implements Serializable {

    @Id
    @SequenceGenerator(name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "student_sequence")
    private Long studentId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_address")
    private String email;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name="student_id",
            referencedColumnName = "studentId"
    )
    private List<Department> departments = new ArrayList<>();



    @Embedded
    private Guardian guardian;

    //@JsonIgnoreProperties({"hibernateLazyInitializer"})
//    @ManyToMany(
//            mappedBy = "students"
//    )
//    @JsonIgnoreProperties({"hibernateLazyInitializer"})
//    private Set<Course> courses=new TreeSet<>();

//    public void addCourses(Course course){
//        if (courses==null)
//            courses=new TreeSet<>();
//
//        courses.add(course);
//    }

}
