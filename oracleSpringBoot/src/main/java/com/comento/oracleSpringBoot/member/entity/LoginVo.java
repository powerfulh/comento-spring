package com.comento.oracleSpringBoot.member.entity;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class LoginVo {
	String id;
	@NotBlank(message = "pw require")
	String pw;
}
