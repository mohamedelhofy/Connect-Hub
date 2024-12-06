/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author rawan
 */
public class newsFeedGUI {
 
public class NewsfeedPage extends JFrame {
    private JPanel headerPanel;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public NewsfeedPage() {
        setTitle("Newsfeed Page");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
//////////////////////////////////////the fixed header for newsfeed
        headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        headerPanel.add(new JLabel("Newsfeed"));
        headerPanel.add(new JButton("Home"));
        headerPanel.add(new JButton("Posts"));
        headerPanel.add(new JButton("Stories"));
        headerPanel.add(new JButton("Friends"));
        headerPanel.add(new JButton("Suggestions"));
        headerPanel.add(new JButton("logout"));

        // Create main panel 
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add different pages to the main panel
        mainPanel.add(createHomePage(), "Home");
        mainPanel.add(createPostsPage(), "Posts");
        mainPanel.add(createStoriesPage(), "Stories");
        mainPanel.add(createFriendsPage(), "Friends");
        mainPanel.add(createSuggestionsPage(), "Suggestions");

        // Add header and main panel to the frame
        setLayout(new BorderLayout());
        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        cardLayout.show(mainPanel, "Home");
    }

 
   ///////////////////start main functions///////////////////////////////////
    private JPanel createHomePage() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Welcome to the Newsfeed Home Page!"));
        return panel;
    }

    private JPanel createPostsPage() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Posts Page"));
        return panel;
    }

    private JPanel createStoriesPage() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Stories Page"));
        return panel;
    }

    private JPanel createFriendsPage() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Friends Page"));
        return panel;
    }

    private JPanel createSuggestionsPage() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Suggestions Page"));
        return panel;
    }


}
}
