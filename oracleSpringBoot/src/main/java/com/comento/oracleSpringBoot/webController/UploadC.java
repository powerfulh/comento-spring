package com.comento.oracleSpringBoot.webController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("upload")
public class UploadC extends WebC{
	@GetMapping("")
	public String index() {
		
		return "upload";
	}
	@PostMapping("proc")
	public String upload(Model m, MultipartFile[] fileList) {
		m.addAttribute("msg", "Upload fail");
		System.out.println(fileList.length);
		return "index";
	}
}
