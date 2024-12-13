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
    
    private NotificationsManager(){
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
}
