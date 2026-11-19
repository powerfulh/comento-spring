package com.comento.oracleSpringBoot.service;

import com.comento.oracleSpringBoot.mapper.CurtainMapper;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CurtainService {
  final CurtainMapper mapper;

  public List<Map<String, Object>> getDeniedImg() {
    List<Map<String, Object>> list = mapper.selectLatestImg();
    return list.stream().filter(item -> {
      String url = item.get("img0url").toString();
      RestTemplate template = new RestTemplate();
      try {
        ResponseEntity<String> res = template.getForEntity(url, String.class);
        return res.getStatusCodeValue() != 200;
      } catch (HttpClientErrorException e) {
        item.put("status code", e.getStatusCode());
        return true;
      }
    }).collect(Collectors.toList());
  }
}
