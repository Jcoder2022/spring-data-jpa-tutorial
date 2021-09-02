package com.jcoder.spring.data.jpa.tutorial.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "course",uniqueConstraints = @UniqueConstraint(name="title",columnNames = "title"))
public class Course implements Serializable {
    @Id
    @SequenceGenerator(name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "course_sequence")
    private Long courseId;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "credit")
    private Integer credit;

    @OneToOne(
            mappedBy = "course"
    ) //  we will not define join column here, we will say it is mapped by another column
    private CourseMaterial courseMaterial;

    //Here table structure will remain same
    @ManyToOne(cascade = CascadeType.ALL

    )
    @JoinColumn(
            name="teacher_id",
            referencedColumnName = "teacherId"
    )
    private Teacher teacher;


    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name="student_course",
            joinColumns = @JoinColumn(name = "course_id" //database column
                    ,referencedColumnName = "courseId" //class property
                     ),//owning side
            inverseJoinColumns=@JoinColumn(name = "student_id", referencedColumnName = "studentId")// non owning side
    )

    private List<Student> students;

    public void addStudent(Student student){
        if (students==null)
            students = new ArrayList<>();

        students.add(student);
    }

}
