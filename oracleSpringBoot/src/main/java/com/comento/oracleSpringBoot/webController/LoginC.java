package com.comento.oracleSpringBoot.webController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.comento.oracleSpringBoot.member.MemberS;
import com.comento.oracleSpringBoot.member.entity.LoginVo;

@Controller
@RequestMapping("login")
public class LoginC extends WebC {
	public LoginC(MemberS ms) {
		super(ms);
	}
	@PostMapping("")
	public String proc(@Valid LoginVo lvo, HttpSession s, Model m) {
		logger.info("Someone try to Login!");
		if(ms.logicProc(lvo) == 1) s.setAttribute("sid", lvo.getId());
		else m.addAttribute("msg", "Login fail");
		return "index";
	}
	@GetMapping("out")
	public RedirectView out(HttpSession s) {
		logger.info(s.getAttribute("sid") + " has logout");
		s.removeAttribute("sid");
		return new RedirectView("/");
	}
	@GetMapping("info")
	public String myInfo() {
		
		return "member-info";
	}
}
