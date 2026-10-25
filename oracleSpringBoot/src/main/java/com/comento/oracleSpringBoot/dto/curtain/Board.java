package com.comento.oracleSpringBoot.dto.curtain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
	@ApiModelProperty(value = "Name who posted", example = "Jackson")
	String nn;
	@ApiModelProperty(value = "Body")
	String b;
	@ApiModelProperty(value = "Title")
	String t;
	@ApiModelProperty(value = "YYYY-MM-DD")
	String d;
	@ApiModelProperty(value = "Post category", example = "자유게시판")
	String cn;
	@ApiModelProperty(value = "User category", example = "Gold")
	String cp;
}