package com.nhnacademy.edu.springframework.project.AOP;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StopWatch;

@Aspect
public class TimeCheck {

    private static final Log log = LogFactory.getLog(TimeCheck.class);

    @Around("execution(* com.nhnacademy.edu.springframework.project.service.*.*(..))")
    private Object loggingAllPublicOperation(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            return pjp.proceed();
        } finally {
            stopWatch.stop();
            log.info(pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName() +
                    " " + stopWatch.getTotalTimeMillis() + "ms");
        }
    }
}
