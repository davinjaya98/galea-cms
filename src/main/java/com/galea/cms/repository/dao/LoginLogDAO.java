package com.galea.cms.repository.dao;

import java.util.List;

import com.galea.cms.repository.entity.LoginLog;

public interface LoginLogDAO {
	
	public void save(LoginLog entity);

	public List<LoginLog> getAllLogSortedByLatest();
	public LoginLog getLatestLog();
}