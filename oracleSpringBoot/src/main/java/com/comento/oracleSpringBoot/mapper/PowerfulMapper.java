package com.comento.oracleSpringBoot.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.comento.oracleSpringBoot.powerfulh.PowerfulApi;

@Mapper
public interface PowerfulMapper {
	List<Map<String, String>> select(int owner);
	int insert(String name, int owner);
	int update(PowerfulApi a);
}
