/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.BackEndFriendManagement;

import NotificationsBackEnd.NotificationsManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class FriendManager { // handles friend requests
    private static FriendManager instance = null;
    private ArrayList<Map<String, String>> friendRequestsList;
    
    private FriendManager(){
         // singelton (private constructor)
    }
    public static synchronized FriendManager getInstance(){
        if(instance == null){
            instance = new FriendManager();
        }
        return instance;
    }
    
    public void sendFriendRequest(String senderId, String receiverId){
        ReadFriendRequestsFromJSON reader = new ReadFriendRequestsFromJSON();
        this.friendRequestsList = reader.readToListOfMaps();
        for(Map<String, String> request: this.friendRequestsList){
            String senderList = request.get("sender");
            String receiverList = request.get("receiver");
            String requestStatusList = request.get("requestStatus");
            if(senderList != null && receiverList != null && senderId.equals(senderList) && receiverId.equals(receiverList)){
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
            newRequest.put("sender", senderId);
            newRequest.put("receiver", receiverId);
            newRequest.put("requestStatus", "Pending");
            this.friendRequestsList.add(newRequest);
            WriteFriendRequestsToJSON writer = new WriteFriendRequestsToJSON();
            writer.writeFromListOfMaps(this.friendRequestsList);
            NotificationsManager n = NotificationsManager.getInstance();
            n.addFriendRequestNotification(senderId, receiverId);
    }

    public void respondToFriendRequest(String senderId, String receiverId, String response){
        ReadFriendRequestsFromJSON reader = new ReadFriendRequestsFromJSON();
        this.friendRequestsList = reader.readToListOfMaps();
        Iterator<Map<String, String>> iterator = this.friendRequestsList.iterator();
        while(iterator.hasNext()){
            Map<String, String> request = iterator.next();
            String senderList = request.get("sender");
            String receiverList = request.get("receiver");
            String requestStatusList = request.get("requestStatus");
            if(senderList != null && receiverList != null && senderId.equals(senderList) && receiverId.equals(receiverList)){
                if(requestStatusList.equals("Pending")){
                    if(response.equals("Declined")){
                        iterator.remove();
                    }
                    else{
                        request.put("requestStatus", response);
                    }
                    NotificationsManager manager = NotificationsManager.getInstance();
                    manager.removeFriendRequestNotification(senderId, receiverId);
                    manager.removeFriendRequestNotification(receiverId, senderId);
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
            if(senderList != null && receiverList != null && ((userId.equals(senderList) && friendId.equals(receiverList)) || (userId.equals(receiverList) && friendId.equals(senderList)))){
                if(requestStatusList.equals("Accepted")){
                    request.put("requestStatus", "Blocked");
                }
                WriteFriendRequestsToJSON writer = new WriteFriendRequestsToJSON();
                writer.writeFromListOfMaps(this.friendRequestsList);
            }
        }
    }

    public void blockNonFriend(String userId, String nonFriendId){
        ReadFriendRequestsFromJSON reader = new ReadFriendRequestsFromJSON();
        this.friendRequestsList = reader.readToListOfMaps();
        Map<String, String> blockMap = new HashMap<>();
        blockMap.put("sender", userId);
        blockMap.put("receiver", nonFriendId);
        blockMap.put("requestStatus", "Blocked");
        this.friendRequestsList.add(blockMap);
        WriteFriendRequestsToJSON writer = new WriteFriendRequestsToJSON();
        writer.writeFromListOfMaps(this.friendRequestsList);
        NotificationsManager manager = NotificationsManager.getInstance();
        manager.removeFriendRequestNotification(userId, nonFriendId);
        manager.removeFriendRequestNotification(nonFriendId, userId);
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
            if(senderList != null && receiverList != null && (userId.equals(senderList) && friendId.equals(receiverList)) || (userId.equals(receiverList) && friendId.equals(senderList))){
                if(requestStatusList.equals("Accepted")){
                    iterator.remove();
                }
                WriteFriendRequestsToJSON writer = new WriteFriendRequestsToJSON();
                writer.writeFromListOfMaps(this.friendRequestsList);
            }
        }
    }

}
