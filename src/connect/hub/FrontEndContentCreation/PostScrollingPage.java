/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.FrontEndContentCreation;
import connect.hub.BackEndContentCreation.UpdateDB;
import connect.hub.NewsfeedPage;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
/**
 *
 * @author رحمه صبرى
 */
public class PostScrollingPage {
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Posts");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(postPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel("Posts ", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(59, 89, 152));
        frame.add(titleLabel, BorderLayout.NORTH);

        loadPostsAndStories(postPanel);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new NewsfeedPage().setVisible(true);
                frame.dispose(); 
            }
        });
        frame.setVisible(true);
    }

    private static void loadPostsAndStories(JPanel postPanel) {
        String filePath = "PostsDB.json";
    try {
        String content = Files.readString(Paths.get(filePath));
        JSONArray posts = new JSONArray(content);

        for (int i = 0; i < posts.length(); i++) {
            JSONObject post = posts.getJSONObject(i);

            // Create a panel for each post
            JPanel postItemPanel = new JPanel();
            postItemPanel.setLayout(new BorderLayout());
            postItemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            postItemPanel.setBackground(Color.WHITE);

            // Add author and timestamp
            JLabel authorLabel = new JLabel("User Name ");
            LocalDateTime postTimestamp = LocalDateTime.parse(post.getString("timestamp"));
            LocalDateTime now = LocalDateTime.now();

            Duration durationOfPost = Duration.between(postTimestamp, now);
            JLabel timestampLabel = new JLabel("Posted from: " + durationOfPost.toHours() + " Hours");

            JPanel metaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            metaPanel.setBackground(Color.WHITE);
            metaPanel.add(authorLabel);
           // metaPanel.add(timestampLabel);

            // Add post content
            JTextArea contentArea = new JTextArea(post.getString("content"));
            contentArea.setLineWrap(true);
            contentArea.setWrapStyleWord(true);
            contentArea.setEditable(false);
            contentArea.setBackground(new Color(245, 245, 245));

            Dimension preferredSize = new Dimension(300, 150);
            contentArea.setPreferredSize(preferredSize);

            // Add optional image
            JLabel imageLabel = new JLabel();
            if (post.has("image")) {
                String base64Image = post.getString("image");
                byte[] imageBytes = java.util.Base64.getDecoder().decode(base64Image);
                ImageIcon imageIcon = new ImageIcon(imageBytes);
                Image scaledImage = imageIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));
                imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            }

            // Create a button panel
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton addCommentButton = new JButton("Add Comment");
            JButton showCommentsButton = new JButton("Show Comments");
            JButton likeButton = new JButton("Like");

            // Add button listeners
            addCommentButton.addActionListener(e -> {
                String comment = JOptionPane.showInputDialog("Enter your comment:");
                if (comment != null && !comment.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(postPanel, "Comment added: " + comment);
                    // Here, you'd update your data structure or database with the new comment
                }
            });

            showCommentsButton.addActionListener(e -> {
                // Simulate fetching comments (you should implement the logic)
                JOptionPane.showMessageDialog(postPanel, "Displaying comments for post...");
            });

            likeButton.addActionListener(e -> {
                JOptionPane.showMessageDialog(postPanel, "You liked this post!");
                // Update likes in the database or data structure here
            });

            // Add buttons to the button panel
            buttonPanel.add(addCommentButton);
            buttonPanel.add(showCommentsButton);
            buttonPanel.add(likeButton);

            // Layout the post item panel
            postItemPanel.add(metaPanel, BorderLayout.NORTH);
            postItemPanel.add(new JScrollPane(contentArea), BorderLayout.CENTER);
            if (post.has("image")) {
                postItemPanel.add(imageLabel, BorderLayout.WEST);
            }
            postItemPanel.add(buttonPanel, BorderLayout.SOUTH); // Add button panel at bottom

            // Add spacing
            JPanel spacer = new JPanel();
            spacer.setPreferredSize(new Dimension(10, 10));
            spacer.setBackground(Color.LIGHT_GRAY);

            postPanel.add(postItemPanel);
            postPanel.add(spacer);
        }

    } catch (IOException e) {
        JOptionPane.showMessageDialog(postPanel, "Error loading posts: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(postPanel, "Invalid JSON format in PostsDB.json", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
}
