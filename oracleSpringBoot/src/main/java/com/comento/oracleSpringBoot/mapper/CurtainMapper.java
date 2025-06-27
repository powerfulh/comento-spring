package com.comento.oracleSpringBoot.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CurtainMapper {
	List<Map<String, Object>> select100();
	List<Map<String, Object>> select100comp(String company);
	List<Map<String, Object>> selectCnt();
	List<Map<String, Object>> selectCompCnt();
	List<Map<String, Object>> selectTopicCnt();
	List<Map<String, Object>> select100rand();
}
