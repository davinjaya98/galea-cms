package com.galea.cms.repository.dao.impl;

import java.util.List;

import com.galea.cms.constant.SystemConstant;
import com.galea.cms.repository.dao.CustomDataSettingDAO;
import com.galea.cms.repository.entity.CustomDataSetting;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomDataSettingDAOImpl implements CustomDataSettingDAO {

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
	public void save(CustomDataSetting entity) {

		getHibernateTemplate().persist(entity);
	}

	@Override
	public void delete(CustomDataSetting entity) {

		getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(CustomDataSetting entity) {

		getHibernateTemplate().update(entity);
	}

	@Override
	public List<CustomDataSetting> getCdSettingsListByCdId(Integer id) {
		String query = "from CustomDataSetting cds where cds.customData.cdId = " + id + " AND cds.status='"
				+ SystemConstant.ACTIVE + "'";

		List<CustomDataSetting> entityList = getSession().createQuery(query).list();

		if (entityList != null && !entityList.isEmpty()) {
			return entityList;
		}

		return null;
	}

	@Override
	public List<CustomDataSetting> getAllCustomDataSetting() {

		return (List<CustomDataSetting>) getSession()
				.createQuery("from CustomDataSetting cds where cds.status= '" + SystemConstant.ACTIVE + "' ").list();
	}

	@Override
	public CustomDataSetting getCustomDataSettingById(Integer id, Boolean searchActive) {
		String query = "from CustomDataSetting cds where cds.cdsId = " + id;

		if (searchActive) {
			query += " AND cds.status='" + SystemConstant.ACTIVE + "'";
		}

		List<CustomDataSetting> entityList = getSession().createQuery(query).list();

		if (entityList != null && !entityList.isEmpty()) {
			return entityList.get(0);
		}

		return null;
	}
}