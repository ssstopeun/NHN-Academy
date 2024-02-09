package com.nhnacademy.shoppingmall.check.address.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.data.Address.domain.Address;
import com.nhnacademy.shoppingmall.data.Address.repository.AddressRepository;
import com.nhnacademy.shoppingmall.data.Address.repository.impl.AddressRepositoryImpl;
import com.nhnacademy.shoppingmall.data.user.domain.User;
import com.nhnacademy.shoppingmall.data.user.repository.UserRepository;
import com.nhnacademy.shoppingmall.data.user.repository.impl.UserRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

//todo#3-8 Test Code가 통과하도록 AddressRepositoryImpleTest 구현합니다.

@Slf4j
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class AddressRepositoryImplTest {
//    AddressRepository addressRepository = new AddressRepositoryImpl();
//
//    Address testAddress1;
//    Address testAddress2;
//    ArrayList<Address> testAddressList = new ArrayList<>();
//
//    @BeforeEach
//    void setUp() throws SQLException {
//        DbConnectionThreadLocal.initialize();
//        testAddress1 = new Address(100,"test_user","대구","지은","010-2020-4983","대구 달서구 본리동","문 앞에 둬 주세요");
//        addressRepository.save(testAddress1);
//        testAddressList.add(testAddress1);
//        testAddress2 = new Address(101,"test_user","대전","지은","010-2828-4888","대전 유성구",null);
//        addressRepository.save(testAddress2);
//        testAddressList.add(testAddress2);
//    }
//
//    @AfterEach
//    void tearDown() throws SQLException {
//        DbConnectionThreadLocal.setSqlError(true);
//        DbConnectionThreadLocal.reset();
//    }
//
//    @Test
//    @Order(1)
//    @DisplayName("userId에 해당하는 모든 address find")
//    void findAllByUserId() {
//        ArrayList<Address> addressList = addressRepository.findAllByUserId(testAddress1.getUserId());
//        Assertions.assertEquals(addressList.size(),testAddressList.size());
//    }
//
//    @Test
//    @Order(2)
//    @DisplayName("addressId로 address찾기")
//    void findById(){
//        Optional<Address> addressOptional = addressRepository.findById(addressRepository.findAddressId(testAddress1));
//        Assertions.assertEquals(testAddress1,addressOptional.get());
//    }
//
//    @Test
//    @Order(3)
//    @DisplayName("address 등록")
//    void save() {
//        Address address = new Address(102,"test_user","대구","지은","010-2020-4983","대구 달서구 본리동","문 앞에 둬 주세요");
//        int result = addressRepository.save(address);
//        Assertions.assertAll(
//                ()->Assertions.assertEquals(1,result),
//                ()->Assertions.assertEquals(address, addressRepository.findById(addressRepository.findAddressId(address)).get())
//        );
//    }
//
//    @Test
//    @Order(4)
//    @DisplayName("address 수정")
//    void update() {
//        int addressId = addressRepository.findAddressId(testAddress1);
//        testAddress1.setRecipient("지수");
//        testAddress1.setRequest("현관 비번 1234* 입니다.");
//
//        int result = addressRepository.updateByAddressId(addressId, testAddress1);
//        Assertions.assertAll(
//                ()->Assertions.assertEquals(1,result),
//                ()->Assertions.assertEquals(testAddress1, addressRepository.findById(addressRepository.findAddressId(testAddress1)).get())
//        );
//    }
//
//
//    @Test
//    @Order(5)
//    @DisplayName("addressId로 조회되는 address count 하기")
//    void countByUserId() {
//        int addressId = addressRepository.findAddressId(testAddress1);
//        int count = addressRepository.countByAddressId(addressId);
//        Assertions.assertEquals(1,count);
//
//    }
//
//    @Test
//    @Order(6)
//    @DisplayName("address 삭제")
//    void deleteByAddressId() {
//        int addressId = addressRepository.findAddressId(testAddress1);
//        Assertions.assertAll(
//                ()->Assertions.assertEquals(1,addressRepository.deleteByAddressId(addressId)),
//                ()->Assertions.assertFalse(addressRepository.findById(addressId).isPresent())
//        );
//
//    }
//
//


}