package com.comento.oracleSpringBoot.webController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.comento.oracleSpringBoot.PropVo;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("use-prop")
@RequiredArgsConstructor
public class PropC {
	final PropVo prop;
	@GetMapping
	public String useProp(Model m) {
		m.addAttribute("msg", prop.data);
		return "index";
	}
}
