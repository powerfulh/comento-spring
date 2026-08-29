package com.comento.oracleSpringBoot.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.comento.oracleSpringBoot.dto.song.Song;

@Mapper
public interface SongMapper {
	List<Song> select();
    void insertPlay(Map<String, Object> param);
    int update(Song dto, int n);
    int insert(Song dto);
    List<Map<String, Object>> selectPlay(String from, String to, Integer stage);
    List<Map<String, Object>> selectPlayCount(String from, String to, Integer stage);
    List<Map<String, Object>> selectStage();
    int insertStage(String name);
    int updateStage(int n, String name);
}
