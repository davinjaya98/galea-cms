package com.galea.cms.repository.dao.impl;

import java.util.List;

import com.galea.cms.constant.SystemConstant;
import com.galea.cms.repository.dao.CustomDataDAO;
import com.galea.cms.repository.entity.CustomData;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomDataDAOImpl implements CustomDataDAO {

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
	public void save(CustomData entity) {

		getHibernateTemplate().persist(entity);
	}

	@Override
	public void delete(CustomData entity) {

		getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(CustomData entity) {

		getHibernateTemplate().update(entity);
	}

	@Override
	public List<CustomData> getAllCustomData() {

		return (List<CustomData>) getSession()
				.createQuery("from CustomData cdt where cdt.status= '" + SystemConstant.ACTIVE + "' ").list();
	}

	@Override
	public List<CustomData> getCustomDataListByCdGroupId(Integer id) {
		String query = "from CustomData cdt where cdt.customDataGroup.cdGroupId = " + id + " AND cdt.status='"
				+ SystemConstant.ACTIVE + "'";

		List<CustomData> entityList = getSession().createQuery(query).list();

		if (entityList != null && !entityList.isEmpty()) {
			return entityList;
		}

		return null;
	}

	@Override
	public CustomData getCustomDataById(Integer id, Boolean searchActive) {
		String query = "from CustomData cdt where cdt.cdId = " + id;

		if (searchActive) {
			query += " AND cdt.status='" + SystemConstant.ACTIVE + "'";
		}

		List<CustomData> entityList = getSession().createQuery(query).list();

		if (entityList != null && !entityList.isEmpty()) {
			return entityList.get(0);
		}

		return null;
	}

	@Override
	public CustomData getCustomDataByKey(String key, Boolean searchActive) {
		String query = "from CustomData cdt where cdt.cdKey = '" + key + "'";

		if (searchActive) {
			query += " AND cdt.status='" + SystemConstant.ACTIVE + "'";
		}

		List<CustomData> entityList = getSession().createQuery(query).list();

		if (entityList != null && !entityList.isEmpty()) {
			return entityList.get(0);
		}

		return null;
	}
}