package com.comento.oracleSpringBoot.mail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comento.oracleSpringBoot.mail.entity.MailVo;

@Controller
@CrossOrigin
public class MailC {
	@Autowired
	MailS service;
	@GetMapping("getMailInbox")
	@ResponseBody
	public List<MailVo> getMailInbox(String sid) {
		return service.getMailInbox(sid);
	}
	@GetMapping("getMailOutBox")
	@ResponseBody
	public List<MailVo> getMailOutBox(String sid) {
		return service.getMailOutBox(sid);
	}
}
