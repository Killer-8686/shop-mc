package ru.masta.orders.ordermodule.aop;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Log
@Aspect
public class MethodTimer {


    @Around("execution(* ru.masta.orders.ordermodule.controller..*(..)))")
    public Object controllerMethodTimer(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        log.info("_____________ Executing " + className + "." + methodName + " _____________");


        StopWatch countdown = new StopWatch();
        countdown.start();

        Object result = proceedingJoinPoint.proceed();
        countdown.stop();

        log.info("_____________ Execution time of " + className + "." +
                methodName + " :: " + countdown.getTotalTimeMillis() +" ms  _____________");

        return result;
    }
}
