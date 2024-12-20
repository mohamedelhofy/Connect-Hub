/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.FrontEndContentCreation;

import connect.hub.BackEndContentCreation.Post;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author رحمه صبرى
 */
public class AddComment {

    public static void createAndShowGUI(Post post) {
        JFrame frame = new JFrame("Add Comment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200); // Adjusted size for smaller UI
        frame.setLocationRelativeTo(null); // Center the frame

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel instructionsLabel = new JLabel("Enter your comment:");
        instructionsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        instructionsLabel.setForeground(new Color(24, 119, 242));
        panel.add(instructionsLabel, BorderLayout.NORTH);

        JTextArea commentArea = new JTextArea(3, 20); // Smaller text area
        commentArea.setLineWrap(true);
        commentArea.setWrapStyleWord(true);
        commentArea.setFont(new Font("Arial", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(commentArea);
        scrollPane.setPreferredSize(new Dimension(250, 60)); // Reduced size
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton submitButton = new JButton("Submit Comment");
        submitButton.setBackground(new Color(24, 119, 242));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 12));
        submitButton.setFocusPainted(false);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userId = "User01";
                String comment = commentArea.getText().trim();
                if (!comment.isEmpty()) {
                    post.setComment(userId, comment);
                    commentArea.setText(""); // Clear the comment area
                            System.out.print(post.getComments());
                            frame.dispose();

                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a comment before submitting.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }
}
