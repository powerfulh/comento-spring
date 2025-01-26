package com.comento.oracleSpringBoot.dto.common;

import javax.validation.constraints.NotEmpty;

import com.comento.oracleSpringBoot.dto.BaseDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Gnb extends BaseDto {
	@NotEmpty
	final String name;
	@NotEmpty
	final String href;
}
