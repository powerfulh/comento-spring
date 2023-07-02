package com.comento.oracleSpringBoot.powerfulh;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comento.oracleSpringBoot.member.MemberS;
import com.comento.oracleSpringBoot.member.entity.LoginVo;
import com.comento.oracleSpringBoot.member.entity.MemberVo;

import lombok.RequiredArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin
@RequestMapping("powerful")
@RequiredArgsConstructor
public class PowerfulhC {
	final MemberS service;
	
	@GetMapping("testDownload")
	public ResponseEntity<Object> logicProc() {
		String path = this.getClass().getResource("").getPath();
		path = path.substring(1, path.indexOf("/target")) + "/downloadContents/test2.jpg";
		System.out.println(path);
		Path filePath = Paths.get(path);
		try {
			Resource resource = new InputStreamResource(Files.newInputStream(filePath));
			File file = new File(path);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDisposition(ContentDisposition.builder("attachment").filename(file.getName()).build());
			return new ResponseEntity<Object>(resource, headers, HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("login")
	public int login(@Valid @RequestBody LoginVo lvo, @ApiIgnore HttpSession s) {
		final int cnt = service.logicProc(lvo);
		if(cnt == 1) s.setAttribute("sid", lvo.getId());
		return cnt;
	}
	@GetMapping("member/{id}")
	public MemberVo member(@PathVariable String id) {
		return service.get(id);
	}
}
