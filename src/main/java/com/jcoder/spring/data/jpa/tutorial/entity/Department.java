package com.jcoder.spring.data.jpa.tutorial.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="department")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department {

    @Id
    @SequenceGenerator(name = "department_sequence",
            sequenceName = "department_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "department_sequence")
    private Long departmentId;
    private String departmentName;


}
