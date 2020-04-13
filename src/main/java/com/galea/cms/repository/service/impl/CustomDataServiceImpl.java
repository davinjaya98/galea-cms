package com.galea.cms.repository.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Constant
import com.galea.cms.constant.SystemConstant;

//DB
import com.galea.cms.repository.dao.CustomDataDAO;
import com.galea.cms.repository.dao.CustomDataGroupDAO;
import com.galea.cms.repository.dao.CustomDataValueDAO;
import com.galea.cms.repository.entity.CustomData;
import com.galea.cms.repository.entity.CustomDataGroup;
import com.galea.cms.repository.entity.CustomDataValue;
import com.galea.cms.repository.service.CommonServiceUtils;
import com.galea.cms.repository.service.CustomDataService;

import com.galea.cms.webservices.bean.DeleteEntityReqBean;

//Bean
import com.galea.cms.webservices.bean.GeneralWsResponseBean;
import com.galea.cms.webservices.bean.customdata.CustomDataBean;

@Transactional
@Service("CustomDataService")
public class CustomDataServiceImpl implements CustomDataService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomDataDAO customDataDAO;

    @Autowired
    private CustomDataGroupDAO customDataGroupDAO;

    @Override
    public GeneralWsResponseBean getAllCustomData() {
        GeneralWsResponseBean responseBean = CommonServiceUtils.generateResponseBean();
        try {
            List<CustomData> entityList = customDataDAO.getAllCustomData();

            if (entityList != null && !entityList.isEmpty()) {
                List<CustomDataBean> beanList = new ArrayList<CustomDataBean>();
                for (CustomData entity : entityList) {
                    CustomDataBean bean = new DozerBeanMapper().map(entity, CustomDataBean.class);

                    beanList.add(bean);
                }

                responseBean.setResponseObject(beanList);
                responseBean = CommonServiceUtils.setResponseToSuccess(responseBean);
            }

        } catch (Exception e) {
            responseBean.setResponseObject(e.getMessage());
        }

        return responseBean;
    }

    @Override
    public GeneralWsResponseBean getCustomDataListByCdGroupId(Integer id) {
        GeneralWsResponseBean responseBean = CommonServiceUtils.generateResponseBean();
        try {
            List<CustomData> entityList = customDataDAO.getCustomDataListByCdGroupId(id);

            if (entityList != null && !entityList.isEmpty()) {
                List<CustomDataBean> beanList = new ArrayList<CustomDataBean>();
                for (CustomData entity : entityList) {
                    CustomDataBean bean = new DozerBeanMapper().map(entity, CustomDataBean.class);
                    bean.setCdGroupId(entity.getCustomDataGroup().getCdGroupId());
                    beanList.add(bean);
                }

                responseBean.setResponseObject(beanList);
                responseBean = CommonServiceUtils.setResponseToSuccess(responseBean);
            }

        } catch (Exception e) {
            responseBean.setResponseObject(e.getMessage());
        }

        return responseBean;
    }

    @Override
    public GeneralWsResponseBean getCustomDataById(Integer id) {
        GeneralWsResponseBean responseBean = CommonServiceUtils.generateResponseBean();
        try {
            CustomData entity = customDataDAO.getCustomDataById(id, true);

            if (entity != null) {
                CustomDataBean bean = new DozerBeanMapper().map(entity, CustomDataBean.class);

                responseBean.setResponseObject(bean);
                responseBean = CommonServiceUtils.setResponseToSuccess(responseBean);
            }

        } catch (Exception e) {
            responseBean.setResponseObject(e.getMessage());
        }

        return responseBean;
    }

    @Override
    public GeneralWsResponseBean getCustomDataByKey(String key) {
        GeneralWsResponseBean responseBean = CommonServiceUtils.generateResponseBean();
        try {
            CustomData entity = customDataDAO.getCustomDataByKey(key, true);

            if (entity != null) {
                CustomDataBean bean = new DozerBeanMapper().map(entity, CustomDataBean.class);

                if (entity.getCdValueList() != null && !entity.getCdValueList().isEmpty()) {
                    List<Map<String, Object>> cdValuePairs = new ArrayList<Map<String, Object>>();

                    // Because only the parent 0 tagged into the CustomData Entity
                    for (CustomDataValue parentValue : entity.getCdValueList()) {
                        if (parentValue.getChildValueList() != null && !parentValue.getChildValueList().isEmpty()) {
                            Map<String, Object> cdValuePair = new LinkedHashMap<String, Object>();
                            cdValuePair.put("parentId", parentValue.getCdValueId());
                            
                            Map<String, Object> cdValueChildPair = new LinkedHashMap<String, Object>();
                            for (CustomDataValue childValue : parentValue.getChildValueList()) {
                                Map<String, Object> keyValuePair = new LinkedHashMap<String, Object>();
                                keyValuePair.put("fieldType", childValue.getCustomDataSetting().getCdsType());
                                keyValuePair.put("value", CommonServiceUtils.parseValue(childValue.getCdValue(), childValue.getCustomDataSetting().getCdsType()));

                                cdValueChildPair.put(childValue.getCustomDataSetting().getCdsKey(), keyValuePair);
                            }
                            cdValuePair.put("value", cdValueChildPair);

                            cdValuePairs.add(cdValuePair);
                        }
                    }

                    bean.setCdValuePair(cdValuePairs);
                }

                responseBean.setResponseObject(bean);
                responseBean = CommonServiceUtils.setResponseToSuccess(responseBean);
            }

        } catch (Exception e) {
            responseBean.setResponseObject(e.getMessage());
        }

        return responseBean;
    }

    @Override
    public GeneralWsResponseBean addCustomData(CustomDataBean requestBean) {
        GeneralWsResponseBean responseBean = CommonServiceUtils.generateResponseBean();
        try {
            CustomDataGroup customDataGroup = customDataGroupDAO.getCustomDataGroupById(requestBean.getCdGroupId(),
                    true);

            if (customDataGroup != null) {
                CustomData entity = new DozerBeanMapper().map(requestBean, CustomData.class);
                entity.setCustomDataGroup(customDataGroup);

                entity.setCreateDt(new Date());
                entity.setStatus(SystemConstant.ACTIVE);

                customDataDAO.save(entity);

                responseBean = CommonServiceUtils.setResponseToSuccess(responseBean);
            }
        } catch (Exception e) {
            responseBean.setResponseObject(e.getMessage());
        }

        return responseBean;
    }

    @Override
    public GeneralWsResponseBean updateCustomData(CustomDataBean requestBean) {
        GeneralWsResponseBean responseBean = CommonServiceUtils.generateResponseBean();
        try {
            CustomData entity = customDataDAO.getCustomDataById(requestBean.getCdId(), false);

            if (entity != null) {
                // update custom data
                entity.setCdName(requestBean.getCdName());
                if (requestBean.getCdType() != null) {
                    entity.setCdType(requestBean.getCdType());
                }
                entity.setCdSequence(requestBean.getCdSequence());
                // entity.setCustomDataGroup(requestBean.getCustomDataGroup());
                entity.setModifyDt(new Date());

                customDataDAO.update(entity);
                
                responseBean = CommonServiceUtils.setResponseToSuccess(responseBean);
            }
        } catch (Exception e) {
            responseBean.setResponseObject(e.getMessage());
        }

        return responseBean;
    }

    @Override
    public GeneralWsResponseBean deleteCustomData(DeleteEntityReqBean requestBean) {
        GeneralWsResponseBean responseBean = CommonServiceUtils.generateResponseBean();
        try {
            CustomData entity = customDataDAO.getCustomDataById(requestBean.getEntityId(), false);

            if (entity != null) {
                customDataDAO.delete(entity);

                responseBean = CommonServiceUtils.setResponseToSuccess(responseBean);
            }
        } catch (Exception e) {
            responseBean.setResponseObject(e.getMessage());
        }

        return responseBean;
    }
}