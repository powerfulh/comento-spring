package com.comento.oracleSpringBoot.mapper;

import java.util.List;
import java.util.Map;

import com.comento.oracleSpringBoot.dto.plm.Compound;
import org.apache.ibatis.annotations.Mapper;

import com.comento.oracleSpringBoot.dto.plm.Word;

@Mapper
public interface PlmMapper {
	List<Word> selectWord(String s);
    void insertWord(Word w);
    List<Compound> selectCompounded(int n);
    void insertCompound(Compound c);
    List<Word> selectLeftRight(int n);
    List<Map<String, Object>> selectLearn();
    Map<String, Object> selectOneLearn(int n);
    void deleteLearn(int n);
    void deleteCanceledWord();
    void upsertContextCnt(Map<String, Integer> param);
    Map<String, Object> selectContext(Map<String, Integer> param);
    List<Map<String, Object>> selectContextList(int n);
    List<Map<String, Object>> selectUnderstandBox();
    void deactivateUnderstandBox(int n);
    void upsertContextSpace(Map<String, Integer> param);
    void updateWord(Word w);
}
