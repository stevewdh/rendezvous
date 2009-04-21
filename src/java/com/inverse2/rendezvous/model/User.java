package com.inverse2.rendezvous.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
	
	public static final String ADMIN_CODE				= "admin";
	public static final String FACILITIES_MANAGER_CODE	= "facman";
	public static final String MEETING_ORGANISER_CODE	= "meetorg";
	public static final String MEETING_ATTENDEE_CODE	= "meetatt";
	public static final String PUBLIC_USER_CODE			= "public";
	
	private Integer	userId;
    private String 	description;
    private String 	firstName;
    private String 	surname;
    private String 	email;
    private String 	telephone;
    private String 	mobile;
    private String 	username;
    private String 	password;
    private String 	status;
    private String  userPriviledgeCode;
     
    public User() {
    }

    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getSurname() {
        return this.surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTelephone() {
        return this.telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getUserPriviledgeCode() {
		return userPriviledgeCode;
	}

	public void setUserPriviledgeCode(String userPriviledgeCode) {
		this.userPriviledgeCode = userPriviledgeCode;
	}

	public boolean getIsAdminUser() {
		return(hasPrivilegde(ADMIN_CODE));
	}

	public boolean getIsFacilitiesManagerUser() {
		return(hasPrivilegde(FACILITIES_MANAGER_CODE));
	}

	public boolean getIsMeetingOrganiserUser() {
		return(hasPrivilegde(MEETING_ORGANISER_CODE));
	}
	
	public boolean getIsMeetingAttendeeUser() {
		return(hasPrivilegde(MEETING_ATTENDEE_CODE));
	}

	public boolean getIsPublicUser() {
		return(hasPrivilegde(PUBLIC_USER_CODE));
	}
	
	private boolean hasPrivilegde(String code) {
		boolean admin   = false;
		boolean facman  = false;
		boolean meetorg = false;
		boolean meetatt = false;
		boolean pub     = true;
		
		if (userPriviledgeCode.equals(ADMIN_CODE)) {
			admin   = true;
			facman  = true;
			meetorg = true;
			meetatt = true;
		}
		else
		if (userPriviledgeCode.equals(FACILITIES_MANAGER_CODE)) {
			facman  = true;
			meetorg = true;
			meetatt = true;
		}
		else
		if (userPriviledgeCode.equals(MEETING_ORGANISER_CODE)) {
			meetorg = true;
			meetatt = true;
		}
		else
		if (userPriviledgeCode.equals(MEETING_ATTENDEE_CODE)) {
			meetatt = true;
		}
		
		if (code.equals(ADMIN_CODE) && admin == true) {
			return(true);
		}
		else
		if (code.equals(FACILITIES_MANAGER_CODE) && facman == true) {
			return(true);
		}
		else
		if (code.equals(MEETING_ORGANISER_CODE) && meetorg == true) {
			return(true);
		}
		else
		if (code.equals(MEETING_ATTENDEE_CODE) && meetatt == true) {
			return(true);
		}
		else
		if (code.equals(PUBLIC_USER_CODE) && pub == true) {
			return(true);
		}
		
		return(false);
	}
	

}
