package com.inverse2.rendezvous.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Room {

    private Integer   roomId;
    private String    roomCode;
    private String    roomName;
    private Integer   capacity;
    private String    location;
    private char      selfService;
    private Integer   floorId;

    public Room() {
    }
	
    public Integer getRoomId() {
        return this.roomId;
    }
    
    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
    
    public String getRoomCode() {
        return this.roomCode;
    }
    
    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }
    
    public String getRoomName() {
        return this.roomName;
    }
    
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    
    public Integer getCapacity() {
        return this.capacity;
    }
    
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
    
    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public char getSelfService() {
        return this.selfService;
    }
    
    public void setSelfService(char selfService) {
        this.selfService = selfService;
    }
    
    public Integer getFloorId() {
        return this.floorId;
    }
    
    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }

}


