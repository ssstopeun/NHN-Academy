package com.nhnacademy.jdbc.club.repository.impl;

import com.nhnacademy.jdbc.club.domain.ClubStudent;
import com.nhnacademy.jdbc.club.repository.ClubRegistrationRepository;
import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Slf4j
public class ClubRegistrationRepositoryImpl implements ClubRegistrationRepository {

    @Override
    public int save(Connection connection, String studentId, String clubId) {
        //todo#11 - 핵생 -> 클럽 등록, executeUpdate() 결과를 반환
        String sql = "insert into jdbc_club_registrations values(?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,studentId);
            preparedStatement.setString(2,clubId);
            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int deleteByStudentIdAndClubId(Connection connection, String studentId, String clubId) {
        //todo#12 - 핵생 -> 클럽 탈퇴, executeUpdate() 결과를 반환
        int result = 0;
        String sql = "delete from jdbc_club_registrations where student_id = ? and club_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,studentId);
            preparedStatement.setString(2,clubId);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<ClubStudent> findClubStudentsByStudentId(Connection connection, String studentId) {
        //todo#13 - 핵생 -> 클럽 등록, executeUpdate() 결과를 반환
        String sql1 = "select * from jdbc_club_registrations where student_id = ?";
        String sql2 = "select * from jdbc_students where student_id = ?";
        String sql3 = "select * from jdbc_club where club_id = ?";
        List<ClubStudent> clubStudents = new ArrayList<>();
        try(PreparedStatement registrationStatement = connection.prepareStatement(sql1);
        PreparedStatement studentStatement = connection.prepareStatement(sql2);
        PreparedStatement clubStatement = connection.prepareStatement(sql3)){
            registrationStatement.setString(1,studentId);
            studentStatement.setString(1,studentId);
            ResultSet registrationResultSet = registrationStatement.executeQuery();
            ResultSet studentResultSet = studentStatement.executeQuery();
            String studentName = studentResultSet.getString("student_name");
            while(registrationResultSet.next()){
                String clubId = registrationResultSet.getString("club_id");
                clubStatement.setString(1,clubId);
                ResultSet clubResultSet = clubStatement.executeQuery();
                if(clubResultSet.next()){
                    String clubName = clubResultSet.getString("club_name");
                    ClubStudent clubStudent = new ClubStudent(studentId,studentName,clubId,clubName);
                    clubStudents.add(clubStudent);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clubStudents;
    }

    @Override
    public List<ClubStudent> findClubStudents(Connection connection) {
        //todo#21 - join
        String sql = "select r.student_id as student_id, s.name as student_name, r.club_id as club_id, c.club_name as club_name\n" +
                "from jdbc_club_registrations r\n" +
                "join jdbc_students s on s.id = r.student_id\n" +
                "join jdbc_club c on r.club_id = c.club_id;";
        List<ClubStudent> clubStudents = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String studentId = resultSet.getString("student_id");
                String studentName = resultSet.getString("student_name");
                String clubId = resultSet.getString("club_id");
                String clubName = resultSet.getString("club_name");
                ClubStudent clubStudent = new ClubStudent(studentId,studentName,clubId,clubName);
                clubStudents.add(clubStudent);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clubStudents;
    }

    @Override
    public List<ClubStudent> findClubStudents_left_join(Connection connection) {
        //todo#22 - left join
        return Collections.emptyList();
    }

    @Override
    public List<ClubStudent> findClubStudents_right_join(Connection connection) {
        //todo#23 - right join
        return Collections.emptyList();
    }

    @Override
    public List<ClubStudent> findClubStudents_full_join(Connection connection) {
        //todo#24 - full join = left join union right join
        return Collections.emptyList();
    }

    @Override
    public List<ClubStudent> findClubStudents_left_excluding_join(Connection connection) {
        //todo#25 - left excluding join
        return Collections.emptyList();
    }

    @Override
    public List<ClubStudent> findClubStudents_right_excluding_join(Connection connection) {
        //todo#26 - right excluding join
        return Collections.emptyList();
    }

    @Override
    public List<ClubStudent> findClubStudents_outher_excluding_join(Connection connection) {
        //todo#27 - outher_excluding_join = left excluding join union right excluding join
        return Collections.emptyList();
    }

}