/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/**
 *
 * @author rawan
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Hashing {
    
    // Hash the password using SHA-256
    public String hash(String password) throws NoSuchAlgorithmException {
        // Create an instance of SHA-256
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        
        // Hash the password
        byte[] hash = messageDigest.digest(password.getBytes());
        
        // Convert the byte array into hexadecimal String
        StringBuilder hexaString = new StringBuilder();
        for (byte b : hash) {
            hexaString.append(String.format("%02x", b));
        }
        
        return hexaString.toString();
    }
    
    // Verify the password
    public boolean verifyPassword(String password, String hashedPassword) throws NoSuchAlgorithmException {
        // Hash the input password
        String hashedInputPassword = hash(password);
        
        // Compare the hashed input password with the stored hashed password
        return hashedInputPassword.equals(hashedPassword);
    }
}

