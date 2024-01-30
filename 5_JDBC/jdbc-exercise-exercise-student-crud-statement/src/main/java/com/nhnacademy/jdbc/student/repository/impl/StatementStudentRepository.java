package com.nhnacademy.jdbc.student.repository.impl;

import com.nhnacademy.jdbc.student.domain.Student;
import com.nhnacademy.jdbc.student.repository.StudentRepository;
import com.nhnacademy.jdbc.util.DbUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

@Slf4j
public class StatementStudentRepository implements StudentRepository {
    @Override
    public int save(Student student){
        //todo#1 insert student
        try(Connection connection = DbUtils.getConnection();
        Statement statement = connection.createStatement()){
            String sql = "INSERT INTO jdbc_students(id,name,gender,age) VALUES ('"+student.getId()+"','"+student.getName()+"','"+student.getGender()+"',"+student.getAge()+")";
            return statement.executeUpdate(sql);
        }catch(SQLException e){
            log.error("Error saving student: {}",e.getMessage());
            return 0;
        }
    }

    @Override
    public Optional<Student> findById(String id){
        //todo#2 student 조회
        try(Connection connection = DbUtils.getConnection();
        Statement statement = connection.createStatement()){
            String sql = "SELECT * from jdbc_students where id = '"+id+"'";
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                Student student = new Student(resultSet.getString("id"),resultSet.getString("name"),resultSet.getString("gender"),resultSet.getInt("age"),resultSet.getDate("created_at"));
                return Optional.of(student);
            }else{
                return Optional.empty();
            }
        }catch(SQLException e){
            log.error("Error finid student: {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public int update(Student student){
        //todo#3 student 수정, name <- 수정합니다.
        try(Connection connection = DbUtils.getConnection();
        Statement statement = connection.createStatement()){
            String sql = "UPDATE jdbc_students set name = '"+student.getName()+"', age = "+student.getAge()+", gender = '"+student.getGender()+"' where id = '"+student.getId()+"'";
            return statement.executeUpdate(sql);
        }catch(SQLException e){
            log.error("Error update student: {}",e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteById(String id){
       //todo#4 student 삭제
        try(Connection connection = DbUtils.getConnection();
        Statement statement = connection.createStatement()){
            String sql = "delete from jdbc_students where id='"+id+"'";
            return statement.executeUpdate(sql);
        }catch(SQLException e){
            log.error("Error delete student: {}",e.getMessage());
            return 0;
        }

    }

}
