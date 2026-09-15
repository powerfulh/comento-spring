package com.comento.oracleSpringBoot.plm;

import com.comento.oracleSpringBoot.plm.entity.Compound;
import com.comento.oracleSpringBoot.plm.entity.Context;
import com.comento.oracleSpringBoot.plm.entity.Twoken;
import com.comento.oracleSpringBoot.plm.entity.Word;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StaticUtilFromModel {
    static final int opener = 2903;

    public static Predicate<Twoken> getContextFinder(int lw, int rw) {
        return item -> item.getLeftword() == lw && item.getRightword() == rw;
    }
    // 재사용하려고 함수화
    static Toke generateToke(UnderstandTarget src, Word item, List<Toke> understandList, ContextCore contextCore, Toke lastUnderstand, List<Context> contextList, List<Compound> compoundList, final Dict wordList, boolean forceSpace) {
        Toke toke = src.getAvailableToke(item);
        if(toke == null || understandList.isEmpty()) return toke;
        final boolean betweenSpace = lastUnderstand.rightSpace || forceSpace; // comento fix api 에서는 왼쪽은 항상 false라 의미 없는 폴백, 추후 모델 통합 고려
        try {
            contextCore.rightContext(toke, lastUnderstand, toke, contextList, compoundList, wordList, betweenSpace, lastUnderstand.otherOption, 0);
        } catch (PlmException e) {
            return null;
        }
        return contextCore.step2(toke, betweenSpace); // 이 부분만 모델이랑 울트론이 다르다. 모델과 동일한 동작이며 울트론도 따라와야 되지 않나 싶다
    }
    // fix space api 를 위해 `spaceMap` 인자 추가
    public static void separateToken(List<Toke> understandList, UnderstandTarget src, final Dict wordList, Map<String, List<Word>> failHistory, List<Context> contextList, List<Sentence> sentenceList, List<Compound> compoundList, SuccessHistory successHistory, ContextCore contextCore, Map<Integer, Boolean> spaceMap) {
        if(src.success()) sentenceList.add(new Sentence(understandList, contextList));
        else {
            Toke lastUnderstand = understandList.get(understandList.size() - 1);
            BranchValue sh = successHistory.get(src.getRight(), lastUnderstand.getN());
            if(sh != null) {
                final List<Toke> beforeMerge = understandList.subList(0, understandList.size() - sh.retryCnt());
                sh.toBe().forEach(item -> {
                    List<Toke> merge = new ArrayList<>(beforeMerge);
                    merge.addAll(item);
                    final Sentence s = new Sentence(merge, contextList);
                    if(s.contextPoint > sentenceList.get(sentenceList.size() - 1).contextPoint) sentenceList.add(s);
                });
                return;
            }
            List<Word> h = failHistory.get(src.getRight());
            final List<Word> page = wordList.book.get(src.getRight().charAt(0));
            List<Toke> tokeList = page == null ? Collections.emptyList() : page.stream()
                    .map(item -> generateToke(src, item, understandList, contextCore, lastUnderstand, contextList, compoundList, wordList, false))
                    .filter(item -> {
                        if(item != null) {
                            if(h == null) return true;
                            return h.stream().noneMatch(hi -> Objects.equals(hi.getN(), item.getN()));
                        }
                        return false;
                    })
//                    .sorted(Comparator.comparing(Toke::getRightContext))
                    .collect(Collectors.toList());
            final List<Toke> sameList = new ArrayList<>(); // 띄워 이해 분기 추가하기 위해 도입
            final boolean trySpace = spaceMap.getOrDefault(lastUnderstand.getN(), false);
            tokeList.forEach(item -> {
                sameList.add(item);
                if(trySpace) {
                    final Toke spaced = generateToke(src, item.src, understandList, contextCore, lastUnderstand, contextList, compoundList, wordList, true);
                    if(spaced == null) return;
                    spaced.leftShouldSpace = true;
                    sameList.add(spaced);
                }
            });
            sameList.sort(Comparator.comparing(Toke::getRightContext)); // 띄워 분기 복제까지 하고 정렬해야 되므로 여기서
            if(sameList.isEmpty()) {
                if(understandList.size() < 2) throw failHistory.isEmpty() ? new PlmException("Fail to continue after open", lastUnderstand.getWord()) : new PlmException("Fail to understand", failHistory);
                src.rollback(lastUnderstand);
                failHistory.computeIfAbsent(src.getRight(), k -> new ArrayList<>());
                failHistory.get(src.getRight()).add(lastUnderstand);
                understandList.remove(understandList.size() - 1);
                separateToken(understandList, src, wordList, failHistory, contextList, sentenceList, compoundList, successHistory, contextCore, spaceMap);
                return;
            }
            final Toke best = sameList.get(sameList.size() - 1);
            int ss = sentenceList.size();
            if(sameList.size() > 1) {
                sameList.subList(0, sameList.size() - 1).stream()
                        .filter(item -> item.getRightContext() > 0)
                        .forEach(item -> {
                            List<Toke> clone = new ArrayList<>(understandList);
                            separateToken(clone, src.clone().pushToke(clone, item), wordList, failHistory, contextList, sentenceList, compoundList, successHistory, contextCore, spaceMap);
                        });
                if(best.getRightContext() < 1) best.otherOption = true;
            }
            final String right = src.getRight();
            final int understandSize = understandList.size();
            final List<Integer> currentUnderstand = understandList.stream().map(Toke::getN).collect(Collectors.toList());
            separateToken(understandList, src.pushToke(understandList, best), wordList, failHistory, contextList, sentenceList, compoundList, successHistory, contextCore, spaceMap);
            List<Sentence> branchList = sentenceList.subList(ss, sentenceList.size());
            if(!branchList.isEmpty()) {
                int keepCnt = 0;
                for (int i = 0; i < Math.min(understandSize, branchList.stream().mapToInt(ArrayList::size).min().orElseThrow(NoSuchElementException::new)); i++) {
                    if(!currentUnderstand.get(i).equals(branchList.get(0).get(i).getN())) break;
                    keepCnt++;
                }
                successHistory.put(right, lastUnderstand.getN(), branchList.stream().map(item -> item.subList(understandSize, item.size())).collect(Collectors.toList()), understandSize - keepCnt);
            }
        }
    }
}
