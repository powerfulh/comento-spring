package com.comento.oracleSpringBoot.webController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("table-maker")
public class TableMakerC {

	@GetMapping("")
	public String index() {
		return "table-maker";
	}
}
