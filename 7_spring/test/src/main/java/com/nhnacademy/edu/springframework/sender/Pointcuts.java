package com.nhnacademy.edu.springframework.sender;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class Pointcuts {

    @Before("execution(* com.nhnacademy.edu.springframework.*(..)) && args(user, ..)")
    public void userToString(User user){
        System.out.println("User 정보 : "+user.toString());
    }

    @Around("execution(* com.nhnacademy.edu.springframework.sender.*.sendMessage(..))")
    public Object timeCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed();

        stopWatch.stop();
        System.out.println(joinPoint.getSignature() + "실행 시간 : " + stopWatch.getTotalTimeMillis()+"ms");

        return result;
    }
}
