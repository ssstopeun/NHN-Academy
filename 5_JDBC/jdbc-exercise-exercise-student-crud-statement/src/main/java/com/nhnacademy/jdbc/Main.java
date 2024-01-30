package com.nhnacademy.jdbc;

import com.nhnacademy.jdbc.student.domain.Student;
import com.nhnacademy.jdbc.util.DbUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static com.nhnacademy.jdbc.util.DbUtils.getConnection;

@Slf4j
public class Main {
    private static Connection connection = null;
    public static void main(String[] args) throws SQLException {
        Student student = new Student("student8","엔에이치엔아카데미", Student.GENDER.F,30);
        try(Connection connection = DbUtils.getConnection();
            Statement statement = connection.createStatement()){
            String sql = "update jdbc_students set name = '"+student.getName()+"', age = "+student.getAge()+", gender = '"+student.getGender()+"' where id  '"+student.getId()+"'";
            System.out.println(statement.executeUpdate(sql));
        }catch(SQLException e){
            log.error("Error update student: {}",e.getMessage());
        }
    }
}
