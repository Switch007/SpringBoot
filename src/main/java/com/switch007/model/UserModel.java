package com.switch007.model;

import java.util.Date;

public class UserModel {

	private String id;

	private String username;

	private Integer status;

	private String lastLoginIp;

	private String coverImg;

	private String address;

	private String phone;

	private Date lastSendMailTime;

	private Date forbidTime;

	private Date createDate;

	private Date modifyDate;

	public UserModel(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.status = user.getStatus();
		this.lastLoginIp = user.getLastLoginIp();
		this.coverImg = user.getCoverImg();
		this.address = user.getAddress();
		this.phone = user.getPhone();
		this.lastSendMailTime = user.getLastSendMailTime();
		this.forbidTime = user.getForbidTime();
		this.createDate = user.getCreateDate();
		this.modifyDate = user.getModifyDate();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getCoverImg() {
		return coverImg;
	}

	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getLastSendMailTime() {
		return lastSendMailTime;
	}

	public void setLastSendMailTime(Date lastSendMailTime) {
		this.lastSendMailTime = lastSendMailTime;
	}

	public Date getForbidTime() {
		return forbidTime;
	}

	public void setForbidTime(Date forbidTime) {
		this.forbidTime = forbidTime;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

}
