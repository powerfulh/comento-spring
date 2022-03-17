package com.comento.oracleSpringBoot.webController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FragmentC {
	@GetMapping("test")
	public String test(Model m) {
		m.addAttribute("test", "call");
		return "fragment/frag :: fragnment";
	}
}
