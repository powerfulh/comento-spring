package com.comento.oracleSpringBoot.webController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.comento.oracleSpringBoot.member.MemberS;

@Controller
@RequestMapping("send-string")
public class PrintStringC extends WebC {
	public PrintStringC(MemberS ms) {
		super(ms);
	}

	@GetMapping("")
	public String index(String msg, Model m) {
		if(msg != null) {
			System.out.println("Server get msg: " + msg);
			m.addAttribute("msg", "성공적으로 서버에 전송되었읍니다");
		}
		return "send-string";
	}
}
