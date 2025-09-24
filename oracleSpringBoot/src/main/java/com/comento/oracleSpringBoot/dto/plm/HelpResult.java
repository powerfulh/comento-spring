package com.comento.oracleSpringBoot.dto.plm;

import com.comento.oracleSpringBoot.dto.BaseDto;
import lombok.Getter;

@Getter
public class HelpResult extends BaseDto {
    final String word;

    public HelpResult(int n, String word) {
        this.n = n;
        this.word = word;
    }

    public Integer getN() {
        return n;
    }
}
