package com.comento.oracleSpringBoot.service;

import com.comento.oracleSpringBoot.StaticUtil;
import com.comento.oracleSpringBoot.dto.plm.UnderstandBoxCommit;
import com.comento.oracleSpringBoot.mapper.PlmMapper;
import com.comento.oracleSpringBoot.mapper.PowerfulMapper;
import com.comento.oracleSpringBoot.plm.*;
import com.comento.oracleSpringBoot.plm.entity.Context;
import com.comento.oracleSpringBoot.plm.entity.Word;
import com.comento.oracleSpringBoot.powerfulh.plm.SpaceCase;
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
    final StringHelper stringHelper;
    final ContextCore contextCore;
    final PowerfulMapper powerfulMapper;

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

    List<Sentence> understand(String pureSrc) {
        List<Sentence> sentenceList = new ArrayList<>();
        SuccessHistory successHistory = new SuccessHistory();
        final UnderstandTarget understandTarget = new UnderstandTarget(stringHelper.replaceRepeatedChars(pureSrc, bank.symbols));
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
    public String fixSpace(String pureSrc) {
        final List<Sentence> understand = understand(pureSrc); // 지금은 자체 구현이지만 나중엔 모델 부르게 해야 됨
        if(understand.isEmpty()) throw new RuntimeException("모델이 문장을 이해하지 못했습니다 ㅠ");
        final Sentence sentence = understand.get(0);
        StringBuilder fixing = new StringBuilder(sentence.get(0).getWord());
        for(int i = 0; i < sentence.size(); i++) {
            if(i + 1 == sentence.size()) break;
            final Toke current = sentence.get(i);
            final Toke next = sentence.get(i + 1);
            final Context context = bank.contextList.stream()
                    .filter(item -> item.getLeftword() == current.getN() && item.getRightword() == next.getN()).findFirst().orElse(null);
            if(context == null) {
                final List<Context> contextList = bank.contextList.stream().filter(item -> item.getLeftword() == current.getN()).collect(Collectors.toList());
                if(contextList.isEmpty()) {
                    System.out.println(current.getN() + " + " + next.getN());
                    throw new RuntimeException("모델이 문맥을 이해하지 못했습니다 ㅠ");
                }
                final SpaceCase spaceCase = powerfulMapper.sumSpaceCase(current.getN(), next.getType());
                if(spaceCase != null && spaceCase.expectSpace()) fixing.append(" ");
                fixing.append(next.getWord());
                continue;
            }
            if(context.getSpace() > context.getCnt()) fixing.append(" ");
            fixing.append(next.getWord());
        }
        return fixing.toString();
    }
}
