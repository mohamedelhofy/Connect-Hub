package Chat;

import Chat.Message;
import connect.hub.User;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

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
    
    List<Message> messages;

    public ChatBackend(){
        try {
            this.messages = chatFileHandler.readJsonFile();
        } catch (JSONException ex) {
            Logger.getLogger(ChatBackend.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
                Logger.getLogger(ChatBackend.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        

     /// send message /////
    public void sendMessage(String sender,String reciever , String content){
        LocalDate timestamp = LocalDate.now();
        if (sender != null && reciever != null) {
            messages.add(new Message(sender, reciever, content, timestamp));
            try {
                chatFileHandler.writeJsonFile(messages);
            } catch (IOException ex) {
                Logger.getLogger(ChatBackend.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(ChatBackend.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public List<Message> getMessages(String userId1, String userId2) {
        List<Message> chatHistory = new ArrayList<>();
        for (Message message : messages) {
            if ((message.getSender().equals(userId1) && message.getReciever() .equals(userId2)) ||
                (message.getSender().equals(userId2) && message.getReciever() .equals(userId1))) {
                chatHistory.add(message);
            }
        }
        return chatHistory;
    }
}


