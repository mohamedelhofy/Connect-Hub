/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.FrontEndFriendManagement;

import connect.hub.BackEndFriendManagement.User;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class FriendStatusWindow extends JFrame{
    private ArrayList<User> friendsList;
    private DefaultTableModel tableModel;
    
    
    public void showFrame(){
        // FRAME
        this.setTitle("Friends Status");
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(true);
        this.setLocation(400, 20);
        this.setLayout(null);
        
    this.tableModel = new DefaultTableModel(new Object[]{"Name", "Status"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table cells non-editable
            }
        };


        
        
//        FriendShipDatabase friendShip = new FriendShipDatabase(user1.getUserId());
//        this.friendsList = friendShip.getFriendList();
        for (User friend : friendsList) {
            String status;
            if(friend.isOnline())
                status = "Online";
            else
                status = "Offline";
            tableModel.addRow(new Object[]{friend.getUsername(), status});
        }

        // Create table and set its model
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 50, 900, 900);

        this.add(scrollPane);
    }
}
