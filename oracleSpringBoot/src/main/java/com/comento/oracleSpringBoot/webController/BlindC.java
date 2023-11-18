package com.comento.oracleSpringBoot.webController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comento.oracleSpringBoot.member.MemberS;
import com.comento.oracleSpringBoot.powerfulh.PowerfulMapper;

@Controller
@RequestMapping("blind")
public class BlindC extends WebC {
	@Autowired
	PowerfulMapper pMapper;
	
	public BlindC(MemberS ms) {
		super(ms);
	}
	@GetMapping("")
	public String index() {
		// 이름은 블라인드지만 실제로는 쑥떡의 댄스 목록
		return "blind";
	}
	@GetMapping("get-list")
	@ResponseBody
	public List<Map<String, Object>> getDanceList() {
		return pMapper.getXooDanceList();
	}
}
