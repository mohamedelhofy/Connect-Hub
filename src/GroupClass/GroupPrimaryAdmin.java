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
public class GroupPrimaryAdmin extends GroupAdmin {

    public GroupPrimaryAdmin() {
    }
    
    @Override
    public void removeMember(String userId){
        super.getMembers().remove(userId);
        super.getAdmins().remove(userId);
    }
    
    public void promoteAdmin(String userId){
        super.getMembers().remove(userId);
        super.getAdmins().add(userId);
    }
    
    public void demoteAdmin(String userId){
        super.getAdmins().remove(userId);
        super.getMembers().add(userId);
    }
    
    public void deleteGroup(){
        Path path = Path.of("Group.json");
        JSONArray jsonArray;
        try {
            if (Files.exists(path)) {
                String jsonContent = Files.readString(path);
                try {
                    jsonArray = new JSONArray(jsonContent);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        if (super.getGroupName().equals(jsonObject.getString("groupName"))) {
                            jsonArray.remove(i); 
                            break; 
                        }
                    }

                    Files.writeString(path, jsonArray.toString(4), StandardOpenOption.TRUNCATE_EXISTING);

                } catch (JSONException e) {
                    System.err.println("Invalid JSON format: " + e.getMessage());
                }
            } else {
                System.out.println("File not found: ");
            }
        } catch (IOException e) {
            System.err.println("Error accessing the file: " + e.getMessage());
        }
    }
}
