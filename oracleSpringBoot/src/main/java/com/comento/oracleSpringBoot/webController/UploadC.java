package com.comento.oracleSpringBoot.webController;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("upload")
public class UploadC extends WebC {
	@GetMapping("")
	public String index() {
		
		return "upload";
	}
	@PostMapping("proc")
	public String upload(Model m, MultipartFile[] fileList) {
		m.addAttribute("msg", "Upload fail");
		String path = this.getClass().getResource("").getPath();
		path = path.substring(0, path.indexOf("/target")) + "/upload/";
		File file = new File(path);
		if(!file.exists()) {
			logger.info("I make upload direction: " + file.mkdir());
		}
		file = new File(path + fileList[0].getOriginalFilename());
		try {
			fileList[0].transferTo(file);
			m.addAttribute("msg", "Upload Success");
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "index";
	}
}
