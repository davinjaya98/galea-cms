package com.galea.cms.repository.service;

import com.galea.cms.webservices.bean.DeleteEntityReqBean;

import com.galea.cms.webservices.bean.GeneralWsResponseBean;

import com.galea.cms.webservices.bean.pagesetting.PageSettingBean;

public interface PageSettingService {
    
    public GeneralWsResponseBean getAllPageSetting();
    public GeneralWsResponseBean getPageSettingById(Integer id);
    public GeneralWsResponseBean getPageSettingByKey(String key);
    public GeneralWsResponseBean getAllValueByPageSettingKey(String key);

    public GeneralWsResponseBean addPageSetting(PageSettingBean requestBean);
    //This can be used to update status to active, deactivated, or deleted
    public GeneralWsResponseBean updatePageSetting(PageSettingBean requestBean);
    //This one effectively removed it from db
    public GeneralWsResponseBean deletePageSetting(DeleteEntityReqBean requestBean);
}