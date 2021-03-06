package com.inverse2.rendezvous.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Floor {

    private Integer floorId;
    private Integer buildingId;
    private String floorName;
    private Integer displayOrder;
    
    @XmlElementWrapper (name="rooms")
    @XmlElement        (name="room")
    private List<Room> rooms = new ArrayList<Room>(0);

    public Floor() {
    }

    public Floor(String floorName, Integer displayOrder, List<Room> rooms) {
       this.floorName = floorName;
       this.displayOrder = displayOrder;
       this.rooms = rooms;
    }
   
    public Integer getFloorId() {
        return this.floorId;
    }
    
    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }
    
    public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public String getFloorName() {
        return this.floorName;
    }
    
    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }
    
    public Integer getDisplayOrder() {
        return this.displayOrder;
    }
    
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }
    
    public List<Room> getRooms() {
        return this.rooms;
    }
    
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }




}


