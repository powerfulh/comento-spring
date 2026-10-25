package com.comento.oracleSpringBoot.dto.curtain;

import java.util.List;

import com.comento.oracleSpringBoot.dto.BaseDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class Chart extends BaseDto {
	Summary total;
	List<CurtainUser> comp;
	List<CurtainPost> topic;

	@Getter
	@Setter
	public static class Summary {
		@ApiModelProperty(value = "Latest YYYY-MM-DD")
		String tmd;
		@ApiModelProperty(value = "Total count")
		int tc;
	}
	@Getter
	@Setter
	public static class CurtainUser {
		@ApiModelProperty(value = "Board.cp")
		String cpn;
		@ApiModelProperty(value = "Latest YYYY-MM-DD")
		String cpd;
		@ApiModelProperty(value = "Count")
		int cpc;
	}
	@Getter
	@Setter
	public static class CurtainPost {
		@ApiModelProperty(value = "Board.cn")
		String cnn;
		@ApiModelProperty(value = "Latest YYYY-MM-DD")
		String cnd;
		@ApiModelProperty(value = "Count")
		int cnc;
	}
}
