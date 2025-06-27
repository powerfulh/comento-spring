package com.comento.oracleSpringBoot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.comento.oracleSpringBoot.dto.song.Song;

@Mapper
public interface SongMapper {
	List<Song> select();
}
