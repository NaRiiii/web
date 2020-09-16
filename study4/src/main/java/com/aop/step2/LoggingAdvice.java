package com.aop.step2;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingAdvice {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	public void beforeAdvice(JoinPoint joinPoint) {
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		int cnt =1;
		StringBuffer sb = new StringBuffer();
		sb.append("\n----------------------------------------");
		sb.append("\n  " + className + "." + methodName);
		for(Object obj : args) {
			sb.append("\n args[ " + cnt++ + "] : " + ToStringBuilder.reflectionToString(obj,ToStringStyle.MULTI_LINE_STYLE));
		}
		sb.append("\n----------------------------------------");
		logger.debug(sb.toString());
	}
	
}
