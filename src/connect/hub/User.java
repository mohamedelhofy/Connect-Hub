package connect.hub;


import java.time.LocalDate;
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */




import java.util.Date;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Date;

public class User {
    private static User instance;
    private static int idCounter = 1; // Static counter for unique IDs
    private String userId;
    private String email;
    private String username;
    private Date DOB;
    private boolean status;
    private String hashedPassword;

    private User() {
    }

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    // Constructor to create a new user and assign a unique ID
    public User(String userId , String email, String username, String hashedPassword, Date DOB) {
        this.userId = generateUniqueId();
        this.email = email;
        this.username = username;
        this.DOB = DOB;
        this.hashedPassword = hashedPassword;
        this.status = false; // Default status to offline
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
        this.status = status;
    }
}
