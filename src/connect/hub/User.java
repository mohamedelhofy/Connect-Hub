package connect.hub;


import java.time.LocalDate;
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */




import java.util.Date;



public class User {
    
    private static int idCounter = 0; 
    private String userId;
    private String email;
    private String username;
    private Date DOB;
    private boolean status;
    private String hashedPassword;

    public User(String email, String username, String hashedPassword, Date DOB) {
        this.userId = generateUserId();
        this.email = email;
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.DOB = DOB;
        this.status = false; 
    }

    public User() {
    }
    
    

    private static synchronized String generateUserId() {
        idCounter++;
        return String.valueOf(idCounter); 
    }

    // Getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // Other getters and setters remain unchanged
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

    public static void setIdCounter(int idCounter) {
        User.idCounter = idCounter;
    }

    public boolean isOnline() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}