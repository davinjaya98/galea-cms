package com.galea.cms.repository.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "custom_data_value", catalog = "segosarem_db")
public class CustomDataValue extends GeneralCreateModify implements Serializable {

	private int cdValueId;
    //This is in byte[]
    private byte[] cdValue;
    //0 - Multifield parent
    private Integer cdValueLevel;

    private CustomData customData;
    
    //Recursive for Multifield Type
    private CustomDataValue parentValue;
    private List<CustomDataValue> childValueList = new ArrayList<CustomDataValue>();

    private CustomDataSetting customDataSetting;

    public CustomDataValue() {
    }

    public CustomDataValue(int cdValueId, byte[] cdValue, Integer cdValueLevel, CustomData customData) {
        this.cdValueId = cdValueId;
        this.cdValue = cdValue;
        this.cdValueLevel = cdValueLevel;
        this.customData = customData;
    }

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "cd_value_id", unique = true, nullable = false)
    public int getCdValueId() {
        return this.cdValueId;
    }

    public void setCdValueId(int cdValueId) {
        this.cdValueId = cdValueId;
    }

    @Column(name = "cd_value")
    public byte[] getCdValue() {
        return this.cdValue;
    }

    public void setCdValue(byte[] cdValue) {
        this.cdValue = cdValue;
    }

	@Column(name = "cd_value_level")
    public Integer getCdValueLevel() {
        return this.cdValueLevel;
    }

    public void setCdValueLevel(Integer cdValueLevel) {
        this.cdValueLevel = cdValueLevel;
    }
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_id")
    public CustomData getCustomData() {
        return this.customData;
    }

    public void setCustomData(CustomData customData) {
        this.customData = customData;
    }

    //Recursive for Multifield Type
	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name = "parent_id")
    public CustomDataValue getParentValue() {
        return this.parentValue;
    }

    public void setParentValue(CustomDataValue parentValue) {
        this.parentValue = parentValue;
    }

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parentValue")
    public List<CustomDataValue> getChildValueList() {
        return this.childValueList;
    }

    public void setChildValueList(List<CustomDataValue> childValueList) {
        this.childValueList = childValueList;
    }
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cds_id")
    public CustomDataSetting getCustomDataSetting() {
        return this.customDataSetting;
    }

    public void setCustomDataSetting(CustomDataSetting customDataSetting) {
        this.customDataSetting = customDataSetting;
    }
}