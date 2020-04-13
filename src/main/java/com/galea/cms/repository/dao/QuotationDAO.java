package com.galea.cms.repository.dao;

import java.util.List;

import com.galea.cms.repository.entity.Quotation;

public interface QuotationDAO {
	
	public void save(Quotation entity);

	public List<Quotation> getAllQuotation();
	public Quotation getQuotationById(Integer id, Boolean searchActive);
}