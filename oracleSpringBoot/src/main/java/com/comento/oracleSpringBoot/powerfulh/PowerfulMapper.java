package com.comento.oracleSpringBoot.powerfulh;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PowerfulMapper {
	List<Map<String, String>> select();
	int insert(String name);
	int update(PowerfulApi a);
}
