package com.comento.oracleSpringBoot;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Mapper;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.comento.oracleSpringBoot.mapper.Param;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class Aop {
	static Logger logger = LoggerFactory.getLogger(Aop.class);
	final LogMapper mapper;
	
	@Pointcut("execution(* com.comento.oracleSpringBoot.webController.*.*(..)) || execution(* com.comento.oracleSpringBoot.rest.*.*(..))")
	public void allPoint() {
		System.out.println("포인트컷은 명시적인 함수로 실제로 실행되는 일은 없다");
	}
	@Before("allPoint()")
	public void startControl() {
		System.out.println();
		logger.info("Controll start");
	}
	String getSid(HttpServletRequest req) {
		return (String) req.getSession().getAttribute("sid");
	}
	@After("allPoint()")
	public void insertLog() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest req = attributes.getRequest();
		String id = StaticUtil.nullElse(getSid(req), "비로그인");
		String m = req.getMethod();
		String uri = req.getRequestURI();
		StringBuffer p = new StringBuffer("{");
		for(int i = 0; i < req.getParameterMap().size(); i++) {
			if(i > 0) p.append(", ");
			String k = (String) req.getParameterMap().keySet().toArray()[i];
			String v = Arrays.toString(req.getParameterMap().get(k));
			p.append("\"").append(k).append("\": \"").append(v).append("\"");
		}
		p.append("}");
		Log log = Log.builder().method(m).url(uri).param(p.toString()).build();
		log.setInsertId(id);
		mapper.insert(log);
	}
}

@Builder
@Getter
class Log extends Param {
	final String method;
	final String url;
	final String param;
}

@Mapper
interface LogMapper {
	int insert(Log p);
}