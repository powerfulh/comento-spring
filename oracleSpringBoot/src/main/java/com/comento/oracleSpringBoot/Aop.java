package com.comento.oracleSpringBoot;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Mapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.comento.oracleSpringBoot.mapper.Param;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class Aop {
	static Logger logger = LoggerFactory.getLogger(Aop.class);
	final LogMapper mapper;
	final ObjectMapper objectMapper;
	
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
	String getParamJson(JoinPoint jp) {
		String[] names = ((MethodSignature) jp.getSignature()).getParameterNames();
		Object[] values = jp.getArgs();
		Map<String, Object> map = new LinkedHashMap<>();
		for(int i = 0; i < values.length; i++) {
			Object v = values[i];
			if(v instanceof HttpSession) continue;
			if(v instanceof HttpServletRequest) continue;
			if(v instanceof HttpServletResponse) continue;
			if(v instanceof Model) continue;
			map.put(names[i], v);
		}
		try {
			return objectMapper.writeValueAsString(map);
		} catch(JsonProcessingException e) {
			return "{}";
		}
	}
	@After("allPoint()")
	public void insertLog(JoinPoint jp) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if(attributes == null) return;
		HttpServletRequest req = attributes.getRequest();
		String id = StaticUtil.nullElse(getRequesterId(req), "비로그인");
		Log log = Log.builder().method(req.getMethod()).url(req.getRequestURI()).param(getParamJson(jp)).build();
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