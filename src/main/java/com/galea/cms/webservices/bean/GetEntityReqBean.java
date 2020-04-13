package com.galea.cms.webservices.bean;

public class GetEntityReqBean {
    
    //The id of the entity
    private Integer entityId;
    //The key of the entity
    private String entityKey;
    
    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }
    
    public Integer getEntityId() {
        return this.entityId;
    }
    
    public void setEntityKey(String entityKey) {
        this.entityKey = entityKey;
    }
    
    public String getEntityKey() {
        return this.entityKey;
    }
}