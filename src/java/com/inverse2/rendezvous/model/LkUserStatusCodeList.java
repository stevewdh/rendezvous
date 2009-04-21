package com.inverse2.rendezvous.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LkUserStatusCodeList {
	
	@XmlElement(name="code")
	private List<LkUserStatusCode> codeList;
	
	public LkUserStatusCodeList() {
	}
	
	public List<LkUserStatusCode> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<LkUserStatusCode> codeList) {
		this.codeList = codeList;
	}
	
}
