
import connect.hub.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rawan
 */
public class ChatBackend {
    
        List<User> users = new ArrayList<>();

        User user = User.getInstance();
        List<Message> messages = new ArrayList<>();
        
           public User getUserById(String userId) {
          return users.stream().filter(user -> user.getUserId().equals(userId)).findFirst().orElse(null);
    }
     /// send message /////
        public void sendMessage(String Sender , String Reciever , String content){
         LocalDate timestamp = LocalDate.now();
          User sender = getUserById(Sender);
          User reciever = getUserById(Reciever);
          //LocalDate sendingDate = null ;  ///////////////////---
          if (sender != null && reciever != null) {
            messages.add(new Message(sender, reciever, content, timestamp));
        }
        }
        public List<Message> getMessages(String userId1, String userId2) {
        List<Message> chatHistory = new ArrayList<>();
        for (Message message : messages) {
            if ((message.getSender().getUserId().equals(userId1) && message.getReciever() .getUserId().equals(userId2)) ||
                (message.getSender().getUserId().equals(userId2) && message.getReciever() .getUserId().equals(userId1))) {
                chatHistory.add(message);
            }
        }
        return chatHistory;
    }
}


