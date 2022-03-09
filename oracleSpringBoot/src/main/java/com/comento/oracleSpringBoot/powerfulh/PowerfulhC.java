package com.comento.oracleSpringBoot.powerfulh;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class PowerfulhC {
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
}
