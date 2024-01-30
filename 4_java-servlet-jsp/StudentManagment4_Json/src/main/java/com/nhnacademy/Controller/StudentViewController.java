package com.nhnacademy.Controller;

import com.nhnacademy.Student.Student;
import com.nhnacademy.init.RequestMapping;
import com.nhnacademy.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
@RequestMapping(value = "/student/view.do", method = RequestMapping.Method.GET)
public class StudentViewController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        String id = req.getParameter("id");
        if(Objects.isNull(id)){
            throw new RuntimeException("[id] parameter is null");
        }

        Student student = studentRepository.getStudentById(id);
        if(Objects.isNull(student)){
            throw new RuntimeException("Student Not Found [id] : "+id);
        }
        log.error("student: {}",student);

        req.setAttribute("student",student);
        return "/student/view.jsp";
    }
}
