
import connect.hub.User;
import java.time.LocalDate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rawan
 */
public class Message {
    // sender , Reciever , content , time 
      User user = User.getInstance();

    public Message() {
    }
      private User sender ;
      private User reciever ;
      private String messageContent ;
      private LocalDate timeOfMessage ;

    public Message(User sender, User reciever, String messageContent, LocalDate timeOfMessage) {
        this.sender = sender;
        this.reciever = reciever;
        this.messageContent = messageContent;
        this.timeOfMessage = timeOfMessage;
    }
   

    public User getUser() {
        return user;
    }

    public User getSender() {
        return sender;
    }

    public User getReciever() {
        return reciever;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setReciever(User reciever) {
        this.reciever = reciever;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public void setTimeOfMessage(LocalDate timeOfMessage) {
        this.timeOfMessage = timeOfMessage;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public LocalDate getTimeOfMessage() {
        return timeOfMessage;
    }


        @Override
    public String toString() {
        return sender.getUserId() + " -> " + reciever.getUserId() + ": " + messageContent + " [" + timeOfMessage + "]";
    }
}

