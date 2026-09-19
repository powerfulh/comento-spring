package com.comento.oracleSpringBoot.dto.plm;

import com.comento.oracleSpringBoot.dto.BaseDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SentenceString extends BaseDto {
	String sentence;
	Integer target;
	LocalDateTime updatedDate;
	public Integer getN() {
		return n;
	}
}
