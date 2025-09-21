package com.comento.oracleSpringBoot.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlmHelp {
    /**
     * 주어진 한 문자(완성형 한글 음절)가
     * 1) 받침(종성)이 없고
     * 2) 중성(모음)이 'ㅣ'인지 검사하여 true/false 반환
     * @author ChatGPT
     */
    public boolean helpable(char ch) {
        final int SBase = 0xAC00;
        final int SLast = 0xD7A3;

        if ((int) ch < SBase || (int) ch > SLast) {
            // 완성형 한글 음절이 아님
            return false;
        }

        int SIndex = (int) ch - SBase;
        int jongIndex = SIndex % 28;       // 0이면 받침 없음
        int jungIndex = (SIndex / 28) % 21; // 0..20 (ㅏ..ㅣ)

        final int JONG_NONE = 0;
        final int JUNG_IEU = 20; // 'ㅣ'의 중성 인덱스는 20

        return (jongIndex == JONG_NONE) && (jungIndex == JUNG_IEU);
    }
    /**
     * 입력 글자가 "받침 없음 + 중성 'ㅣ'"인 경우
     *  - 'ㅓ' 로 바꾼 글자
     *  - 'ㅓ' + 받침 'ㅆ' 글자
     * 두 개를 리스트로 반환.
     * 그렇지 않으면 빈 리스트 반환.
     * @author ChatGPT
     */
    public List<Character> help(char ch) {
        List<Character> result = new ArrayList<>();
        final int SBase = 0xAC00;

        int SIndex = ch - SBase;
        int choIndex = SIndex / (21 * 28); // 초성 인덱스

        final int JUNG_YEO = 6;            // 'ㅕ' 의 중성 인덱스 (정정)
        final int JONG_PAST = 20;    // 'ㅆ'의 종성 인덱스

        // 1) 초성 + ㅕ + 받침 없음
        int code1 = SBase + (choIndex * 21 + JUNG_YEO) * 28;
        result.add((char) code1);

        // 2) 초성 + ㅕ + 받침 'ㅆ'
        int code2 = SBase + (choIndex * 21 + JUNG_YEO) * 28 + JONG_PAST;
        result.add((char) code2);

        return result;
    }
}
