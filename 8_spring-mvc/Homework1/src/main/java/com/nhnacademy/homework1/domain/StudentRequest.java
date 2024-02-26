package com.nhnacademy.homework1.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.*;

@Data
@Getter
@Setter
public class StudentRequest {
    @NonNull
    String name;

    @Email
    String email;

    @Min(0)
    @Max(100)
    int score;

    @NotBlank
    String evaluation;

    public StudentRequest(){}
}
