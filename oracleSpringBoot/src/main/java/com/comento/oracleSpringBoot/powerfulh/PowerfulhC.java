package com.comento.oracleSpringBoot.powerfulh;

import com.comento.oracleSpringBoot.mapper.PowerfulMapper;
import com.comento.oracleSpringBoot.member.MemberS;
import com.comento.oracleSpringBoot.member.entity.LoginVo;
import com.comento.oracleSpringBoot.plm.PlmException;
import com.comento.oracleSpringBoot.service.HeaderSetter;
import com.comento.oracleSpringBoot.service.PlmService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.binding.BindingException;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            return plmService.fixSpace(pureSrc);
        } catch (PlmException e) {
            return e.info;
        }
	}
	@GetMapping("fix/space/test")
	public List<String> testGetFixed() {
		List<String> testP = new ArrayList<>();
		testP.add("오늘은 날씨가 좋아서 오랜만에 공원에 산책을 나갔다.");
		testP.add("프로젝트를 시작하기 전에 필요한 요구사항을 먼저 정리해두는 것이 좋다.");
		testP.add("예상보다 문제가 복잡해서 원인을 찾는 데 시간이 좀 더 걸렸다.");
		testP.add("사용자가 입력한 값이 올바른 형식인지 확인한 후 데이터베이스에 저장해야 한다.");
		testP.add("회의가 끝난 뒤에 결정된 사항을 팀원들에게 공유해주시기 바랍니다.");
		testP.add("이 기능은 인터넷에 연결되어있지 않아도 정상적으로 사용할 수 있도록 만들었다.");
		testP.add("오류가 발생했을 때 로그를 확인해보면 문제의 원인을 파악하는데 도움이 된다.");
		testP.add("새로운 기능을 추가하기 전에 기존기능에 영향을 주는 부분이 없는지 꼼꼼하게 확인해야 한다.");
		testP.add("생각보다 많은 사람들이 이 문제를 비슷한 방법으로 해결하고 있다는 사실이 흥미로웠다.");
		testP.add("테스트 결과를 확인해보니 일부 상황에서는 예상했던 것과 다른 결과가 나타나는 것을 알수있었다.");
		testP.add("그때부터가 시작이었어");
		testP.add("여기서 보기엔 집에서 밖에 안 쓴다"); // 오른쪽 결합을 파헤치면 붙는데 안 파헤치면 띄워지는 캐이스
        final List<String> checkList = new ArrayList<>();
        for(String item: testP) {
            final String result = (String) plmService.fixSpace(item).get("result");
            if(!result.equals(item)) checkList.add(result);
        }
		return checkList;
	}
}
