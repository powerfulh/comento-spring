package com.comento.oracleSpringBoot.rest;

import com.comento.oracleSpringBoot.dto.plm.*;
import com.comento.oracleSpringBoot.mapper.PlmMapper;
import com.comento.oracleSpringBoot.service.PlmHelp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "https://powerfulh.github.io"}, allowCredentials = "true")
@RequestMapping("plm")
@RequiredArgsConstructor
public class PlmApi extends RestApi {
	final PlmMapper mapper;
    final PlmHelp help;
	
	@GetMapping("word")
	public List<Word> getWord(String s) {
		return mapper.selectWord(s);
	}
	@PostMapping("word")
	public Suggestion postWord(@RequestBody @Valid Word dto, @ApiIgnore HttpSession s) {
        requester(s);
		mapper.insertWord(dto);
        final Word word = help.getJustPost(dto.getWord());
        final char last = word.getWord().charAt(word.getWord().length() - 1);
        if(word.getType().equals("0") && help.helpable(last) > 0) return new Suggestion(word, help.help(last).stream().map(HelpResult::getWord).collect(Collectors.toList()));
        return null;
	}
    @GetMapping("compound/{n}")
    public List<Compound> getCompound(@PathVariable int n) {
        return mapper.selectCompounded(n);
    }
    @PostMapping("compound")
    public List<Compound> postCompound(@RequestBody @Valid Compound dto, @ApiIgnore HttpSession s) {
        requester(s);
        mapper.insertCompound(dto);
        mapper.updateToCompound(dto.getWord());
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
        if(!mapper.selectContextList(n).isEmpty()) throw new IllegalStateException();
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
        mapper.upsertContextCnt(dto);
        return mapper.selectContext(dto);
    }
    @GetMapping("context/{n}")
    public List<Map<String, Object>> getContext(@PathVariable int n) {
        return mapper.selectContextList(n);
    }
    @GetMapping("understand/box")
    public List<UnderstandBox> getUnderstandBox() {
        return UnderstandBox.of(mapper.selectUnderstandBox());
    }
    @PutMapping("understand/box/{n}")
    public void putUnderstandBoxActivation(@PathVariable int n) {
    	mapper.deactivateUnderstandBox(n);
    }
    @PostMapping("context/space")
    public Map<String, Object> postContextSpace(@RequestBody Map<String, Integer> dto, @ApiIgnore HttpSession s) {
        requester(s);
        mapper.upsertContextSpace(dto);
        return mapper.selectContext(dto);
    }
    @PutMapping("word")
    public void putWord(@RequestBody @Valid Word dto, @ApiIgnore HttpSession s) {
        requester(s);
        mapper.updateWord(dto);
        mapper.deleteDefinedLearn();
    }
    @PostMapping("compound/{n}")
    public void post0Compound(@PathVariable int n, @ApiIgnore HttpSession s) {
        requester(s);
        final Word word = mapper.selectOneWord(n);
        help.help(word.getWord().charAt(word.getWord().length() - 1)).forEach(item -> {
            final String target = word.getWord().substring(0, word.getWord().length() - 1).concat(item.getWord());
            mapper.insertWordTypeCompound(target);
            mapper.insertCompound(Compound.of(help.getJustPost(target).getN(), word.getN(), item.getN()));
        });
    }
}
