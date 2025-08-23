package com.comento.oracleSpringBoot.rest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comento.oracleSpringBoot.dto.plm.Word;
import com.comento.oracleSpringBoot.mapper.PlmMapper;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "https://powerfulh.github.io"}, allowCredentials = "true")
@RequestMapping("plm")
@RequiredArgsConstructor
public class PlmApi {
	final PlmMapper mapper;
	
	@GetMapping("word")
	public List<Word> getWord(String s) {
		return mapper.selectWord(s);
	}
}
