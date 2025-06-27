package com.comento.oracleSpringBoot.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comento.oracleSpringBoot.dto.curtain.Chart;
import com.comento.oracleSpringBoot.mapper.CurtainMapper;

@RestController
@RequestMapping("api/curtain")
@CrossOrigin(origins = {"http://localhost:5173", "https://powerfulh.github.io"})
public class CurtainApi {
	@Autowired
	CurtainMapper mapper;
	
	@GetMapping
	public List<Map<String, Object>> blind100() {
		return mapper.select100();
	}
	@GetMapping("{company}")
	public List<Map<String, Object>> blind100company(@PathVariable String company) {
		return mapper.select100comp(company);
	}
	@GetMapping("chart")
	public Chart getChart() {
		return Chart.builder().total(mapper.selectCnt().get(0)).comp(mapper.selectCompCnt()).topic(mapper.selectTopicCnt()).build();
	}
	@GetMapping("rand")
	public List<Map<String, Object>> blind100rand() {
		return mapper.select100rand();
	}
}
