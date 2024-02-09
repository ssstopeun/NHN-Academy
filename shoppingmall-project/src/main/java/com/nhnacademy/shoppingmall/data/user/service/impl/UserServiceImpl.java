package com.nhnacademy.shoppingmall.data.user.service.impl;

import com.nhnacademy.shoppingmall.data.user.domain.User;
import com.nhnacademy.shoppingmall.data.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.data.user.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.data.user.repository.UserRepository;
import com.nhnacademy.shoppingmall.data.user.service.UserService;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUser(String userId){
        //todo#4-1 회원조회
        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty()){
            return null;
        }

        return user;
    }

    @Override
    public void saveUser(User user) {
        //todo#4-2 회원등록
        if(userRepository.countByUserId(user.getUserId())>0){
            throw new UserAlreadyExistsException(user.getUserId());
        }
        int result = userRepository.save(user);

        if(result<1){
            throw new RuntimeException();
        }
    }

    @Override
    public void updateUser(User user) {
        //todo#4-3 회원수정
        if(userRepository.countByUserId(user.getUserId())<1){
            throw new UserNotFoundException(user.getUserId());
        }

        int result = userRepository.update(user);
        if(result<1){
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteUser(String userId) {
        //todo#4-4 회원삭제
        if(userRepository.countByUserId(userId)<1){
            throw new UserNotFoundException(userId);
        }
        int result = userRepository.deleteByUserId(userId);
        if(result <1){
            throw new RuntimeException();
        }
    }

    @Override
    public User doLogin(String userId, String userPassword) {
        //todo#4-5 로그인 구현, userId, userPassword로 일치하는 회원 조회
        Optional<User> user = userRepository.findByUserIdAndUserPassword(userId,userPassword);
        if(user.isEmpty()){
            throw new UserNotFoundException(userId);
        }

        int result = userRepository.updateLatestLoginAtByUserId(userId, LocalDateTime.now());
        if(result<1){
            throw new RuntimeException();
        }

        return user.get();
    }

}
