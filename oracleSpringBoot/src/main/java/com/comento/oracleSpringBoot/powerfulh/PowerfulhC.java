package com.comento.oracleSpringBoot.powerfulh;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	final PowerfulMapper mapper;
		
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
	@GetMapping("api")
	public List<Map<String, String>> getApi() {
		return mapper.select();
	}
	@PostMapping("api/{name}")
	public int postApi(@PathVariable String name) {
		return mapper.insert(name);
	}
	@PutMapping("api")
	public int putApi(@RequestBody @Valid PowerfulApi a) {
		return mapper.update(a);
	}
}
