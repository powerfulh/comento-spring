package com.comento.oracleSpringBoot.webController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("upload")
public class UploadC extends WebC{
	@GetMapping("")
	public String index() {
		
		return "upload";
	}
}
