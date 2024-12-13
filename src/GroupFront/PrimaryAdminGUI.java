/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupFront;

import GroupClass.Group;
import GroupClass.GroupPrimaryAdmin;
import connect.hub.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author رحمه صبرى
 */
public class PrimaryAdminGUI {

    public static void PrimaryFrame(User user, Group group) {
        JFrame frame = new JFrame("Primary Admin Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300); // Increased height to accommodate both buttons and greeting
        frame.setLayout(new BorderLayout(10, 10));
        frame.setLocationRelativeTo(null);

        // Create a panel for the greeting message
        JPanel greetingPanel = new JPanel();
        greetingPanel.setLayout(new BorderLayout());
        greetingPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20)); // Added some space for the greeting

        // Create the greeting label
        JLabel greetingLabel = new JLabel("Hello, " + user.getUsername(), JLabel.CENTER);
        greetingLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        greetingLabel.setForeground(Color.BLACK);
        greetingPanel.add(greetingLabel, BorderLayout.CENTER);

        // Create a panel to hold the buttons in the same row
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Align buttons horizontally with gap of 20px
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        // Create the Delete Group button
        JButton deleteButton = new JButton("Delete Group");
        deleteButton.setPreferredSize(new Dimension(150, 50)); // Set button size
        deleteButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setBackground(new Color(59, 89, 182)); // Blue color
        deleteButton.setFocusPainted(false);
        deleteButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(39, 59, 142), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        //deleteButton.setBorderRadius(20); // Set rounded corners (ensure method is available)

        // Add a click listener for Delete Group button
        deleteButton.addActionListener(e -> {
            GroupPrimaryAdmin.deleteGroup(group);
            JOptionPane.showMessageDialog(frame, "Successfully deleted the group!");
        });

        // Create the View Group button
        JButton viewGroupButton = new JButton("View Group");
        viewGroupButton.setPreferredSize(new Dimension(150, 50)); // Set button size
        viewGroupButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        viewGroupButton.setForeground(Color.WHITE);
        viewGroupButton.setBackground(new Color(34, 139, 34)); // Green color
        viewGroupButton.setFocusPainted(false);
        viewGroupButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 100, 0), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        //viewGroupButton.setBorderRadius(20); // Set rounded corners (ensure method is available)

        // Add a click listener for View Group button
        viewGroupButton.addActionListener(e -> {
            GroupPrimaryAdmin adminGroup = new GroupPrimaryAdmin();
            adminGroup.setGroupName(group.getGroupName());
            adminGroup.setGroupPhotoPath(group.getGroupPhotoPath());
            adminGroup.setGroupDescription(group.getGroupDescription());
            adminGroup.setAdmins(group.getAdmins());
            adminGroup.setMemberRequst(group.getMemberRequst());
            adminGroup.setMembers(group.getMembers());
            adminGroup.setPostsId(group.getPostsId());
            adminGroup.setPrimaryAdmin(group.getPrimaryAdmin());
            
            new GroupPrimaryAdminGui(adminGroup).setVisible(true);
        });

        // Add the buttons to the panel (arranged horizontally in a row)
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewGroupButton);

        // Add the greeting panel and the button panel to the frame
        frame.add(greetingPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }
}
