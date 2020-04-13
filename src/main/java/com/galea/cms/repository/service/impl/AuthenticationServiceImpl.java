package com.galea.cms.repository.service.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Constant
import com.galea.cms.constant.SystemConstant;

//DB
import com.galea.cms.repository.dao.LoginLogDAO;
import com.galea.cms.repository.entity.LoginLog;
import com.galea.cms.repository.service.AuthenticationService;
import com.galea.cms.repository.service.CommonServiceUtils;
//Bean
import com.galea.cms.webservices.bean.GeneralWsResponseBean;

@Transactional
@Service("AuthenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private ResourceBundle labels = ResourceBundle.getBundle("messages");

    @Autowired
    private LoginLogDAO loginLogDAO;

    @Override
    public GeneralWsResponseBean authenticate(String usernameToAuthenticate, String passwordToAuthenticate, String token) {
        GeneralWsResponseBean responseBean = CommonServiceUtils.generateResponseBean();

        try{
            String username = labels.getString("authentication.username");
            String password = labels.getString("authentication.password");
            
            List<LoginLog> logsEntity = loginLogDAO.getAllLogSortedByLatest();

            Boolean allowLogin = CommonServiceUtils.checkAllowLogin(logsEntity);

            //Bypass if the user token from frontend is same with the latest token
            if(token != null && !token.isEmpty()) {
                String latestToken = getLatestValidToken();

                if(latestToken.equals(token)) {
                    allowLogin = true;
                }
            } 
    
            if(allowLogin) {
                if(username.equals(usernameToAuthenticate) && password.equals(passwordToAuthenticate)) {
                    LoginLog entity = new LoginLog();
                    entity.setCreateDt(new Date());
                    entity.setCreatedToken(generateToken());
                    entity.setStatus(SystemConstant.LOG_SUCCESS);
        
                    loginLogDAO.save(entity);

                    Map<String,Object> responseObject = new LinkedHashMap<String, Object>();
                    responseObject.put("token", entity.getCreatedToken());

                    responseBean.setResponseObject(responseObject);
                    responseBean = CommonServiceUtils.setResponseToSuccess(responseBean);
                }
                else {
                    LoginLog entity = new LoginLog();
                    entity.setCreateDt(new Date());
                    entity.setStatus(SystemConstant.LOG_FAILED);
        
                    loginLogDAO.save(entity);
                    responseBean.setReturnCode(SystemConstant.FAILED_AUTHENTICATION_FAILED);
                }
            }
            else {
                responseBean.setReturnCode(SystemConstant.FAILED_MAXIMUM_ATTEMPT);
            }
        }catch(Exception e) {
            responseBean.setResponseObject(e.getMessage());
        }

        return responseBean;
    }

    @Override
    public String getLatestToken() {
        // TODO Auto-generated method stub
        return getLatestValidToken();
    }

    private String getLatestValidToken() {
        LoginLog latestLog = loginLogDAO.getLatestLog();

        if(latestLog != null) {
            return latestLog.getCreatedToken();
        }
        else {
            return null;
        }
    }

    private String generateToken() {
        String uuid = UUID.randomUUID().toString().replace("-","");
        return uuid;
    }
}