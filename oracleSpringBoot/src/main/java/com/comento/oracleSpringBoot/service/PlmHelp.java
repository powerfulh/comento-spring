package com.comento.oracleSpringBoot.service;

import com.comento.oracleSpringBoot.dto.plm.Word;
import com.comento.oracleSpringBoot.mapper.PlmMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PlmHelp {
    final PlmMapper mapper;

    final int SBase = 0xAC00;
    final int JUNG_YEO = 6; // ㅕ
    final int JONG_NONE = 0;
    final int JONG_PAST = 20; // ㅆ
    final int JUNG_IEU = 20; // ㅣ
    final int JONG_GOING = 8; // ㄹ
    final int JONG_COMPLETE = 4; // ㄴ
    /**
     * 주어진 한 문자(완성형 한글 음절)가
     * 1) 받침(종성)이 없고
     * 2) 중성(모음)이 'ㅣ'인지 검사하여 true/false 반환
     * @author ChatGPT
     */
    public boolean helpable(char ch) {
        final int SLast = 0xD7A3;

        if ((int) ch < SBase || (int) ch > SLast) {
            // 완성형 한글 음절이 아님
            return false;
        }

        int SIndex = (int) ch - SBase;
        int jongIndex = SIndex % 28;       // 0이면 받침 없음
        int jungIndex = (SIndex / 28) % 21; // 0..20 (ㅏ..ㅣ)

        final int JUNG_IEU = 20; // 'ㅣ'의 중성 인덱스는 20

        return (jongIndex == JONG_NONE) && (jungIndex == JUNG_IEU);
    }
    /**
     * @author ChatGPT
     */
    public List<String> help(char ch) {
        List<String> result = new ArrayList<>();

        int SIndex = ch - SBase;
        int choIndex = SIndex / (21 * 28); // 초성 인덱스

        // 1) ㅕ (받침 없음)
        int codeYeo = SBase + (choIndex * 21 + JUNG_YEO) * 28 + JONG_NONE;
        String sYeo = String.valueOf((char) codeYeo);
        result.add(sYeo);

        // 2) ㅕ + ㅆ (받침 ㅆ)
        int codeYeoSs = SBase + (choIndex * 21 + JUNG_YEO) * 28 + JONG_PAST;
        result.add(String.valueOf((char) codeYeoSs));

        // 3) ㅕ + "서"  -> e.g. "려서"
        result.add(sYeo + "서");

        // 4) 받침 ㄹ 추가 (모음은 여전히 ㅣ) -> e.g. "릴"
        int codeRiRieul = SBase + (choIndex * 21 + JUNG_IEU) * 28 + JONG_GOING;
        result.add(String.valueOf((char) codeRiRieul));

        // 5) 받침 ㄹ 추가 + "게" -> e.g. "릴게"
        result.add((char) codeRiRieul + "게");

        // 6) 받침 ㄴ 추가 (모음 ㅣ) -> e.g. "린"
        int codeRiNieun = SBase + (choIndex * 21 + JUNG_IEU) * 28 + JONG_COMPLETE;
        result.add(String.valueOf((char) codeRiNieun));

        // 7) 받침 ㄴ 추가 + "다" -> e.g. "린다"
        result.add((char) codeRiNieun + "다");

        return result;
    }
    public Map<String, Integer> getCompound(char ch) {
        Map<String, Integer> map = new HashMap<>();
        List<String> helped = help(ch);
        map.put(helped.get(0), 184); // 어
        map.put(helped.get(1), 162); // 었
        map.put(helped.get(2), 50); // 어서
        map.put(helped.get(3), 48); // ㄹ
        map.put(helped.get(4), 3053); // 을게
        map.put(helped.get(5), 309); // 은
        map.put(helped.get(6), 215); // 는다
        return map;
    }
    public Word getJustPost(String word) {
        return mapper.selectWord(word).stream().max(Comparator.comparing(Word::getN)).orElseThrow(RuntimeException::new);
    }
}
