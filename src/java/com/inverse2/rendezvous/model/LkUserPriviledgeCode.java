package com.inverse2.rendezvous.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class LkUserPriviledgeCode {
	
	private String	userPriviledgeCode;
	private String	description;
	
	public String getUserPriviledgeCode() {
		return userPriviledgeCode;
	}
	
	public void setUserPriviledgeCode(String userPriviledgeCode) {
		this.userPriviledgeCode = userPriviledgeCode;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}
