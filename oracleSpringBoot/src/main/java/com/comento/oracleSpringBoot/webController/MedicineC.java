package com.comento.oracleSpringBoot.webController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comento.oracleSpringBoot.dto.medicine.Count;
import com.comento.oracleSpringBoot.member.MemberS;
import com.comento.oracleSpringBoot.powerfulh.PowerfulMapper;
import com.comento.oracleSpringBoot.service.MedicineS;

@Controller
@RequestMapping("medicine")
public class MedicineC extends WebC {
	@Autowired
	PowerfulMapper pMapper;
	@Autowired
	MedicineS ms;

	public MedicineC(MemberS ms) {
		super(ms);
	}
	@GetMapping("")
	public String index() {
		return "medicine";
	}
	@PostMapping("new")
	public String addNew(@RequestParam Map<String, String> param) {
		pMapper.newMedicine(param);
		return "redirect:/medicine";
	}
	@GetMapping("get-list")
	@ResponseBody
	public List<Map<String, Object>> getList() {
		return pMapper.getMedicineList();
	}
	@GetMapping("edit")
	@ResponseBody
	public int edit(@RequestParam Map<String, String> param) {
		return pMapper.editMedicine(param);
	}
	@PostMapping("delete")
	public String delete(@RequestParam Map<String, String> param) {
		pMapper.deleteMedicine(param);
		return "redirect:/medicine";
	}
	@GetMapping("get-list-most-plus")
	@ResponseBody
	public List<Map<String, Object>> getListMostPlus() {
		return pMapper.getMedicineListMostPlus();
	}
	@GetMapping("get-count")
	@ResponseBody
	public List<Count> getCount() {
		return ms.getCount();
	}
}
