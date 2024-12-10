/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.FrontEndFriendManagement;

import connect.hub.BackEndFriendManagement.FriendshipDatabase;
import connect.hub.User;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class FriendManagementInterface extends JFrame{
//  View and respond to friend requests. 
//  View and manage the friend list. 
//  View and send friend suggestions. 
//  Block or remove friends
    private User userInstance = User.getInstance();

    private ArrayList<User> pendingList;
    private ArrayList<User> friendsList;
    private ArrayList<User> suggestedList;

    private DefaultTableModel pendingTableModel = new DefaultTableModel(new Object[]{"Username", "Type", "Accept", "Decline"}, 0);
    private DefaultTableModel friendsTableModel = new DefaultTableModel(new Object[]{"Username", "Type", "Remove", "Block"}, 0);
    private DefaultTableModel suggestedTableModel = new DefaultTableModel(new Object[]{"Username", "Type", "Add"}, 0);
    
    public void showFrame(){
        // FRAME
        this.setTitle("Friend Management");
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(true);
        this.setLocation(400, 20);
        this.setLayout(null);
        
        FriendshipDatabase friendShip = new FriendshipDatabase("User1"); ///////////////////////////////////////////////////////////
        this.pendingList = friendShip.getFriendList();
        this.friendsList = friendShip.getFriendList();
        this.suggestedList = friendShip.getFriendList();
        String name;
        for(User user : this.pendingList){
            name = user.getUsername();
            this.pendingTableModel.addRow(new Object[]{name, "Friend Request", "Accept", "Decline"});
        }
        for(User user : this.friendsList){
            name = user.getUsername();
            this.friendsTableModel.addRow(new Object[]{name, "Friend", "Remove", "Block"});
        }
        for(User user : this.suggestedList){
            name = user.getUsername();
            this.suggestedTableModel.addRow(new Object[]{name, "Suggested Friendship", "Add"});
        }
        JTable pendingTable = new JTable(pendingTableModel);
        JTable friendsTable = new JTable(friendsTableModel);
        JTable suggestedTable = new JTable(suggestedTableModel);
        
        // Add a custom renderer and editor for the button columns
        pendingTable.getColumn("Accept").setCellRenderer(new ButtonRenderer());
        table.getColumn("Accept").setCellEditor(new ButtonEditor(new JCheckBox(), userInstance.getUserId(), pendingList));
    }
}
