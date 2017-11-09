package com.switch007.model;

import java.util.Date;

public class AdminerModel {
	private String id;
	private String account;
	private String nickName;
	private int role;
	private int isDel;
	private String lastLoginIp;
	private String coverImg;
	private Date createTime;

	public AdminerModel(Adminer admin) {
		this.id = admin.getId();
		this.account = admin.getAccount();
		this.nickName = admin.getNickName();
		this.role = admin.getRole();
		this.isDel = admin.getIsDel();
		this.lastLoginIp = admin.getLastLoginIp();
		this.coverImg = admin.getCoverImg();
		this.createTime = admin.getCreateTime();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
