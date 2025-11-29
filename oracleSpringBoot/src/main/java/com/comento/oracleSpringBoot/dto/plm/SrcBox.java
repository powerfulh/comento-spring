package com.comento.oracleSpringBoot.dto.plm;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SrcBox {
    @NotBlank
//    @Size(min = 1, max = 40) 입력 기준
    @Size(min = 1, max = 60) // 출력 기준
    public String src;
}
