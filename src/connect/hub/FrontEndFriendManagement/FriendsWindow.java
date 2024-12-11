/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.FrontEndFriendManagement;

import connect.hub.BackEndFriendManagement.FriendshipDatabase;
import connect.hub.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class FriendsWindow extends JFrame implements ActionListener{
//  View and respond to friend requests. 
//  View and manage the friend list. 
//  View and send friend suggestions. 
//  Block or remove friends
    private User userInstance = User.getInstance();

    private JButton backButton = new JButton("Back");

    private ArrayList<User> friendsList;

    private DefaultTableModel friendsTableModel = new DefaultTableModel(new Object[]{"Username", "Remove", "Block"}, 0);
    
    public void showFrame(){
        // FRAME
        this.setTitle("Friends");
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(true);
        this.setLocation(400, 20);
        this.setLayout(null);

        this.backButton.setBounds(600, 700, 230, 60);
        this.add(this.backButton);
        this.backButton.addActionListener(this);
        FriendshipDatabase friendShip = new FriendshipDatabase("User1"); ///////////////////////////////////////////////////////////
        this.friendsList = friendShip.getFriendList();
        String name;
        for(User user : this.friendsList){
            name = user.getUsername();
            this.friendsTableModel.addRow(new Object[]{name, "Remove", "Block"});
        }
        JTable friendsTable = new JTable(friendsTableModel);
        
        // Add a custom renderer and editor for the button columns
        friendsTable.getColumn("Remove").setCellRenderer(new ButtonRenderer2());
        friendsTable.getColumn("Remove").setCellEditor(new RemoveButtonEditor(new JCheckBox(), "User1", friendsList, friendsTableModel));
        friendsTable.getColumn("Block").setCellRenderer(new ButtonRenderer3());
        friendsTable.getColumn("Block").setCellEditor(new BlockButtonEditor(new JCheckBox(), "User1", friendsList, friendsTableModel));
        
        ////////// i used User1 !!!!!!!!!!!!!!!!!!
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(friendsTable);
        scrollPane.setBounds(50, 50, 500, 500);
        TitledBorder titledBorder = new TitledBorder("Friends");
        scrollPane.setBorder(titledBorder);
        this.add(scrollPane);
        this.setVisible(true);       

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==backButton){
            dispose();
            FriendManagementInterface fmi = new FriendManagementInterface();
            fmi.showFrame();
        }
    }
}