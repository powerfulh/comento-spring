package com.comento.oracleSpringBoot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.RequiredArgsConstructor;
@ConstructorBinding
@ConfigurationProperties("some-prop")
@RequiredArgsConstructor
public class PropVo {
	public final String data;
}
