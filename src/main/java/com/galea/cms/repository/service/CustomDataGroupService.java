package com.galea.cms.repository.service;

import com.galea.cms.webservices.bean.DeleteEntityReqBean;

import com.galea.cms.webservices.bean.GeneralWsResponseBean;

import com.galea.cms.webservices.bean.customdatagroup.CustomDataGroupBean;

public interface CustomDataGroupService {

    public GeneralWsResponseBean getAllCustomDataGroup();

    public GeneralWsResponseBean getCdGroupByPageStgId(Integer id);

    public GeneralWsResponseBean getCustomDataGroupById(Integer id);

    public GeneralWsResponseBean addCustomDataGroup(CustomDataGroupBean requestBean);

    // This can be used to update status to active, deactivated, or deleted
    public GeneralWsResponseBean updateCustomDataGroup(CustomDataGroupBean requestBean);

    // This one effectively removed it from db
    public GeneralWsResponseBean deleteCustomDataGroup(DeleteEntityReqBean requestBean);
}