/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NotificationsBackEnd;

import connect.hub.User;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author rawan
 */
public class UsersReadWriteManager {
    private ArrayList<User> usersList;
    
    public UsersReadWriteManager() {
        this.usersList = new ArrayList<>();
    }

    public ArrayList<User> getUsersList() {
        this.usersList.clear();
        updateList();
        return this.usersList;
    }

    private void updateList() {
        Path filePath = Paths.get("users.Json");
        if (Files.exists(filePath)) {
            try {
                String jsonString = Files.readString(filePath);
                JSONArray jsonArray = new JSONArray(jsonString);
                for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (jsonObject.has("userId")) {
                        String jsonUserId = jsonObject.getString("userId");
                        String email = jsonObject.getString("email");
                        String username = jsonObject.getString("username");
                        String password = jsonObject.getString("password");
                        boolean statusUser = jsonObject.getBoolean("isOnline");
                        Date dateOfBirth = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
                        User user = new User(jsonUserId , email, username, password, dateOfBirth, statusUser);
                        user.setUserId(jsonUserId);
                        usersList.add(user);
                    }
                }
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Reading File Error", "MESSAGE", JOptionPane.ERROR_MESSAGE);
            }
            catch (JSONException j) {
                JOptionPane.showMessageDialog(null, "JSON Parsing Error", "MESSAGE", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
            JOptionPane.showMessageDialog(null, "File does not exist: " + filePath, "MESSAGE", JOptionPane.WARNING_MESSAGE);
    }
}