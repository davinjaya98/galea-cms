package com.galea.cms.webservices.bean.quotation;

public class QuotationBean {
    
    //For Response
    private Long quotationDate;
    private Integer quotationId;
	private String custName;
	private String custEmail;
    private String custPhoneNumber;

    public QuotationBean() {
    }

    public QuotationBean(Integer quotationId, String custName, String custEmail, String custPhoneNumber, Long quotationDate) {
        this.quotationId = quotationId;
        this.custName = custName;
        this.custEmail = custEmail;
        this.custPhoneNumber = custPhoneNumber;
        this.quotationDate = quotationDate;
    }

    public Integer getQuotationId() {
        return this.quotationId;
    }

    public void setQuotationId(Integer quotationId) {
        this.quotationId = quotationId;
    }

    public String getCustName() {
        return this.custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustEmail() {
        return this.custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public String getCustPhoneNumber() {
        return this.custPhoneNumber;
    }

    public void setCustPhoneNumber(String custPhoneNumber) {
        this.custPhoneNumber = custPhoneNumber;
    }

    public Long getQuotationDate() {
        return this.quotationDate;
    }

    public void setQuotationDate(Long quotationDate) {
        this.quotationDate = quotationDate;
    }

}