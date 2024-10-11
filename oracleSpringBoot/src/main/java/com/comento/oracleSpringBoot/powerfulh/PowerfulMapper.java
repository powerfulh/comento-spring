package com.comento.oracleSpringBoot.powerfulh;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.comento.oracleSpringBoot.dto.medicine.Count;

@Mapper
@Repository
public interface PowerfulMapper {
	public void newMedicine(Map<String, String> param);
	public List<Map<String, Object>> getMedicineList();
	public int editMedicine(Map<String, String> param);
	public int deleteMedicine(Map<String, String> param);
	public List<Map<String, Object>> getMedicineListMostPlus();
	public List<Count> selectMedicineCnt();
}
