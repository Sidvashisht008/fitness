package com.fitworld.fitness.aop;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {

	public Logger LOGGER = Logger.getLogger(getClass().getName());
	
	@Pointcut("execution(* com.fitworld.fitness.controller..*(..))")
	public void serviceLogs() {};
	
	@Around("serviceLogs()")
	public Object pointCut(ProceedingJoinPoint jp) throws Throwable {
		String methodName = jp.getSignature().toShortString();
		LOGGER.info("Before run - "+methodName);
		Object result = jp.proceed();
		LOGGER.info("After run");
		return result;
	}
	
	@Before("execution(* com.fitworld.fitness.controller..*(..))")
	public void pointCutBefore(JoinPoint jp) throws Throwable {
		String methodName = jp.getSignature().getName();
		LOGGER.info("Before run - "+methodName);
	}
	
}
