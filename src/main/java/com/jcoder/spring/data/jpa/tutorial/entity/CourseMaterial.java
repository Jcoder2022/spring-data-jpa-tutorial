package com.jcoder.spring.data.jpa.tutorial.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "course_material")
@ToString(exclude = "course")
public class CourseMaterial implements Serializable {
    @Id
    @SequenceGenerator(name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "course_material_sequence")
    private Long courseMaterialId;
    @Column(name = "url")
    private String url;
    //CourseMaterial consists of course, course material can not exists on its own, it has to have a course
    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false // while saving course material, it is mandatory to provide courses, otherwise it will throw dataIntegrityViolation Exception
    )
    //Two types of fetching LAZY and EAGER. that means when you call the parent(like data from courseMaterial, its children will be uploaded/not)
    //for which particular column, foreign key will be applied, it is referencing to courseId inside course and column id in CourseMaterial will be course_id
    //in case of lazy , will not bring children
    //in case of eager, will bring children
    @JoinColumn(name = "course_id", //
    referencedColumnName = "courseId"
    )
    private Course course;
}
