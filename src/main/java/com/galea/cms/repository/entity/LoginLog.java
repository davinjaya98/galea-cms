package com.galea.cms.repository.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "login_log", catalog = "galea_db")
public class LoginLog extends GeneralCreateModify implements Serializable {

    private int logId;
    private String createdToken;

    public LoginLog() {
    }

    public LoginLog(int logId, String createdToken) {
        this.logId = logId;
        this.createdToken = createdToken;
    }

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "log_id", unique = true, nullable = false)
    public int getLogId() {
        return this.logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

	@Column(name = "token")
    public String getCreatedToken() {
        return this.createdToken;
    }

    public void setCreatedToken(String createdToken) {
        this.createdToken = createdToken;
    }

}