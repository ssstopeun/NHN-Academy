package com.nhnacademy.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.Student.Student;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JsonStudentRepository implements StudentRepository {
    private final ObjectMapper objectMapper;
    //json file 저장 경로
    private static final String JSON_FILE_PATH="C:\\Users\\User\\Desktop\\pre_project\\NHN-Academy\\4_java-servlet-jsp\\StudentManagment4_Json\\src\\main\\json\\student.json";

    public JsonStudentRepository(){
        objectMapper = new ObjectMapper();
        //LocalDatetime json 직열화/역직렬화 가능하도록 설정
        objectMapper.registerModule(new JavaTimeModule());
        //todo JSON_FILE_PATH 경로에 json 파일이 존재하면 삭제 합니다.

        File file = new File(JSON_FILE_PATH);
        if(file.exists()){
            file.delete();
        }
    }

    private synchronized List<Student> readJsonFile(){
        //todo json 파일이 존재하지 않다면 비어있는 List<Student> 리턴
        List<Student> students;
        File file = new File(JSON_FILE_PATH);

        if(!file.exists()){
            return new ArrayList<>();
        }

        //json read & 역직렬화 ( json string -> Object )
        try(FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            students = objectMapper.readValue(bufferedReader, new TypeReference<List<Student>>() {});
            return  students;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private synchronized void writeJsonFile(List<Student> studentList){
        // List<Student> 객체를 -> json 파일로 저장 : 직렬화
        File file = new File(JSON_FILE_PATH);

        try(
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ) {
            objectMapper.writeValue(bufferedWriter,studentList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Student student) {
        //json String -> Object 형태로 변화 (직렬화)
        List<Student> students = readJsonFile();
        //List에 student 추가
        students.add(student);
        //List<Student>객체를 -> json String 형태로 저장(직렬화)
        writeJsonFile(students);
    }

    @Override
    public void update(Student student) {
        List<Student> students = readJsonFile();
        Optional<Student> target = students.stream()
                .filter(o->o.getId().equals(student.getId()))
                .findFirst();

        if(target.isPresent()){
            target.get().update(student.getName(),student.getGender(),student.getAge());
            writeJsonFile(students);
        }
    }

    @Override
    public void deleteById(String id) {
        List<Student> students = readJsonFile();
        boolean isDeleted = students.removeIf(o->o.getId().equals(id));
        if(isDeleted){
            writeJsonFile(students);
        }

    }

    @Override
    public Student getStudentById(String id) {
        List<Student> students = readJsonFile();
        Optional<Student> student = students.stream()
                .filter(o->o.getId().equals(id)).findFirst();

        if(student.isPresent()){
            return student.get();
        }
        return null;
    }

    @Override
    public List<Student> getStudents() {
        return readJsonFile();
    }

    @Override
    public boolean existById(String id) {
        List<Student> students = readJsonFile();
        long count = students.stream()
                .filter(o->o.getId().equals(id))
                .count();
        return count>0?true:false;
    }
}