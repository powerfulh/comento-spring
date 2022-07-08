package com.comento.oracleSpringBoot.webController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("ajax-download")
public class AjaxDownloadC {
	@GetMapping
	public String index() {
		
		return "ajax-download";
	}
	
	@GetMapping("download")
	@ResponseBody
	public boolean download() {
		File file = new File("download-content/c0.hwp");
		return file.exists();
	}
	
	@GetMapping("proc")
	public void proc(HttpServletResponse res) throws IOException {
		File file = new File("download-content/c0.hwp");
		byte[] fileByte = Files.readAllBytes(file.toPath());
		res.setContentType("application/hwp");
		res.setHeader("Content-Disposition", "attachment;filename=c0.hwp");
		res.getOutputStream().write(fileByte);
	}
}
