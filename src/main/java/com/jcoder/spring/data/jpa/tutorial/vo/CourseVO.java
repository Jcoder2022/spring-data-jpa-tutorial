package com.jcoder.spring.data.jpa.tutorial.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseVO {
    private Long course_id;

    private String title;

    private Integer credit;
}
