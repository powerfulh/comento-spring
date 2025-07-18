package com.comento.oracleSpringBoot.member;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.comento.oracleSpringBoot.member.entity.LoginVo;
import com.comento.oracleSpringBoot.member.entity.MemberVo;

@Mapper
@Repository
public interface MemberS {
	int logicProc(LoginVo lvo);
	MemberVo get(String id);
	int selectPk(LoginVo lvo);
}
