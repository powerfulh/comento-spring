package com.comento.oracleSpringBoot.dto.plm;

import com.comento.oracleSpringBoot.dto.BaseDto;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Suggestion extends BaseDto {
    final List<String> expect;

    public Suggestion(Word word, List<Character> characters) {
        this.n = word.getN();
        this.expect = characters.stream().map(item -> word.getWord().substring(0, word.getWord().length() - 1).concat(item.toString())).collect(Collectors.toList());
    }

    public Integer getN() {
        return n;
    }
}
