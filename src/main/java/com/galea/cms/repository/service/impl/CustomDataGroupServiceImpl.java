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
import com.galea.cms.repository.dao.CustomDataGroupDAO;
import com.galea.cms.repository.dao.CustomDataDAO;
import com.galea.cms.repository.dao.CustomDataValueDAO;
import com.galea.cms.repository.dao.PageSettingDAO;

import com.galea.cms.repository.entity.CustomDataGroup;
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
}