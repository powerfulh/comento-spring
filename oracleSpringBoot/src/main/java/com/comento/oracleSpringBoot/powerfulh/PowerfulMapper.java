package com.comento.oracleSpringBoot.powerfulh;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PowerfulMapper {
	public List<Map<String, Object>> getXooDanceList();
}
