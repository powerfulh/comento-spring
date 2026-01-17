package com.comento.oracleSpringBoot.service;

import com.comento.oracleSpringBoot.StaticUtil;
import com.comento.oracleSpringBoot.dto.plm.UnderstandBoxCommit;
import com.comento.oracleSpringBoot.mapper.PlmMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class PlmService {
    final PlmMapper mapper;

//    final String jpaServerCommit = "http://localhost:8080/llm/commit";
    final String jpaServerCommit = "https://port-0-jpa-5o1j2llh1wq9rp.sel4.cloudtype.app/llm/commit";

    @Transactional
    public void commitUnderstandBox(int n) {
        mapper.deactivateUnderstandBox(n);
        RestTemplate template = new RestTemplate();
        UnderstandBoxCommit dto = mapper.selectUnderstandBox(n);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<UnderstandBoxCommit> entity = new HttpEntity<>(dto, headers);
        String url = jpaServerCommit + "?src=" + dto.src;
        try {
            template.postForEntity(url, entity, String.class);
        } catch (HttpServerErrorException e) {
            if (StaticUtil.nullElse(e.getMessage(), "").contains("No context")) {
                template.postForEntity(url + "&learnContext=true", entity, String.class);
            }
        }
    }
}
