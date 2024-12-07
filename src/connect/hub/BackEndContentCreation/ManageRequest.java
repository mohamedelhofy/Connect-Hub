/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.BackEndContentCreation;

import connect.hub.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class ManageRequest{ 
    private String senderId;
    private String receiverId;
    private String requestStatus;  // Pending/Accepted/Blocked
    private ArrayList<Map<String, String>> friendRequestsList;
     
    
    public ManageRequest(String senderId, String reveiverId){
        this.senderId = senderId;
        this.receiverId = reveiverId;
    }
    public ManageRequest(String senderId){
        this.senderId = senderId;
    }
        
    public void send(){ 
        ReadFriendRequestsFromJSON reader = new ReadFriendRequestsFromJSON();
        this.friendRequestsList = reader.readToListOfMaps();
        for(Map<String, String> request: this.friendRequestsList){
            String senderList = request.get("sender");
            String receiverList = request.get("receiver");
            String requestStatusList = request.get("requestStatus");
            if(senderList != null && receiverList != null && this.senderId.equals(senderList) && this.receiverId.equals(receiverList)){
                if(requestStatusList.equals("Pending")){
                    JOptionPane.showMessageDialog(null, "Request Pending, can't send twice", "MESSAGE", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(requestStatusList.equals("Accepted")){
                    JOptionPane.showMessageDialog(null, "Already Friends", "MESSAGE", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(requestStatusList.equals("Blocked")){
                    JOptionPane.showMessageDialog(null, "This User Blocked You", "MESSAGE", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }
            Map<String, String> newRequest = new HashMap<>();
            newRequest.put("sender", this.senderId);
            newRequest.put("receiver", this.receiverId);
            newRequest.put("requestStatus", "Pending");
            this.friendRequestsList.add(newRequest);
            WriteFriendRequestsToJSON writer = new WriteFriendRequestsToJSON();
            writer.writeFromListOfMaps(this.friendRequestsList);
    }
    
    public void respond(String response){ // response = Accepted/Declined/Blocked/
        ReadFriendRequestsFromJSON reader = new ReadFriendRequestsFromJSON();
        this.friendRequestsList = reader.readToListOfMaps();
        Iterator<Map<String, String>> iterator = this.friendRequestsList.iterator();
        while(iterator.hasNext()){
            Map<String, String> request = iterator.next();
            String senderList = request.get("sender");
            String receiverList = request.get("receiver");
            String requestStatusList = request.get("requestStatus");
            if(senderList != null && receiverList != null && this.senderId.equals(senderList) && this.receiverId.equals(receiverList)){
                if(requestStatusList.equals("Pending")){
                    if(response.equals("Declined")){
                        iterator.remove();
                    }
                    else{
                        request.put("requestStatus", response);
                    }
                }
            WriteFriendRequestsToJSON writer = new WriteFriendRequestsToJSON();
            writer.writeFromListOfMaps(this.friendRequestsList);
            }
        }
    }
    
    public void blockFriend(String userId, String friendId){
        ReadFriendRequestsFromJSON reader = new ReadFriendRequestsFromJSON();
        this.friendRequestsList = reader.readToListOfMaps();
        for (Map<String, String> request : this.friendRequestsList) {
            String senderList = request.get("sender");
            String receiverList = request.get("receiver");
            String requestStatusList = request.get("requestStatus");
            if(senderList != null && receiverList != null && this.senderId.equals(senderList) && this.receiverId.equals(receiverList)){
                if(requestStatusList.equals("Accepted")){
                    request.put("requestStatus", "Blocked");
                }
                WriteFriendRequestsToJSON writer = new WriteFriendRequestsToJSON();
                writer.writeFromListOfMaps(this.friendRequestsList);
            }
        }
    }

    public void removeFriend(String userId, String friendId){
        ReadFriendRequestsFromJSON reader = new ReadFriendRequestsFromJSON();
        this.friendRequestsList = reader.readToListOfMaps();
        Iterator<Map<String, String>> iterator = this.friendRequestsList.iterator();
        while(iterator.hasNext()){
            Map<String, String> request = iterator.next();
            String senderList = request.get("sender");
            String receiverList = request.get("receiver");
            String requestStatusList = request.get("requestStatus");
            if(senderList != null && receiverList != null && this.senderId.equals(senderList) && this.receiverId.equals(receiverList)){
                if(requestStatusList.equals("Accepted")){
                        iterator.remove();
                }
                WriteFriendRequestsToJSON writer = new WriteFriendRequestsToJSON();
                writer.writeFromListOfMaps(this.friendRequestsList);
            }
        }
    }
    
    public void suggest(){
        ReadFriendRequestsFromJSON reader = new ReadFriendRequestsFromJSON();
        this.friendRequestsList = reader.readToListOfMaps();
        ArrayList<User> suggestList;
        for (Map<String, String> request : this.friendRequestsList) {
            String senderList = request.get("sender");
            String receiverList = request.get("receiver");
            String requestStatusList = request.get("requestStatus");
            if((senderList != null && receiverList != null && !(this.senderId.equals(senderList) || this.senderId.equals(receiverList)))){
                
            }
        }

    }
}