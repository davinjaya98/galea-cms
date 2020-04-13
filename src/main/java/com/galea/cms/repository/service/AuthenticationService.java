package com.galea.cms.repository.service;

import com.galea.cms.webservices.bean.GeneralWsResponseBean;

public interface AuthenticationService {

    public GeneralWsResponseBean authenticate(String username, String password, String token);
    public String getLatestToken();
}