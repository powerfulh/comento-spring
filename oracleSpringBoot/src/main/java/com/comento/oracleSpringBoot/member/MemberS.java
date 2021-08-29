package com.comento.oracleSpringBoot.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.comento.oracleSpringBoot.approval.entity.ApprovalVo;
import com.comento.oracleSpringBoot.mail.entity.MailVo;
import com.comento.oracleSpringBoot.member.entity.LoginVo;

@Mapper
@Repository
public interface MemberS {
	public int logicProc(LoginVo lvo);
	public List<MailVo> getMainMail(String sid);
	public List<ApprovalVo> getMainApproval(String sid);
}
