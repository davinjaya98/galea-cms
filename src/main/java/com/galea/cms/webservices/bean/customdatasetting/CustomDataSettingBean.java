package com.galea.cms.webservices.bean.customdatasetting;

public class CustomDataSettingBean {

    //For Response
    private Integer cdsId;

    private String cdsName;
    private String cdsKey;
    private Integer cdsType;
    // This is for data presentation sequence
    private String cdsSequence;

    private Integer cdId;

    public CustomDataSettingBean() {
    }

    public CustomDataSettingBean(Integer cdsId, String cdsName, String cdsKey, Integer cdsType, String cdsSequence, Integer cdId) {
        this.cdsId = cdsId;
        this.cdsName = cdsName;
        this.cdsKey = cdsKey;
        this.cdsType = cdsType;
        this.cdsSequence = cdsSequence;
        this.cdId = cdId;
    }

    public Integer getCdsId() {
        return this.cdsId;
    }

    public void setCdsId(Integer cdsId) {
        this.cdsId = cdsId;
    }

    public String getCdsName() {
        return this.cdsName;
    }

    public void setCdsName(String cdsName) {
        this.cdsName = cdsName;
    }

    public String getCdsKey() {
        return this.cdsKey;
    }

    public void setCdsKey(String cdsKey) {
        this.cdsKey = cdsKey;
    }

    public Integer getCdsType() {
        return this.cdsType;
    }

    public void setCdsType(Integer cdsType) {
        this.cdsType = cdsType;
    }

    public String getCdsSequence() {
        return this.cdsSequence;
    }

    public void setCdsSequence(String cdsSequence) {
        this.cdsSequence = cdsSequence;
    }

    public Integer getCdId() {
        return this.cdId;
    }

    public void setCdId(Integer cdId) {
        this.cdId = cdId;
    }
}