package com.nhnacademy.certificatesystem.config;

import com.nhnacademy.certificatesystem.Base;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackageClasses = Base.class,
    excludeFilters = { @ComponentScan.Filter(Controller.class)})
public class RootConfig {

//    @Bean
//    public StudentRepository studentRepository() {
//        StudentRepository studentRepository = new StudentRepositoryImpl();
//        studentRepository.register("김학생", "kim.student@nhnacademy.com", 100, "훌륭");
//
//        return studentRepository;
//    }


    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasename("message");

        return messageSource;
    }

}
