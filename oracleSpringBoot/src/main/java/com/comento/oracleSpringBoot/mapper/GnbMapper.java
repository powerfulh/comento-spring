package com.comento.oracleSpringBoot.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.comento.oracleSpringBoot.dto.common.Gnb;

@Mapper
public interface GnbMapper {
	int insert(Gnb param);
	List<Gnb> selectActivated();
	List<Map<String, Object>> select();
}
