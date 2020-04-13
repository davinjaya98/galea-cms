package com.galea.cms.repository.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "custom_data_setting", catalog = "segosarem_db")
public class CustomDataSetting extends GeneralCreateModify implements Serializable {

    private int cdsId;
    private String cdsName;
    private String cdsKey;
    // This is for type
    // Can be implemented this way
    // 1 - Textfield / Path Field (For Images)
    // 2 - Boolean
    // 3 - Blob (For Images)
    private Integer cdsType;
    // This is for data presentation sequence
    private String cdsSequence;

    private CustomData customData;

    public CustomDataSetting() {
    }

    public CustomDataSetting(int cdsId, String cdsName, String cdsKey, Integer cdsType, String cdsSequence, CustomData customData) {
        this.cdsId = cdsId;
        this.cdsName = cdsName;
        this.cdsKey = cdsKey;
        this.cdsType = cdsType;
        this.cdsSequence = cdsSequence;
        this.customData = customData;
    }

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "cds_id", unique = true, nullable = false)
    public int getCdsId() {
        return this.cdsId;
    }

    public void setCdsId(int cdsId) {
        this.cdsId = cdsId;
    }

	@Column(name = "cds_name")
    public String getCdsName() {
        return this.cdsName;
    }

    public void setCdsName(String cdsName) {
        this.cdsName = cdsName;
    }

	@Column(name = "cds_key")
    public String getCdsKey() {
        return this.cdsKey;
    }

    public void setCdsKey(String cdsKey) {
        this.cdsKey = cdsKey;
    }

	@Column(name = "cds_type")
    public Integer getCdsType() {
        return this.cdsType;
    }

    public void setCdsType(Integer cdsType) {
        this.cdsType = cdsType;
    }

	@Column(name = "cds_sequence")
    public String getCdsSequence() {
        return this.cdsSequence;
    }

    public void setCdsSequence(String cdsSequence) {
        this.cdsSequence = cdsSequence;
    }
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_id")
    public CustomData getCustomData() {
        return this.customData;
    }

    public void setCustomData(CustomData customData) {
        this.customData = customData;
    }
}