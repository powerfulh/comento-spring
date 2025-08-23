package com.comento.oracleSpringBoot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.comento.oracleSpringBoot.dto.plm.Word;

@Mapper
public interface PlmMapper {
	List<Word> selectWord(String s);
}
