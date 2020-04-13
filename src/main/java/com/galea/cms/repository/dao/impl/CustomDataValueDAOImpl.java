package com.galea.cms.repository.dao.impl;

import java.util.List;

import com.galea.cms.constant.SystemConstant;
import com.galea.cms.repository.dao.CustomDataValueDAO;
import com.galea.cms.repository.entity.CustomDataValue;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomDataValueDAOImpl implements CustomDataValueDAO {

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
	public void persist(CustomDataValue entity) {

		getHibernateTemplate().persist(entity);
	}

	@Override
	public void save(CustomDataValue entity) {

		getHibernateTemplate().save(entity);
	}

	@Override
	public void delete(CustomDataValue entity) {

		getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(CustomDataValue entity) {

		getHibernateTemplate().update(entity);
	}

	@Override
	public List<CustomDataValue> getAllCustomDataValue() {

		return (List<CustomDataValue>) getSession().createQuery("from CustomDataValue cdv where cdv.status= '"+SystemConstant.ACTIVE+"' ").list();
	}

	@Override
	public CustomDataValue getCustomDataValueById(Integer id, Boolean searchActive) {
		String query = "from CustomDataValue cdv where cdv.cdValueId = " + id;

		if(searchActive) {
			query += " AND cdv.status='"+SystemConstant.ACTIVE+"'";
		}

		List<CustomDataValue> entityList = getSession().createQuery(query).list();

		if(entityList != null && !entityList.isEmpty()) {
			return entityList.get(0);
		}

		return null;
	}
}