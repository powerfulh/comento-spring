package com.comento.oracleSpringBoot.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.comento.oracleSpringBoot.member.entity.LoginVo;
import com.comento.oracleSpringBoot.member.entity.MemberVo;

@Mapper
@Repository
public interface MemberS {
	public int logicProc(LoginVo lvo);
	public List<LoginVo> getLoginList();
	public MemberVo get(String id);
}
