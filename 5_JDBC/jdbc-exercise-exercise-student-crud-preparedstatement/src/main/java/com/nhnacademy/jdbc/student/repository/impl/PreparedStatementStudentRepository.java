package com.nhnacademy.jdbc.student.repository.impl;

import com.nhnacademy.jdbc.student.domain.Student;
import com.nhnacademy.jdbc.student.repository.StudentRepository;
import com.nhnacademy.jdbc.util.DbUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Slf4j
public class PreparedStatementStudentRepository implements StudentRepository {

    @Override
    public int save(Student student){
        //todo#1 insert student
        try(Connection connection = DbUtils.getConnection()){
            String sql = "INSERT INTO jdbc_students(id,name,gender,age) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1,student.getId());
            statement.setString(2, student.getName());
            statement.setString(3, String.valueOf(student.getGender()));
            statement.setInt(4,student.getAge());

            int i = statement.executeUpdate();
            statement.close();
            return i;
        }catch(SQLException e){
            log.error("Error saving student: {}",e.getMessage());
            return 0;
        }
    }

    @Override
    public Optional<Student> findById(String id){
        //todo#2 student 조회
        try(Connection connection = DbUtils.getConnection()){
            String sql = "SELECT * from jdbc_students where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                Date date = resultSet.getDate("created_at");
                LocalDateTime localDateTime = Instant.ofEpochMilli(date.getTime())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();
                Student student = new Student(resultSet.getString("id"),resultSet.getString("name"), Student.GENDER.valueOf(resultSet.getString("gender")),resultSet.getInt("age"),localDateTime);
                statement.close();
                return Optional.of(student);
            }else{
                statement.close();
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
        try(Connection connection = DbUtils.getConnection()){
            String sql = "UPDATE jdbc_students set name = ?,age=?,gender=? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,student.getName());
            preparedStatement.setInt(2,student.getAge());
            preparedStatement.setString(3, String.valueOf(student.getGender()));
            preparedStatement.setString(4,student.getId());
            preparedStatement.executeUpdate();
            int i = preparedStatement.executeUpdate(sql);
            preparedStatement.close();
            return i;
        }catch(SQLException e){
            log.error("Error update student: {}",e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteById(String id){
        //todo#4 student 삭제
        try(Connection connection = DbUtils.getConnection()){
            String sql = "delete from jdbc_students where id= ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            int i = preparedStatement.executeUpdate();
            preparedStatement.close();
            return i;
        }catch(SQLException e){
            log.error("Error delete student: {}",e.getMessage());
            return 0;
        }

    }


}
