package com.comento.oracleSpringBoot.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.comento.oracleSpringBoot.dto.common.Gnb;
import com.comento.oracleSpringBoot.dto.gnb.Post;

@Mapper
public interface GnbMapper {
	int insert(Post param);
	List<Gnb> selectActivated();
	List<Map<String, Object>> select();
	int update(Post param, int n);
}
