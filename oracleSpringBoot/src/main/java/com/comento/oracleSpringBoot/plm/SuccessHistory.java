package com.comento.oracleSpringBoot.plm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SuccessHistory {
    Map<BranchKey, BranchValue> map = new HashMap<>();

    public BranchValue get(String right, int n) {
        return map.get(new BranchKey(right, n));
    }
    public void put(String right, int n, List<List<Toke>> v, int retryCnt) {
        map.put(new BranchKey(right, n), new BranchValue(v, retryCnt));
    }
}

final class BranchKey {
    private final String src;
    private final int n;

    BranchKey(String src, int n) {
        this.src = src;
        this.n = n;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        BranchKey that = (BranchKey) obj;
        return Objects.equals(this.src, that.src) &&
                this.n == that.n;
    }

    @Override
    public int hashCode() {
        return Objects.hash(src, n);
    }

    @Override
    public String toString() {
        return "BranchKey[" +
                "src=" + src + ", " +
                "n=" + n + ']';
    }
}

final class BranchValue {
    private final List<List<Toke>> toBe;
    private final int retryCnt;

    BranchValue(List<List<Toke>> toBe, int retryCnt) {
        this.toBe = toBe;
        this.retryCnt = retryCnt;
    }

    public List<List<Toke>> toBe() {
        return toBe;
    }

    public int retryCnt() {
        return retryCnt;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        BranchValue that = (BranchValue) obj;
        return Objects.equals(this.toBe, that.toBe) &&
                this.retryCnt == that.retryCnt;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toBe, retryCnt);
    }

    @Override
    public String toString() {
        return "BranchValue[" +
                "toBe=" + toBe + ", " +
                "retryCnt=" + retryCnt + ']';
    }
}