package com.comento.oracleSpringBoot.dto.plm;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SrcBox {
    @NotBlank
    @Size(min = 1, max = 40)
    public String src;
}
