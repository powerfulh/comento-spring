package com.comento.oracleSpringBoot.dto.curtain;

import java.util.List;
import java.util.Map;

import com.comento.oracleSpringBoot.dto.BaseDto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Chart extends BaseDto {
	Map<String, Object> total;
	List<Map<String, Object>> comp;
	List<Map<String, Object>> topic;
}
