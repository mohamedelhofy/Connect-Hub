/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FileDataActions implements UserDataActions {

    public Map<String, User> getUsers() {
        return users;
    }

    public static String getFILE_PATH() {
        return FILE_PATH;
    }

    public UserServices getUserService() {
        return userService;
    }
    private Map<String, User> users = new HashMap<>();
    private static final String FILE_PATH = "users.json";
    private UserServices userService = new UserServices();

    public FileDataActions() throws JSONException {
        loadUsers();
    }

    @Override
    public void save(User user) {
        users.put(user.getEmail(), user);
        try {
            saveUsers();
        } catch (JSONException ex) {
            Logger.getLogger(FileDataActions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public User findByEmail(String email) {
        return users.get(email);
    }

    @Override
    public void updateUser(User user) {
        users.put(user.getEmail(), user);
        try {
            saveUsers();
        } catch (JSONException ex) {
            Logger.getLogger(FileDataActions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean login(String email, String plainTextPassword) throws NoSuchAlgorithmException {
        User user = findByEmail(email);
        if (user != null) {
            return userService.checkPassword(plainTextPassword, user.getHashedPassword());
        }
        return false;
    }

    private void loadUsers() throws JSONException {
        try {
            String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            if (content.isEmpty()) {
                return;
            }

            JSONArray jsonArray = new JSONArray(content);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                try {
                    String userId = jsonObject.getString("userId");
                
                    String email = jsonObject.getString("email");
                    String username = jsonObject.getString("username");
                    String password = jsonObject.getString("password");
                    Date dateOfBirth = dateFormat.parse(jsonObject.getString("dateOfBirth"));
                    boolean status = jsonObject.getBoolean("isOnline");

                    User user = new User( userId , email, username, password, dateOfBirth);
                    user.setStatus(status);
                    users.put(email, user);
                } catch (Exception e) {
                    System.err.println("Error parsing user at index " + i + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   private void saveUsers() throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (User user : users.values()) {
            JSONObject jsonObject = new JSONObject();
             jsonObject.put("userId", user.getEmail() );

            jsonObject.put("email", user.getEmail() );
            
            jsonObject.put("username", user.getUsername());
            jsonObject.put("password", user.getHashedPassword());
            jsonObject.put("dateOfBirth", new SimpleDateFormat("yyyy-MM-dd").format(user.getDOB())+ "\n");
            jsonObject.put("isOnline", user.isOnline());
            jsonArray.put(jsonObject);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(jsonArray.toString(4)); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}