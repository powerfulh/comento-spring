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
    static Toke generateToke(UnderstandTarget src, Word item, List<Toke> understandList, ContextCore contextCore, Toke lastUnderstand, List<Context> contextList, List<Compound> compoundList, final Dict wordList, boolean shouldSpace) {
        Toke toke = src.getAvailableToke(item, shouldSpace);
        if(toke == null || understandList.isEmpty()) return toke;
        try {
            contextCore.rightContext(toke, lastUnderstand, toke, contextList, compoundList, wordList, lastUnderstand.isRightSpace(), lastUnderstand.otherOption, 0);
        } catch (PlmException e) {
            return null;
        }
        return contextCore.step2(toke, lastUnderstand.isRightSpace()); // 이 부분만 모델이랑 울트론이 다르다. 모델과 동일한 동작이며 울트론도 따라와야 되지 않나 싶다
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
                    sentenceList.add(new Sentence(merge, contextList));
                });
                return;
            }
            List<Word> h = failHistory.get(src.getRight());
            final List<Word> page = wordList.book.get(src.getRight().charAt(0));
            List<Toke> sameList = page == null ? Collections.emptyList() : page.stream()
                    // fix space 흐름에서는 재사용이 필요하므로 함수로 분리했다
                    .map(item -> generateToke(src, item, understandList, contextCore, lastUnderstand, contextList, compoundList, wordList, false))
                    .filter(item -> {
                        if(item != null) {
                            if(h == null) return true;
                            return h.stream().noneMatch(hi -> Objects.equals(hi.getN(), item.getN()));
                        }
                        return false;
                    })
                    .sorted(Comparator.comparing(Toke::getRightContext))
                    .collect(Collectors.toList());
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
                final boolean shouldSpace = !lastUnderstand.rightSpace && spaceMap.getOrDefault(lastUnderstand.getN(), false);
                sameList.subList(0, sameList.size() - 1)
//                        .filter(item -> item.getRightContext() > 0)
                        .forEach(item -> {
                            List<Toke> clone = new ArrayList<>(understandList);
                            if (item.getRightContext() > 0) {
                                separateToken(clone, src.clone().pushToke(clone, item), wordList, failHistory, contextList, sentenceList, compoundList, successHistory, contextCore, spaceMap);
                            } else if(shouldSpace) {
                                UnderstandTarget rollbackSrcClone = src.clone();
                                rollbackSrcClone.rollback(lastUnderstand);
                                if(clone.size() == 1) clone.set(0, rollbackSrcClone.getAvailableToke(lastUnderstand.src, true));
                                else {
                                    clone.set(clone.size() - 1, generateToke(rollbackSrcClone, lastUnderstand.src, understandList.subList(0, understandList.size() - 1), contextCore, understandList.get(understandList.size() - 2), contextList, compoundList, wordList, true));
                                }
                                separateToken(clone, src.clone().pushToke(clone, item), wordList, failHistory, contextList, sentenceList, compoundList, successHistory, contextCore, spaceMap);
                            }
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
