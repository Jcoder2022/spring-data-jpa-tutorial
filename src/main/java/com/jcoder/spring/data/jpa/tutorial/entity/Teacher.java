package com.jcoder.spring.data.jpa.tutorial.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "teacher")
public class Teacher implements Serializable {

    @Id
    @SequenceGenerator(name = "teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "teacher_sequence")
    private Long teacherId;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
/*NOTE : initially, we define list of courses available, i.e OneToMany relationship,
 There are many courses are available, and anyone course is taught by any one particular teacher
 rather than having this class with one teacher teaching multiple courses,
 one particular teacher teaching one particular course is more readable and understandable (This is preferable by JPA specs as well)

 Better approach is to define
 @ManyToOne in Course


    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY) //As one teacher can teacher multiple courses, Course table will have a teacher id
    @JoinColumn(
            name="teacher_id", //name of column
            referencedColumnName = "teacherId")
    private Set<Course> courses;
*/
}
