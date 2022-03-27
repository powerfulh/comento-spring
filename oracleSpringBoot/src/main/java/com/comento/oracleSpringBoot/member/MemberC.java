package com.comento.oracleSpringBoot.member;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comento.oracleSpringBoot.mail.entity.MailVo;
import com.comento.oracleSpringBoot.member.entity.LoginVo;
import com.comento.oracleSpringBoot.powerfulh.PowerfulhS;
import com.comento.oracleSpringBoot.powerfulh.TestVO;

@CrossOrigin
@Controller
@RequestMapping("m")
public class MemberC {
	@Autowired
	MemberS service;
	@Autowired
	PowerfulhS ps;
	int cnt = 0;
	@PostMapping("loginProc")
	public String logicProc(@Valid LoginVo lvo, Model model) {
		if(service.logicProc(lvo) == 1) model.addAttribute("sid", lvo.getId());
		return "index";
	}
	@GetMapping("getMainMail")
	@ResponseBody
	public List<MailVo> getMainMail(String sid) {
		return service.getMainMail(sid);
	}
	@GetMapping("async-test")
	@ResponseBody
	public TestVO asyncTest() throws InterruptedException, ExecutionException {
		Future<Integer> a = ps.asyncInt(cnt++);
		Future<Integer> b = ps.asyncInt(cnt++);
		return TestVO.builder().a(a.get()).b(b.get()).build();
	}
}
