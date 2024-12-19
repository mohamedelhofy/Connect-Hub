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
public class NotificationWindow extends JFrame{
    private User userInstance = User.getInstance();
        
    private ArrayList<User> list;
    private ArrayList<String> groupList;

    private DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Username", "Accept", "Decline", "Block"}, 0);
    private DefaultTableModel groupTableModel = new DefaultTableModel(new Object[]{"Group Notification"}, 0);
    private NotificationsDatabase database;
    
    public void showFrame() {
        // FRAME
        this.setTitle("Notifications");
        this.setSize(1200, 750);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(true);
        this.setLocation(200, 100);
        this.setLayout(null);

        // Reload the pending list
        database = new NotificationsDatabase(userInstance.getUserId());
        this.list = database.getUsersFriendRequestsNotificationsList();
        groupList = database.getGroupNotifications();
        // Clear the existing table model and populate it again
        tableModel.setRowCount(0); // Clear existing rows
        for (User user : this.list) {
            String name = user.getUsername();
            tableModel.addRow(new Object[]{name, "Accept", "Decline", "Block"});
        }
        groupTableModel.setRowCount(0); // Clear existing rows
        for (String notification : this.groupList) {
            groupTableModel.addRow(new Object[]{notification});
        }

        JTable table = new JTable(tableModel);
        JTable groupTable = new JTable(groupTableModel);

        // Add fresh button editors and renderers with the updated list
        table.getColumn("Accept").setCellRenderer(new ButtonRenderer());
        table.getColumn("Accept").setCellEditor(new AcceptButtonEditor(new JCheckBox(), userInstance.getUserId(), list, tableModel));
        table.getColumn("Decline").setCellRenderer(new ButtonRenderer());
        table.getColumn("Decline").setCellEditor(new DeclineButtonEditor(new JCheckBox(), userInstance.getUserId(), list, tableModel));
        table.getColumn("Block").setCellRenderer(new ButtonRenderer());
        table.getColumn("Block").setCellEditor(new BlockButtonEditor(new JCheckBox(), userInstance.getUserId(), list, tableModel));       
                
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 50, 500, 500);
        TitledBorder titledBorder = new TitledBorder("Received Friend Requests");
        scrollPane.setBorder(titledBorder);
        this.add(scrollPane);
        
        JScrollPane groupScrollPane = new JScrollPane(groupTable);
        groupScrollPane.setBounds(600, 50, 500, 500);
        TitledBorder groupTitledBorder = new TitledBorder("Group Notifications");
        groupScrollPane.setBorder(groupTitledBorder);
        this.add(groupScrollPane);
        
        // Refresh the frame
        this.revalidate();
        this.repaint();
    }

}