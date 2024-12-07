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

public class NewsfeedPage extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private User user = User.getInstance();

    public NewsfeedPage() {
        setTitle("Newsfeed Page");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 2)); 

       
        JButton postsButton = new JButton("Posts");
        postsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                PostScrollingPage postScrollingPage = new PostScrollingPage();
                postScrollingPage.createAndShowGUI();
            }
        });
        this.add(postsButton);

        JButton storiesButton = new JButton("Stories");
        storiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                StoryScrollingPage storyScrollingPage = new StoryScrollingPage();
                storyScrollingPage.createAndShowGUI();
            }
        });
        this.add(storiesButton);

        JButton newPostsButton = new JButton("New Posts");
        newPostsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                NewPostGUI newPostFrame = new NewPostGUI(user.getUserId());
                newPostFrame.newPostFrame();
            }
        });
        this.add(newPostsButton);

        JButton newStoryButton = new JButton("New Story");
        newStoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                NewStoryGUI newStoryFrame = new NewStoryGUI(user.getUserId());
                newStoryFrame.newStoryFrame();
            }
        });
        this.add(newStoryButton);

        JButton profileButton = new JButton("Profile");
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ProfileManagementPage().setVisible(true);
            }
        });
        this.add(profileButton);

        /////////////// backend of logout (friendmanagement)
        JButton logoutButton = new JButton("Log Out");
        logoutButton.setBackground(Color.RED);
        logoutButton.setForeground(Color.WHITE);
        this.add(logoutButton);     }


}