package com.comento.oracleSpringBoot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.comento.oracleSpringBoot.dto.common.Gnb;

@Mapper
public interface GnbMapper {
	int insert(Gnb param);
	List<Gnb> select();
}
