package com.inverse2.rendezvous.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class UserPriviledge {
	
	private Integer userPriviledgeId;
	private String  priviledgeCode;
	private String  priviledgeDescription;
	private String  active;
	
	public UserPriviledge() {
		active = "Y";
	}
	
	public Integer getUserPriviledgeId() {
		return userPriviledgeId;
	}

	public void setUserPriviledgeId(Integer userPriviledgeId) {
		this.userPriviledgeId = userPriviledgeId;
	}

	public String getPriviledgeCode() {
		return priviledgeCode;
	}
	
	public void setPriviledgeCode(String priviledgeCode) {
		this.priviledgeCode = priviledgeCode;
	}
	
	public String getPriviledgeDescription() {
		return priviledgeDescription;
	}
	
	public void setPriviledgeDescription(String priviledgeDescription) {
		this.priviledgeDescription = priviledgeDescription;
	}
	
	public String getActive() {
		return active;
	}
	
	public void setActive(String active) {
		this.active = active;
	}
	
	public boolean isActive() {
		if (active != null && active.equals("Y")) {
			return(true);
		}
		return(false);
	}
	
	
}
