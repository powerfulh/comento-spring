package com.comento.oracleSpringBoot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comento.oracleSpringBoot.dto.song.Song;
import com.comento.oracleSpringBoot.mapper.SongMapper;

@RestController
@RequestMapping("api/song")
public class SongApi {
	@Autowired
	SongMapper mapper;
	
	@GetMapping
	public List<Song> get() {
		return mapper.select();
	}
}
