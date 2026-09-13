package com.comento.oracleSpringBoot.dto.common;

import com.comento.oracleSpringBoot.dto.BaseDto;

import java.time.LocalDate;

public class ReqLog extends BaseDto {
    public String method;
    public String url;
    public String param;
    public String insertId;
    public LocalDate insertDate;
}
