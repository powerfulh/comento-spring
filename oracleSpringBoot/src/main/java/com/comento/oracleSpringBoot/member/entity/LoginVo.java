package com.comento.oracleSpringBoot.member.entity;

import javax.validation.constraints.NotBlank;

public class LoginVo {
	@NotBlank(message = "id require")
	private String id;
	String pw;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
}
