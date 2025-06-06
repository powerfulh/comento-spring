package com.comento.oracleSpringBoot.dto.gnb;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.comento.oracleSpringBoot.dto.common.Gnb;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Post {
	@NotNull
	final Integer seq;
	final boolean activate;
	@NotNull
	@Valid
	final Gnb gnb;
}
