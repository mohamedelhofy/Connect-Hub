/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NotificationsBackEnd;

import GroupClass.Group;
import GroupClass.readGroupFromJSON;
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
    private ArrayList<Group> userGroups;
    private ArrayList<Group> allGroups;
    private ArrayList<String> groupNotifications;
    private ArrayList<User> allUsers;

    private ArrayList<Map<String, String>> allGroupNotifications;
        
    public NotificationsDatabase(String userId) {
        this.userId = userId;
        groupNotifications = new ArrayList<>();
        usersFriendRequestsNotificationsList = new ArrayList<>();
        userGroups = new ArrayList<>();
    }
        
    public ArrayList<User> getUsersFriendRequestsNotificationsList(){
        updateUsersFriendRequestsNotificationsList();
        return this.usersFriendRequestsNotificationsList;
    }
    public ArrayList<String> getGroupNotifications(){
        updateGroupLists();
        return this.groupNotifications;
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
//        WriteReceivedFriendRequestsNotificationsToJSON writer = new WriteReceivedFriendRequestsNotificationsToJSON();
//        writer.writeFromListOfMaps(emptyList);

    }

    public void updateGroupLists(){
        NotificationReadWriteManager reader = new NotificationReadWriteManager();
        allGroupNotifications = reader.readToListOfMaps();
        readGroupFromJSON groupReader = new readGroupFromJSON();
        allGroups = (ArrayList <Group>) groupReader.convertToGroupList();
        
        // Get groups the user is in
        for(Group group : allGroups){
            for(String userId : group.getMembers()){
                if(this.userId.toLowerCase().equals(userId))
                    userGroups.add(group);
            }
            for(String userId : group.getAdmins()){
                if(this.userId.toLowerCase().equals(userId))
                    userGroups.add(group);
            }
            if(this.userId.toLowerCase().equals(group.getPrimaryAdmin()))
                userGroups.add(group);
        }
        UsersReadWriteManager userFileManager = new UsersReadWriteManager();
        allUsers = userFileManager.getUsersList();
        for(Group group : userGroups){
            for(Map map : allGroupNotifications){
                String name = "";
                String name2 = "";
                String name3 = "";
                String name4 = "";
                for(User user : allUsers){
                    if(map.get("addedMemberId") != null)
                        if(user.getUserId().toLowerCase().equals(((String)map.get("addedMemberId")).toLowerCase())){
                            name = user.getUsername();
                            break;
                        }
                    if(map.get("commenterId") != null){
                        if(user.getUserId().toLowerCase().equals(((String)map.get("commenterId")).toLowerCase())){
                            name2 = user.getUsername();
                            break;
                        }
                    }
                    else if(map.get("senderId") != null){
                        if(user.getUserId().toLowerCase().equals(((String)map.get("senderId")).toLowerCase())){
                            name3 = user.getUsername();
                            break;
                        }
                    }
                    else if(map.get("authorId") != null){
                        if(user.getUserId().toLowerCase().equals(((String)map.get("authorId")).toLowerCase())){
                            name4 = user.getUsername();
                            break;
                        }
                    }
                }
//                System.out.println(name);
                if(map.containsKey("groupName"))
                    if(map.get("groupName").equals(group.getGroupName())){
                        if(map.get("type").equals("new Member") && !map.get("addedMemberId").equals(userId)){
                            groupNotifications.add("New Group Member!!! Welcome \"" + name + "\" to \"" + map.get("groupName") + "\"");
                        }
                        if(map.get("type").equals("new Post")){
                            groupNotifications.add("\"" + name4 + "\" added a new post in \"" + map.get("groupName") + "\"!!!");
                        }
                        if(map.get("type").equals("promotion") && ((String)map.get("userID")).toLowerCase().equals(userId.toLowerCase())){
                            groupNotifications.add("You got promoted in " + map.get("groupName") + "!!!");
                        }
                        if(map.get("type").equals("demotion") && ((String)map.get("userID")).toLowerCase().equals(userId.toLowerCase())){
                            groupNotifications.add("You got demoted in \"" + map.get("groupName") + "\"!!!");
                        }
                    }
                if(map.get("type").equals("newComment") && ((String)map.get("authorId")).toLowerCase().equals(userId.toLowerCase())){
                    groupNotifications.add("\"" + name2 + "\" commented on a post you shared");

                }
                if(map.get("type").equals("newChat") && map.get("receiverId").equals(userId)){
                    groupNotifications.add("\"" + name3 + "\" sent you a chat");
                }
            }
        }
    }
}