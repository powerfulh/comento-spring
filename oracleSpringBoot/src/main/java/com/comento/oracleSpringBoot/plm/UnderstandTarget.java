package com.comento.oracleSpringBoot.plm;


import com.comento.oracleSpringBoot.plm.entity.Word;

import java.util.List;

public class UnderstandTarget implements Cloneable {
    final String src;
    int currentCut = 0;

    public UnderstandTarget(String src) {
        this.src = src;
    }

    public String getRight() {
        return src.substring(currentCut);
    }
    // fix space api 를 위해 `spaceMap` 인자 추가 및 기존 함수 오버로드
    public Toke getAvailableToke(Word word) {
        return getAvailableToke(word, false);
    }
    public Toke getAvailableToke(Word word, boolean shouldSpace) {
        final String right = getRight();
        int ignored = 0;
        int wordSpace = 0;
        for (int i = 0; i < word.getWord().length(); i++) {
            char currentWord = word.getWord().charAt(i);
            char currentSrc;
            try {
                currentSrc = right.charAt(i - wordSpace + ignored);
            } catch (StringIndexOutOfBoundsException e) {
                return null;
            }
            if(currentSrc == currentWord) continue;
            if(currentWord == ' ') {
                wordSpace++;
                continue;
            }
            if(currentSrc == ' ') {
                ignored++;
                i--;
            } else return null;
        }
        int nextSpace = 0;
        final int consumedLength = word.getWord().length() + ignored - wordSpace;
        while (right.length() > consumedLength + nextSpace && right.charAt(consumedLength + nextSpace) == ' ') nextSpace++;
        // fix space api 를 위한 shouldSpace 처리 추가
        return new Toke(word, currentCut, currentCut + consumedLength + nextSpace, shouldSpace || 0 < nextSpace);
    }
    public UnderstandTarget pushToke(List<Toke> understandList, Toke toke) {
        understandList.add(toke);
        currentCut = toke.end;
        return this;
    }
    public boolean success() {
        return src.length() == currentCut;
    }
    public void rollback(Toke toke) {
        currentCut = toke.start;
    }
    @Override
    public UnderstandTarget clone() {
        try {
            return (UnderstandTarget) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
