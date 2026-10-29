package com.comento.oracleSpringBoot.rest.curtain;

import com.comento.oracleSpringBoot.dto.curtain.Board;
import com.comento.oracleSpringBoot.dto.curtain.Chart;
import com.comento.oracleSpringBoot.mapper.CurtainMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/curtain")
@CrossOrigin(origins = {"http://localhost:5173", "https://powerfulh.github.io"})
public class CurtainApi {
	@Autowired
	CurtainMapper mapper;
	
	@GetMapping
	public List<Board> blind100() {
		return mapper.select100();
	}
	@GetMapping("{company}")
	public List<Board> blind100company(@PathVariable String company) {
		return mapper.select100comp(company);
	}
	@GetMapping("chart")
	public Chart getChart() {
		return Chart.builder().total(mapper.selectCnt().get(0)).comp(mapper.selectCompCnt()).topic(mapper.selectTopicCnt()).build();
	}
	@GetMapping("rand")
	public List<Board> blind100rand() {
		return mapper.select100rand();
	}
	@PostMapping("build/refresh")
	@ApiOperation(value = "최신 만 건 적재 후 적재된 최신 글 번호 응답")
	public int postRefresh() {
		return mapper.refreshCurtain();
	}
	@GetMapping("build/{key}")
	public List<Board> getCurtain(@PathVariable String key, int offset) {
		return mapper.selectChunk(key, offset);
	}
}
