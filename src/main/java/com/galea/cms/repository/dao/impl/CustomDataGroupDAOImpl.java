package com.galea.cms.repository.dao.impl;

import java.util.List;

import com.galea.cms.constant.SystemConstant;
import com.galea.cms.repository.dao.CustomDataGroupDAO;
import com.galea.cms.repository.entity.CustomDataGroup;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomDataGroupDAOImpl implements CustomDataGroupDAO {

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
	public void save(CustomDataGroup entity) {

		getHibernateTemplate().persist(entity);
	}

	@Override
	public void delete(CustomDataGroup entity) {

		getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(CustomDataGroup entity) {

		getHibernateTemplate().update(entity);
	}

	@Override
	public List<CustomDataGroup> getAllCustomDataGroup() {

		return (List<CustomDataGroup>) getSession()
				.createQuery("from CustomDataGroup cdg where cdg.status= '" + SystemConstant.ACTIVE + "' ").list();
	}

	@Override
	public List<CustomDataGroup> getCdGroupByPageStdId(Integer id) {
		String query = "from CustomDataGroup cdg where cdg.pageSetting.settingId = " + id + " AND cdg.status='"
				+ SystemConstant.ACTIVE + "'";

		List<CustomDataGroup> entityList = getSession().createQuery(query).list();

		if (entityList != null && !entityList.isEmpty()) {
			return entityList;
		}

		return null;
	}

	@Override
	public CustomDataGroup getCustomDataGroupById(Integer id, Boolean searchActive) {
		String query = "from CustomDataGroup cdg where cdg.cdGroupId = " + id;

		if (searchActive) {
			query += " AND cdg.status='" + SystemConstant.ACTIVE + "'";
		}

		List<CustomDataGroup> entityList = getSession().createQuery(query).list();

		if (entityList != null && !entityList.isEmpty()) {
			return entityList.get(0);
		}

		return null;
	}

	@Override
	public List<CustomDataGroup> getCdGroupByPageStgKey(String key) {
		String query = "from CustomDataGroup cdg where cdg.pageSetting.pageKey = '" + key + "' AND cdg.status='"
				+ SystemConstant.ACTIVE + "'";

		List<CustomDataGroup> entityList = getSession().createQuery(query).list();

		if (entityList != null && !entityList.isEmpty()) {
			return entityList;
		}

		return null;
	}

}