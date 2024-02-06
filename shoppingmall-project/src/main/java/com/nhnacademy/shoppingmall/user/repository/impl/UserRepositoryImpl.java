package com.nhnacademy.shoppingmall.user.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class UserRepositoryImpl implements UserRepository {

    @Override
    public Optional<User> findByUserIdAndUserPassword(String userId, String userPassword) {
        /*todo#3-1 회원의 아이디와 비밀번호를 이용해서 조회하는 코드 입니다.(로그인)
          해당 코드는 SQL Injection이 발생합니다. SQL Injection이 발생하지 않도록 수정하세요.
         */

        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select user_id, user_name, user_password, user_birth, user_auth, user_point, created_at, latest_login_at from Users where user_id=? and user_password = ?";

        log.debug("sql:{}",sql);

        try(PreparedStatement psmt = connection.prepareStatement(sql)){
            psmt.setString(1,userId);
            psmt.setString(2,userPassword);
            ResultSet resultSet = psmt.executeQuery();
            if(resultSet.next()) {
                User user = new User(
                        resultSet.getString("user_id"),
                        resultSet.getString("user_name"),
                        resultSet.getString("user_password"),
                        resultSet.getString("user_birth"),
                        User.Auth.valueOf(resultSet.getString("user_auth")),
                        resultSet.getInt("user_point"),
                        Objects.nonNull(resultSet.getTimestamp("created_at")) ? resultSet.getTimestamp("created_at").toLocalDateTime() : null,
                        Objects.nonNull(resultSet.getTimestamp("latest_login_at")) ? resultSet.getTimestamp("latest_login_at").toLocalDateTime() : null
                );
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(String userId) {
        //todo#3-2 회원조회
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from Users where user_id = ?";

        try(PreparedStatement psmt = connection.prepareStatement(sql)){
            psmt.setString(1,userId);
            ResultSet resultSet = psmt.executeQuery();
            if(resultSet.next()) {
                User user = new User(
                        resultSet.getString("user_id"),
                        resultSet.getString("user_name"),
                        resultSet.getString("user_password"),
                        resultSet.getString("user_birth"),
                        User.Auth.valueOf(resultSet.getString("user_auth")),
                        resultSet.getInt("user_point"),
                        Objects.nonNull(resultSet.getTimestamp("created_at")) ? resultSet.getTimestamp("created_at").toLocalDateTime() : null,
                        Objects.nonNull(resultSet.getTimestamp("latest_login_at")) ? resultSet.getTimestamp("latest_login_at").toLocalDateTime() : null
                );
                return Optional.of(user);
            }
        }catch(SQLException e){
            log.error("Error findById from Users : {}",e.getMessage());
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public int save(User user) {
        //todo#3-3 회원등록, executeUpdate()을 반환합니다.

        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "INSERT INTO Users VALUES(?,?,?,?,?,?,?,?)";
        try(PreparedStatement psmt = connection.prepareStatement(sql)){
            psmt.setString(1,user.getUserId());
            psmt.setString(2,user.getUserName());
            psmt.setString(3,user.getUserPassword());
            psmt.setString(4,user.getUserBirth());
            psmt.setString(5, String.valueOf(user.getUserAuth()));
            psmt.setInt(6,user.getUserPoint());
            Timestamp createTime = Objects.nonNull(user.getCreatedAt()) ? Timestamp.valueOf(user.getCreatedAt()) : null;
            Timestamp loginTime = Objects.nonNull(user.getLatestLoginAt()) ? Timestamp.valueOf(user.getLatestLoginAt()) : null;
            psmt.setTimestamp(7, createTime);
            psmt.setTimestamp(8,loginTime);

            return psmt.executeUpdate();
        }catch(SQLException e){
            log.error("Error save from Users : {}",e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByUserId(String userId) {
        //todo#3-4 회원삭제, executeUpdate()을 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from Users where user_id = ?";
        try(PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1,userId);
            return psmt.executeUpdate();
        }catch(SQLException e){
            log.error("Error deleteByUserId from Users : {}",e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(User user) {
        //todo#3-5 회원수정, executeUpdate()을 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "update Users set user_name = ?,user_password = ?, user_birth = ?, user_auth = ?, user_point = ?, created_at = ?, latest_login_at = ? where user_id = ?";
        try(PreparedStatement psmt = connection.prepareStatement(sql)){
            psmt.setString(1,user.getUserName());
            psmt.setString(2,user.getUserPassword());
            psmt.setString(3,user.getUserBirth());
            psmt.setString(4, String.valueOf(user.getUserAuth()));
            psmt.setInt(5,user.getUserPoint());
            Timestamp createTime = Objects.nonNull(user.getCreatedAt()) ? Timestamp.valueOf(user.getCreatedAt()) : null;
            Timestamp loginTime = Objects.nonNull(user.getLatestLoginAt()) ? Timestamp.valueOf(user.getLatestLoginAt()) : null;
            psmt.setTimestamp(6, createTime);
            psmt.setTimestamp(7,loginTime);
            psmt.setString(8,user.getUserId());
            return psmt.executeUpdate();
        }catch(SQLException e){
            log.error("Error update from Users : {}",e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateLatestLoginAtByUserId(String userId, LocalDateTime latestLoginAt) {
        //todo#3-6, 마지막 로그인 시간 업데이트, executeUpdate()을 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "update Users set latest_login_at = ? where user_id = ?";
        try(PreparedStatement psmt = connection.prepareStatement(sql)){
            Timestamp loginTime = Timestamp.valueOf(latestLoginAt);
            psmt.setTimestamp(1,loginTime);
            psmt.setString(2,userId);
            return psmt.executeUpdate();
        }catch(SQLException e) {
            log.error("Error updateLatestLoginAtByUserId from Users : {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByUserId(String userId) {
        //todo#3-7 userId와 일치하는 회원의 count를 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from Users where user_id=?";
        try(PreparedStatement psmt = connection.prepareStatement(sql)){
            psmt.setString(1,userId);
            ResultSet resultSet = psmt.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
        }catch(SQLException e){
            log.error("Error countByUserId from Users : {}",e.getMessage());
            throw new RuntimeException(e);
        }
        return 0;
    }

}
