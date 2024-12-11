package connect.hub;




/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Date;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class User {
    private static User instance;
    private static int idCounter = 1; // Static counter for unique IDs
    private String userId;
    private String email;
    private String username;
    private Date DOB;
    private boolean status;
    private String hashedPassword;

    private User(boolean status1, String userId1, String email1, String username1, String password, Date dateOfBirth) {
    }

    public static User getInstance() {
//        if (instance == null) {
//            instance = new User();
//        }
        return instance;
    }
    //
    // Constructor to create a new user and assign a unique ID    
    public User(String userId , String email, String username, String hashedPassword, Date DOB, boolean status) {
        
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.DOB = DOB;
        this.hashedPassword = hashedPassword;
        this.status = status;  
        
    }

    // Static method to generate unique IDs
    public static String generateUniqueId() {
        return "User" + (idCounter++); 
    }

    public void initialize(String userId, String email, String username, String hashedPassword, Date DOB) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.DOB = DOB;
        this.status = false; 
    }

    public void updateProfile(String email, String username) {
        this.email = email;
        this.username = username;
    }

    // Getters and setters
    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public boolean isOnline() {
        return status;
    }

    public void setStatus(boolean status) {
        if(status==true){
            instance = new User(status, userId, email, username, hashedPassword, DOB);
            instance.initialize(userId, email, username, hashedPassword, DOB);
        }
        this.status = status;
    }
    
    public static void setIdCounter(int idCounter) {
        User.idCounter = idCounter;
    }
    
}
