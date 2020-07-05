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
import com.galea.cms.repository.dao.CustomDataGroupDAO;
import com.galea.cms.repository.dao.CustomDataDAO;
import com.galea.cms.repository.dao.CustomDataValueDAO;
import com.galea.cms.repository.dao.PageSettingDAO;
import com.galea.cms.repository.entity.CustomData;
import com.galea.cms.repository.entity.CustomDataGroup;
import com.galea.cms.repository.entity.CustomDataValue;
import com.galea.cms.repository.entity.PageSetting;
import com.galea.cms.repository.service.CommonServiceUtils;
import com.galea.cms.repository.service.CustomDataGroupService;
import com.galea.cms.webservices.bean.DeleteEntityReqBean;
//Bean
import com.galea.cms.webservices.bean.GeneralWsResponseBean;
import com.galea.cms.webservices.bean.customdatagroup.CustomDataGroupBean;

@Transactional
@Service("CustomDataGroupService")
public class CustomDataGroupServiceImpl implements CustomDataGroupService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomDataGroupDAO customDataGroupDAO;

    @Autowired
    private PageSettingDAO pageSettingDAO;

    @Override
    public GeneralWsResponseBean getAllCustomDataGroup() {
        GeneralWsResponseBean responseBean = CommonServiceUtils.generateResponseBean();
        try {
            List<CustomDataGroup> entityList = customDataGroupDAO.getAllCustomDataGroup();

            if (entityList != null && !entityList.isEmpty()) {
                List<CustomDataGroupBean> beanList = new ArrayList<CustomDataGroupBean>();
                for (CustomDataGroup entity : entityList) {
                    CustomDataGroupBean bean = new DozerBeanMapper().map(entity, CustomDataGroupBean.class);

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
    public GeneralWsResponseBean getCdGroupByPageStgId(Integer id) {
        GeneralWsResponseBean responseBean = CommonServiceUtils.generateResponseBean();
        try {
            List<CustomDataGroup> entityList = customDataGroupDAO.getCdGroupByPageStdId(id);

            if (entityList != null && !entityList.isEmpty()) {
                List<CustomDataGroupBean> beanList = new ArrayList<CustomDataGroupBean>();
                for (CustomDataGroup entity : entityList) {
                    CustomDataGroupBean bean = new DozerBeanMapper().map(entity, CustomDataGroupBean.class);
                    bean.setPageSettingId(entity.getPageSetting().getSettingId());
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
    public GeneralWsResponseBean getCdGroupByPageStgKey(String key) {
        GeneralWsResponseBean responseBean = CommonServiceUtils.generateResponseBean();
        try {
            List<CustomDataGroup> entityList = customDataGroupDAO.getCdGroupByPageStgKey(key);

            if (entityList != null && !entityList.isEmpty()) {
                List<CustomDataGroupBean> beanList = new ArrayList<CustomDataGroupBean>();
                for (CustomDataGroup entity : entityList) {
                    // CustomDataGroupBean bean = new DozerBeanMapper().map(entity,
                    // CustomDataGroupBean.class);
                    CustomDataGroupBean bean = new CustomDataGroupBean();
                    bean.setCdGroupId(entity.getCdGroupId());
                    bean.setCdGroupName(entity.getCdGroupName());
                    bean.setCdGroupSequence(entity.getCdGroupSequence());
                    bean.setCdGroupImage(entity.getCdGroupImage());
                    bean.setCdGroupDescription(entity.getCdGroupDescription());
                    // bean.setPageSettingId(entity.getPageSetting().getSettingId());
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
    public GeneralWsResponseBean getCustomDataGroupById(Integer id) {
        GeneralWsResponseBean responseBean = CommonServiceUtils.generateResponseBean();
        try {
            CustomDataGroup entity = customDataGroupDAO.getCustomDataGroupById(id, true);

            if (entity != null) {
                CustomDataGroupBean bean = new DozerBeanMapper().map(entity, CustomDataGroupBean.class);

                responseBean.setResponseObject(bean);
                responseBean = CommonServiceUtils.setResponseToSuccess(responseBean);
            }

        } catch (Exception e) {
            responseBean.setResponseObject(e.getMessage());
        }

        return responseBean;
    }

    @Override
    public GeneralWsResponseBean addCustomDataGroup(CustomDataGroupBean requestBean) {
        GeneralWsResponseBean responseBean = CommonServiceUtils.generateResponseBean();

        try {
            // Get the page setting first
            PageSetting pageSetting = pageSettingDAO.getPageSettingById(requestBean.getPageSettingId(), true);

            if (pageSetting != null) {
                CustomDataGroup newCustomDataGroupEntity = new DozerBeanMapper().map(requestBean,
                        CustomDataGroup.class);
                newCustomDataGroupEntity.setPageSetting(pageSetting);

                newCustomDataGroupEntity.setCreateDt(new Date());
                newCustomDataGroupEntity.setStatus(SystemConstant.ACTIVE);

                // Add the new custom data group
                customDataGroupDAO.save(newCustomDataGroupEntity);

                responseBean = CommonServiceUtils.setResponseToSuccess(responseBean);
            }
        } catch (Exception e) {
            responseBean.setResponseObject(e);
        }

        return responseBean;
    }

    @Override
    public GeneralWsResponseBean updateCustomDataGroup(CustomDataGroupBean requestBean) {
        GeneralWsResponseBean responseBean = CommonServiceUtils.generateResponseBean();
        try {
            CustomDataGroup entity = customDataGroupDAO.getCustomDataGroupById(requestBean.getCdGroupId(), false);

            if (entity != null) {
                // update custom data group
                entity.setCdGroupName(requestBean.getCdGroupName());
                entity.setCdGroupDescription(requestBean.getCdGroupDescription());
                entity.setCdGroupSequence(requestBean.getCdGroupSequence());
                entity.setCdGroupImage(requestBean.getCdGroupImage());
                entity.setModifyDt(new Date());

                customDataGroupDAO.update(entity);

                responseBean = CommonServiceUtils.setResponseToSuccess(responseBean);
            }
        } catch (Exception e) {
            responseBean.setResponseObject(e.getMessage());
        }

        return responseBean;
    }

    @Override
    public GeneralWsResponseBean deleteCustomDataGroup(DeleteEntityReqBean requestBean) {
        GeneralWsResponseBean responseBean = CommonServiceUtils.generateResponseBean();
        try {
            CustomDataGroup entity = customDataGroupDAO.getCustomDataGroupById(requestBean.getEntityId(), false);

            if (entity != null) {
                customDataGroupDAO.delete(entity);

                responseBean = CommonServiceUtils.setResponseToSuccess(responseBean);
            }
        } catch (Exception e) {
            responseBean.setResponseObject(e.getMessage());
        }

        return responseBean;
    }

    @Override
    public GeneralWsResponseBean getAllValueByCdGroupId(Integer id) {
        GeneralWsResponseBean responseBean = CommonServiceUtils.generateResponseBean();
        try {
            Map<String, Object> responseObj = new LinkedHashMap<String, Object>();
            Map<String, Object> pageSettingValue = new LinkedHashMap<String, Object>();

            // Get custom data group
            CustomDataGroup customDataGroup = customDataGroupDAO.getCustomDataGroupById(id, false);
            if (customDataGroup != null) {
                // Set the cd group first
                Map<String, Object> customDataGroupObject = new LinkedHashMap<String, Object>();
                customDataGroupObject.put("name", customDataGroup.getCdGroupName());
                customDataGroupObject.put("description", customDataGroup.getCdGroupDescription());
                customDataGroupObject.put("image", customDataGroup.getCdGroupImage());

                responseObj.put("cdGroup", customDataGroupObject);

                // Get custom data
                List<CustomData> customDataList = customDataGroup.getCustomDataList();

                if (customDataList != null && !customDataList.isEmpty()) {
                    for (CustomData customData : customDataList) {

                        List<CustomDataValue> parentValueList = customData.getCdValueList();

                        List<Map<String, Object>> valueMap = new ArrayList<Map<String, Object>>();

                        if (parentValueList != null && !parentValueList.isEmpty()) {
                            for (CustomDataValue parentValue : parentValueList) {
                                List<CustomDataValue> childValueList = parentValue.getChildValueList();

                                Map<String, Object> childMap = new LinkedHashMap<String, Object>();
                                for (CustomDataValue childValue : childValueList) {
                                    childMap.put(childValue.getCustomDataSetting().getCdsKey(),
                                            CommonServiceUtils.parseValue(childValue.getCdValue(),
                                                    childValue.getCustomDataSetting().getCdsType()));
                                }
                                valueMap.add(childMap);
                            }
                        }
                        pageSettingValue.put(customData.getCdKey(), valueMap);
                    }
                }
            }

            responseObj.put("cdGroupValue", pageSettingValue);

            responseBean.setResponseObject(responseObj);
            responseBean = CommonServiceUtils.setResponseToSuccess(responseBean);

        } catch (Exception e) {
            responseBean.setResponseObject(e.getMessage());
        }

        return responseBean;
    }
}