package com.comento.oracleSpringBoot.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comento.oracleSpringBoot.mapper.CurtainMapper;

@RestController
@RequestMapping("api/curtain")
@CrossOrigin(origins = {"http://localhost:5173", "https://powerfulh.github.io"})
public class CurtainApi {
	@Autowired
	CurtainMapper mapper;
	
	@GetMapping
	public List<Map<String, Object>> blind100() {
		return mapper.select100();
	}
}
