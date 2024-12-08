/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub;
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

public class UserServices implements Services {
    private UserDataActions userDataActions;;
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
            user.setHashedPassword(passwordHasher.hash(user.getHashedPassword()));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        userDataActions.save(user);
    }

    @Override
    public User login(String email, String password) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }

        User user = userDataActions.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        String hashedPassword;
        try {
            hashedPassword = passwordHasher.hash(password);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException("Error hashing password", ex);
        }



        if (!hashedPassword.equals(user.getHashedPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        user.setStatus(true);
        userDataActions.updateUser(user);

        return user;
    }

    @Override
    public void logout(User user) {
        user.setStatus(false);
        userDataActions.updateUser(user);
        System.out.println("User " + user.getUsername() + " has logged out.");
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email != null && email.matches(emailRegex);
    }

    public UserDataActions getUserDataActions() {
        return userDataActions;
    }

    boolean checkPassword(String plainTextPassword, String hashedPassword) throws NoSuchAlgorithmException {
        String hashedInput =  passwordHasher.hash(plainTextPassword);
        return hashedInput.equals(hashedPassword);
    }    }

 



