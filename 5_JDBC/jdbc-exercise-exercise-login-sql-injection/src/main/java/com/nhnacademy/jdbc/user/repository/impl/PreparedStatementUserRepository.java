package com.nhnacademy.jdbc.user.repository.impl;

import com.nhnacademy.jdbc.user.domain.User;
import com.nhnacademy.jdbc.user.repository.UserRepository;
import com.nhnacademy.jdbc.util.DbUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.Optional;

@Slf4j
public class PreparedStatementUserRepository implements UserRepository {
    @Override
    public Optional<User> findByUserIdAndUserPassword(String userId, String userPassword) {
        String sql = "select * from jdbc_users where user_id= ? and user_password = ?";
        //todo#11 -PreparedStatement- 아이디 , 비밀번호가 일치하는 회원조회
        try(Connection connection = DbUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,userId);
            preparedStatement.setString(2,userPassword);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                User user = new User(resultSet.getString("user_id"),resultSet.getString("user_name"),resultSet.getString("user_password"));
                return Optional.of(user);
            }
            return Optional.empty();
        }catch(SQLException e){
            log.info("Error findByUserIdAndUserPassword : {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findById(String userId) {
        //todo#12-PreparedStatement-회원조회
        try(Connection connection = DbUtils.getConnection()){
            String sql = "select * from jdbc_users where user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                User user = new User(resultSet.getString("user_id"),resultSet.getString("user_name"),resultSet.getString("user_password"));
                preparedStatement.close();
                return Optional.of(user);
            }
            return Optional.empty();
        }catch(SQLException e){
            log.error("Error findById : {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public int save(User user) {
        //todo#13-PreparedStatement-회원저장
        try(Connection connection = DbUtils.getConnection()){
            String sql = "INSERT into jdbc_users values(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getUserId());
            preparedStatement.setString(2,user.getUserName());
            preparedStatement.setString(3,user.getUserPassword());
            int i = preparedStatement.executeUpdate();
            preparedStatement.close();
            return i;
        } catch (SQLException e) {
            log.error("Error save : {}",e.getMessage());
        }
        return 0;
    }

    @Override
    public int updateUserPasswordByUserId(String userId, String userPassword) {
        //todo#14-PreparedStatement-회원정보 수정
        try(Connection connection = DbUtils.getConnection()){
            String sql="update jdbc_users set user_password = ? where user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userPassword);
            preparedStatement.setString(2,userId);
            int i = preparedStatement.executeUpdate();
            preparedStatement.close();
            return i;
        }catch(SQLException e){
            log.error("Error updateUserPasswordByUserId : {}",e.getMessage());
        }
        return 0;
    }

    @Override
    public int deleteByUserId(String userId) {
        //todo#15-PreparedStatement-회원삭제
        try(Connection connection=DbUtils.getConnection()){
            String sql = "delete from jdbc_users where user_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userId);
            int i = preparedStatement.executeUpdate();
            preparedStatement.close();
            return i;
        }catch(SQLException e){
            log.error("Error deleteByUserId : {}",e.getMessage());
        }
        return 0;
    }
}
