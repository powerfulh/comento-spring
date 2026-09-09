package com.comento.oracleSpringBoot.service;

import com.comento.oracleSpringBoot.StaticUtil;
import com.comento.oracleSpringBoot.dto.plm.UnderstandBoxCommit;
import com.comento.oracleSpringBoot.mapper.PlmMapper;
import com.comento.oracleSpringBoot.plm.*;
import com.comento.oracleSpringBoot.plm.entity.Word;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlmService {
    final PlmMapper mapper;
    final Bank bank;
    final ReplaceRepeatedChars replaceRepeatedChars;
    final ContextCore contextCore;

//    final String jpaServerCommit = "http://localhost:8080/llm/commit";
    final String jpaServerCommit = "https://port-0-jpa-5o1j2llh1wq9rp.sel4.cloudtype.app/llm/commit";

    @Transactional
    public void commitUnderstandBox(int n) {
        mapper.deactivateUnderstandBox(n);
        RestTemplate template = new RestTemplate();
        UnderstandBoxCommit dto = mapper.selectUnderstandBox(n);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<UnderstandBoxCommit> entity = new HttpEntity<>(dto, headers);
        String url = jpaServerCommit + "?src=" + dto.src;
        try {
            template.postForEntity(url, entity, String.class);
        } catch (HttpServerErrorException e) {
            if (StaticUtil.nullElse(e.getMessage(), "").contains("No context")) {
                template.postForEntity(url + "&learnContext=true", entity, String.class);
            }
        }
    }

    public List<Sentence> understand(String pureSrc) {
        List<Sentence> sentenceList = new ArrayList<>();
        SuccessHistory successHistory = new SuccessHistory();
        final UnderstandTarget understandTarget = new UnderstandTarget(replaceRepeatedChars.replaceRepeatedChars(pureSrc, bank.symbols));
        List<Toke> openerList = bank.wordList.stream().map(understandTarget::getAvailableToke).filter(Objects::nonNull).collect(Collectors.toList());
//        logger.info("opener cnt: {}", openerList.size());
        Map<String, List<Word>> failHistory = new HashMap<>();
        PlmException e = null;
        for (Toke opener: openerList) {
//            logger.info("understanding with opener: {}", opener.getWord());
            List<Toke> understandList = new ArrayList<>();
            try {
                StaticUtilFromModel.separateToken(understandList, understandTarget.pushToke(understandList, opener), new Dict(bank.wordList), failHistory, bank.contextList, sentenceList, bank.compoundList, successHistory, contextCore);
            } catch (PlmException plmException) {
                e = plmException;
            }
        }
        if(sentenceList.isEmpty() && e != null) throw e; // 싹 다 실패한 경우 나중에는 편집 거리로 리트해봐야겠지
        sentenceList.sort(Comparator.comparing(item -> item.getContextPoint() * -1));
//        logger.info("Understand success: sentence: {}, total length: {}", sentenceList.size(), sentenceList.stream().mapToInt(List::size).sum());
        return sentenceList.size() > 9 ? sentenceList.subList(0, 9) : sentenceList;
    }
}
