package com.galea.cms.repository.dao.impl;

import java.util.List;

import com.galea.cms.constant.SystemConstant;
import com.galea.cms.repository.dao.QuotationDAO;
import com.galea.cms.repository.entity.Quotation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class QuotationDAOImpl implements QuotationDAO {

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
	public void save(Quotation entity) {

		getHibernateTemplate().persist(entity);
	}

	@Override
	public List<Quotation> getAllQuotation() {

		return (List<Quotation>) getSession().createQuery("from Quotation qt where qt.status= '"+SystemConstant.ACTIVE+"' ").list();
	}

	@Override
	public Quotation getQuotationById(Integer id, Boolean searchActive) {
		String query = "from Quotation qt where qt.quotationId = " + id;

		if(searchActive) {
			query += " AND qt.status='"+SystemConstant.ACTIVE+"'";
		}

		List<Quotation> entityList = getSession().createQuery(query).list();

		if(entityList != null && !entityList.isEmpty()) {
			return entityList.get(0);
		}

		return null;
	}
}