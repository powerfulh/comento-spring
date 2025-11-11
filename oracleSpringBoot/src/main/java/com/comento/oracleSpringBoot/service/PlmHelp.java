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
    public int helpable(final String word) {
        final int length = word.length();
        final char last = word.charAt(length - 1);
        final Character second = length > 1 ? word.charAt(length - 2) : null;
        final int SLast = 0xD7A3;
        if ((int) last < SBase || (int) last > SLast) {
            // 완성형 한글 음절이 아님
            return 0;
        }
        int SIndex = (int) last - SBase;
        int footer = SIndex % 28;       // 0이면 받침 없음
        int jungIndex = (SIndex / 28) % 21; // 0..20 (ㅏ..ㅣ)
        if(footer == JONG_NONE) {
            switch (jungIndex) {
                case 0: // ㅏ
                    return 1;
                case 20: // ㅣ
                    return 2;
                case 18: // ㅡ
                    if((SIndex / (21 * 28)) == 5) { // 5: 르
                        if(second == null) return 4;
                        final int secondIndex = second - SBase;
                        final int secondMother = (secondIndex / 28) % 21;
                        return (secondMother == 4 || secondMother == 13 || secondMother == 18) ? 4 : 7; // ㅓ || ㅜ || ㅡ
                    }
                    break;
                case 13: // ㅜ
                    return 6;
            }
        } else if(footer == 17) { // ㅂ
            if(jungIndex == 13) return 5; // 13: 'ㅜ'
            else if(jungIndex != 8) return 3; // 8: 'ㅗ' 는 존나 규칙이 없음 (고운 좁은 고와 좁아)
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
    String recomp(final char[] src, char last, final String append, Character second) {
        final int addLength = append.length();
        final char[] res = new char[src.length + addLength];
        System.arraycopy(src, 0, res, 0, src.length);
        res[src.length - 1] = last;
        for (int i = 0; i < addLength; i++) {
            res[src.length + i] = append.charAt(i);
        }
        if(second != null) res[src.length - 2] = second;
        return String.valueOf(res);
    }
    String recomp(String src, char last, final String append, Character second) {
        return recomp(src.toCharArray(), last, append, second);
    }
    /**
     * @author ChatGPT
     * 참고: <a href="https://github.com/powerfulh/comento-spring/issues/29">...</a>
     */
    public List<HelpResult> help(final String word) {
        final int length = word.length();
        final char last = word.charAt(length - 1);
        final Character second = length > 1 ? word.charAt(length - 2) : null;
        List<HelpResult> list = new ArrayList<>();
        switch (helpable(word)) {
            case 1:
                list.add(new HelpResult(309, recomp(word, addFooter(last, JONG_COMPLETE), "", second)));
                list.add(new HelpResult(215, recomp(word, addFooter(last, JONG_COMPLETE), "다", second)));
                list.add(new HelpResult(3039, recomp(word, addFooter(last, JONG_COMPLETE), "다고", second)));
                list.add(new HelpResult(3437, recomp(word, addFooter(last, JONG_COMPLETE), "다는", second)));
                list.add(new HelpResult(48, recomp(word, addFooter(last, JONG_GOING), "", second)));
                list.add(new HelpResult(3053, recomp(word, addFooter(last, JONG_GOING), "게", second)));
                list.add(new HelpResult(2912, recomp(word, addFooter(last, JONG_GOING), "지", second)));
                list.add(new HelpResult(3069, recomp(word, addFooter(last, JONG_GOING), "까", second)));
                list.add(new HelpResult(318, recomp(word, addFooter(last, JONG_RESPECT), "니다", second)));
                list.add(new HelpResult(208, recomp(word, addFooter(last, JONG_PAST), "", second)));
                break;
            case 2:
                list.add(new HelpResult(184, recomp(word, changeMother(last, 6), "", second))); // 6: ㅕ
                list.add(new HelpResult(50, recomp(word, changeMother(last, 6), "서", second)));
                list.add(new HelpResult(154, recomp(word, changeMother(last, 6), "지", second)));
                list.add(new HelpResult(3765, recomp(word, changeMother(last, 6), "진", second)));
                list.add(new HelpResult(162, recomp(word, addFooter(changeMother(last, 6), JONG_PAST), "", second)));
                list.add(new HelpResult(48, recomp(word, addFooter(last, JONG_GOING), "", second)));
                list.add(new HelpResult(3053, recomp(word, addFooter(last, JONG_GOING), "게", second)));
                list.add(new HelpResult(3047, recomp(word, addFooter(last, JONG_GOING), "래", second)));
                list.add(new HelpResult(309, recomp(word, addFooter(last, JONG_COMPLETE), "", second)));
                list.add(new HelpResult(215, recomp(word, addFooter(last, JONG_COMPLETE), "다", second)));
                break;
            case 3:
                list.add(new HelpResult(4602, recomp(word, removeFooter(last), "운데", second)));
                list.add(new HelpResult(105, recomp(word, removeFooter(last), "움", second)));
                list.add(new HelpResult(184, recomp(word, removeFooter(last), "워", second)));
                list.add(new HelpResult(309, recomp(word, removeFooter(last), "운", second)));
                list.add(new HelpResult(50, recomp(word, removeFooter(last), "워서", second)));
                break;
            case 4:
                list.add(new HelpResult(309, recomp(word, addFooter(last, JONG_COMPLETE), "", second)));
                list.add(new HelpResult(48, recomp(word, addFooter(last, JONG_GOING), "", second)));
                list.add(new HelpResult(3053, recomp(word, addFooter(last, JONG_GOING), "게", second)));
                list.add(new HelpResult(2912, recomp(word, addFooter(last, JONG_GOING), "지", second)));
                list.add(new HelpResult(3069, recomp(word, addFooter(last, JONG_GOING), "까", second)));
                list.add(new HelpResult(318, recomp(word, addFooter(last, JONG_RESPECT), "니다", second)));
                assert second != null;
                list.add(new HelpResult(184, recomp(word, changeMother(last, 4), "", addFooter(second, JONG_GOING)))); // 4: ㅓ
                break;
            case 5:
                // 춥다: 추운데, 굽다: 굽는데 예외가 있어서 는/은데는 제외
                list.add(new HelpResult(105, recomp(word, removeFooter(last), "움", second)));
                list.add(new HelpResult(184, recomp(word, removeFooter(last), "워", second)));
                list.add(new HelpResult(50, recomp(word, removeFooter(last), "워서", second)));
                list.add(new HelpResult(309, recomp(word, removeFooter(last), "운", second)));
                list.add(new HelpResult(824, recomp(word, removeFooter(last), "우면", second))); // case 5 로 빠진 이유
                break;
            case 6:
                list.add(new HelpResult(184, recomp(word, changeMother(last, 14), "", second))); // 14: ㅝ
                list.add(new HelpResult(50, recomp(word, changeMother(last, 14), "서", second)));
                list.add(new HelpResult(154, recomp(word, changeMother(last, 14), "지", second)));
                list.add(new HelpResult(212, recomp(word, changeMother(last, 14), "져", second)));
                list.add(new HelpResult(48, recomp(word, addFooter(last, JONG_GOING), "", second)));
                list.add(new HelpResult(3069, recomp(word, addFooter(last, JONG_GOING), "까", second)));
                list.add(new HelpResult(309, recomp(word, addFooter(last, JONG_COMPLETE), "", second)));
                break;
            case 7:
                list.add(new HelpResult(48, recomp(word, addFooter(last, JONG_GOING), "", second)));
                list.add(new HelpResult(2912, recomp(word, addFooter(last, JONG_GOING), "지", second)));
                list.add(new HelpResult(3053, recomp(word, addFooter(last, JONG_GOING), "게", second)));
                list.add(new HelpResult(3069, recomp(word, addFooter(last, JONG_GOING), "까", second)));
                list.add(new HelpResult(309, recomp(word, addFooter(last, JONG_COMPLETE), "", second)));
                list.add(new HelpResult(318, recomp(word, addFooter(last, JONG_RESPECT), "니다", second)));
                assert second != null;
                list.add(new HelpResult(62, recomp(word, changeMother(last, 0), "", addFooter(second, JONG_GOING)))); // 0: ㅏ
                break;
        }
        return list;
    }
    public Word getJustPost(String word) {
        return mapper.selectWord(word).stream().max(Comparator.comparing(Word::getN)).orElseThrow(RuntimeException::new);
    }
}