package com.comento.oracleSpringBoot.member.entity;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class LoginVo {
	String id;
	@NotNull(message = "pw require")
	String pw;
}
