package com.comento.oracleSpringBoot.dto.plm;

import com.comento.oracleSpringBoot.dto.BaseDto;
import lombok.Getter;

@Getter
public class Compound extends BaseDto {
    Integer word;
    Integer leftword;
    Integer rightword;
    String cw;
    String lw;
    String rw;

    public Integer getN() {
        return n;
    }
}
