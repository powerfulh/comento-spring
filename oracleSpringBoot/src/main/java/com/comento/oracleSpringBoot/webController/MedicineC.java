package com.comento.oracleSpringBoot.webController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.comento.oracleSpringBoot.member.MemberS;
import com.comento.oracleSpringBoot.powerfulh.PowerfulMapper;

@Controller
@RequestMapping("medicine")
public class MedicineC extends WebC {
	@Autowired
	PowerfulMapper pMapper;

	public MedicineC(MemberS ms) {
		super(ms);
	}
	@GetMapping("")
	public String index() {
		// 선릉 프리티 목록
		return "medicine";
	}
	@PostMapping("new")
	public String addNew() {
		// 이름은 블라인드지만 실제로는 쑥떡의 댄스 목록
		return "medicine";
	}
}
