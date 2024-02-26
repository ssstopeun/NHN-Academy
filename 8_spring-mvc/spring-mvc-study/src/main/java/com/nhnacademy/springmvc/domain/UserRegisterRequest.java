package com.nhnacademy.springmvc.domain;

import lombok.Value;

// TODO #5: 사용자 등록 요청 객체
@Value
public class UserRegisterRequest {
    String id;
    String password;
    int age;

}
