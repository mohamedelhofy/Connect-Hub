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
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author rawan
 */
 
public class NewsfeedPage extends JFrame {
    private JPanel headerPanel;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private User user=User.getInstance();
    public NewsfeedPage() {
        setTitle("Newsfeed Page");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 2));
        JButton homeButton = new JButton("Home");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new NewsfeedPage().setVisible(true);
            }
        });
        this.add(homeButton);
        JButton PostsButton = new JButton("Posts");
        PostsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                PostScrollingPage postscrollingPage =new PostScrollingPage();
                postscrollingPage.createAndShowGUI();
            }
        });
        this.add(PostsButton);
        JButton StoriesButton = new JButton("Stories");
        StoriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                StoryScrollingPage storyscrollingpage =new StoryScrollingPage();
                storyscrollingpage.createAndShowGUI();
            }
        });
        this.add(StoriesButton);
        JButton newpostsButton = new JButton("New posts");
        newpostsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                NewPostGUI newPostFrame = new NewPostGUI(user.getUserId());
                newPostFrame.newPostFrame();
            }
        });
        this.add(newpostsButton);  
        JButton newstorysButton = new JButton("New stroy");
        newstorysButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                NewStoryGUI newstoryFrame = new NewStoryGUI(user.getUserId());
                newstoryFrame.newStoryFrame();
            }
        });
        this.add(newstorysButton);
//        headerPanel.add(new JLabel("Newsfeed"));
//        headerPanel.add(new JButton("Home"));
//        headerPanel.add(new JButton("Posts"));
//        headerPanel.add(new JButton("Stories"));
//        headerPanel.add(new JButton("Friends"));
//        headerPanel.add(new JButton("Suggestions"));
//        headerPanel.add(new JButton("logout"));

        // Create main panel 

    }

 



}

