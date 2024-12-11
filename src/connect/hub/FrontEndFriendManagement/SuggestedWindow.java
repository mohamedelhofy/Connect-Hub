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
public class SuggestedWindow extends JFrame implements ActionListener{
//  View and respond to friend requests. 
//  View and manage the friend list. 
//  View and send friend suggestions. 
//  Block or remove friends
    private User userInstance = User.getInstance();

    private JButton backButton = new JButton("Back");

    private ArrayList<User> suggestedList;

    private DefaultTableModel suggestedTableModel = new DefaultTableModel(new Object[]{"Username", "Add"}, 0);
    
    public void showFrame(){
        // FRAME
        this.setTitle("Suggested Friendship");
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
        this.suggestedList = friendShip.getSuggestedList();
        String name;
        for(User user : this.suggestedList){
            name = user.getUsername();
            this.suggestedTableModel.addRow(new Object[]{name, "Add"});
        }
        JTable suggestedTable = new JTable(suggestedTableModel);
        
        // Add a custom renderer and editor for the button columns
        suggestedTable.getColumn("Add").setCellRenderer(new ButtonRenderer4());
        suggestedTable.getColumn("Add").setCellEditor(new AddButtonEditor(new JCheckBox(), "User1", suggestedList, suggestedTableModel));
        
        ////////// i used User1 !!!!!!!!!!!!!!!!!!
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(suggestedTable);
        scrollPane.setBounds(50, 50, 500, 500);
        TitledBorder titledBorder = new TitledBorder("suggested friendship");
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