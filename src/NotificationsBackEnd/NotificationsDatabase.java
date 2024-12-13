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
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class NotificationsDatabase {
    private String userId;
    private ArrayList<Map<String, String>> allFriendRequestsNotificationsList;
    private ArrayList<User> usersFriendRequestsNotificationsList;
    private ArrayList<Map<String, String>> emptyList = new ArrayList<>();

    public NotificationsDatabase(String userId) {
        this.userId = userId;
        this.usersFriendRequestsNotificationsList = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }
        
    public ArrayList<User> getUsersFriendRequestsNotificationsList(){
//        this.usersFriendRequestsNotificationsList.clear();
//        updateUsersFriendRequestsNotificationsList();
        return this.usersFriendRequestsNotificationsList;
    }
    
    public void updateUsersFriendRequestsNotificationsList(){
        ReadReceivedFriendRequestsNotificationsFromJSON reader = new ReadReceivedFriendRequestsNotificationsFromJSON();
        allFriendRequestsNotificationsList = reader.readToListOfMaps();
        ArrayList<String> ids = new ArrayList<>();
        for(Map map : allFriendRequestsNotificationsList){
            if(userId.equals(map.get("receiver"))){
                ids.add((String) map.get("sender"));
            }
        }
        Path filePath = Paths.get("users.Json");
        if (Files.exists(filePath)) {
            try {
                String jsonString = Files.readString(filePath);
                JSONArray jsonArray = new JSONArray(jsonString);

                for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (jsonObject.has("userId")) {
                        String jsonUserId = jsonObject.getString("userId");
                        for(String id : ids){
                            if(id.equals(jsonUserId)){
                                String email = jsonObject.getString("email");
                                String username = jsonObject.getString("username");
                                String password = jsonObject.getString("password");
                                boolean statusUser = jsonObject.getBoolean("isOnline");
                                Date dateOfBirth = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
                                User user = new User(jsonUserId , email, username, password, dateOfBirth, statusUser);
                                user.setUserId(jsonUserId);
                                usersFriendRequestsNotificationsList.add(user);
                            }
                        }
                        System.out.println("");
                        for(User user : usersFriendRequestsNotificationsList){
                            System.out.println(user.getUsername());
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
        WriteReceivedFriendRequestsNotificationsToJSON writer = new WriteReceivedFriendRequestsNotificationsToJSON();
        writer.writeFromListOfMaps(emptyList);

    }
}


