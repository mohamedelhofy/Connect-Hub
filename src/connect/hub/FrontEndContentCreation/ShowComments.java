/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.FrontEndContentCreation;

import connect.hub.BackEndContentCreation.Post;
import java.awt.BorderLayout;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.*;

/**
 *
 * @author رحمه صبرى
 */
public class ShowComments extends JFrame {

    private JTextArea commentsArea;
    private JScrollPane scrollPane;

    public ShowComments() {
        // Set up the frame
        setTitle("Comments");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        // Set up the comments area
        commentsArea = new JTextArea();
        commentsArea.setEditable(false); // Make the text area non-editable
        commentsArea.setLineWrap(true);  // Allow line wrapping
        commentsArea.setWrapStyleWord(true);

        // Add a scroll pane to handle overflow of comments
        scrollPane = new JScrollPane(commentsArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Add the scroll pane to the frame
        add(scrollPane, BorderLayout.CENTER);
    }

    public void showComments(Post post) {
        // Retrieve the list of comments from the Post object
        List<Map<String, String>> comments = post.getComments();

        if (comments == null || comments.isEmpty()) {
            commentsArea.setText("No comments available.");
            return;
        }

        // Build the comment string to display in the text area
        StringBuilder commentText = new StringBuilder();
        for (Map<String, String> commentMap : comments) {
            String comment = commentMap.get("comment");
            if (comment != null) {
                commentText.append(comment).append("\n");
            }
        }

        // Set the text of the JTextArea
        commentsArea.setText(commentText.toString());
    }
}
