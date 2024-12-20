package NotificationsFrontEnd;

import NotificationsBackEnd.NotificationsDatabase;
import connect.hub.FrontEndFriendManagement.AcceptButtonEditor;
import connect.hub.FrontEndFriendManagement.BlockButtonEditor;
import connect.hub.FrontEndFriendManagement.DeclineButtonEditor;
import connect.hub.User;
import connect.hub.UserSearchFrontEnd.ButtonRenderer;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NotificationWindow extends JFrame implements Runnable {
    private User userInstance = User.getInstance();
    private ArrayList<User> list;
    private ArrayList<String> groupList;
    private DefaultTableModel tableModel;
    private DefaultTableModel groupTableModel;
    private NotificationsDatabase database;
    
    private volatile boolean running = true; // Flag to control the thread
    private JPanel mainPanel; // for dynamic updates

    public NotificationWindow() {
        tableModel = new DefaultTableModel(new Object[]{"Username", "Accept", "Decline", "Block"}, 0);
        groupTableModel = new DefaultTableModel(new Object[]{"Group Notification"}, 0);

        // Set up the frame
        this.setTitle("Notifications");
        this.setSize(1200, 750);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(true);
        this.setLocation(200, 100);
        this.setLayout(new BorderLayout());

        // stop the thread when the window is closed
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                running = false; 
            }
        });

        // holds all dynamic components
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        this.add(mainPanel, BorderLayout.CENTER);

        loadComponents();
    }

    private void loadComponents() {
        mainPanel.removeAll();

        // Reload the lists
        database = new NotificationsDatabase(userInstance.getUserId());
        list = database.getUsersFriendRequestsNotificationsList();
        groupList = database.getGroupNotifications();

        // Populate friend request table
        tableModel.setRowCount(0); // Clear existing rows
        for (User user : list) {
            String name = user.getUsername();
            tableModel.addRow(new Object[]{name, "Accept", "Decline", "Block"});
        }
        JTable table = new JTable(tableModel);
        table.getColumn("Accept").setCellRenderer(new ButtonRenderer());
        table.getColumn("Accept").setCellEditor(new AcceptButtonEditor(new JCheckBox(), userInstance.getUserId(), list, tableModel));
        table.getColumn("Decline").setCellRenderer(new ButtonRenderer());
        table.getColumn("Decline").setCellEditor(new DeclineButtonEditor(new JCheckBox(), userInstance.getUserId(), list, tableModel));
        table.getColumn("Block").setCellRenderer(new ButtonRenderer());
        table.getColumn("Block").setCellEditor(new BlockButtonEditor(new JCheckBox(), userInstance.getUserId(), list, tableModel));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 50, 500, 500);
        TitledBorder titledBorder = new TitledBorder("Received Friend Requests");
        scrollPane.setBorder(titledBorder);
        mainPanel.add(scrollPane);

        // Populate group notifications table
        groupTableModel.setRowCount(0); // Clear existing rows
        for (String notification : groupList) {
            groupTableModel.addRow(new Object[]{notification});
        }
        JTable groupTable = new JTable(groupTableModel);
        JScrollPane groupScrollPane = new JScrollPane(groupTable);
        groupScrollPane.setBounds(600, 50, 500, 500);
        TitledBorder groupTitledBorder = new TitledBorder("Group Notifications");
        groupScrollPane.setBorder(groupTitledBorder);
        mainPanel.add(groupScrollPane);

        // Refresh the panel
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    @Override
    public void run() {
        while (running) {
            // Dynamically refresh the data and UI
            SwingUtilities.invokeLater(this::loadComponents);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(NotificationWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
