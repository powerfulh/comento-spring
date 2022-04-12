package com.comento.oracleSpringBoot;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class Config implements WebMvcConfigurer {
	public void addInterceptors(InterceptorRegistry r) {
		System.out.println("I add interceptor");
		r.addInterceptor(new Interceptor()).addPathPatterns("/**").excludePathPatterns("/favicon.ico", "/**/*.js", "/**/*.css");
	}
}
