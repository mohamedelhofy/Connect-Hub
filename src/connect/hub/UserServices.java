/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub;
import connect.hub.FileDataActions;
import connect.hub.Hashing;
import connect.hub.Services;
import connect.hub.User;
import connect.hub.UserDataActions;
import java.io.FileReader;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author rawan
 */

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServices implements Services {
    private UserDataActions userDataActions;
    private Hashing passwordHasher = new Hashing();

    public UserServices() throws JSONException {
        this.userDataActions = new FileDataActions(this);
    }

    @Override
    public void signUp(User user) {
        if (!isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username is required");
        }
        if (user.getHashedPassword() == null || user.getHashedPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
        if (user.getDOB() == null) {
            throw new IllegalArgumentException("Date of birth is required");
        }

        try {
            // Hash the password before saving
            user.setHashedPassword(passwordHasher.hash(user.getHashedPassword()));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, "Error hashing password", ex);
            throw new RuntimeException("Error hashing password", ex);
        }

        // Save the user data
        userDataActions.save(user);
    }

    @Override
    public User login(String email, String password) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }

        User user = userDataActions.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User  not found");
        }

        // Log the hashed password and the input password for debugging
//        System.out.println("Stored Hashed Password: " + user.getHashedPassword());
//        System.out.println("Input Password: " + password);
        // Verify the password
        try {
            if (!passwordHasher.verifyPassword(password, user.getHashedPassword())) {
                throw new IllegalArgumentException("Invalid password");
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, "Error verifying password", ex);
            throw new RuntimeException("Error verifying password", ex);
        }

        // If we reach here, the login is successful
        user.setStatus(true);
        userDataActions.updateUser (user);
        return user;
    }

    @Override
    public void logout(User user) {
        user.setStatus(false);
        userDataActions.updateUser (user);
//        System.out.println("User  " + user.getUsername() + " has logged out.");
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email != null && email.matches(emailRegex);
    }
    
    
    public boolean checkPassword(String plainTextPassword, String hashedPassword) {
    try {
        return passwordHasher.verifyPassword(plainTextPassword, hashedPassword);
    } catch (NoSuchAlgorithmException e) {
        Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, "Error verifying password", e);
        throw new RuntimeException("Error verifying password", e);
    }
}
}



