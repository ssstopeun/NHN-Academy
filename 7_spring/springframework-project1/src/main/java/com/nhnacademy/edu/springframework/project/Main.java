package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.service.*;
import com.nhnacademy.edu.springframework.project.repository.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;
import java.util.List;

public class Main {

    // TODO 9 - 성공적으로 실행되어야 합니다.
    public static void main(String[] args) {
//        DataLoadService dataLoadService = new CsvDataLoadService();
//        dataLoadService.loadAndMerge();
//
//        DefaultStudentService studentService = new DefaultStudentService();
//        Collection<Student> passedStudents = studentService.getPassedStudents();
//        System.out.println(passedStudents);
//
//        Collection<Student> orderedStudents = studentService.getStudentsOrderByScore();
//        System.out.println(orderedStudents);
//
//        DefaultGradeQueryService defaultGradeQueryService = new DefaultGradeQueryService();
//        List<Score> sameNameStudents = defaultGradeQueryService.getScoreByStudentName("A");
//        System.out.println(sameNameStudents);
//
//        Score seq1Score = defaultGradeQueryService.getScoreByStudentSeq(12);
//        System.out.println(seq1Score);


//        AnnotationConfigApplicationContext context =
//                      new AnnotationConfigApplicationContext("com.nhnacademy.edu.springFramework.project.AOP");
//
//            DataLoadService dataLoadService = context.getBean("csvDataLoadService", CsvDataLoadService.class);
//            dataLoadService.loadAndMerge();
//
//            StudentService studentService = context.getBean("defaultStudentService",DefaultStudentService.class);
//            System.out.println(studentService.getPassedStudents());
//
//            System.out.println(studentService.getStudentsOrderByScore());
//
//            GradeQueryService gradeQueryService = context.getBean("defaultGradeQueryService",DefaultGradeQueryService.class);
//            System.out.println(gradeQueryService.getScoreByStudentSeq(12));
//            System.out.println(gradeQueryService.getScoreByStudentName("A"));
//

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("com.nhnacademy.edu.springframework.project.AOP");
        DataLoadService dataLoadService = ctx.getBean("csvDataLoadService", CsvDataLoadService.class);
        dataLoadService.loadAndMerge();

        DefaultStudentService studentService = ctx.getBean("defaultStudentService", DefaultStudentService.class);
        Collection<Student> passedStudents = studentService.getPassedStudents();
        System.out.println("passed students : " + passedStudents);

        Collection<Student> orderedStudents = studentService.getStudentsOrderByScore();
        System.out.println("ordered students : " + orderedStudents);

        DefaultGradeQueryService queryService = ctx.getBean("defaultGradeQueryService", DefaultGradeQueryService.class);
        Collection<Score> scores = queryService.getScoreByStudentName("A");
        System.out.println("score : " + scores);

        Score seqScores = queryService.getScoreByStudentSeq(1);
        System.out.println("seq : " + seqScores);

    }
}
