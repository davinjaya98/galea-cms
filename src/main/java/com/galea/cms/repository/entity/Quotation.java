package com.galea.cms.repository.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "quotation", catalog = "segosarem_db")
public class Quotation extends GeneralCreateModify implements Serializable {

	private int quotationId;
	private String custName;
	private String custEmail;
    private String custPhoneNumber;
    
    public Quotation() {
    }

    public Quotation(int quotationId, String custName, String custEmail, String custPhoneNumber) {
        this.quotationId = quotationId;
        this.custName = custName;
        this.custEmail = custEmail;
        this.custPhoneNumber = custPhoneNumber;
    }

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "quotation_id", unique = true, nullable = false)
    public int getQuotationId() {
        return this.quotationId;
    }

    public void setQuotationId(int quotationId) {
        this.quotationId = quotationId;
    }

	@Column(name = "cust_name")
    public String getCustName() {
        return this.custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

	@Column(name = "cust_email")
    public String getCustEmail() {
        return this.custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

	@Column(name = "cust_phone_number")
    public String getCustPhoneNumber() {
        return this.custPhoneNumber;
    }

    public void setCustPhoneNumber(String custPhoneNumber) {
        this.custPhoneNumber = custPhoneNumber;
    }
}