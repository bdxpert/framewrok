package com.framework.artisan.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;

@Aspect
@Configuration
public class TraceAdvice {
    @After("execution(* customers.EmailSender.sendEmail(..))  && args(email, message)")
    public void tracemethodA(JoinPoint joinpoint, String email, String message) {
        IEmailSender emailSender = (IEmailSender) joinpoint.getTarget();
        emailSender.getOutgoingMailServer();
        Object[] args = joinpoint.getArgs();
        String name = (String)args[0];
        System.out.println(LocalDateTime.now() + " method ="+joinpoint.getSignature().getName() +
                " address="+email +" message="+message +" outgoing mail server="+emailSender.getOutgoingMailServer());
    }
    @Around("execution(* *.*DAO.*(..))")
    public Object profile (ProceedingJoinPoint call) throws Throwable{
        StopWatch clock = new StopWatch("");
        clock.start(call.toShortString());
        Object object= call.proceed();
        clock.stop();
        System.out.println(clock.prettyPrint());
        return object;
    }
}
