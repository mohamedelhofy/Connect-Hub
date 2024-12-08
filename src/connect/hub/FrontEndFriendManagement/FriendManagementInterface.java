/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.FrontEndFriendManagement;

import connect.hub.BackEndFriendManagement.User;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;

/**
 *
 * @author Lenovo
 */
public class FriendManagementInterface extends JFrame{
//  View and respond to friend requests. 
//  View and manage the friend list. 
//  View and send friend suggestions. 
//  Block or remove friends

    private ArrayList<User> friendsList;

    DefaultListModel<User> listModel = new DefaultListModel<>();
    
    public void showFrame(){
        // FRAME
        this.setTitle("Friend Management");
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(true);
        this.setLocation(400, 20);
        this.setLayout(null);
//        User user1 = User.getInstance();
//        FriendShipDatabase friendShip = new FriendShipDatabase(user1.getUserId());
//        this.friendsList = friendShip.get
        
        
        
    }
}
