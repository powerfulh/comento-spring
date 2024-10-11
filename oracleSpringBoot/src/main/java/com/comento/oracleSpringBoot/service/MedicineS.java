package com.comento.oracleSpringBoot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comento.oracleSpringBoot.dto.medicine.Count;
import com.comento.oracleSpringBoot.powerfulh.PowerfulMapper;

@Service
public class MedicineS extends BaseService {
	@Autowired
	PowerfulMapper pMapper;

	public List<Count> getCount() {
		List<Count> list = pMapper.selectMedicineCnt();
		Count total = new Count();
		total.setName("T");
		total.setTotal(list.stream().map(Count::getTotal).reduce(0, Integer::sum));
		list.add(total);
		return list;
	}
}
