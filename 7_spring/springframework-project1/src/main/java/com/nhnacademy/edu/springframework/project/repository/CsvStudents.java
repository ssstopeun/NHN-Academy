package com.nhnacademy.edu.springframework.project.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;



public class CsvStudents implements Students {
    private static CsvStudents csvStudents;

    private Collection<Student> students;

    public CsvStudents() {
        students = new ArrayList<>();
    }

    /** TODO 3 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/
    public static Students getInstance() {
        if(csvStudents == null){
            csvStudents = new CsvStudents();
        }
        return csvStudents;
    }

    // TODO 7 : student.csv 파일에서 데이터를 읽어 클래스 멤버 변수에 추가하는 로직을 구현하세요.
    // 데이터를 적재하고 읽기 위해서, 적절한 자료구조를 사용하세요.
    @Override
    public void load() {
        String csvFile = "src/main/resources/data/student.csv";
        String line;

        try(BufferedReader br= new BufferedReader(new FileReader(csvFile))){
            while((line = br.readLine())!=null){
                String[] data = line.split(",");
                Student student = new Student(Integer.parseInt(data[0]),data[1]);
                students.add(student);
            }

        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Collection<Student> findAll() {
        return students;
    }

    /**
     * TODO 8 : students 데이터에 score 정보를 추가하세요.
     * @param scores
     */
    @Override
    public void merge(Collection<Score> scores) {
        students.stream().sorted(Comparator.comparingInt(Student::getSeq));
        scores.stream().sorted(Comparator.comparingInt(Score::getStudentSeq));

        Iterator studentIterator = students.iterator();
        Iterator scoreIterator = scores.iterator();
        while(studentIterator.hasNext()&&scoreIterator.hasNext()){
            Student student = (Student) studentIterator.next();
            student.setScore((Score) scoreIterator.next());
        }

    }
}
