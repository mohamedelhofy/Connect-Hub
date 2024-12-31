/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chat;

import connect.hub.BackEndFriendManagement.FriendshipDatabase;
import connect.hub.FrontEndFriendManagement.ButtonRenderer2;
import connect.hub.NewsfeedPage;
import connect.hub.User;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class FriendList extends JFrame {
    private User userInstance = User.getInstance();
    private JButton backButton = new JButton("Back");
    private DefaultTableModel friendsTableModel = new DefaultTableModel(new Object[]{"Username", "Chat"}, 0);

    public FriendList() {
        setTitle("Friends Chat");
        setSize(600, 750);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); // Center the frame
        setLayout(new BorderLayout());

        // Back button
        backButton.addActionListener(e -> {
            dispose();
            
            new NewsfeedPage().setVisible(true);
        });

        // Friend list setup
//        FriendshipDatabase friendshipDatabase = new FriendshipDatabase("User1");

        FriendshipDatabase friendshipDatabase = new FriendshipDatabase(userInstance.getUserId());
        ArrayList<User> friendsList = friendshipDatabase.getFriendList();
        for (User user : friendsList) {

            friendsTableModel.addRow(new Object[]{user.getUserId(), "Chat"});
        }

        // Friends table
        JTable friendsTable = new JTable(friendsTableModel);
        friendsTable.getColumn("Chat").setCellRenderer(new ButtonRenderer());
        friendsTable.getColumn("Chat").setCellEditor(new ButtonEditor(new JCheckBox()));
        JScrollPane scrollPane = new JScrollPane(friendsTable);
        scrollPane.setBorder(new TitledBorder("Friends"));

        // Add components to frame
        add(scrollPane, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new NewsfeedPage().setVisible(true);
                dispose();
            }
        });
    }
}
