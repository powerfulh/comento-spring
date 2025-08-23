package com.comento.oracleSpringBoot.dto.plm;

import com.comento.oracleSpringBoot.dto.BaseDto;

import lombok.Getter;

@Getter
public class Word extends BaseDto {
	String word;
	String type;
	String memo;
	
	public Integer getN() {
		return n;
	}
}
