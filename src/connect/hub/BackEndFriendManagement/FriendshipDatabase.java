/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.BackEndFriendManagement;

import connect.hub.User;
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
import java.util.Map;

/**
 *
 * @author رحمه صبرى
 */
public class FriendshipDatabase {

    private String userId;
    private ArrayList<Map<String, String>> friendRequestsList;
    
    private ArrayList<User> friendsList = new ArrayList<>();
    private ArrayList<User> blockedList = new ArrayList<>();
    private ArrayList<User> suggestedList = new ArrayList<>();
    private ArrayList<User> receivedPendingList = new ArrayList<>();
    private ArrayList<User> sentPendingList = new ArrayList<>();;
    
    public FriendshipDatabase(String userId) {
        this.userId = userId;
        this.friendsList = new ArrayList<>();
        this.blockedList = new ArrayList<>();
        this.suggestedList = new ArrayList<>();
        this.receivedPendingList = new ArrayList<>();
        this.sentPendingList = new ArrayList<>();
    }

    public ArrayList<User> getFriendList() {
        this.friendsList.clear();
        this.blockedList.clear();
        this.suggestedList.clear();
        this.sentPendingList.clear();
        this.receivedPendingList.clear();
        updateFriendshipLists();
        return this.friendsList;
    }

    public ArrayList<User> getBlockedList() {
        this.friendsList.clear();
        this.blockedList.clear();
        this.suggestedList.clear();
        this.sentPendingList.clear();
        this.receivedPendingList.clear();
        updateFriendshipLists();
        return this.blockedList;
    }

    public ArrayList<User> getSuggestedList() {
        this.friendsList.clear();
        this.blockedList.clear();
        this.suggestedList.clear();
        this.sentPendingList.clear();
        this.receivedPendingList.clear();
        updateFriendshipLists();
        return this.suggestedList;
    }

    public ArrayList<User> getReceivedPendingList() {
        this.friendsList.clear();
        this.blockedList.clear();
        this.suggestedList.clear();
        this.sentPendingList.clear();
        this.receivedPendingList.clear();
        updateFriendshipLists();
        return this.receivedPendingList;
    }

    public ArrayList<User> getSentPendingList() {
        this.friendsList.clear();
        this.blockedList.clear();
        this.suggestedList.clear();
        this.sentPendingList.clear();
        this.receivedPendingList.clear();
        updateFriendshipLists();
        return this.sentPendingList;
    }


    private void updateFriendshipLists() {
        Path filePath = Paths.get("users.Json");
        if (Files.exists(filePath)) {
            try {
                String jsonString = Files.readString(filePath);
                JSONArray jsonArray = new JSONArray(jsonString);

                checkRequestStatus friendshipChecker = new checkRequestStatus();
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
                        String requestStatus = friendshipChecker.check(this.userId, jsonUserId);
                        if (!this.userId.equals(jsonUserId)){
                            if(requestStatus == null)
                                suggestedList.add(user); 
                            else
                                switch (requestStatus) {
                                    case "Accepted" -> friendsList.add(user);
                                    case "Blocked" -> blockedList.add(user);
                                    case "Pending" -> {
                                        ReadFriendRequestsFromJSON reader = new ReadFriendRequestsFromJSON();
                                        this.friendRequestsList = reader.readToListOfMaps();
                                        for (Map<String, String> request : this.friendRequestsList) {
                                            String senderList = request.get("sender");
                                            String receiverList = request.get("receiver");
                                            String requestStatusList = request.get("requestStatus");
                                            if(requestStatusList.equals("Pending")){
                                                if(senderList != null && receiverList != null && userId.equals(senderList) && jsonUserId.equals(receiverList)){
                                                    sentPendingList.add(user);
                                                }
                                                if(senderList != null && receiverList != null && userId.equals(receiverList) && jsonUserId.equals(senderList)){
                                                    receivedPendingList.add(user);
                                                }
                                            }
                                        }
                                    }

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