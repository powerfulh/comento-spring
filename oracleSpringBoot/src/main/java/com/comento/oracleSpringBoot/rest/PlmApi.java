package com.comento.oracleSpringBoot.rest;

import java.util.List;
import java.util.Map;

import com.comento.oracleSpringBoot.dto.plm.Compound;
import org.springframework.web.bind.annotation.*;

import com.comento.oracleSpringBoot.dto.plm.Word;
import com.comento.oracleSpringBoot.mapper.PlmMapper;

import lombok.RequiredArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "https://powerfulh.github.io"}, allowCredentials = "true")
@RequestMapping("plm")
@RequiredArgsConstructor
public class PlmApi extends RestApi {
	final PlmMapper mapper;
	
	@GetMapping("word")
	public List<Word> getWord(String s) {
		return mapper.selectWord(s);
	}
	@PostMapping("word")
	public void postWord(@RequestBody @Valid Word dto, @ApiIgnore HttpSession s) {
        requester(s);
		mapper.insertWord(dto);
	}
    @GetMapping("compound/{n}")
    public List<Compound> getCompound(@PathVariable int n) {
        return mapper.selectCompounded(n);
    }
    @PostMapping("compound")
    public List<Compound> postCompound(@RequestBody @Valid Compound dto, @ApiIgnore HttpSession s) {
        requester(s);
        mapper.insertCompound(dto);
        return mapper.selectCompounded(dto.getLeftword());
    }
    @GetMapping("leftright/{n}")
    public List<Word> getLeftRight(@PathVariable int n) {
        return mapper.selectLeftRight(n);
    }
    @GetMapping("learn")
    public List<Map<String, Object>> getLearnList() {
        return mapper.selectLearn();
    }
    @DeleteMapping("learn/{n}")
    public Map<String, Object> deleteLearn(@PathVariable int n, @ApiIgnore HttpSession s) {
        requester(s);
        Map<String, Object> r = mapper.selectOneLearn(n);
        mapper.deleteLearn(n);
        mapper.deleteCanceledWord();
        return r;
    }
    @GetMapping("learn/{n}")
    public Map<String, Object> getLearn(@PathVariable int n) {
        return mapper.selectOneLearn(n);
    }
    @PostMapping("context")
    public Map<String, Object> postContext(@RequestBody Map<String, Integer> dto, @ApiIgnore HttpSession s) {
        requester(s);
        mapper.upsertContext(dto);
        return mapper.selectContext(dto);
    }
}
