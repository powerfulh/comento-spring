package com.comento.oracleSpringBoot.dto.plm;

import com.comento.oracleSpringBoot.dto.BaseDto;
import lombok.Getter;

import java.util.List;

@Getter
public class Suggestion extends BaseDto {
    final List<String> expect;

    public Suggestion(Word word, List<String> characters) {
        this.n = word.getN();
        this.expect = characters;
    }

    public Integer getN() {
        return n;
    }
}
