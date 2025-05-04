package com.comento.oracleSpringBoot.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comento.oracleSpringBoot.dto.gnb.Post;
import com.comento.oracleSpringBoot.mapper.GnbMapper;

@RestController
@RequestMapping("api/gnb")
public class GnbApi {
	@Autowired
	GnbMapper gnbMapper;
	
	@PostMapping
	public int post(@RequestBody @Valid Post dto) {
		return gnbMapper.insert(dto);
	}
}
