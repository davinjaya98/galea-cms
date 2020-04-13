package com.galea.cms.webservices.bean.customdatavalue;

import java.util.List;

public class AddValueWrapperBean {
    
    private Integer cdId;
    private Integer parentId;
    private List<CustomDataValueBean> valueBeans;

    public AddValueWrapperBean() {
    }

    public AddValueWrapperBean(Integer cdId, Integer parentId, List<CustomDataValueBean> valueBeans) {
        this.cdId = cdId;
        this.parentId = parentId;
        this.valueBeans = valueBeans;
    }

    public Integer getCdId() {
        return this.cdId;
    }

    public void setCdId(Integer cdId) {
        this.cdId = cdId;
    }

    public Integer getParentId() {
        return this.parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<CustomDataValueBean> getValueBeans() {
        return this.valueBeans;
    }

    public void setValueBeans(List<CustomDataValueBean> valueBeans) {
        this.valueBeans = valueBeans;
    }

}