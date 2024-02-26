package com.nhnacademy.homework1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.homework1.config.RootConfig;
import com.nhnacademy.homework1.config.WebConfig;
import com.nhnacademy.homework1.domain.Student;
import com.nhnacademy.homework1.domain.StudentRequest;
import com.nhnacademy.homework1.exception.StudentNotFoundException;
import com.nhnacademy.homework1.exception.ValidationFailedException;
import com.nhnacademy.homework1.repository.StudentRepository;
import com.nhnacademy.homework1.validator.StudentValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.util.NestedServletException;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class StudentRestControllerTest {
    private StudentRepository studentRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    private final LocalValidatorFactoryBean validatorFactory = new LocalValidatorFactoryBean();

    MockMvc mockMvc;


    @BeforeEach
    void setUp(){
            studentRepository = mock(StudentRepository.class);
            mockMvc = MockMvcBuilders.standaloneSetup(new StudentRestController(studentRepository, new StudentValidator()))
                    .build();


    }

    @Test
    @Order(1)
    @DisplayName("POST TEST")
    void registerStudent() throws Exception {
        StudentRequest studentRequest = new StudentRequest();
        ReflectionTestUtils.setField(studentRequest, "name", "test");
        ReflectionTestUtils.setField(studentRequest, "email", "jeongji1416@naver.com");
        ReflectionTestUtils.setField(studentRequest, "score", 30);
        ReflectionTestUtils.setField(studentRequest, "evaluation", "good");



        mockMvc.perform(post("/students/register").content(objectMapper.writeValueAsString(studentRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    @Order(2)
    @DisplayName("POST TEST - 잘못된 입력값 부여")
    void registerStudentInvalidInput() throws Exception{
        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setName("test");
        studentRequest.setEmail("jeongji1416@naver.com");
        studentRequest.setScore(30);
        studentRequest.setEvaluation("200자넘는 evaluation - ........................................................................................................................................................................................................");

        String student = objectMapper.writeValueAsString(studentRequest);


        Throwable th = catchThrowable(() ->
                mockMvc.perform(post("/students/register").contentType(MediaType.APPLICATION_JSON).content(student)).andExpect(status().isBadRequest()).andReturn());
        assertThat(th)
                .isInstanceOf(NestedServletException.class)
                .hasCauseInstanceOf(ValidationFailedException.class);
    }


    @Test
    @Order(3)
    @DisplayName("GET TEST")
    void getStudent() throws Exception {
        Student student = Student.create("김학생","kim.student@nhnacademy.com",100,"훌륭");
        when(studentRepository.exists(anyLong())).thenReturn(true);
        when(studentRepository.getStudent(anyLong())).thenReturn(student);
        mockMvc.perform(get("/students/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("김학생"))
                        .andExpect(jsonPath("$.email").value("kim.student@nhnacademy.com"))
                        .andExpect(jsonPath("$.score").value(100))
                        .andExpect(jsonPath("$.evaluation").value("훌륭"))
                .andReturn();
    }


    @Test
    @Order(4)
    @DisplayName("GET TEST - 없는 student 반환")
    void getStudentFail() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/students/3"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        assertEquals(StudentNotFoundException.class, mvcResult.getResolvedException().getClass());
    }

    @Test
    @Order(5)
    @DisplayName("PUT TEST")
    void updateStudent() throws Exception {

        Student student1 = Student.create("김학생","kim.student@nhnacademy.com",100,"훌륭");
        Student student2 = Student.create("change","change@naver.com",50,"덜 훌륭");

        StudentRequest studentRequest = new StudentRequest();
        ReflectionTestUtils.setField(studentRequest, "name", "change");
        ReflectionTestUtils.setField(studentRequest, "email", "change@naver.com");
        ReflectionTestUtils.setField(studentRequest, "score", 50);
        ReflectionTestUtils.setField(studentRequest, "evaluation", "덜 훌륭");


        when(studentRepository.exists(anyLong())).thenReturn(true);
        when(studentRepository.getStudent(anyLong())).thenReturn(student1);
        when(studentRepository.getStudent(anyLong())).thenReturn(student2);

        mockMvc.perform(put("/students/1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(studentRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    @Order(6)
    @DisplayName("PUT TEST - 없는 student 수정 시도")
    void updateStudentFail() throws Exception {

        StudentRequest studentRequest = new StudentRequest();
        ReflectionTestUtils.setField(studentRequest, "name", "change");
        ReflectionTestUtils.setField(studentRequest, "email", "change@naver.com");
        ReflectionTestUtils.setField(studentRequest, "score", 50);
        ReflectionTestUtils.setField(studentRequest, "evaluation", "덜 훌륭");


        MvcResult mvcResult = mockMvc.perform(put("/students/1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(studentRequest)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
        assertEquals(StudentNotFoundException.class, mvcResult.getResolvedException().getClass());
    }

    @Test
    @Order(7)
    @DisplayName("POST TEST - 잘못된 수정값 부여")
    void modifyStudentInvalidInput() throws Exception{
        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setName("test");
        studentRequest.setEmail("jeongji1416@naver.com");
        studentRequest.setScore(30);
        studentRequest.setEvaluation("200자넘는 evaluation - ........................................................................................................................................................................................................");

        String student = objectMapper.writeValueAsString(studentRequest);

        when(studentRepository.exists(anyLong())).thenReturn(true);

        Throwable th = catchThrowable(() ->
                mockMvc.perform(put("/students/1").contentType(MediaType.APPLICATION_JSON).content(student)).andExpect(status().isBadRequest()).andReturn());
        assertThat(th)
                .isInstanceOf(NestedServletException.class)
                .hasCauseInstanceOf(ValidationFailedException.class);
    }

}
