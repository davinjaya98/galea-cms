package com.galea.cms.webservices.bean;

public class ListEntityReqBean {
    
    private Integer limit;
    private Integer offset;
    
    public Integer getLimit() {
        return this.limit;
    }
    
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    
    public Integer getOffset() {
        return this.offset;
    }
    
    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}