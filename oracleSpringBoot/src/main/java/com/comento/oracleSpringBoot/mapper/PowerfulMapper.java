package com.comento.oracleSpringBoot.mapper;

import com.comento.oracleSpringBoot.powerfulh.PowerfulApi;
import com.comento.oracleSpringBoot.powerfulh.plm.SpaceCase;
import com.comento.oracleSpringBoot.powerfulh.plm.UltronAnswerCase;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PowerfulMapper {
	List<Map<String, String>> select(int owner);
	int insert(String name, int owner);
	int update(PowerfulApi a);
    SpaceCase sumSpaceCase(int n, String type);
    List<UltronAnswerCase> sumUltronAnswerSpaceCase();
	List<Integer> selectSuffix();
}
