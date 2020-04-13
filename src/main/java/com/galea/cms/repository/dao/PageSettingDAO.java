package com.galea.cms.repository.dao;

import java.util.List;

import com.galea.cms.repository.entity.PageSetting;

public interface PageSettingDAO {
	
	public void save(PageSetting entity);
    //This can be used to update status to active, deactivated, or deleted
	public void update(PageSetting entity);
    //This one effectively removed it from db
	public void delete(PageSetting entity);

	public List<PageSetting> getAllPageSetting();
	public PageSetting getPageSettingById(Integer id, Boolean searchActive);
	public PageSetting getPageSettingByKey(String key, Boolean searchActive);
}