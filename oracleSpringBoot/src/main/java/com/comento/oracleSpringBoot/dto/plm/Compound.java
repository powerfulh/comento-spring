package com.comento.oracleSpringBoot.dto.plm;

import com.comento.oracleSpringBoot.dto.BaseDto;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class Compound extends BaseDto {
    @NotNull
    Integer word;
    @NotNull
    Integer leftword;
    @NotNull
    Integer rightword;
    String cw;
    String lw;
    String rw;

    public Integer getN() {
        return n;
    }
}
