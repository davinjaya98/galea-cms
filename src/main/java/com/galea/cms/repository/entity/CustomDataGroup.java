package com.galea.cms.repository.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "custom_data_group", catalog = "galea_db")
public class CustomDataGroup extends GeneralCreateModify implements Serializable {

    private int cdGroupId;
    private String cdGroupName;
    private String cdGroupDescription;
    private List<CustomData> customDataList = new ArrayList<CustomData>();
    private PageSetting pageSetting;
    private String cdGroupSequence;
    private String cdGroupImage;

    public CustomDataGroup() {
    }

    public CustomDataGroup(int cdGroupId, String cdGroupName, String cdGroupDescription,
            List<CustomData> customDataList, PageSetting pageSetting, String cdGroupSequence, String cdGroupImage) {
        this.cdGroupId = cdGroupId;
        this.cdGroupName = cdGroupName;
        this.cdGroupDescription = cdGroupDescription;
        this.customDataList = customDataList;
        this.pageSetting = pageSetting;
        this.cdGroupSequence = cdGroupSequence;
        this.cdGroupImage = cdGroupImage;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "cd_group_id", unique = true, nullable = false)
    public int getCdGroupId() {
        return this.cdGroupId;
    }

    public void setCdGroupId(int cdGroupId) {
        this.cdGroupId = cdGroupId;
    }

    @Column(name = "cd_group_name")
    public String getCdGroupName() {
        return this.cdGroupName;
    }

    public void setCdGroupName(String cdGroupName) {
        this.cdGroupName = cdGroupName;
    }

    @Column(name = "cd_group_description")
    public String getCdGroupDescription() {
        return this.cdGroupDescription;
    }

    public void setCdGroupDescription(String cdGroupDescription) {
        this.cdGroupDescription = cdGroupDescription;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customDataGroup")
    public List<CustomData> getCustomDataList() {
        return this.customDataList;
    }

    public void setCustomDataList(List<CustomData> customDataList) {
        this.customDataList = customDataList;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "setting_id")
    public PageSetting getPageSetting() {
        return this.pageSetting;
    }

    public void setPageSetting(PageSetting pageSetting) {
        this.pageSetting = pageSetting;
    }

    @Column(name = "cd_group_sequence")
    public String getCdGroupSequence() {
        return this.cdGroupSequence;
    }

    public void setCdGroupSequence(String cdGroupSequence) {
        this.cdGroupSequence = cdGroupSequence;
    }

    @Column(name = "cd_group_image")
    public String getCdGroupImage() {
        return this.cdGroupImage;
    }

    public void setCdGroupImage(String cdGroupImage) {
        this.cdGroupImage = cdGroupImage;
    }
}
