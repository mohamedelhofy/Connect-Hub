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
    
    public void sendFriendRequest(String senderId, String receiverId){
        ManageRequest manageRequest = new ManageRequest(senderId, receiverId);
        manageRequest.send();
    }

    public void respondToFriendRequest(String senderId, String receiverId, String response){
        ManageRequest manageRequest = new ManageRequest(senderId, receiverId);
        manageRequest.respond(response);
    }

    public void blockFriend(String userId, String friendId){
        ManageRequest manageRequest = new ManageRequest(userId, friendId);
        manageRequest.blockFriend(userId, friendId);
    }

    public void removeFriend(String userId, String friendId){
        ManageRequest manageRequest = new ManageRequest(userId, friendId);
        manageRequest.removeFriend(userId, friendId);
    }

    public void getFriendSuggestions(String userId){
        ManageRequest manageRequest = new ManageRequest(userId);
        manageRequest.suggest();
    }

}
