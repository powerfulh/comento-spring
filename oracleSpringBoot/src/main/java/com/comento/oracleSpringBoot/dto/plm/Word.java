package com.comento.oracleSpringBoot.dto.plm;

import com.comento.oracleSpringBoot.dto.BaseDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    public String getWordMemo() {
        return memo == null ? null : (memo.isEmpty() ? null : memo);
    }
}
