package com.inverse2.rendezvous.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType (XmlAccessType.FIELD)
public class BuildingSummary {
	
    private Integer			buildingId;
    private LkCountryCode 	country;
    private String 			buildingName;
    private String 			address1;
    private String 			address2;
    private String 			address3;
    private String 			town;
    private String 			postcode;
    private Date 			activeFrom;
    private Date 			activeUntil;
    
    public BuildingSummary() {
    	
    }
    
	public Integer getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}
	public LkCountryCode getCountry() {
		return country;
	}
	public void setCountry(LkCountryCode country) {
		this.country = country;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public Date getActiveFrom() {
		return activeFrom;
	}
	public void setActiveFrom(Date activeFrom) {
		this.activeFrom = activeFrom;
	}
	public Date getActiveUntil() {
		return activeUntil;
	}
	public void setActiveUntil(Date activeUntil) {
		this.activeUntil = activeUntil;
	}
	
	/**
	 * Had to name the method *get*IsActive because the JSP EL expects ${xxx.isActive} to resolve to getIsActive().
	 * @return
	 */
    public boolean getIsActive() {
    	Date now = new Date();
    	if ((activeFrom  == null || now.after(activeFrom)  ) && 
    		(activeUntil == null || now.before(activeUntil))    ) {
    		return(true);
    	}
    	return(false);
    }

}
