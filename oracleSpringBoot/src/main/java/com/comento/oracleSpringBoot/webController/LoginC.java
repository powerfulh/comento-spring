package com.comento.oracleSpringBoot.webController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.comento.oracleSpringBoot.member.MemberS;
import com.comento.oracleSpringBoot.member.entity.LoginVo;

@Controller
@RequestMapping("login")
public class LoginC extends WebC{
	@Autowired
	MemberS service;
	@PostMapping("")
	public RedirectView proc(@Valid LoginVo lvo, HttpSession s) {
		logger.info("Someone try to Login!");
		if(service.logicProc(lvo) == 1) s.setAttribute("sid", lvo.getId());
		return new RedirectView("");
	}
	@GetMapping("out")
	public RedirectView out(HttpSession s) {
		logger.info(s.getAttribute("sid") + " has logout");
		s.removeAttribute("sid");
		return new RedirectView("/");
	}
}
