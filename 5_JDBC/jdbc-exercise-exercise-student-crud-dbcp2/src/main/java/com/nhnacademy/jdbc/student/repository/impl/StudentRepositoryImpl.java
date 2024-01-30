package com.nhnacademy.jdbc.student.repository.impl;

import com.nhnacademy.jdbc.student.domain.Student;
import com.nhnacademy.jdbc.student.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import java.sql.*;
import java.util.Optional;

@Slf4j
public class StudentRepositoryImpl implements StudentRepository {

    @Override
    public int save(Connection connection, Student student){
        //todo#2 학생등록
        try(Statement statement = connection.createStatement()){
            String sql = "INSERT INTO jdbc_students(id,name,gender,age) VALUES ('"+student.getId()+"','"+student.getName()+"','"+student.getGender()+"',"+student.getAge()+")";
            return statement.executeUpdate(sql);
        }catch(SQLException e){
            log.error("Error save : {}",e.getMessage());
        }
        return 0;
    }

    @Override
    public Optional<Student> findById(Connection connection,String id){
        //todo#3 학생조회
        try(Statement statement = connection.createStatement()){
            String sql = "SELECT * from jdbc_students where id = '"+id+"'";
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                Student student = new Student(resultSet.getString("id"),resultSet.getString("name"),resultSet.getString("gender"),resultSet.getInt("age"),resultSet.getDate("created_at"));
                return Optional.of(student);
            }else{
                return Optional.empty();
            }
        }catch(SQLException e){
            log.error("Error findById : {}",e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public int update(Connection connection,Student student){
        //todo#4 학생수정
        try(Statement statement = connection.createStatement()){
            String sql = "UPDATE jdbc_students set name = '"+student.getName()+"', age = "+student.getAge()+", gender = '"+student.getGender()+"' where id = '"+student.getId()+"'";
            return statement.executeUpdate(sql);
        }catch(SQLException e){
            log.error("Error update : {}",e.getMessage());
        }

        return 0;
    }

    @Override
    public int deleteById(Connection connection,String id){
        //todo#5 학생삭제
        try(Statement statement = connection.createStatement()){
            String sql = "delete from jdbc_students where id='"+id+"'";
            return statement.executeUpdate(sql);
        }catch(SQLException e){
            log.error("Error deleteById : {}",e.getMessage());
        }
        return 0;
    }

}