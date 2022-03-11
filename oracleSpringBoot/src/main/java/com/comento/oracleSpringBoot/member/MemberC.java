package com.comento.oracleSpringBoot.member;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comento.oracleSpringBoot.mail.entity.MailVo;
import com.comento.oracleSpringBoot.member.entity.LoginVo;

@Controller
@CrossOrigin
public class MemberC {
	@Autowired
	MemberS service;
	@GetMapping("loginProc")
	@ResponseBody
	public int logicProc(LoginVo lvo) {
		
		return service.logicProc(lvo);
	}
	@GetMapping("getMainMail")
	@ResponseBody
	public List<MailVo> getMainMail(String sid) {
		return service.getMainMail(sid);
	}
}
