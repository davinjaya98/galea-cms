package com.galea.cms.repository.service;

import com.galea.cms.webservices.bean.DeleteEntityReqBean;

import com.galea.cms.webservices.bean.GeneralWsResponseBean;
import com.galea.cms.webservices.bean.customdatavalue.AddValueWrapperBean;
import com.galea.cms.webservices.bean.customdatavalue.CustomDataValueBean;

public interface CustomDataValueService {
    
    public GeneralWsResponseBean getAllCustomDataValue();
    public GeneralWsResponseBean getCustomDataValueById(Integer id);

    public GeneralWsResponseBean addOrUpdateCustomDataValue(AddValueWrapperBean requestBean);
    //This can be used to update status to active, deactivated, or deleted
    public GeneralWsResponseBean updateCustomDataValue(CustomDataValueBean requestBean);
    //This one effectively removed it from db
    public GeneralWsResponseBean deleteCustomDataValue(DeleteEntityReqBean requestBean);
}