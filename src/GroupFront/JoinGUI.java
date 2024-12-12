/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupFront;

import GroupClass.Group;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author رحمه صبرى
 */
public class JoinGUI {
    public void joinFrame(String userId) {
         // Create the main frame
          JFrame frame = new JFrame("Join Group");
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setSize(400, 200);
          frame.setLayout(new BorderLayout(10, 10));
          frame.setLocationRelativeTo(null);
  
          // Create a panel to hold the button
          JPanel buttonPanel = new JPanel();
          buttonPanel.setLayout(new BorderLayout());
          buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
  
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
            
        });

        // Add the button to the panel
        buttonPanel.add(joinButton, BorderLayout.CENTER);

        // Add the panel to the frame
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }

}
