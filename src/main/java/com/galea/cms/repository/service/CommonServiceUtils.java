package com.galea.cms.repository.service;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.galea.cms.constant.SystemConstant;
import com.galea.cms.webservices.bean.GeneralWsResponseBean;
import com.galea.cms.repository.entity.LoginLog;

//Common functions for services
public class CommonServiceUtils {
	private static ResourceBundle labels = ResourceBundle.getBundle("messages");

    // Generate initial response bean
    public static GeneralWsResponseBean generateResponseBean() {
        GeneralWsResponseBean obj = new GeneralWsResponseBean();
        obj.setReturnCode(SystemConstant.FAILED);

        return obj;
    }

    // Generate initial response bean
    public static GeneralWsResponseBean generateResponseBeanWithUnauthorizedStatus() {
        GeneralWsResponseBean obj = new GeneralWsResponseBean();
        obj.setReturnCode(SystemConstant.FAILED_AUTHENTICATION_FAILED);

        return obj;
    }

    // Set response bean to success
    public static GeneralWsResponseBean setResponseToSuccess(GeneralWsResponseBean responseBean) {
        responseBean.setReturnCode(SystemConstant.SUCCESS);

        return responseBean;
    }

    // Parse value to the correct type
    public static Object parseValue(byte[] value, Integer type) {
        Object parsedValue = null;

        switch (type) {
            case SystemConstant.CUSTOM_DATA_SETTING_TEXTFIELD:
                parsedValue = new String(value);
                break;
            case SystemConstant.CUSTOM_DATA_SETTING_BOOLEAN:
                parsedValue = new String(value);
                break;
            case SystemConstant.CUSTOM_DATA_SETTING_URL_PATH:
                parsedValue = new String(value);
                break;
            case SystemConstant.CUSTOM_DATA_SETTING_TEXTAREA:
                parsedValue = new String(value);
                break;
            case SystemConstant.CUSTOM_DATA_SETTING_IMAGE_BLOB:
                parsedValue = value;
                break;
            case SystemConstant.CUSTOM_DATA_SETTING_RICH_TEXT_WYSWYG:
                parsedValue = new String(value);
                break;
            case SystemConstant.CUSTOM_DATA_SETTING_URL_PATH_MULTIPLE:
                String tempParsedValue = new String(value);
                List<String> valueList = Arrays.asList(tempParsedValue.split(","));
                parsedValue = valueList;

                break;
        }

        return parsedValue;
    }

    // Parse value to the correct type
    public static byte[] parseIntoDbValue(String value, Integer type) {
        byte[] parsedValue = null;

        switch (type) {
            case SystemConstant.CUSTOM_DATA_SETTING_TEXTFIELD:
                parsedValue = value.getBytes();
                break;
            case SystemConstant.CUSTOM_DATA_SETTING_BOOLEAN:
                parsedValue = value.getBytes();
                break;
            case SystemConstant.CUSTOM_DATA_SETTING_URL_PATH:
                parsedValue = value.getBytes();
                break;
            case SystemConstant.CUSTOM_DATA_SETTING_TEXTAREA:
                //THIS IS ALREADY IN IMAGE PATH FORMAT
                parsedValue = value.getBytes();
                break;
            case SystemConstant.CUSTOM_DATA_SETTING_IMAGE_BLOB:
                //THIS SHOULD BE SAVING IMAGE INTO A FOLDER
                parsedValue = value.getBytes();
                break;
            case SystemConstant.CUSTOM_DATA_SETTING_RICH_TEXT_WYSWYG:
                parsedValue = value.getBytes();
                break;
            case SystemConstant.CUSTOM_DATA_SETTING_URL_PATH_MULTIPLE:
                parsedValue = value.getBytes();
                break;
        }

        return parsedValue;
    }

    public static Boolean checkAllowLogin(List<LoginLog> logsEntity) {
        Integer maxAttempt = Integer.valueOf(labels.getString("authentication.maxattempt"));
        
        Integer failedCounter = 0;
        Boolean allowLogin= true;
        for(LoginLog log : logsEntity) {
            if(log.getStatus().equals(SystemConstant.LOG_FAILED)) {
                failedCounter++;
            }
            else {
                //Stop if no failed login attempt
                break;
            }
            if(failedCounter > maxAttempt) {
                //Stop if more than login attempt
                allowLogin = false;
                break;
            }
        }

        return allowLogin;
    }
}