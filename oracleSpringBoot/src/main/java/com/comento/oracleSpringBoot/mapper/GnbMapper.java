package com.comento.oracleSpringBoot.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.comento.oracleSpringBoot.dto.common.Gnb;

@Mapper
public interface GnbMapper {
	int insert(Gnb param);
}
