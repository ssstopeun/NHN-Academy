package com.nhnacademy.shoppingmall.data.Address.service;

import com.nhnacademy.shoppingmall.data.Address.domain.Address;

import java.util.ArrayList;
import java.util.Optional;

public interface AddressService {
    ArrayList<Address> getAllAddress(String userId);
    Optional<Address> getAddress(int addressId);
    void saveAddress(Address address);
    void updateAddress(int addressId,Address address);
    void deleteAddress(int addressId);
}
