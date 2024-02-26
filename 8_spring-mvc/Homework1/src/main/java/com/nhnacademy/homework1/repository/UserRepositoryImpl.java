package com.nhnacademy.homework1.repository;

import com.nhnacademy.homework1.domain.User;
import com.nhnacademy.homework1.exception.UserAlreadyExistsException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final Map<String, User> userMap = new HashMap<>();

    @Override
    public boolean exists(String id) {
        return userMap.containsKey(id);
    }

    @Override
    public boolean matches(String id, String password) {
        return Optional.ofNullable(getUser(id))
                       .map(user -> user.getPassword().equals(password))
                       .orElse(false);
    }

    @Override
    public User getUser(String id) {
        return exists(id) ? userMap.get(id) : null;
    }

    @Override
    public User addUser(String id, String password) {
        return addUser(id, password, 0);
    }

    @Override
    public User addUser(String id, String password, int age) {
        if (exists(id)) {
            throw new UserAlreadyExistsException();
        }

        User user = User.create(id, password);
        user.setAge(age);

        userMap.put(id, user);

        return user;
    }

}
