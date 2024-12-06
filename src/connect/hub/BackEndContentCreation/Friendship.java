/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.BackEndContentCreation;

/**
 *
 * @author Lenovo
 */
public class Friendship { // Manages relationships between users
    private String userId1;
    private String userId2;
    private boolean isBlocked;
    
    public Friendship(String userId1, String userId2){
        this.userId1 = userId1;
        this.userId2 = userId2;
    }
}
