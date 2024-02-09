package com.nhnacademy.shoppingmall.data.user.service;

import com.nhnacademy.shoppingmall.data.user.domain.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUser(String userId);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(String userId);

    User doLogin(String userId, String userPassword);

}
