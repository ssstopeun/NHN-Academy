package com.nhnacademy.Controller;

import com.nhnacademy.Student.Student;
import com.nhnacademy.repository.StudentRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class StudentListController implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        StudentRepository studentRepository = (StudentRepository) request.getServletContext().getAttribute("studentRepository");
        List<Student> studentList = studentRepository.getStudents();
        request.setAttribute("studentList",studentList);

        return "/student/list.jsp";
    }
}
