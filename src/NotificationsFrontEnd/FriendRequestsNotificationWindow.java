/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NotificationsFrontEnd;

import NotificationsBackEnd.NotificationsDatabase;
import connect.hub.FrontEndFriendManagement.AcceptButtonEditor;
import connect.hub.FrontEndFriendManagement.BlockButtonEditor;
import connect.hub.FrontEndFriendManagement.DeclineButtonEditor;
import connect.hub.User;
import connect.hub.UserSearchFrontEnd.ButtonRenderer;
import java.util.ArrayList;
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
public class FriendRequestsNotificationWindow extends JFrame{
    private User userInstance = User.getInstance();
    
    private ArrayList<User> list;

    private DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Username", "Accept", "Decline", "Block"}, 0);
    
    public void showFrame() {
        // FRAME
        this.setTitle("Friend Requests");
        this.setSize(600, 750);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(true);
        this.setLocation(400, 20);
        this.setLayout(null);

        // Reload the pending list
        
//////////////////////////////////////// User1 ////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////// User1 ////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////// User1 ////////////////////////////////////////////////////////////////////////////////
        NotificationsDatabase database = new NotificationsDatabase("User1");
        this.list = database.getUsersFriendRequestsNotificationsList();

        // Clear the existing table model and populate it again
        tableModel.setRowCount(0); // Clear existing rows
        for (User user : this.list) {
            String name = user.getUsername();
            tableModel.addRow(new Object[]{name, "Accept", "Decline", "Block"});
        }

        JTable table = new JTable(tableModel);

        // Add fresh button editors and renderers with the updated list
//////////////////////////////////////// User1 ////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////// User1 ////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////// User1 ////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////// User1 ////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////// User1 ////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////// User1 ////////////////////////////////////////////////////////////////////////////////
        table.getColumn("Accept").setCellRenderer(new ButtonRenderer());
        table.getColumn("Accept").setCellEditor(new AcceptButtonEditor(new JCheckBox(), "User1", list, tableModel));
        table.getColumn("Decline").setCellRenderer(new ButtonRenderer());
        table.getColumn("Decline").setCellEditor(new DeclineButtonEditor(new JCheckBox(), "User1", list, tableModel));
        table.getColumn("Block").setCellRenderer(new ButtonRenderer());
        table.getColumn("Block").setCellEditor(new BlockButtonEditor(new JCheckBox(), "User1", list, tableModel));
//////////////////////////////////////// User1 ////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////// User1 ////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////// User1 ////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////// User1 ////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////// User1 ////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////// User1 ////////////////////////////////////////////////////////////////////////////////
        


        
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 50, 500, 500);
        TitledBorder titledBorder = new TitledBorder("Received Friend Requests");
        scrollPane.setBorder(titledBorder);
        this.add(scrollPane);

        // Refresh the frame
        this.revalidate();
        this.repaint();
    }

}