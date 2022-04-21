package com.comento.oracleSpringBoot.webController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.comento.oracleSpringBoot.member.MemberS;

@Controller
@RequestMapping("extend-test")
public class ExtendTestC extends WebC {
	public ExtendTestC(MemberS ms) {
		super(ms);
	}

	@GetMapping
	public String test(Model m) {
		StringBuffer msg = new StringBuffer();
		ms.getLoginList().forEach(item -> msg.append(item.getId() + " "));
		m.addAttribute("msg", msg);
		return "index";
	}
}
