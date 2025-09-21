package com.comento.oracleSpringBoot.service;

import org.springframework.stereotype.Service;

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
}
