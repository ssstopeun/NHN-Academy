package com.nhnacademy.shoppingmall.data.Address.service.impl;

import com.nhnacademy.shoppingmall.data.Address.domain.Address;
import com.nhnacademy.shoppingmall.data.Address.repository.AddressRepository;
import com.nhnacademy.shoppingmall.data.Address.service.AddressService;

import java.util.ArrayList;
import java.util.Optional;

public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository){
        this.addressRepository=addressRepository;
    }
    @Override
    public ArrayList<Address> getAllAddress(String userId) {
        return addressRepository.findAllByUserId(userId);
    }

    @Override
    public Optional<Address> getAddress(int addressId) {
        Optional<Address> result = addressRepository.findById(addressId);
        return result;
    }

    @Override
    public void saveAddress(Address address) {
        //중복된 데이터들이라도 저장되게 되어야함.
        //addressId가 자동 증가라서 가능
        int result = addressRepository.save(address);
        if(result<1){
            throw new RuntimeException();
        }
    }

    @Override
    public void updateAddress(int addressId,Address address) {
        int result = addressRepository.updateByAddressId(addressId, address);
        if(result<1){
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteAddress(int addressId){
        int result = addressRepository.deleteByAddressId(addressId);
    }
}
