package com.comento.oracleSpringBoot.dto.plm;

import com.comento.oracleSpringBoot.dto.BaseDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SentenceString extends BaseDto {
	String sentence;
	int target;
	LocalDateTime updatedDate;
	public Integer getN() {
		return n;
	}
}
