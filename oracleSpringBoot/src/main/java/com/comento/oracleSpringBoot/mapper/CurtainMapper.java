package com.comento.oracleSpringBoot.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CurtainMapper {
	List<Map<String, Object>> select100();
}
