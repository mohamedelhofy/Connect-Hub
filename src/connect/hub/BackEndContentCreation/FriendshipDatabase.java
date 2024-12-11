/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.BackEndContentCreation;

import connect.hub.User;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;

/**
 *
 * @author رحمه صبرى
 */
public class FriendshipDatabase {

    private String userId;
    private List<User> getFriendList;
    private List<User> getBlockedList;
    private List<User> getSuggestedList;

    public List<User> getFriendList() {
        return getFriendList;
    }

    public List<User> getBlockedList() {
        return getBlockedList;
    }

    public List<User> getSuggestedList() {
        return getSuggestedList;
    }

    public FriendshipDatabase(String userId) {
        this.userId = userId;
        this.getFriendList = new ArrayList<>();
        this.getBlockedList = new ArrayList<>();
        this.getSuggestedList = new ArrayList<>();
    }

    public void updateFriendshipLists() {
        Path filePath = Paths.get("user.Json");

        if (Files.exists(filePath)) {
            try {
                String jsonString = Files.readString(filePath);
                JSONArray jsonArray = new JSONArray(jsonString);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String userIdRecieved = jsonObject.getString("userId");
                    String email = jsonObject.getString("email");
                    String username = jsonObject.getString("username");
                    String password = jsonObject.getString("password");
                    boolean statusUser = jsonObject.getBoolean("isOnline");
                    Date dateOfBirth = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    
                    User user = new User(userIdRecieved , email, username, password, dateOfBirth , statusUser);
//                    user.setUserId(userIdRecieved);
                    user.setStatus(statusUser);
                    
                    
                    if (jsonObject.has("userId")) {
                        String receiverUser = jsonObject.getString("userId");
                        checkRequestStatus checkedObject = new checkRequestStatus();
                        String recivesRelation = checkedObject.check(this.userId, receiverUser);
                        if (recivesRelation.equals("Accepted")) {
                            getFriendList.add(user);
                        } else if (recivesRelation.equals("Blocked")) {
                            getBlockedList.add(user);
                        } else {
                            getSuggestedList.add(user);
                        }
                    }
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Reading File Error", "MESSAGE", JOptionPane.ERROR_MESSAGE);
            } catch (JSONException j) {
                JOptionPane.showMessageDialog(null, "JSON Parsing Error", "MESSAGE", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "File does not exist: " + filePath, "MESSAGE", JOptionPane.WARNING_MESSAGE);
        }
    }

}
