package com.comento.oracleSpringBoot.plm;

import com.comento.oracleSpringBoot.plm.entity.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sentence extends ArrayList<Toke> {
    final int contextPoint;

    public Sentence(List<Toke> list, List<Context> contextList) {
        super(list);
        Context openerContext = contextList.stream().filter(StaticUtilFromModel.getContextFinder(StaticUtilFromModel.opener, get(0).getN())).findAny().orElse(null);
        int p = openerContext == null ? 0 : (openerContext.getCnt() * get(0).getWord().length());
        if(p == 0) p = get(0).getWord().length() - 1; // 오프너도 마찬가지로 오프너 콘텍스트가 없더라도 길이가 긴 것부터 잡게 해보자
        contextPoint = p + list.stream().mapToInt(Toke::getRightContext).sum();
    }

    public int getContextPoint() {
        return contextPoint;
    }
    public Map<String, Object> getDto() {
        Map<String, Object> dto = new HashMap<>();
        dto.put("point", contextPoint);
        dto.put("list", this);
        return dto;
    }
}
