package com.nhnacademy.homework1.validator;

import com.nhnacademy.homework1.domain.StudentRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class StudentValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return StudentRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        StudentRequest request = (StudentRequest) target;

        String evaluation = request.getEvaluation().replace((" "), "");
        if (evaluation.length() > 200) {
            errors.rejectValue("evaluation", "", "content max length is 200");
        }
    }
}
