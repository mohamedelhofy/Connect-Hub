package Chat;


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
    private String sender ;
    private String reciever ;
    private String messageContent ;
    private LocalDate timeOfMessage ;
    public Message() {
    }
    public Message(String sender, String reciever, String messageContent, LocalDate timeOfMessage) {
        this.sender = sender;
        this.reciever = reciever;
        this.messageContent = messageContent;
        this.timeOfMessage = timeOfMessage;
    }

    public String getSender() {
        return sender;
    }

    public String getReciever() {
        return reciever;
    }
    public String getMessageContent() {
        return messageContent;
    }

    public LocalDate getTimeOfMessage() {
        return timeOfMessage;
    }
    @Override
    public String toString() {
        return sender + " -> " + reciever + ": " + messageContent + " [" + timeOfMessage + "]";
    }
}

