/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.BackEndContentCreation;

/**
 *
 * @author Lenovo
 */
public class FriendManager { // handles friend requests
    private static FriendManager instance = null;
    
    private FriendManager(){
         // singelton (private constructor)
    }
    public static FriendManager getInstance(){
        if(instance == null){
            instance = new FriendManager();
        }
        return instance;
    }
    
    public static void sendFriendRequest(String senderId, String receiverId){
        SendRequest friendRequestManager = new SendRequest(senderId, receiverId);
        friendRequestManager.send();
    }

    public static void respondToFriendRequest(String senderId, String receiverId, String response){
        SendRequest friendRequestManager = new SendRequest(senderId, receiverId);
        friendRequestManager.respond(response);
    }

    public static void blockFriend(String userId, String friendId){
        
    }

    public static void removeFriend(String userId, String friendId){
        
    }

    public static void getFriendSuggestions(String userId){
        
    }

}
