/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NotificationsBackEnd;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author rawan
 */
public class NotificationReadWriteManager {
    private UsersReadWriteManager userReaderWriter = new UsersReadWriteManager();
    private final Path filePath = Paths.get("notifications.json");

    public ArrayList<Map<String, String>> readToListOfMaps() {
        ArrayList<Map<String, String>> notificationsList = new ArrayList<>();

        if (Files.exists(filePath)) {
            String notificationsString;
            try {
                notificationsString = Files.readString(filePath);
                JSONArray jsonArray = new JSONArray(notificationsString);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Map<String, String> notificationMap = new HashMap<>();
                    String type = jsonObject.getString("type");
                    notificationMap.put("type", type);
                    switch (type) {
                        case "new Member":
                            notificationMap.put("groupName", jsonObject.getString("groupName"));
                            notificationMap.put("addedMemberId", jsonObject.getString("addedMemberId"));
                            break;
                        case "new Post":
                            notificationMap.put("groupName", jsonObject.getString("groupName"));
                            notificationMap.put("postId", jsonObject.getString("postId"));
                            break;
                        case "status change":
                            notificationMap.put("groupName", jsonObject.getString("groupName"));
                            notificationMap.put("userID", jsonObject.getString("userID"));
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Unknown notification type", "MESSAGE", JOptionPane.ERROR_MESSAGE);
                            break;
                    }
                    notificationsList.add(notificationMap);
                }
                return notificationsList;
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Reading File Error", "MESSAGE", JOptionPane.ERROR_MESSAGE);
            } catch (JSONException j) {
                JOptionPane.showMessageDialog(null, "JSON Parsing Error", "MESSAGE", JOptionPane.ERROR_MESSAGE);
            }
        }
        return notificationsList;
    }
    

    public void writeFromListOfMaps(ArrayList<Map<String, String>> notificationsList) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (Map<String, String> notificationMap : notificationsList) {
            JSONObject jsonObject = new JSONObject();
            String type = notificationMap.get("type");
            jsonObject.put("type", type);
            switch (type) {
                case "new Member":
                    jsonObject.put("groupName", notificationMap.get("groupName"));
                    jsonObject.put("addedMemberId", notificationMap.get("addedMemberId"));
                    break;
                case "new Post":
                    jsonObject.put("groupName", notificationMap.get("groupName"));
                    jsonObject.put("postId", notificationMap.get("postId"));
                    break;
                case "status change":
                    jsonObject.put("groupName", notificationMap.get("groupName"));
                    jsonObject.put("userID", notificationMap.get("userID"));
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Unknown notification type", "MESSAGE", JOptionPane.ERROR_MESSAGE);
                    break;
            }

            jsonArray.put(jsonObject);
        }
        try {
            Files.writeString(filePath, jsonArray.toString());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Writing File Error", "MESSAGE", JOptionPane.ERROR_MESSAGE);
        }
    }
}

