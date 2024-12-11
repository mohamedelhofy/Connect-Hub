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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

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
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Newsfeed", JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // Log Out Button
        JButton logoutButton = createStyledButton("Log Out", Color.RED, Color.WHITE);
        logoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "You have logged out.");
            dispose();
            // Add logout logic here
        });
        headerPanel.add(logoutButton, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        // Sidebar Panel
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new GridLayout(6, 6, 20, 20));
        sidebarPanel.setBackground(new Color(240, 240, 240));
        sidebarPanel.setBorder(BorderFactory.createTitledBorder("Navigation"));

        // Add buttons to the sidebar
        sidebarPanel.add(createSidebarButton("Posts", e -> navigateToPosts()));
        sidebarPanel.add(createSidebarButton("Stories", e -> navigateToStories()));
        sidebarPanel.add(createSidebarButton("New Post", e -> navigateToNewPost()));
        sidebarPanel.add(createSidebarButton("New Story", e -> navigateToNewStory()));
        sidebarPanel.add(createSidebarButton("Profile", e -> navigateToProfile()));
        sidebarPanel.add(createSidebarButton("Friends", e -> navigateToFriends()));
        ///rgb(217, 234, 253)
        add(sidebarPanel, BorderLayout.WEST);
sidebarPanel.setBackground(new Color (217, 234, 253));
        // Main Panel
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        JPanel newsfeedPanel = new JPanel(new BorderLayout());
        newsfeedPanel.setBackground(new Color(245, 245, 245));
        newsfeedPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel newsfeedLabel = new JLabel("Welcome to Connect HUB!", JLabel.CENTER);
        newsfeedLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        newsfeedPanel.add(newsfeedLabel, BorderLayout.CENTER);

        mainPanel.add(newsfeedPanel, "Newsfeed");
        add(mainPanel, BorderLayout.CENTER);

        // Set the default panel to Newsfeed
        cardLayout.show(mainPanel, "Newsfeed");
    }

    private JButton createSidebarButton(String text, ActionListener action) {
        JButton button = createStyledButton(text, new Color(36, 48, 69), Color.WHITE);
        button.addActionListener(action);
        return button;
    }

    private JButton createStyledButton(String text, Color background, Color foreground) {
        JButton button = new JButton(text);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(200, 40));
        return button;
    }

    private void navigateToPosts() {
        dispose();
        new PostScrollingPage().createAndShowGUI();
    }

    private void navigateToStories() {
        dispose();
        new StoryScrollingPage().createAndShowGUI();
    }

    private void navigateToNewPost() {
        dispose();
        new NewPostGUI(user.getUserId()).newPostFrame();
    }

    private void navigateToNewStory() {
        dispose();
        new NewStoryGUI(user.getUserId()).newStoryFrame();
    }

    private void navigateToProfile() {
        dispose();
        new profileGui().setVisible(true);
    }

    private void navigateToFriends() {
        dispose();
        new profileGui().setVisible(true);
}
}