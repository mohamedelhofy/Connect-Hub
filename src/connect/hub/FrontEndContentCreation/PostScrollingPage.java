/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.FrontEndContentCreation;

import connect.hub.BackEndContentCreation.Post;
import connect.hub.BackEndContentCreation.ReadFromJSON;
import connect.hub.BackEndContentCreation.UpdateDB;
import connect.hub.NewsfeedPage;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.util.List;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Base64;

/**
 *
 * @author رحمه صبرى
 */
public class PostScrollingPage {

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Posts");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(postPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel("Posts", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(59, 89, 152));
        frame.add(titleLabel, BorderLayout.NORTH);

        loadPostsAndStories(postPanel);

        frame.setVisible(true);
    }

    private static void loadPostsAndStories(JPanel postPanel) {

        String filePath = "PostsDB.json";
        ReadFromJSON reader = new ReadFromJSON(filePath);
        List<Post> posts = reader.getPostList();
//        for (Post poststs : posts) {
//            System.out.println(poststs.getAuthorId());
//        }
        try {
            for (Post post : posts) {
                JPanel postItemPanel = new JPanel();
                postItemPanel.setLayout(new BorderLayout());
                postItemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                postItemPanel.setBackground(Color.WHITE);

                JLabel authorLabel = new JLabel(post.getAuthorId());
                LocalDateTime postTimestamp = post.getTimestamp();
                LocalDateTime now = LocalDateTime.now();

                Duration durationOfPost = Duration.between(postTimestamp, now);
                JLabel timestampLabel = new JLabel("Posted from: " + durationOfPost.toHours() + " Hours ago");

                JPanel metaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                metaPanel.setBackground(Color.WHITE);
                metaPanel.add(authorLabel);
                metaPanel.add(timestampLabel);

                JTextArea contentArea = new JTextArea(post.getContentText());
                contentArea.setLineWrap(true);
                contentArea.setWrapStyleWord(true);
                contentArea.setEditable(false);
                contentArea.setBackground(new Color(245, 245, 245));

                JLabel imageLabel = new JLabel();
                if (post.getImage() != null) {
                    ImageIcon originalIcon = new ImageIcon(post.getImage());
                    Image scaledImage = originalIcon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH); // Set width and height
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
                    imageLabel.setIcon(scaledIcon);
                    imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                }

                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                buttonPanel.setBackground(Color.WHITE);

                JButton addCommentButton = new JButton("Add Comment");
                addCommentButton.addActionListener(e -> {
                    AddComment.createAndShowGUI(post);
                });

                JButton showCommentsButton = new JButton("Show Comments");
                showCommentsButton.addActionListener(e -> {
                    ShowComments showComments = new ShowComments();
                    showComments.setVisible(true);
                    showComments.showComments(post);
                });

                JButton addLikeButton = new JButton(post.getSizeLike() + " Likes");
                final boolean[] liked = {false};
                addLikeButton.addActionListener(e -> {
                    liked[0] = !liked[0];
                    if (liked[0]) {
                        post.setLike("User5");
                        addLikeButton.setText("Liked");
                        addLikeButton.setBackground(new Color(24, 119, 242));
                        addLikeButton.setForeground(Color.WHITE);
                        addLikeButton.setText(post.getSizeLike() + " Likes");
                    } else {
                        post.getLikes().remove("User5");
                        addLikeButton.setText("Like");
                        addLikeButton.setBackground(UIManager.getColor("Button.background"));
                        addLikeButton.setForeground(Color.BLACK);
                        addLikeButton.setText(post.getSizeLike() + " Likes");
                    }
                });

                buttonPanel.add(addCommentButton);
                buttonPanel.add(showCommentsButton);
                buttonPanel.add(addLikeButton);

                postItemPanel.add(metaPanel, BorderLayout.NORTH);
                postItemPanel.add(new JScrollPane(contentArea), BorderLayout.CENTER);
                if (post.getImage() != null) {
                    postItemPanel.add(imageLabel, BorderLayout.WEST);
                }
                postItemPanel.add(buttonPanel, BorderLayout.SOUTH);

                JPanel spacer = new JPanel();
                spacer.setPreferredSize(new Dimension(10, 10));
                spacer.setBackground(Color.LIGHT_GRAY);

                postPanel.add(postItemPanel);
                postPanel.add(spacer);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(postPanel, "Invalid JSON format in PostsDB.json", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}