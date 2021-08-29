package com.comento.oracleSpringBoot.approval;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.comento.oracleSpringBoot.approval.entity.ApprovalVo;
import com.comento.oracleSpringBoot.approval.entity.FormVo;

@Mapper
@Repository
public interface ApprovalS {
	public List<FormVo> getApprovalFormList();
	public List<ApprovalVo> getNewApproval(String sid);
}
