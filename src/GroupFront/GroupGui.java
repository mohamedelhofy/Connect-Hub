/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupFront;

import GroupClass.Group;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Compu City
 */
public class GroupGui extends JFrame {

//    public GroupGui(Group group) {
 public GroupGui() {
        // Set up the frame
        setTitle("Group Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600); // Increased height to accommodate the description
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // Center the frame on the screen

        // Photo (use JLabel with an ImageIcon)
        String photoPath = "D:\\\\OOP\\\\Connect-Hub\\\\my_photo\\\\panda.jpg"; // Replace with your photo path
        ImageIcon groupPhoto = new ImageIcon(photoPath);
        JLabel photoLabel = new JLabel();
        photoLabel.setIcon(new ImageIcon(groupPhoto.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
        photoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Group name
        JLabel groupNameLabel = new JLabel("Group Name"); // Replace with your group name
        groupNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        groupNameLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Group description
        JTextArea groupDescriptionArea = new JTextArea("This is a description of the group. " +
                "You can include details about the group's purpose, members, and other relevant information.");
        groupDescriptionArea.setLineWrap(true);
        groupDescriptionArea.setWrapStyleWord(true);
        groupDescriptionArea.setFont(new Font("Arial", Font.PLAIN, 16));
        groupDescriptionArea.setEditable(false); // Make it non-editable
        groupDescriptionArea.setBackground(getBackground()); // Match the background color to the frame

        // Add a scroll pane in case the description is long
        JScrollPane descriptionScrollPane = new JScrollPane(groupDescriptionArea);
        descriptionScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        descriptionScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Button at the bottom
        JButton actionButton = new JButton("Click Me"); // Replace with your button text
        actionButton.setFont(new Font("Arial", Font.PLAIN, 18));
        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(GroupGui.this, "Button Clicked!");
            }
        });

        // Add components to the frame
        add(groupNameLabel, BorderLayout.NORTH);
        add(photoLabel, BorderLayout.CENTER);
        add(descriptionScrollPane, BorderLayout.EAST); // Add description to the East
        add(actionButton, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        // Create and show the GUI

    new GroupGui().setVisible(true);

    }
}
