package com.comento.oracleSpringBoot.mapper;

import com.comento.oracleSpringBoot.dto.curtain.Board;
import com.comento.oracleSpringBoot.dto.curtain.Chart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CurtainMapper {
	List<Board> select100();
	List<Board> select100comp(String company);
	List<Chart.Summary> selectCnt();
	List<Chart.CurtainUser> selectCompCnt();
	List<Chart.CurtainPost> selectTopicCnt();
	List<Board> select100rand();
	int refreshCurtain();
	List<Board> selectChunk(String key, int offset);
	Map<String, byte[]> selectImg(int n);
}
