package com.nhnacademy.springmvc.domain;

import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// TODO #11: 수정 요청 객체
@Value
public class UserModifyRequest {
    @NotNull
    @Size(max = 50)
    String name;

    @Min(0)
    int age;
}
