package com.comento.oracleSpringBoot.dto.plm;

import com.comento.oracleSpringBoot.dto.BaseDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class UnderstandBox extends BaseDto {
    final List<Map<String, Object>> sentence = new ArrayList<>();
    final String src;

    UnderstandBox(String src, Map<String, Object> first, int n) {
        this.src = src;
        sentence.add(first);
        this.n = n;
    }

    public static List<UnderstandBox> of(List<Map<String, Object>> list) {
        Integer current = null;
        List<UnderstandBox> understandBoxList = new ArrayList<>();
        for (Map<String, Object> item: list) {
            if(item.get("understand").equals(current)) understandBoxList.get(understandBoxList.size() - 1).sentence.add(item);
            else understandBoxList.add(new UnderstandBox((String) item.get("src"), item, (Integer) item.get("understand")));
            current = (Integer) item.get("understand");
            item.remove("src");
            item.remove("understand");
            item.remove("i");
        }
        return understandBoxList;
    }
    public int getN() {
        return n;
    }
}
