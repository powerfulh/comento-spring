package com.comento.oracleSpringBoot.webController;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comento.oracleSpringBoot.dto.common.Gnb;
import com.comento.oracleSpringBoot.mapper.GnbMapper;
import com.comento.oracleSpringBoot.member.MemberS;

@Controller
@RequestMapping("gnb")
public class GnbC extends WebC {
	@Autowired
	GnbMapper gnbMapper;

	public GnbC(MemberS ms) {
		super(ms);
	}

	@PostMapping
	@ResponseBody
	public int post(@RequestBody @Valid Gnb dto) {
		return gnbMapper.insert(dto);
	}
	
	@GetMapping
	@ResponseBody
	public List<Gnb> get() {
		return gnbMapper.select();
	}
}
