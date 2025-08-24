package com.comento.oracleSpringBoot.rest;

import javax.servlet.http.HttpSession;

public class RestApi {
    void requester(HttpSession s) {
        if(s.getAttribute("sn") == null) throw new Authentication();
    }
}
