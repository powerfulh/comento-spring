package com.comento.oracleSpringBoot.powerfulh;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public class PowerfulApi {
	@NotBlank
	final String name;
	@NotBlank
	final String data;
	@Setter
	int owner;
}
