/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupFront;

import GroupClass.Group;
import connect.hub.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
public class JoinGUI {

    public void joinFrame(User user, Group group) {
        // Create the main frame
        JFrame frame = new JFrame("Join Group");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250); // Increased height to accommodate the greeting
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

        // Create a panel to hold the button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        // Create the Join button
        JButton joinButton = new JButton("Join Group");
        joinButton.setPreferredSize(new Dimension(200, 50));
        joinButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        joinButton.setForeground(Color.WHITE);
        joinButton.setBackground(new Color(59, 89, 182));
        joinButton.setFocusPainted(false);
        joinButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(39, 59, 142), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Add a click listener to handle join actions
        joinButton.addActionListener(e -> {
            group.addToMembers(user.getUserId());// we need to update database
            frame.dispose();
            JOptionPane.showMessageDialog(frame, "Successfully joined the group!");
        });

        // Add the button to the panel
        buttonPanel.add(joinButton, BorderLayout.CENTER);

        // Add the greeting panel and the button panel to the frame
        frame.add(greetingPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
        }

}
