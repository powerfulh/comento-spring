package com.comento.oracleSpringBoot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.comento.oracleSpringBoot.member.MemberS;
import com.comento.oracleSpringBoot.member.entity.LoginVo;

@SpringBootTest
class OracleSpringBootApplicationTests {
	@Autowired
	MemberS service;
	@Test
	void test() {
		LoginVo lvo = new LoginVo();
		lvo.setId("hh");
		lvo.setPw("123");
		System.out.println("select count from member : " + service.logicProc(lvo));
	}
}
