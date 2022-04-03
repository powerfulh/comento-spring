package com.comento.oracleSpringBoot;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class Aop {
	static Logger logger = LoggerFactory.getLogger(Aop.class);
	@Pointcut("execution(* com.comento.oracleSpringBoot.webController.*.*(..))")
	public void allPoint() {
		System.out.println("포인트컷은 명시적인 함수로 실제로 실행되는 일은 없다");
	}
	@Before("allPoint()")
	public void startControl() {
		System.out.println();
		logger.info("Controll start");
	}
}
