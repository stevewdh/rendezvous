package com.inverse2.rendezvous.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LkUserPriviledgeCodeList {
	
	@XmlElement(name="code")
	private List<LkUserPriviledgeCode> codeList;
	
	public LkUserPriviledgeCodeList() {
	}
	
	public List<LkUserPriviledgeCode> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<LkUserPriviledgeCode> codeList) {
		this.codeList = codeList;
	}
	
}
