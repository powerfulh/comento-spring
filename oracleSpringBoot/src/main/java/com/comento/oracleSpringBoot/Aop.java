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
	
	@Pointcut("@within(org.springframework.stereotype.Controller) || @within(org.springframework.web.bind.annotation.RestController)")
	public void allPoint() {

	}
	@Before("allPoint()")
	public void startControl() {
		System.out.println();
		logger.info("Controll start");
	}
	String getRequesterId(HttpServletRequest req) {
		String sid = (String) req.getSession().getAttribute("sid");
		if(sid != null) return sid;
		Integer sn = (Integer) req.getSession().getAttribute("sn");
		return sn == null ? null : String.valueOf(sn);
	}
	@After("allPoint()")
	public void insertLog() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if(attributes == null) return;
		HttpServletRequest req = attributes.getRequest();
		String id = StaticUtil.nullElse(getRequesterId(req), "비로그인");
		String m = req.getMethod();
		String uri = req.getRequestURI();
		StringBuilder p = new StringBuilder("{");
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