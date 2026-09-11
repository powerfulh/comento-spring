package com.comento.oracleSpringBoot.plm;

import com.comento.oracleSpringBoot.mapper.MapperFromUltron;
import com.comento.oracleSpringBoot.mapper.PowerfulMapper;
import com.comento.oracleSpringBoot.plm.entity.Compound;
import com.comento.oracleSpringBoot.plm.entity.Context;
import com.comento.oracleSpringBoot.plm.entity.Word;
import com.comento.oracleSpringBoot.plm.entmap.CompoundMap;
import com.comento.oracleSpringBoot.plm.entmap.ContextMap;
import com.comento.oracleSpringBoot.plm.entmap.WordMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Bank {
    final MapperFromUltron mapper;
    public List<Word> wordList;
    public char[] symbols;
    public List<Context> contextList;
    public List<Compound> compoundList;
    // fix space api 를 위해 여기서 추가된 정보
    final PowerfulMapper powerfulMapper;
    public Map<Integer, Boolean> spaceMap;

    public Bank(MapperFromUltron mapper, PowerfulMapper powerfulMapper) {
        this.mapper = mapper;
        this.powerfulMapper = powerfulMapper;
        update();
    }

    void update() {
        wordList = mapper.selectWord().stream().map(item -> (Word) new WordMap(item)).collect(Collectors.toList());
//        log.info("Words loaded");
        symbols = mapper.selectSymbolWord().stream().map(item -> item.get("word").toString()).collect(Collectors.joining()).toCharArray();
        contextList = mapper.selectContext().stream().map(item -> (Context) new ContextMap(item)).collect(Collectors.toList());
//        log.info("Contexts loaded");
        compoundList = mapper.selectCompound().stream().map(item -> (Compound) new CompoundMap(item)).collect(Collectors.toList());
//        log.info("Bank updated: words={}, contexts={}, compounds={}", wordList.size(), contextList.size(), compoundList.size());
        // fix space api
        spaceMap = powerfulMapper.sumUltronAnswerSpaceCase().stream().collect(Collectors.toMap(k -> k.leftword, v -> v.space > v.cnt * 10));
    }
}
