package com.galea.cms.repository.service;

import com.galea.cms.webservices.bean.GeneralWsResponseBean;

import com.galea.cms.webservices.bean.quotation.QuotationBean;

public interface QuotationService {
    
    public GeneralWsResponseBean getAllQuotation();
    public GeneralWsResponseBean getQuotationById(Integer id);
    public GeneralWsResponseBean addQuotation(QuotationBean requestBean);
}