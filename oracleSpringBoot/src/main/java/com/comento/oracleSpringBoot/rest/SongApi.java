package com.comento.oracleSpringBoot.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.comento.oracleSpringBoot.dto.song.Song;
import com.comento.oracleSpringBoot.mapper.SongMapper;

import springfox.documentation.annotations.ApiIgnore;

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
    @PutMapping("{n}")
    public int put(@RequestBody Song dto, @PathVariable int n, @ApiIgnore HttpSession s) {
        if(s.getAttribute("sid") == null) throw new Authentication();
        return mapper.update(dto, n);
    }
    @PostMapping
    public int post(@RequestBody Song dto, @ApiIgnore HttpSession s) {
        if(s.getAttribute("sid") == null) throw new Authentication();
        return mapper.insert(dto);
    }
}
