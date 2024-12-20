/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author رحمه صبرى
 */
public class readGroupFromJSON {

    public static List<Map<String, Object>> getGroupListDB() {
        List<Map<String, Object>> dataList = new ArrayList<>();
        Path filePath = Path.of("Group.json");

        try {
            if (Files.exists(filePath)) {
                String jsonContent = Files.readString(filePath);

                try {
                    JSONArray jsonArray = new JSONArray(jsonContent);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Map<String, Object> dataMap = new HashMap<>();
                            dataMap.put("groupName", jsonObject.optString("groupName", "Unknown"));
                            dataMap.put("groupDescription", jsonObject.optString("groupDescription", "No Description"));
                            dataMap.put("groupPhotoPath", jsonObject.optString("groupPhotoPath", ""));
                            dataMap.put("PrimaryAdmin", jsonObject.optString("PrimaryAdmin", "No Admin"));

                            // Convert admins JSONArray to List
                            JSONArray adminsArray = jsonObject.optJSONArray("admins");
                            List<String> adminsList = new ArrayList<>();
                            if (adminsArray != null) {
                                for (int j = 0; j < adminsArray.length(); j++) {
                                    adminsList.add(adminsArray.optString(j, ""));
                                }
                            }
                            dataMap.put("admins", adminsList);

                            // Convert members JSONArray to List
                            JSONArray membersArray = jsonObject.optJSONArray("members");
                            List<String> membersList = new ArrayList<>();
                            if (membersArray != null) {
                                for (int j = 0; j < membersArray.length(); j++) {
                                    membersList.add(membersArray.optString(j, ""));
                                }
                            }
                            dataMap.put("members", membersList);

                            // Convert postsId JSONArray to List
                            JSONArray postsIdArray = jsonObject.optJSONArray("postsId");
                            List<String> postsIdList = new ArrayList<>();
                            if (postsIdArray != null) {
                                for (int j = 0; j < postsIdArray.length(); j++) {
                                    postsIdList.add(postsIdArray.optString(j, ""));
                                }
                            }
                            dataMap.put("postsId", postsIdList);

                            // Convert memberRequst JSONArray to List
                            JSONArray memberRequestArray = jsonObject.optJSONArray("memberRequest");
                            List<String> memberRequestList = new ArrayList<>();
                            if (memberRequestArray != null) {
                                for (int j = 0; j < memberRequestArray.length(); j++) {
                                    memberRequestList.add(postsIdArray.optString(j, ""));
                                }
                            }
                            dataMap.put("memberRequest", memberRequestList);

                            dataList.add(dataMap);

                        } catch (JSONException e) {
                            System.err.println("Error processing a group entry: " + e.getMessage());
                        }
                    }
                } catch (JSONException e) {
                    System.err.println("Invalid JSON format in file: " + e.getMessage());
                }
            } else {
                System.out.println("File does not exist: " + filePath);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }

        return dataList;
    }

    public  List<Group> convertToGroupList() {
        List<Map<String, Object>> dataList = getGroupListDB();
        List<Group> groups = new ArrayList<>();
        for (Map<String, Object> dataMap : dataList) {
            String groupName = (String) dataMap.get("groupName");
            String groupDescription = (String) dataMap.get("groupDescription");
            String groupPhotoPath = (String) dataMap.get("groupPhotoPath");
            List<String> admins = (List<String>) dataMap.get("admins");
            List<String> members = (List<String>) dataMap.get("members");

            // Ensure members is non-null
            if (members == null) {
                members = new ArrayList<>();
            }
            
            if (admins == null) {
                admins = new ArrayList<>();
            }
            groups.add(new Group(groupName, groupDescription, groupPhotoPath,admins, members));
        }
        return groups;
    }

}
