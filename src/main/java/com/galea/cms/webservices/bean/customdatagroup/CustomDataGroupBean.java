package com.galea.cms.webservices.bean.customdatagroup;

import java.util.List;

import com.galea.cms.webservices.bean.customdata.CustomDataBean;

public class CustomDataGroupBean {

    private Integer cdGroupId;
    private String cdGroupName;
    private String cdGroupDescription;

    private int pageSettingId;
    private String pageSettingKey;
    private String cdGroupSequence;
    private String cdGroupImage;

    // For Response
    private List<CustomDataBean> customDataList;

    public CustomDataGroupBean() {
    }

    public CustomDataGroupBean(Integer cdGroupId, String cdGroupName, String cdGroupDescription, int pageSettingId,
            List<CustomDataBean> customDataList, String cdGroupSequence, String cdGroupImage, String pageSettingKey) {
        this.cdGroupId = cdGroupId;
        this.cdGroupName = cdGroupName;
        this.cdGroupDescription = cdGroupDescription;
        this.pageSettingId = pageSettingId;
        this.customDataList = customDataList;
        this.cdGroupSequence = cdGroupSequence;
        this.cdGroupImage = cdGroupImage;
        this.pageSettingKey = pageSettingKey;
    }

    public Integer getCdGroupId() {
        return this.cdGroupId;
    }

    public void setCdGroupId(Integer cdGroupId) {
        this.cdGroupId = cdGroupId;
    }

    public String getCdGroupName() {
        return this.cdGroupName;
    }

    public void setCdGroupName(String cdGroupName) {
        this.cdGroupName = cdGroupName;
    }

    public String getCdGroupDescription() {
        return this.cdGroupDescription;
    }

    public void setCdGroupDescription(String cdGroupDescription) {
        this.cdGroupDescription = cdGroupDescription;
    }

    public int getPageSettingId() {
        return this.pageSettingId;
    }

    public void setPageSettingId(int pageSettingId) {
        this.pageSettingId = pageSettingId;
    }

    public List<CustomDataBean> getCustomDataList() {
        return this.customDataList;
    }

    public void setCustomDataList(List<CustomDataBean> customDataList) {
        this.customDataList = customDataList;
    }

    public String getCdGroupSequence() {
        return this.cdGroupSequence;
    }

    public void setCdGroupSequence(String cdGroupSequence) {
        this.cdGroupSequence = cdGroupSequence;
    }

    public String getCdGroupImage() {
        return this.cdGroupImage;
    }

    public void setCdGroupImage(String cdGroupImage) {
        this.cdGroupImage = cdGroupImage;
    }

    public String getPageSettingKey() {
        return this.pageSettingKey;
    }

    public void setPageSettingKey(String pageSettingKey) {
        this.pageSettingKey = pageSettingKey;
    }
}