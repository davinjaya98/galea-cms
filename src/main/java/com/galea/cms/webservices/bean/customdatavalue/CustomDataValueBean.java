package com.galea.cms.webservices.bean.customdatavalue;

public class CustomDataValueBean {

    //For response
    private Integer cdValueId;
    
    private String cdValueKey;
    //Get as base64
    private String cdValue;
    
    private Integer cdsId;

    public CustomDataValueBean() {
    }

    public CustomDataValueBean(Integer cdValueId, String cdValueKey, String cdValue, Integer cdsId) {
        this.cdValueId = cdValueId;
        this.cdValueKey = cdValueKey;
        this.cdValue = cdValue;
        this.cdsId = cdsId;
    }

    public Integer getCdValueId() {
        return this.cdValueId;
    }

    public void setCdValueId(Integer cdValueId) {
        this.cdValueId = cdValueId;
    }

    public String getCdValueKey() {
        return this.cdValueKey;
    }

    public void setCdValueKey(String cdValueKey) {
        this.cdValueKey = cdValueKey;
    }

    public String getCdValue() {
        return this.cdValue;
    }

    public void setCdValue(String cdValue) {
        this.cdValue = cdValue;
    }

    public Integer getCdsId() {
        return this.cdsId;
    }

    public void setCdsId(Integer cdsId) {
        this.cdsId = cdsId;
    }
}