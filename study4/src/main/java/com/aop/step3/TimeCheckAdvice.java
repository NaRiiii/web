package com.aop.step3;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeCheckAdvice {

	// private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Around("execution(public * com.aop.step3..*.get*(..))")
	public Object aroundCheck(ProceedingJoinPoint joinPoint) throws Throwable {
		Class clazz = joinPoint.getClass().getClass();
		Logger logger = LoggerFactory.getLogger(clazz);
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		long startTime = System.currentTimeMillis();
		try {
			Object obj =  joinPoint.proceed();//원본(대상객체)를 호출
			return obj;
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			throw e;
		}finally {			
			long duration = System.currentTimeMillis() - startTime;
			logger.info("-----------------------------------------------------");
			logger.info("{}.{} : 수행시간 = {}", className, methodName, duration);
			logger.info("-----------------------------------------------------");
		}
	}
	
}
