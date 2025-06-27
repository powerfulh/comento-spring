package com.comento.oracleSpringBoot.dto.song;

import com.comento.oracleSpringBoot.dto.BaseDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Song extends BaseDto {
	final String name;
	final String songBy;
	final int n;
}
