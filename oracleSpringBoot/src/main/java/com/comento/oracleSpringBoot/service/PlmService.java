package com.comento.oracleSpringBoot.service;

import com.comento.oracleSpringBoot.mapper.PlmMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlmService {
    final PlmMapper mapper;

    @Transactional
    public void commitUnderstandBox(int n) {
        mapper.deactivateUnderstandBox(n);
    }
}
