/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.BackEndFriendManagement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author رحمه صبرى
 */
public class FriendshipDatabase {

    private String userId;
    private ArrayList<User> friendsList;
    private ArrayList<User> blockedList;
    private ArrayList<User> suggestedList;
    private ArrayList<User> pendingList;
    
    public ArrayList<User> getFriendList() {
        return this.friendsList;
    }

    public ArrayList<User> getBlockedList() {
        return this.blockedList;
    }

    public ArrayList<User> getSuggestedList() {
        return this.suggestedList;
    }

    public ArrayList<User> getPendingList() {
        return this.pendingList;
    }

    public FriendshipDatabase(String userId) {
        this.userId = userId;
        this.friendsList = new ArrayList<>();
        this.blockedList = new ArrayList<>();
        this.suggestedList = new ArrayList<>();
        this.pendingList = new ArrayList<>();
    }

    public void updateFriendshipLists() {
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
                        User user = new User();
                        user.setUserId(jsonUserId);
                        user.setEmail(email);
                        user.setUsername(username);
                        user.setHashedPassword(password);
                        user.setStatus(statusUser);
                        user.setDOB(dateOfBirth);

                        checkRequestStatus friendshipChecker = new checkRequestStatus();
                        String requestStatus = friendshipChecker.check(this.userId, jsonUserId);
                        if (!this.userId.equals(jsonUserId)){
                            if(requestStatus == null)
                                suggestedList.add(user); 
                            else
                                switch (requestStatus) {
                                case "Accepted" -> friendsList.add(user);
                                case "Blocked" -> blockedList.add(user); 
                                case "Pending" -> pendingList.add(user); 
                                }
                        }
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
