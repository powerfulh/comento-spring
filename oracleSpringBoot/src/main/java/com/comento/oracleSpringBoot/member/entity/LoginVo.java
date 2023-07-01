package com.comento.oracleSpringBoot.member.entity;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class LoginVo {
	@NotBlank(message = "id require")
	@ApiModelProperty(required = true)
	String id;
	@NotBlank(message = "pw require")
	@ApiModelProperty(required = true)
	String pw;
}
