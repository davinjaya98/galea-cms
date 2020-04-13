package com.galea.cms.webservices.bean.pagesetting;

import java.util.List;

import com.galea.cms.webservices.bean.customdatagroup.CustomDataGroupBean;

public class PageSettingBean {

    // For Response
    private Integer settingId;

    private String pageTitle;
    private String pageDescription;
    private String pageSeoKeywords;
    private String pageKey;
    private String pageSequence;

    // For Response
    private List<CustomDataGroupBean> customDataGroupList;

    public PageSettingBean() {
    }

    public PageSettingBean(Integer settingId, String pageTitle, String pageDescription, String pageSeoKeywords,
            String pageKey, List<CustomDataGroupBean> customDataGroupList, String pageSequence) {
        this.settingId = settingId;
        this.pageTitle = pageTitle;
        this.pageDescription = pageDescription;
        this.pageSeoKeywords = pageSeoKeywords;
        this.pageKey = pageKey;
        this.customDataGroupList = customDataGroupList;
        this.pageSequence = pageSequence;
    }

    public Integer getSettingId() {
        return this.settingId;
    }

    public void setSettingId(Integer settingId) {
        this.settingId = settingId;
    }

    public String getPageTitle() {
        return this.pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPageDescription() {
        return this.pageDescription;
    }

    public void setPageDescription(String pageDescription) {
        this.pageDescription = pageDescription;
    }

    public String getPageSeoKeywords() {
        return this.pageSeoKeywords;
    }

    public void setPageSeoKeywords(String pageSeoKeywords) {
        this.pageSeoKeywords = pageSeoKeywords;
    }

    public String getPageKey() {
        return this.pageKey;
    }

    public void setPageKey(String pageKey) {
        this.pageKey = pageKey;
    }

    public List<CustomDataGroupBean> getCustomDataGroupList() {
        return this.customDataGroupList;
    }

    public void setCustomDataGroupList(List<CustomDataGroupBean> customDataGroupList) {
        this.customDataGroupList = customDataGroupList;
    }

    public String getPageSequence() {
        return this.pageSequence;
    }

    public void setPageSequence(String pageSequence) {
        this.pageSequence = pageSequence;
    }
}