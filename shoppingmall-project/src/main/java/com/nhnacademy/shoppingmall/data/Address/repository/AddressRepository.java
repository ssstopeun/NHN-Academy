package com.nhnacademy.shoppingmall.data.Address.repository;

import com.nhnacademy.shoppingmall.data.Address.domain.Address;

import java.util.ArrayList;
import java.util.Optional;

public interface AddressRepository {
    ArrayList<Address> findAllByUserId(String userId);
    Optional<Address> findById(int addressId);
    int save(Address address);
    int updateByAddressId(int addressId, Address address);
    int deleteByAddressId(int addressId);
//    int findAddressId(Address address);
//    int countByAddressId(int addressId);

}
