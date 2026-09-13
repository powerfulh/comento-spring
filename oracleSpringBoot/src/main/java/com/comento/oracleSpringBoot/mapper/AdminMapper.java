package com.comento.oracleSpringBoot.mapper;

import com.comento.oracleSpringBoot.dto.common.ReqLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
	List<ReqLog> selectUserLog();
}
