package com.comento.oracleSpringBoot.dto.plm;

import com.comento.oracleSpringBoot.dto.BaseDto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class Word extends BaseDto {
    @NotBlank
	String word;
    @NotBlank
	String type;
	String memo;
	
	public Integer getN() {
		return n;
	}
}
