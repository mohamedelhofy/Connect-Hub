/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author rawan
 */
public class suggestGroup {
      String filePath = "Group.json";
      List<Group> groups = new ArrayList<>();
    //  Group group = new Group;
          public static List<Group> convertToGroupList(List<Map<String, Object>> dataList) {
              
        List<Group> groups = new ArrayList<>();
        for (Map<String, Object> dataMap : dataList) {
            String groupName = (String) dataMap.get("groupName");
            String groupDescription = (String) dataMap.get("groupDescription");
            String groupPhotoPath = (String) dataMap.get("groupPhotoPath");
            List<String> members = (List<String>) dataMap.get("members");
            
            //// put members in list 
            groups.add(new Group(groupName, groupDescription, groupPhotoPath));
        }
        return groups;
    }
   //// start suggesting 
    
          
          
        /// get all users
 public static Map<String, List<String>> suggestGroups(List<Group> groups) {
        // Extract all users from the data
        Set<String> allUsers = new HashSet<>();
        for (Group group : groups) {
            allUsers.addAll(group.getMembers());
        }
      
        // Suggest groups for users who are not in the group    
        Map<String, List<String>> suggestions = new HashMap<>();
        for (String user : allUsers) {
            suggestions.put(user, new ArrayList<>());
        for (Group group : groups) {
                if (!group.getMembers().contains(user)) {
                    suggestions.get(user).add(group.getGroupName());
                }
            }
        }

        return suggestions;
    }
      
 
 ////view the suggestions ////
 public static void printSuggestions(Map<String, List<String>> suggestions) {
        for (Map.Entry<String, List<String>> entry : suggestions.entrySet()) {
            System.out.println("User " + entry.getKey() + " is not in the following groups: " + String.join(", ", entry.getValue()));
}}
}

