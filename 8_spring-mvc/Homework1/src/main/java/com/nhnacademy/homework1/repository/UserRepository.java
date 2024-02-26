package com.nhnacademy.homework1.repository;

import com.nhnacademy.homework1.domain.User;

public interface UserRepository {
    boolean exists(String id);
    boolean matches(String id, String password);

    User getUser(String id);

    User addUser(String id, String password);

    User addUser(String id, String password, int age);

}
