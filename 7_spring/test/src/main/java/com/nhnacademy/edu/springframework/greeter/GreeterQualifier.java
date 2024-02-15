package com.nhnacademy.edu.springframework.greeter;

import com.nhnacademy.edu.springframework.greeter.Language;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
//@Qualifier
public @interface GreeterQualifier {
    Language language();
    boolean dummy();
}