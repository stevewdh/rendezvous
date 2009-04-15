package com.inverse2.rendezvous.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class LkCountryCode {

    private String countryCode;
    private String countryName;

    public LkCountryCode() {
    }
	
    public LkCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    
    public LkCountryCode(String countryCode, String countryName) {
       this.countryCode = countryCode;
       this.countryName = countryName;
    }
   
    public String getCountryCode() {
        return this.countryCode;
    }
    
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    
    public String getCountryName() {
        return this.countryName;
    }
    
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

}


