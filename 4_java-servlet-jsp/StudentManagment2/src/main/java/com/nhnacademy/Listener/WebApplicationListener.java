package com.nhnacademy.Listener;

import com.nhnacademy.Student.Gender;
import com.nhnacademy.repository.MapStudentRepository;
import com.nhnacademy.Student.Student;
import com.nhnacademy.repository.StudentRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalDateTime;

@WebListener
public class WebApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        StudentRepository studentRepository = new MapStudentRepository();

        for(int i=1;i<=10;i++){

            String id = "student"+i;
            String name = "아카데미"+i;
            int age = (int)(Math.random()*11)+10;
            Gender gender = i%2==0?Gender.M:Gender.F;

            Student student = new Student(id,name,gender,age);
            studentRepository.save(student);
        }
        // ...application scope에서 studentRepository객체에 접근할 수 있도록 구현하기
        context.setAttribute("studentRepository",studentRepository);
    }
}
