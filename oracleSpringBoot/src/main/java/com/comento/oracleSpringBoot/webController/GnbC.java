package com.comento.oracleSpringBoot.webController;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comento.oracleSpringBoot.dto.common.Gnb;
import com.comento.oracleSpringBoot.mapper.GnbMapper;
import com.comento.oracleSpringBoot.member.MemberS;

@RestController
@RequestMapping("gnb")
public class GnbC extends WebC {
	@Autowired
	GnbMapper gnbMapper;

	public GnbC(MemberS ms) {
		super(ms);
	}

	@PostMapping
	public int post(@RequestBody @Valid Gnb dto) {
		return gnbMapper.insert(dto);
	}
	
	@GetMapping
	public List<Gnb> get() {
		return gnbMapper.selectActivated();
	}
	
	@GetMapping("all")
	public List<Map<String, Object>> getAll() {
		return gnbMapper.select();
	}
}
