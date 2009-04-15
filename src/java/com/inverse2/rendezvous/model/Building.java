package com.inverse2.rendezvous.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Building {

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
    
    @XmlElementWrapper (name="floors")
    @XmlElement        (name="floor")
    private List<Floor>		floors = new ArrayList<Floor>(0);

    public Building() {
    }
	
    public Building(LkCountryCode lkCountryCode) {
        this.country = lkCountryCode;
    }
    public Building(LkCountryCode lkCountryCode, String buildingName, String address1, String address2, String address3, String town, String postcode, Date activeFrom, Date activeUntil, List<Floor> floors) {
       this.country = lkCountryCode;
       this.buildingName = buildingName;
       this.address1 = address1;
       this.address2 = address2;
       this.address3 = address3;
       this.town = town;
       this.postcode = postcode;
       this.activeFrom = activeFrom;
       this.activeUntil = activeUntil;
       this.floors = floors;
    }
   
    public Integer getBuildingId() {
        return this.buildingId;
    }
    
    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }
    
    public LkCountryCode getCountryCode() {
        return this.country;
    }
    
    public void setCountryCode(LkCountryCode countryCode) {
        this.country = countryCode;
    }
    
    public String getBuildingName() {
        return this.buildingName;
    }
    
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
    
    public String getAddress1() {
        return this.address1;
    }
    
    public void setAddress1(String address1) {
        this.address1 = address1;
    }
    
    public String getAddress2() {
        return this.address2;
    }
    
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    
    public String getAddress3() {
        return this.address3;
    }
    
    public void setAddress3(String address3) {
        this.address3 = address3;
    }
    
    public String getTown() {
        return this.town;
    }
    
    public void setTown(String town) {
        this.town = town;
    }
    
    public String getPostcode() {
        return this.postcode;
    }
    
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    
    public Date getActiveFrom() {
        return this.activeFrom;
    }
    
    public void setActiveFrom(Date activeFrom) {
        this.activeFrom = activeFrom;
    }
    
    public Date getActiveUntil() {
        return this.activeUntil;
    }
    
    public void setActiveUntil(Date activeUntil) {
        this.activeUntil = activeUntil;
    }
    
    public List<Floor> getFloors() {
        return this.floors;
    }
    
    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

}
