package com.comento.oracleSpringBoot.service;

import com.comento.oracleSpringBoot.dto.curtain.Board;
import com.comento.oracleSpringBoot.dto.curtain.Expired;
import com.comento.oracleSpringBoot.mapper.CurtainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurtainService {
  final CurtainMapper mapper;

  public List<Expired> getDeniedImg(int limit) {
    List<Board> list = mapper.selectLatestImg(limit);
    return list.stream().filter(item -> {
      String url = item.getImg0url();
      RestTemplate template = new RestTemplate();
      try {
        ResponseEntity<String> res = template.getForEntity(url, String.class);
        return res.getStatusCodeValue() != 200;
      } catch (HttpClientErrorException e) {
		  item.setT(item.getT() + " [" + e.getStatusCode() + "]");
        return true;
      }
    }).map(item -> Expired.builder().board(item).blindN(item.getN()).build()).collect(Collectors.toList());
  }
}
