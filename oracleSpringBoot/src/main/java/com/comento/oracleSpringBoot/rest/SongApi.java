package com.comento.oracleSpringBoot.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("play")
    public int postPlay(@RequestBody Map<String, Object> dto) {
        List<Integer> list = (List<Integer>) dto.get("list");
        list.forEach(item -> {
            Map<String, Object> p = new HashMap<>();
            p.put("song", item);
            p.put("stage", dto.get("stage"));
            p.put("playDate", ((String) dto.get("playDate")).replaceAll("-", "").substring(2));
            mapper.insertPlay(p);
        });
        return list.size();
    }
}
