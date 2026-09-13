package com.comento.oracleSpringBoot.rest;

import com.comento.oracleSpringBoot.dto.common.ReqLog;
import com.comento.oracleSpringBoot.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminApi {
    final AdminMapper mapper;

    @GetMapping("req-log")
    public List<ReqLog> getReqLog() {
        return mapper.selectUserLog();
    }
}
