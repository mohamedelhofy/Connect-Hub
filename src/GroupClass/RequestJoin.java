/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupClass;

import GroupFront.suggestGui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import org.json.JSONArray;

/**
 *
 * @author rawan
 */
public class RequestJoin implements ActionListener{
        private String userId;
        private String groupName;
        private List<Map<String, Object>> groupListDB;
        
      public RequestJoin(String userId, String groupName, List<Map<String, Object>> groupListDB) {
            this.userId = userId;
            this.groupName = groupName;
            this.groupListDB = groupListDB;
        }
    @Override
public void actionPerformed(ActionEvent e) {
    for (Map<String, Object> groupData : groupListDB) {
        if (groupData.get("groupName").equals(groupName)) {
            List<String> memberRequest = (List<String>) groupData.get("memberRequest");
            if (memberRequest == null) {
                memberRequest = new ArrayList<>();
                groupData.put("memberRequest", memberRequest);
            }
            if (!memberRequest.contains(userId)) {
                memberRequest.add(userId);
                /// i will put it in GUI
               /// JOptionPane.showMessageDialog(suggestGui.this, "Request to join " + groupName + " sent.");
                /// put the new reuqest to the file 
                updateJSONFile(groupListDB);
            } else {
                /// i will put it in GUI
               // JOptionPane.showMessageDialog(suggestGui.this, "You have already requested to join " + groupName + ".");
            }
            break;
        }
    }
}
    private void updateJSONFile(List<Map<String, Object>> groupListDB) {
        JSONArray jsonArray = new JSONArray(groupListDB);
        try {
            Files.write(Paths.get("Group.json"), jsonArray.toString().getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }    }

   


