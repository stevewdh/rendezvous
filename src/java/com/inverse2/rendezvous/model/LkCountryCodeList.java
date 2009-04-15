package com.inverse2.rendezvous.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LkCountryCodeList {
	
	@XmlElement(name="code")
	private List<LkCountryCode> codeList;
	
	public LkCountryCodeList() {
		
	}
	
	public List<LkCountryCode> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<LkCountryCode> codeList) {
		this.codeList = codeList;
	}
	
	
}
