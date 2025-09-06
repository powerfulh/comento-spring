package com.comento.oracleSpringBoot.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.comento.oracleSpringBoot.dto.song.Song;

@Mapper
public interface SongMapper {
	List<Song> select();
    void insertPlay(Map<String, Object> param);
}
