package com.comento.oracleSpringBoot.powerfulh;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.ibatis.binding.BindingException;
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
@CrossOrigin(origins = {"http://localhost:5173", "https://powerfulh.github.io"}, allowCredentials = "true")
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
	@PostMapping("authenticate")
	public boolean authenticate(@Valid @RequestBody LoginVo lvo, @ApiIgnore HttpSession s) {
		try {			
			final int n = service.selectPk(lvo);
			s.setAttribute("sn", n);
		} catch(BindingException e) {
			return false;
		}
		return true;
	}
	int requester(HttpSession s) {
		try {
			return (int) s.getAttribute("sn");			
		} catch(NullPointerException e) {
			throw new NoSessionNumber();
		}
	}
	@GetMapping("api")
	public List<Map<String, String>> getApi(@ApiIgnore HttpSession s) {
		return mapper.select(requester(s));
	}
	@PostMapping("api/{name}")
	public int postApi(@PathVariable String name, @ApiIgnore HttpSession s) {
		return mapper.insert(name, requester(s));
	}
	@PutMapping("api")
	public int putApi(@RequestBody @Valid PowerfulApi a, @ApiIgnore HttpSession s) {
		a.setOwner(requester(s));
		return mapper.update(a);
	}
}
