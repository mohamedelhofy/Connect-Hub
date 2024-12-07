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

public class User {
    private static User instance;
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

    public User(String email, String username,  String hashedPassword,Date DOB) {
        this.email = email;
        this.username = username;
        this.DOB = DOB;
        this.hashedPassword = hashedPassword;
    }

    public void initialize(String userId, String email, String username, String hashedPassword, Date DOB) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.DOB = DOB;
        this.status = false; 
    }

    public void updateProfile(String email, String username, String bio, String profilePhotoPath, String coverPhotoPath) {
        this.email = email;
        this.username = username;
        this.hashedPassword =hashedPassword;
        this.DOB = DOB; 
    }

    // Getters and setters for other fields
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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