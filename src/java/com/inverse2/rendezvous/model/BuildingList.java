package com.inverse2.rendezvous.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class BuildingList {
	
	@XmlElement (name="building")
	private List<BuildingSummary> buildingList;

	public BuildingList() {
		
	}
	
	public List<BuildingSummary> getBuildingList() {
		return buildingList;
	}

	public void setBuildingList(List<BuildingSummary> buildingList) {
		this.buildingList = buildingList;
	}
	
}
