package com.comento.oracleSpringBoot.webController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.comento.oracleSpringBoot.member.MemberS;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class WebC {
	static Logger logger = LoggerFactory.getLogger(LoginC.class);
	final MemberS ms;
}
