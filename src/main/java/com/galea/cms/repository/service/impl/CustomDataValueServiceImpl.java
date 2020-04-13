package com.galea.cms.repository.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.galea.cms.repository.dao.CustomDataSettingDAO;
import com.galea.cms.repository.dao.CustomDataValueDAO;
import com.galea.cms.repository.entity.CustomData;
import com.galea.cms.repository.entity.CustomDataSetting;
import com.galea.cms.repository.entity.CustomDataValue;
import com.galea.cms.repository.service.CommonServiceUtils;
import com.galea.cms.repository.service.CustomDataValueService;
import com.galea.cms.webservices.bean.DeleteEntityReqBean;
//Bean
import com.galea.cms.webservices.bean.GeneralWsResponseBean;
import com.galea.cms.webservices.bean.customdatavalue.AddValueWrapperBean;
import com.galea.cms.webservices.bean.customdatavalue.CustomDataValueBean;

@Transactional
@Service("CustomDataValueService")
public class CustomDataValueServiceImpl implements CustomDataValueService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomDataDAO customDataDAO;

    @Autowired
    private CustomDataSettingDAO customDataSettingDAO;

    @Autowired
    private CustomDataValueDAO customDataValueDAO;

    @Override
    public GeneralWsResponseBean getAllCustomDataValue() {
        GeneralWsResponseBean responseBean = CommonServiceUtils.generateResponseBean();
        try {
            List<CustomDataValue> entityList = customDataValueDAO.getAllCustomDataValue();

            if (entityList != null && !entityList.isEmpty()) {
                List<CustomDataValueBean> beanList = new ArrayList<CustomDataValueBean>();
                for (CustomDataValue entity : entityList) {
                    CustomDataValueBean bean = new DozerBeanMapper().map(entity, CustomDataValueBean.class);

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
    public GeneralWsResponseBean getCustomDataValueById(Integer id) {
        GeneralWsResponseBean responseBean = CommonServiceUtils.generateResponseBean();
        try {
            CustomDataValue entity = customDataValueDAO.getCustomDataValueById(id, true);

            if (entity != null) {
                CustomDataValueBean bean = new DozerBeanMapper().map(entity, CustomDataValueBean.class);

                responseBean.setResponseObject(bean);
                responseBean = CommonServiceUtils.setResponseToSuccess(responseBean);
            }

        } catch (Exception e) {
            responseBean.setResponseObject(e.getMessage());
        }

        return responseBean;
    }

    private void addCustomDataValue(CustomDataValueBean valueBean, CustomDataValue firstLevelEntity) {
        CustomDataSetting settingEntity = customDataSettingDAO.getCustomDataSettingById(valueBean.getCdsId(), true);
        if (settingEntity != null) {
            CustomDataValue secondLevelEntity = new CustomDataValue();
            secondLevelEntity.setCdValueLevel(1);
            secondLevelEntity.setParentValue(firstLevelEntity);
            secondLevelEntity.setCustomDataSetting(settingEntity);
            secondLevelEntity.setCdValue(
                    CommonServiceUtils.parseIntoDbValue(valueBean.getCdValue(), settingEntity.getCdsType()));

            secondLevelEntity.setCreateDt(new Date());
            secondLevelEntity.setStatus(SystemConstant.ACTIVE);

            customDataValueDAO.persist(secondLevelEntity);
        }
    }

    private void updateCustomDataValue(CustomDataValueBean valueBean, CustomDataValue firstLevelEntity) {
        for(CustomDataValue childEntity : firstLevelEntity.getChildValueList()) {
            if(valueBean.getCdsId() == childEntity.getCustomDataSetting().getCdsId()) {
                CustomDataSetting settingEntity = childEntity.getCustomDataSetting();
                if (settingEntity != null) {
                    CustomDataValue secondLevelEntity = childEntity;
                    secondLevelEntity.setCdValue(
                            CommonServiceUtils.parseIntoDbValue(valueBean.getCdValue(), settingEntity.getCdsType()));
        
                    secondLevelEntity.setModifyDt(new Date());
        
                    customDataValueDAO.update(secondLevelEntity);
                }
            }
        }
    }

    @Override
    public GeneralWsResponseBean addOrUpdateCustomDataValue(AddValueWrapperBean requestBean) {
        GeneralWsResponseBean responseBean = CommonServiceUtils.generateResponseBean();
        try {
            CustomData dataEntity = customDataDAO.getCustomDataById(requestBean.getCdId(), true);

            if (dataEntity != null) {
                CustomDataValue firstLevelEntity = null;
                List<String> existingKeyList = new ArrayList<String>();

                //Differentiate between add or update setting first
                //Update setting will have the parentId of the custom value
                if(requestBean.getParentId() != null && requestBean.getParentId() != 0) {
                    //Try to get the first level
                    if(dataEntity.getCdValueList() != null && !dataEntity.getCdValueList().isEmpty()) {
                        for(CustomDataValue parentValue : dataEntity.getCdValueList()) {
                            if(parentValue.getCdValueId() == requestBean.getParentId()) {
                                firstLevelEntity = parentValue;

                                for(CustomDataValue childValue : parentValue.getChildValueList()) {
                                    existingKeyList.add(childValue.getCustomDataSetting().getCdsKey());
                                }
                            }
                        }
                    }
                }

                if(firstLevelEntity == null) {
                    //Add new 
                    // Create the first level first
                    firstLevelEntity = new CustomDataValue();
                    firstLevelEntity.setCdValueLevel(0);
                    firstLevelEntity.setCustomData(dataEntity);

                    firstLevelEntity.setCreateDt(new Date());
                    firstLevelEntity.setStatus(SystemConstant.ACTIVE);

                    //Then create the child entity
                    for (CustomDataValueBean newValue : requestBean.getValueBeans()) {
                        addCustomDataValue(newValue, firstLevelEntity);
                    }
                }
                else {
                    //Try to update or create new value
                    for (CustomDataValueBean newValue : requestBean.getValueBeans()) {
                        if(existingKeyList.contains(newValue.getCdValueKey())) {
                            //Update
                            updateCustomDataValue(newValue, firstLevelEntity);
                        }
                        else {
                            addCustomDataValue(newValue, firstLevelEntity);
                        }
                    }
                }
                

                responseBean = CommonServiceUtils.setResponseToSuccess(responseBean);
            }
        } catch (Exception e) {
            responseBean.setResponseObject(e.getMessage());
        }

        return responseBean;
    }

    @Override
    public GeneralWsResponseBean updateCustomDataValue(CustomDataValueBean requestBean) {
        GeneralWsResponseBean responseBean = CommonServiceUtils.generateResponseBean();
        try {
            CustomDataValue entity = customDataValueDAO.getCustomDataValueById(requestBean.getCdValueId(), false);

            if (entity != null) {
                // entity = new DozerBeanMapper().map(requestBean, CustomDataValue.class);
                // entity.setCdValue(requestBean.getCdValue());
                // entity.setCustomData(requestBean.getCustomData());
                // entity.setParentValue(requestBean.getParentValue());
                // entity.setChildValueList(requestBean.getChildValueList());
                entity.setModifyDt(new Date());

                customDataValueDAO.update(entity);

                responseBean = CommonServiceUtils.setResponseToSuccess(responseBean);
            }
        } catch (Exception e) {
            responseBean.setResponseObject(e.getMessage());
        }

        return responseBean;
    }

    @Override
    public GeneralWsResponseBean deleteCustomDataValue(DeleteEntityReqBean requestBean) {
        GeneralWsResponseBean responseBean = CommonServiceUtils.generateResponseBean();
        try {
            CustomDataValue entity = customDataValueDAO.getCustomDataValueById(requestBean.getEntityId(), false);

            if (entity != null) {
                List<CustomDataValue> childValueList = entity.getChildValueList();

                if(childValueList != null && !childValueList.isEmpty()) {
                    //Delete children first
                    for(CustomDataValue childValue : childValueList) {
                        customDataValueDAO.delete(childValue);
                    }
                }

                //Then delete own entity
                customDataValueDAO.delete(entity);

                responseBean = CommonServiceUtils.setResponseToSuccess(responseBean);
            }
        } catch (Exception e) {
            responseBean.setResponseObject(e.getMessage());
        }

        return responseBean;
    }
}