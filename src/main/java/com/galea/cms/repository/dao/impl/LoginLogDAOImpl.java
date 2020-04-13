package com.galea.cms.repository.dao.impl;

import java.util.List;

import com.galea.cms.constant.SystemConstant;
import com.galea.cms.repository.dao.LoginLogDAO;
import com.galea.cms.repository.entity.LoginLog;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginLogDAOImpl implements LoginLogDAO {

	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	public HibernateTemplate getHibernateTemplate() {
		HibernateTemplate hibernateTemplate = new HibernateTemplate(_sessionFactory);
		return hibernateTemplate;
	}

	@Override
	public void save(LoginLog entity) {

		getHibernateTemplate().persist(entity);
	}

	@Override
	public List<LoginLog> getAllLogSortedByLatest() {

		return (List<LoginLog>) getSession().createQuery("from LoginLog qt ORDER BY qt.createDt DESC").list();
	}

	@Override
	public LoginLog getLatestLog() {

		List<LoginLog> entityList = getSession().createQuery("from LoginLog qt  where qt.status= '"+SystemConstant.LOG_SUCCESS+"'  ORDER BY qt.createDt DESC").list();

		if(entityList != null && !entityList.isEmpty()) {
			return entityList.get(0);
		}
		
		return null;
	}
}