package com.comento.oracleSpringBoot.member;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comento.oracleSpringBoot.mail.entity.MailVo;
import com.comento.oracleSpringBoot.member.entity.LoginVo;

@CrossOrigin
@Controller
public class MemberC {
	@Autowired
	MemberS service;
	@GetMapping("loginProc")
	public String logicProc(@Valid LoginVo lvo, Model model) {
		if(service.logicProc(lvo) == 1) model.addAttribute("sid", lvo.getId());
		return "index";
	}
	@GetMapping("getMainMail")
	@ResponseBody
	public List<MailVo> getMainMail(String sid) {
		return service.getMainMail(sid);
	}
}
