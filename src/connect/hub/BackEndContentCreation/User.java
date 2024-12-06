package connect.hub.BackEndContentCreation;


import java.time.LocalDate;
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rawan
 */
public class User {
String userId; 
String email;   
String username; 
Date DOB;  
boolean Status;
String hashedPassword;  

    public User( String email, String username, String hashedPassword, Date DOB) {
     
        this.email = email;
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.DOB = DOB;
        this.Status = Status;
    }


//    public void setUserId(String userId) {
//        this.userId = userId;
//    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

//    public String getUserId() {
//        return userId;
//    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public Date getDOB() {
        return DOB;
    }

    public boolean isOnline() {
        return Status;
    }

}
