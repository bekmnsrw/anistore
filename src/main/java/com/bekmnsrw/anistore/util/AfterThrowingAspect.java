package com.bekmnsrw.anistore.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AfterThrowingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(com.bekmnsrw.anistore.controller..*)")
    public void controllerPointcut() {}

    @Pointcut("within(com.bekmnsrw.anistore.service..*)")
    public void servicePointcut() {}

    @Pointcut("within(com.bekmnsrw.anistore.repository..*)")
    public void repositoryPointcut() {}

    @AfterThrowing(pointcut = "controllerPointcut() || servicePointcut() || repositoryPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        logger.error(
                "Exception in {}.{} with cause = {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                e.getCause() != null ? e.getCause() : "NULL"
        );
    }
}
