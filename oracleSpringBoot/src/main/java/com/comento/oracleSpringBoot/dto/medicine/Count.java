package com.comento.oracleSpringBoot.dto.medicine;

import com.comento.oracleSpringBoot.dto.BaseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Count extends BaseDto {
	int total;
	String name;
}
