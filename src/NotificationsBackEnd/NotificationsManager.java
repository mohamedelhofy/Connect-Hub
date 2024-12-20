/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NotificationsBackEnd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Lenovo
 */
public class NotificationsManager{ // handles Notifications
    private static NotificationsManager instance = null;
    private ArrayList<Map<String, String>> allFriendRequestsNotificationsList;
    private ArrayList<Map<String, String>> allGroupNotifications;
    public NotificationsManager(){
         // singelton (private constructor)
    }
    public static synchronized NotificationsManager getInstance(){
        if(instance == null){
            instance = new NotificationsManager();
        }
        return instance;
    }
    
    public void addFriendRequestNotification(String senderId, String receiverId){
        ReadReceivedFriendRequestsNotificationsFromJSON reader = new ReadReceivedFriendRequestsNotificationsFromJSON();
        this.allFriendRequestsNotificationsList = reader.readToListOfMaps();
        Map<String, String> newNotification = new HashMap<>();
        newNotification.put("sender", senderId);
        newNotification.put("receiver", receiverId);
        this.allFriendRequestsNotificationsList.add(newNotification);
        WriteReceivedFriendRequestsNotificationsToJSON writer = new WriteReceivedFriendRequestsNotificationsToJSON();
        writer.writeFromListOfMaps(this.allFriendRequestsNotificationsList);
    }
    
    public void removeFriendRequestNotification(String senderId, String receiverId){
        ReadReceivedFriendRequestsNotificationsFromJSON reader = new ReadReceivedFriendRequestsNotificationsFromJSON();
        this.allFriendRequestsNotificationsList = reader.readToListOfMaps();
        Iterator<Map<String, String>> iterator = this.allFriendRequestsNotificationsList.iterator();
        while(iterator.hasNext()){
            Map<String, String> request = iterator.next();
            String senderList = request.get("sender");
            String receiverList = request.get("receiver");
            if(senderList != null && receiverList != null && senderId.equals(senderList) && receiverId.equals(receiverList)){
                iterator.remove();
            }
        WriteReceivedFriendRequestsNotificationsToJSON writer = new WriteReceivedFriendRequestsNotificationsToJSON();
        writer.writeFromListOfMaps(this.allFriendRequestsNotificationsList);
        }
    }
    
    public void addNewGroupMemberNotification(String addedMemberId, String groupName){
        String type = "new Member";
        NotificationReadWriteManager readWriteManager = new NotificationReadWriteManager();
        allGroupNotifications = readWriteManager.readToListOfMaps();
        Map<String, String> newNotification = new HashMap<>();
        newNotification.put("type", type);
        newNotification.put("addedMemberId", addedMemberId);
        newNotification.put("groupName", groupName);
        allGroupNotifications.add(newNotification);
        readWriteManager.writeFromListOfMaps(allGroupNotifications);
    }

    public void addNewPostNotification(String authorId, String groupName){
        String type = "new Post";
        NotificationReadWriteManager readWriteManager = new NotificationReadWriteManager();
        allGroupNotifications = readWriteManager.readToListOfMaps();
        Map<String, String> newNotification = new HashMap<>();
        newNotification.put("type", type);
        newNotification.put("authorId", authorId);
        newNotification.put("groupName", groupName);
        allGroupNotifications.add(newNotification);
        readWriteManager.writeFromListOfMaps(allGroupNotifications);
    }

    public void addPromotionNotification(String userID, String groupName){
        String type = "promotion";
        NotificationReadWriteManager readWriteManager = new NotificationReadWriteManager();
        allGroupNotifications = readWriteManager.readToListOfMaps();
        Map<String, String> newNotification = new HashMap<>();
        newNotification.put("type", type);
        newNotification.put("userID", userID);
        newNotification.put("groupName", groupName);
        allGroupNotifications.add(newNotification);
        readWriteManager.writeFromListOfMaps(allGroupNotifications);
    }

    public void addDemotionNotification(String userID, String groupName){
        String type = "demotion";
        NotificationReadWriteManager readWriteManager = new NotificationReadWriteManager();
        allGroupNotifications = readWriteManager.readToListOfMaps();
        Map<String, String> newNotification = new HashMap<>();
        newNotification.put("type", type);
        newNotification.put("userID", userID);
        newNotification.put("groupName", groupName);
        allGroupNotifications.add(newNotification);
        readWriteManager.writeFromListOfMaps(allGroupNotifications);
    }
    
    public void addNewChatNotification(String senderId, String receiverId){
        String type = "newChat";
        NotificationReadWriteManager readWriteManager = new NotificationReadWriteManager();
        allGroupNotifications = readWriteManager.readToListOfMaps();
        Map<String, String> newNotification = new HashMap<>();
        newNotification.put("type", type);
        newNotification.put("senderId", senderId);
        newNotification.put("receiverId", receiverId);
        allGroupNotifications.add(newNotification);
        readWriteManager.writeFromListOfMaps(allGroupNotifications);
    }
    
    public void addCommentNotification(String commenterId, String authorId){
        String type = "newComment";
        NotificationReadWriteManager readWriteManager = new NotificationReadWriteManager();
        allGroupNotifications = readWriteManager.readToListOfMaps();
        Map<String, String> newNotification = new HashMap<>();
        newNotification.put("type", type);
        newNotification.put("commenterId", commenterId);
        newNotification.put("authorId", authorId);
        allGroupNotifications.add(newNotification);
        readWriteManager.writeFromListOfMaps(allGroupNotifications);
    }
}
