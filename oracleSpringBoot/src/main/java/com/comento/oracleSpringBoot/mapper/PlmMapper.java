package com.comento.oracleSpringBoot.mapper;

import java.util.List;

import com.comento.oracleSpringBoot.dto.plm.Compound;
import org.apache.ibatis.annotations.Mapper;

import com.comento.oracleSpringBoot.dto.plm.Word;

@Mapper
public interface PlmMapper {
	List<Word> selectWord(String s);
    void insertWord(Word w);
    List<Compound> selectCompounded(int n);
}
