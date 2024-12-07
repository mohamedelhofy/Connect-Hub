/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.BackEndContentCreation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class checkRequestStatus {
    private ArrayList<Map<String, String>> friendRequestsList;    
    
    public String check(String senderId, String receiverId){
        String requestStatus = checkTwice(senderId, receiverId);
        if(requestStatus == null){
            return checkTwice(receiverId, senderId);
        }
        return requestStatus;
    }
    private String checkTwice(String senderId, String receiverId){
        ReadFriendRequestsFromJSON reader = new ReadFriendRequestsFromJSON();
        this.friendRequestsList = reader.readToListOfMaps();
        for(Map<String, String> request: this.friendRequestsList){
            String senderList = request.get("sender");
            String receiverList = request.get("receiver");
            String requestStatusList = request.get("requestStatus");
            if(senderList != null && receiverList != null && senderId.equals(senderList) && receiverId.equals(receiverList))
                    return requestStatusList;
        }
        return null;
    }
}
