/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.BackEndContentCreation;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.JSONException;

/**
 *
 * @author رحمه صبرى
 */
public class ReadFriendRequestsFromJSON{
    private final Path filePath = Paths.get("Friend Requests.json");

    public ArrayList<Map<String, String>> readToListOfMaps() {
        ArrayList<Map<String, String>> friendRequestsList = new ArrayList<>();

        if (Files.exists(filePath)) {
            String friendRequestsString;
            try {
                friendRequestsString = Files.readString(filePath);
                JSONArray jsonArray = new JSONArray(friendRequestsString);
                for (int i=0; i<jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Map<String, String> requestMap = new HashMap<>();
                    requestMap.put("sender", jsonObject.getString("sender"));
                    requestMap.put("receiver", jsonObject.getString("receiver"));
                    requestMap.put("requestStatus", jsonObject.getString("requestStatus"));
                    friendRequestsList.add(requestMap);
                }
                return friendRequestsList;
            } 
            catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Reading File Error", "MESSAGE", JOptionPane.ERROR_MESSAGE);
            }
            catch (JSONException j){
                JOptionPane.showMessageDialog(null, "JSON Parsing Error", "MESSAGE", JOptionPane.ERROR_MESSAGE);
            }
        }
        return friendRequestsList;
    }
}
// get file path
// create list of map
// if file path exists 
    // read file path to sring
    // convert string to jsonArray
    // loop over jsonArray
        // create json object and get element from jsonArray
        // create map for each element
        // put jsonObject.getString into map
        // add each map to to list
//return list

