package com.nhnacademy.shoppingmall.data.Address.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.data.Address.domain.Address;
import com.nhnacademy.shoppingmall.data.Address.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

@Slf4j
public class AddressRepositoryImpl implements AddressRepository {


    @Override
    public ArrayList<Address> findAllByUserId(String userId) {
        ArrayList<Address> results = new ArrayList<>();
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM Address WHERE user_id = ?";
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, userId);
            ResultSet resultSet = psmt.executeQuery();
            while (resultSet.next()) {
                Address result = new Address(
                        resultSet.getInt("address_id"),
                        resultSet.getString("user_id"),
                        resultSet.getString("address_name"),
                        resultSet.getString("recipient"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("address"),
                        resultSet.getString("request")
                );
                results.add(result);
            }
        } catch (SQLException e) {
            log.error("Error findAllByUserId from Address: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return results;
    }

    @Override
    public Optional<Address> findById(int addressId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM Address WHERE address_id = ?";
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, addressId);
            ResultSet resultSet = psmt.executeQuery();
            if (resultSet.next()) {
                Address result = new Address(
                        resultSet.getInt("address_id"),
                        resultSet.getString("user_id"),
                        resultSet.getString("address_name"),
                        resultSet.getString("recipient"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("address"),
                        resultSet.getString("request")
                );
                return Optional.of(result);
            }
        } catch (SQLException e) {
            log.error("Error findAddress from Address: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public int save(Address address) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "INSERT INTO Address(user_id,address_name,recipient,phone_number,address,request) VALUES(?,?,?,?,?,?);";
        try(PreparedStatement psmt = connection.prepareStatement(sql)){
            psmt.setString(1,address.getUserId());
            psmt.setString(2,address.getAddressName());
            psmt.setString(3,address.getRecipient());
            psmt.setString(4,address.getPhoneNumber());
            psmt.setString(5,address.getAddress());
            psmt.setString(6,address.getRequest());

            return psmt.executeUpdate();
        } catch (SQLException e) {
            log.error("Error findById from Address : {}",e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateByAddressId(int addressId, Address address) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "UPDATE Address SET address_name = ?, recipient = ?, phone_number = ?, address = ?, request = ? WHERE address_id = ?";
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, address.getAddressName());
            psmt.setString(2, address.getRecipient());
            psmt.setString(3, address.getPhoneNumber());
            psmt.setString(4, address.getAddress());
            psmt.setString(5, address.getRequest());
            psmt.setInt(6, addressId);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            log.error("Error update from Address: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByAddressId(int addressId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "DELETE FROM Address WHERE address_id = ?";
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, addressId);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            log.error("Error deleteByAddressId from Address: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public int findAddressId(Address address){
//        Connection connection = DbConnectionThreadLocal.getConnection();
//        String sql = "SELECT address_id FROM Address WHERE user_id = ? and address_name = ? and recipient = ? and phone_number = ? and address = ? and request = ?";
//        try(PreparedStatement psmt = connection.prepareStatement(sql)){
//            psmt.setString(1,address.getUserId());
//            psmt.setString(2,address.getAddressName());
//            psmt.setString(3,address.getRecipient());
//            psmt.setString(4,address.getPhoneNumber());
//            psmt.setString(5,address.getAddress());
//            psmt.setString(6,address.getRequest());
//            ResultSet resultSet = psmt.executeQuery();
//            if(resultSet.next()){
//                int result = resultSet.getInt(1);
//                return result;
//            }else{
//                throw new RuntimeException();
//            }
//        } catch (SQLException e) {
//            log.error("Error findAddressId from Address: {}",e.getMessage());
//            throw new RuntimeException(e);
//        }
//    }

//    @Override
//    public int countByAddressId(int addressId) {
//        Connection connection = DbConnectionThreadLocal.getConnection();
//        String sql = "SELECT COUNT(*) FROM Address WHERE address_id = ?";
//        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
//            psmt.setInt(1, addressId);
//            ResultSet rs = psmt.executeQuery();
//            if (rs.next()) {
//                return rs.getInt(1);
//            }
//            return 0;
//        } catch (SQLException e) {
//            log.error("Error countByUserId from userId: {}", e.getMessage());
//            throw new RuntimeException(e);
//        }
//    }
}
