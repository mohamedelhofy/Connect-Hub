/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author رحمه صبرى
 */
public class StoreGroupJSON {
    public void addToJSON(Group group) {
        Path filePath = Path.of("Group.json");
        JSONArray jsonArray;

        try {
            if (Files.exists(filePath)) {
                String jsonContent = Files.readString(filePath);

                try {
                    jsonArray = new JSONArray(jsonContent);
                } catch (JSONException e) {
                    System.err.println("Invalid JSON format detected. Creating a new JSON array.");
                    jsonArray = new JSONArray();
                }
            } else {
                jsonArray = new JSONArray();
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
            }

            JSONObject newEntry = new JSONObject();
            newEntry.put("groupName", group.getGroupName());
            newEntry.put("groupDescription", group.getGroupDescription());
            newEntry.put("groupPhotoPath", group.getGroupPhotoPath());
            newEntry.put("PrimaryAdmin", group.getPrimaryAdmin());
            newEntry.put("postsId", group.getPostsId());
            newEntry.put("admins", group.getAdmins());
            newEntry.put("members", group.getMembers());

            jsonArray.put(newEntry);
            Files.writeString(filePath, jsonArray.toString(4), StandardOpenOption.TRUNCATE_EXISTING);

        } catch (IOException e) {
            System.err.println("An IOException occurred while accessing the file: " + e.getMessage());
        } catch (JSONException e) {
            System.err.println("An error occurred while working with JSON data: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
