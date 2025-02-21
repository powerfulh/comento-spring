package com.comento.oracleSpringBoot.webController;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.comento.oracleSpringBoot.member.MemberS;

@Controller
@RequestMapping("index")
public class HtmlC extends WebC {

	public HtmlC(MemberS ms) {
		super(ms);
	}

	String loginCheck(HttpSession s, Model m, String page) {
		if(s.getAttribute("sid") == null) {
			m.addAttribute("msg", "Need login to access " + page);
			return "index";
		}
		return page;
	}
	
	@GetMapping("managegnb")
	public String managegnb(HttpSession s, Model m) {
		return loginCheck(s, m, "managegnb");
	}
}
