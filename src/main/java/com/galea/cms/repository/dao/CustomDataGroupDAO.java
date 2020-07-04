package com.galea.cms.repository.dao;

import java.util.List;

import com.galea.cms.repository.entity.CustomDataGroup;

public interface CustomDataGroupDAO {

	public void save(CustomDataGroup entity);

	// This can be used to update status to active, deactivated, or deleted
	public void update(CustomDataGroup entity);

	// This one effectively removed it from db
	public void delete(CustomDataGroup entity);

	public List<CustomDataGroup> getAllCustomDataGroup();

	public List<CustomDataGroup> getCdGroupByPageStdId(Integer id);

	public CustomDataGroup getCustomDataGroupById(Integer id, Boolean searchActive);

	public List<CustomDataGroup> getCdGroupByPageStgKey(String key);
}