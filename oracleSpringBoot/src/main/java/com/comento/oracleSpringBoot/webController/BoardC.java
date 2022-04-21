package com.comento.oracleSpringBoot.webController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.comento.oracleSpringBoot.member.MemberS;

@Controller
@RequestMapping("board")
public class BoardC extends WebC {
	public BoardC(MemberS ms) {
		super(ms);
	}

	@GetMapping("")
	public String index() {
		
		return "board/list";
	}
}
