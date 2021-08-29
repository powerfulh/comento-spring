package com.comento.oracleSpringBoot.approval;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comento.oracleSpringBoot.approval.entity.ApprovalVo;
import com.comento.oracleSpringBoot.approval.entity.FormVo;

@RestController
@CrossOrigin
public class ApprovalC {
	@Autowired
	ApprovalS service;
	@GetMapping("getApprovalFormList")
	public List<FormVo> getApprovalFormList() {
		return service.getApprovalFormList();
	}
	@GetMapping("getNewApproval")
	public List<ApprovalVo> getNewApproval(String sid) {
		return service.getNewApproval(sid);
	}
}
