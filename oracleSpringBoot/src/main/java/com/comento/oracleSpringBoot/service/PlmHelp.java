package com.comento.oracleSpringBoot.service;

import com.comento.oracleSpringBoot.dto.plm.HelpResult;
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
    final int JONG_NONE = 0;
    final int JONG_PAST = 20; // ㅆ
    final int JONG_GOING = 8; // ㄹ
    final int JONG_COMPLETE = 4; // ㄴ
    final int JONG_RESPECT = 17; // ㅂ
    /**
     * @author ChatGPT
     */
    public int helpable(char ch) {
        final int SLast = 0xD7A3;
        if ((int) ch < SBase || (int) ch > SLast) {
            // 완성형 한글 음절이 아님
            return 0;
        }
        int SIndex = (int) ch - SBase;
        int footer = SIndex % 28;       // 0이면 받침 없음
        int jungIndex = (SIndex / 28) % 21; // 0..20 (ㅏ..ㅣ)
        if(footer == JONG_NONE) {
            switch (jungIndex) {
                case 0: // ㅏ
                    return 1;
                case 20: // ㅣ
                    return 2;
                case 18: // ㅡ
                    if((SIndex / (21 * 28)) == 5) return 4; // 르
            }
        } else if(footer == 17) { // ㅂ
            if(jungIndex != 8) return 3; // 8: 'ㅗ' 는 존나 규칙이 없음 (고운 좁은 고와 좁아)
        }
        return 0;
    }
    char addFooter(char target, int footerIndex) {
        return (char) (target + footerIndex);
    }
    char changeMother(char target, int motherIndex) {
        int SIndex = target - SBase;
        int choIndex = SIndex / (21 * 28); // 초성 인덱스
        return (char) (SBase + (choIndex * 21 + motherIndex) * 28);
    }
    char removeFooter(char target) {
        return (char) (target - ((target - SBase) % 28));
    }
    /**
     * @author ChatGPT
     */
    public List<HelpResult> help(char target) {
        List<HelpResult> list = new ArrayList<>();
        switch (helpable(target)) {
            case 1:
                list.add(new HelpResult(215, addFooter(target, JONG_COMPLETE) + "다"));
                list.add(new HelpResult(48, String.valueOf(addFooter(target, JONG_GOING))));
                list.add(new HelpResult(3039, addFooter(target, JONG_COMPLETE) + "다고"));
                list.add(new HelpResult(3053, addFooter(target, JONG_GOING) + "게"));
                list.add(new HelpResult(309, String.valueOf(addFooter(target, JONG_COMPLETE))));
                list.add(new HelpResult(2912, addFooter(target, JONG_GOING) + "지"));
                list.add(new HelpResult(3069, addFooter(target, JONG_GOING) + "까"));
                list.add(new HelpResult(3437, addFooter(target, JONG_COMPLETE) + "다는"));
                list.add(new HelpResult(318, addFooter(target, JONG_RESPECT) + "니다"));
                list.add(new HelpResult(208, String.valueOf(addFooter(target, JONG_PAST))));
                break;
            case 2:
                // 6: ㅕ
                list.add(new HelpResult(184, String.valueOf(changeMother(target, 6))));
                list.add(new HelpResult(162, String.valueOf(addFooter(changeMother(target, 6), JONG_PAST))));
                list.add(new HelpResult(50, changeMother(target, 6) + "서"));
                list.add(new HelpResult(48, String.valueOf(addFooter(target, JONG_GOING))));
                list.add(new HelpResult(3053, addFooter(target, JONG_GOING) + "게"));
                list.add(new HelpResult(309, String.valueOf(addFooter(target, JONG_COMPLETE))));
                list.add(new HelpResult(215, addFooter(target, JONG_COMPLETE) + "다"));
                list.add(new HelpResult(154, changeMother(target, 6) + "지"));
                list.add(new HelpResult(3765, changeMother(target, 6) + "진"));
                break;
            case 3:
                list.add(new HelpResult(4602, removeFooter(target) + "운데"));
                list.add(new HelpResult(105, removeFooter(target) + "움"));
                list.add(new HelpResult(184, removeFooter(target) + "워"));
                list.add(new HelpResult(309, removeFooter(target) + "운"));
                list.add(new HelpResult(50, removeFooter(target) + "워서"));
                break;
            case 4:
                list.add(new HelpResult(309, String.valueOf(addFooter(target, JONG_COMPLETE))));
                list.add(new HelpResult(48, String.valueOf(addFooter(target, JONG_GOING))));
                list.add(new HelpResult(3053, addFooter(target, JONG_GOING) + "게"));
                list.add(new HelpResult(2912, addFooter(target, JONG_GOING) + "지"));
                list.add(new HelpResult(3069, addFooter(target, JONG_GOING) + "까"));
                list.add(new HelpResult(318, addFooter(target, JONG_RESPECT) + "니다"));
                break;
        }
        return list;
    }
    public Word getJustPost(String word) {
        return mapper.selectWord(word).stream().max(Comparator.comparing(Word::getN)).orElseThrow(RuntimeException::new);
    }
}