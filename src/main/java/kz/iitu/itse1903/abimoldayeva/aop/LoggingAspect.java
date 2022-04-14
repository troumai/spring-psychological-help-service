package kz.iitu.itse1903.abimoldayeva.aop;

import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@Aspect
@Log
public class LoggingAspect {

    @Pointcut("execution(* kz.iitu.itse1903.abimoldayeva.service.ClientService.*(..))")
    public void callAtAllClientMethods(){}

    @Before("callAtAllClientMethods()")
    public void logBeforeAllClientMethods(JoinPoint jp){
        String args = Arrays.stream(jp.getArgs())
                .map(a -> a.toString())
                .collect(Collectors.joining(","));
        log.info("before " + jp + ", args=[" + args + "]");
    }

    @After("callAtAllClientMethods()")
    public void logAfterAllClientMethods(JoinPoint jp){
        String methodName = jp.getSignature()
                .getName();
        log.info("after - method name: " + methodName);
    }

    @Pointcut("execution(* kz.iitu.itse1903.abimoldayeva.service.TherapistService.getTherapistById(..))")
    public void callAtGetTherapistById(){}

    @Around("callAtGetTherapistById()")
    public Object logAroundGetTherapistById(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("logAroundGetTherapistById - " + joinPoint.getSignature().getName() + ": Before Method Execution");

        Object proceed = joinPoint.proceed();

        log.info("logAroundGetTherapistById" + joinPoint.getSignature().getName() + ": After Method Execution");

        return proceed;
    }

    @AfterReturning(pointcut = "execution(* kz.iitu.itse1903.abimoldayeva.service.TherapistService.getTherapistsByExperience(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {

        log.info( "returned value: " + result.getClass().getTypeName());
    }

}
