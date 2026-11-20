package com.comento.oracleSpringBoot.dto.curtain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Expired {
	int blindN;
	Board board;

	public String getResourceUrl() {
		return "https://port-0-powerful-spring-boot-5o1j2llh1wq9rp.sel4.cloudtype.app/api/curtain/img/" + blindN;
	}
}
