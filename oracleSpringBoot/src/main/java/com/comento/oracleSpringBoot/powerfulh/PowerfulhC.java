package com.comento.oracleSpringBoot.powerfulh;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.comento.oracleSpringBoot.plm.PlmException;
import com.comento.oracleSpringBoot.service.PlmService;
import org.apache.ibatis.binding.BindingException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comento.oracleSpringBoot.mapper.PowerfulMapper;
import com.comento.oracleSpringBoot.member.MemberS;
import com.comento.oracleSpringBoot.member.entity.LoginVo;
import com.comento.oracleSpringBoot.service.HeaderSetter;

import lombok.RequiredArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
@RequestMapping("powerful")
@RequiredArgsConstructor
public class PowerfulhC {
	final MemberS service;
	final PowerfulMapper mapper;
	final HeaderSetter headerSetter;
	final PlmService plmService;
	
	@PostMapping("authenticate")
	public boolean authenticate(@Valid @RequestBody LoginVo lvo, @ApiIgnore HttpSession s, HttpServletResponse res) {
		try {			
			final int n = service.selectPk(lvo);
			s.setAttribute("sn", n);
		} catch(BindingException e) {
			return false;
		}
		headerSetter.setAuthentication(res);
		return true;
	}
	int requester(HttpSession s) {
		try {
			return (int) s.getAttribute("sn");			
		} catch(NullPointerException e) {
			throw new NoSessionNumber();
		}
	}
	@GetMapping("api")
	public List<Map<String, String>> getApi(@ApiIgnore HttpSession s) {
		return mapper.select(requester(s));
	}
	@PostMapping("api/{name}")
	public int postApi(@PathVariable String name, @ApiIgnore HttpSession s) {
		return mapper.insert(name, requester(s));
	}
	@PutMapping("api")
	public int putApi(@RequestBody @Valid PowerfulApi a, @ApiIgnore HttpSession s) {
		a.setOwner(requester(s));
		return mapper.update(a);
	}
	@GetMapping("fix/space")
	public Map<String, Object> getFixed(String pureSrc) {
        try {
            return plmService.fixSpace(pureSrc.replace(" ", ""));
        } catch (PlmException e) {
            return e.info;
        }
	}
	@GetMapping("fix/space/test")
	public List<Object> testGetFixed() {
		List<String> testP = new ArrayList<>();
		testP.add("오늘은날씨가좋아서오랜만에공원에산책을나갔다.");
		testP.add("프로젝트를시작하기전에필요한요구사항을먼저정리해두는것이좋다.");
		testP.add("예상보다문제가복잡해서원인을찾는데시간이좀더걸렸다.");
		testP.add("사용자가입력한값이올바른형식인지확인한후데이터베이스에저장해야한다.");
		testP.add("회의가끝난뒤에결정된사항을팀원들에게공유해주시기바랍니다.");
		testP.add("이기능은인터넷에연결되어있지않아도정상적으로사용할수있도록만들었다.");
		testP.add("오류가발생했을때로그를확인해보면문제의원인을파악하는데도움이된다.");
		testP.add("새로운기능을추가하기전에기존기능에영향을주는부분이없는지꼼꼼하게확인해야한다.");
		testP.add("생각보다많은사람들이이문제를비슷한방법으로해결하고있다는사실이흥미로웠다.");
		testP.add("테스트결과를확인해보니일부상황에서는예상했던것과다른결과가나타나는것을알수있었다.");
		testP.add("그때부터가시작이었어");
		testP.add("여기서보기엔집에서밖에안쓴다"); // 오른쪽 결합을 파헤치면 붙는데 안 파헤치면 띄워지는 캐이스
		return testP.stream().map(item -> plmService.fixSpace(item).get("result")).collect(Collectors.toList());
	}
}
