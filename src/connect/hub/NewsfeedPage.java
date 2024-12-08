/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub;

import connect.hub.FrontEndContentCreation.NewPostGUI;
import connect.hub.FrontEndContentCreation.NewStoryGUI;
import connect.hub.FrontEndContentCreation.PostScrollingPage;
import connect.hub.FrontEndContentCreation.StoryScrollingPage;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author rawan
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NewsfeedPage extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private User user = User.getInstance();

    public NewsfeedPage() {
        // Frame settings
        setTitle("Newsfeed Page");
        setSize(1000, 700);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10)); 

        // Header Panel - Title and Log Out button
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(36, 48, 69)); // Dark Blue Header

        JLabel titleLabel = new JLabel("Newsfeed", JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // Log Out Button
        JButton logoutButton = new JButton("Log Out");
        logoutButton.setBackground(Color.RED);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setPreferredSize(new Dimension(100, 40));

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Log out functionality
                JOptionPane.showMessageDialog(NewsfeedPage.this, "You have logged out.");
                new LoginWindow().setVisible(true);  // Open the login window
                dispose();  // Close the current NewsfeedPage
            }
        });

        headerPanel.add(logoutButton, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        // Sidebar Panel for navigation
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new GridLayout(6, 1, 10, 10));  
        sidebarPanel.setBackground(new Color(240, 240, 240)); 
        sidebarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //  Buttons to different windows
        JButton postsButton = new JButton("Posts");
        postsButton.setPreferredSize(new Dimension(200, 50));
        postsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                PostScrollingPage postScrollingPage = new PostScrollingPage();
                postScrollingPage.createAndShowGUI();
            }
        });
        sidebarPanel.add(postsButton);

        JButton storiesButton = new JButton("Stories");
        storiesButton.setPreferredSize(new Dimension(200, 50));
        storiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                StoryScrollingPage storyScrollingPage = new StoryScrollingPage();
                storyScrollingPage.createAndShowGUI();
            }
        });
        sidebarPanel.add(storiesButton);

        JButton newPostsButton = new JButton("New Post");
        newPostsButton.setPreferredSize(new Dimension(200, 50));
        newPostsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                NewPostGUI newPostFrame = new NewPostGUI(user.getUserId());
                newPostFrame.newPostFrame();
            }
        });
        sidebarPanel.add(newPostsButton);
        JButton newStoryButton = new JButton("New Story");
        newStoryButton.setPreferredSize(new Dimension(200, 50));
        newStoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                NewStoryGUI newStoryFrame = new NewStoryGUI(user.getUserId());
                newStoryFrame.newStoryFrame();
            }
        });
        sidebarPanel.add(newStoryButton);

        JButton profileButton = new JButton("Profile");
        profileButton.setPreferredSize(new Dimension(200, 50));
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new profileGui().setVisible(true);  // Open Profile page
            }
        });
        sidebarPanel.add(profileButton);

        // Friends button functionality  temporarily opens profile page
        JButton friendsButton = new JButton("Friends");
        friendsButton.setBackground(new Color(36, 48, 69));  
        friendsButton.setForeground(Color.WHITE);
        friendsButton.setPreferredSize(new Dimension(200, 50));
        friendsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new profileGui().setVisible(true);  // Temporarily opening profile page
            }
        });
        
        sidebarPanel.add(friendsButton);  

        sidebarPanel.add(logoutButton); 

        add(sidebarPanel, BorderLayout.WEST);

        // Main Area 
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        JPanel newsfeedPanel = new JPanel();
        newsfeedPanel.setLayout(new BorderLayout());
        JLabel newsfeedLabel = new JLabel("Welcome to Connect HUB!", JLabel.CENTER);
        newsfeedLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        newsfeedPanel.add(newsfeedLabel, BorderLayout.CENTER);
        mainPanel.add(newsfeedPanel, "Newsfeed");

        add(mainPanel, BorderLayout.CENTER);

        // Set the default panel to Newsfeed
        cardLayout.show(mainPanel, "Newsfeed");
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            NewsfeedPage newsfeedPage = new NewsfeedPage();
//            newsfeedPage.setVisible(true);
//        });
//    }
}
