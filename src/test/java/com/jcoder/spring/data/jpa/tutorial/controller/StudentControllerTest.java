package com.jcoder.spring.data.jpa.tutorial.controller;

import com.jcoder.spring.data.jpa.tutorial.AbstractTest;
import com.jcoder.spring.data.jpa.tutorial.entity.Department;
import com.jcoder.spring.data.jpa.tutorial.entity.Student;
import com.jcoder.spring.data.jpa.tutorial.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(StudentController.class)
public class StudentControllerTest extends AbstractTest {


    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentService studentService;

    @Test
    public void getStudentsList() throws Exception {
        String uri = "http://localhost:8080/api/students";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Student[] studentlist = super.mapFromJson(content, Student[].class);
        assertEquals(studentlist, super.mapFromJson(Collections.EMPTY_LIST.toString(), Student[].class));
    }
    @MockBean
    UriComponentsBuilder builder;

    @Test
    public void saveStudent() throws Exception {
        String uri = "http://localhost:8080/api/student";

        Department department_HR = Department.builder()
                .departmentName("HR")
                .build();

        Department department_BUSINESS = Department.builder()
                .departmentName("BUSINESS")
                .build();



        Student student = Student.builder()
                .firstName("Arsalan")
                .lastName("Shahid")
                .email("1000000arsalan@gmail.com")
                .departments(List.of(department_HR,department_BUSINESS))
                .build();


        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType("application/json")
                        .content(super.mapToJson(student))).andReturn();


        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

    }


}