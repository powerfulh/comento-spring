package com.comento.oracleSpringBoot.mail;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.comento.oracleSpringBoot.mail.entity.MailVo;

@Mapper
@Repository
public interface MailS {
	public List<MailVo> getMailInbox(String sid);
	public List<MailVo> getMailOutBox(String sid);
}
