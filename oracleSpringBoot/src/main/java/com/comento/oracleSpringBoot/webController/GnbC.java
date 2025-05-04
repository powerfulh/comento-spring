package com.comento.oracleSpringBoot.webController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping
	public List<Gnb> get() {
		return gnbMapper.selectActivated();
	}
	
	@GetMapping("all")
	public List<Map<String, Object>> getAll() {
		return gnbMapper.select();
	}
}
