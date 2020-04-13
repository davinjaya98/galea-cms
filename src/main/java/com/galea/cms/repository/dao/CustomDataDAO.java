package com.galea.cms.repository.dao;

import java.util.List;

import com.galea.cms.repository.entity.CustomData;

public interface CustomDataDAO {

	public void save(CustomData entity);

	// This can be used to update status to active, deactivated, or deleted
	public void update(CustomData entity);

	// This one effectively removed it from db
	public void delete(CustomData entity);

	public List<CustomData> getAllCustomData();

	public List<CustomData> getCustomDataListByCdGroupId(Integer id);

	public CustomData getCustomDataById(Integer id, Boolean searchActive);

	public CustomData getCustomDataByKey(String key, Boolean searchActive);
}