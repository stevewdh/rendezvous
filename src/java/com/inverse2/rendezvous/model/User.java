package com.inverse2.rendezvous.model;
// Generated Apr 6, 2009 5:27:32 PM by Hibernate Tools 3.2.4.CR1



/**
 * User generated by hbm2java
 */
public class User  implements java.io.Serializable {


     private Integer userId;
     private String userPreviledgeCode;
     private String description;
     private String firstName;
     private String surname;
     private String email;
     private String telephone;
     private String mobile;

    public User() {
    }

	
    public User(String userPreviledgeCode, String firstName, String surname) {
        this.userPreviledgeCode = userPreviledgeCode;
        this.firstName = firstName;
        this.surname = surname;
    }
    public User(String userPreviledgeCode, String description, String firstName, String surname, String email, String telephone, String mobile) {
       this.userPreviledgeCode = userPreviledgeCode;
       this.description = description;
       this.firstName = firstName;
       this.surname = surname;
       this.email = email;
       this.telephone = telephone;
       this.mobile = mobile;
    }
   
    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUserPreviledgeCode() {
        return this.userPreviledgeCode;
    }
    
    public void setUserPreviledgeCode(String userPreviledgeCode) {
        this.userPreviledgeCode = userPreviledgeCode;
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




}


