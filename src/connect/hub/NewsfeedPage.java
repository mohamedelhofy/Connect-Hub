/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub;

//import connect.hub.FrontEndContentCreation.FriendManagementInterface;
import GroupClass.Group;
import GroupClass.readGroupFromJSON;
import NotificationsFrontEnd.NotificationsWindow;
import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import connect.hub.FrontEndContentCreation.NewPostGUI;
import connect.hub.FrontEndContentCreation.NewStoryGUI;
import connect.hub.FrontEndContentCreation.PostScrollingPage;
import connect.hub.FrontEndContentCreation.StoryScrollingPage;
import connect.hub.FrontEndFriendManagement.FriendManagementInterface;
import connect.hub.FrontEndFriendManagement.FriendStatusWindow;
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

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NewsfeedPage extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JTextArea suggestionsTextArea;
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
        headerPanel.setBackground(new Color(36, 48, 69)); // Dark Blue Header for all buttons//////////////
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
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            new LoginWindow().setVisible(true);
            
        });
        
        JButton notificationsButton = createStyledButton("notifications", new Color(217, 234, 253),new Color(36, 48, 69) );
        notificationsButton.addActionListener(e -> {
            
            NotificationsWindow notificationsWindow = new NotificationsWindow();
            notificationsWindow.showFrame();
            new LoginWindow().setVisible(true);
            
        });
        
        
        JButton refreshButton = createStyledButton("Refresh", new Color(217, 234, 253),new Color(36, 48, 69) );
        refreshButton.addActionListener(e -> {
            
            //// place the notification window here ya A7med
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            new FileDataActions().refresh();
            JOptionPane.showMessageDialog(this, "Your data has been refreshed");

            
        });
        
   headerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); //  buttons to the right
   headerPanel.add(notificationsButton);
     headerPanel.add(refreshButton);
     headerPanel.add(logoutButton);
 
        logoutButton.setPreferredSize(new Dimension(120, 40)); // Width: 120, Height: 40
        notificationsButton.setPreferredSize(new Dimension(120, 40));
        refreshButton.setPreferredSize(new Dimension(110, 40));
        add(headerPanel, BorderLayout.NORTH);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
JTextField searchBar = new JTextField("Search...");
searchBar.setForeground(Color.GRAY);

// Add focus listener to manage placeholder text
searchBar.addFocusListener(new java.awt.event.FocusAdapter() {
    public void focusGained(java.awt.event.FocusEvent e) {
        if (searchBar.getText().equals("Search...")) {
            searchBar.setText("");
            searchBar.setForeground(Color.BLACK);
        }
    }

    public void focusLost(java.awt.event.FocusEvent e) {
        if (searchBar.getText().isEmpty()) {
            searchBar.setForeground(Color.GRAY);
            searchBar.setText("Search...");
        }
    }
});
     ////headerPanel.add(searchBar);
        searchBar.setPreferredSize(new Dimension(200, 30));
        searchBar.setPreferredSize(new Dimension(80, 30));
        headerPanel.add(searchBar, BorderLayout.WEST);


 searchBar.addActionListener(e -> {
            
            //// place the Search  here ya A7med
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            new FileDataActions().refresh();
            JOptionPane.showMessageDialog(this, "Your data has been refreshed");  
        });
 
 
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new GridLayout(7, 1, 10, 10));
        sidebarPanel.setBackground(new Color(240, 240, 240));
        sidebarPanel.setBorder(BorderFactory.createTitledBorder("Navigation"));

        // Add buttons to the sidebar
        sidebarPanel.add(createSidebarButton("Online Friends", e -> showOnline()));
        sidebarPanel.add(createSidebarButton("Posts", e -> navigateToPosts()));
        sidebarPanel.add(createSidebarButton("Stories", e -> navigateToStories()));
        sidebarPanel.add(createSidebarButton("New Post", e -> navigateToNewPost()));
        sidebarPanel.add(createSidebarButton("New Story", e -> navigateToNewStory()));
        sidebarPanel.add(createSidebarButton("Profile", e -> navigateToProfile()));
        sidebarPanel.add(createSidebarButton("Friends", e -> navigateToFriends()));
        sidebarPanel.setBackground(new Color(217, 234, 253));
        add(sidebarPanel, BorderLayout.WEST);
        
        // Group Suggestion Panel
        JPanel suggestionPanel = new JPanel();
        suggestionPanel.setLayout(new BorderLayout());
        suggestionPanel.setBackground(new Color(240, 240, 240));
        suggestionPanel.setBorder(BorderFactory.createTitledBorder("Group Suggestions"));
         suggestionPanel.setBackground(new Color(217, 234, 253));
        // Add a button to suggest groups for the user
        JButton suggestGroupsButton = createStyledButton("Suggest Groups", new Color(36, 48, 69), Color.WHITE);
        suggestGroupsButton.addActionListener(e -> suggestGroupsForUser(user.getUserId()));
        suggestionPanel.add(suggestGroupsButton, BorderLayout.NORTH);

        // Button to show groups the user is in
        JButton userGroupsButton = createStyledButton("Groups you are in", new Color(36, 48, 69), Color.WHITE);
        userGroupsButton.addActionListener(e -> displayGroupsForUser(user.getUserId()));
        suggestionPanel.add(userGroupsButton, BorderLayout.SOUTH);

        // Add a text area to display the suggestions
        suggestionsTextArea = new JTextArea();
        suggestionsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(suggestionsTextArea);
        suggestionPanel.add(scrollPane, BorderLayout.CENTER);

        add(suggestionPanel, BorderLayout.EAST);

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
        FriendManagementInterface friendGui = new FriendManagementInterface();
        friendGui.showFrame();
    }

    private void suggestGroupsForUser(String userId) {
        // Read the group data from the JSON file
        readGroupFromJSON readGroupJSON = new readGroupFromJSON();
        List<Map<String, Object>> groupListDB = readGroupJSON.getGroupListDB();

        // Convert the data into a list of Group objects
        List<Group> groups = suggestGroup.convertToGroupList(groupListDB);
        
        // Generate group suggestions for the user
        Map<String, List<String>> suggestions = suggestGroup.suggestGroups(groups);

        // Normalize userId to lowercase for case-insensitive comparison
        String normalizedUserId = userId.toLowerCase();

        // Display the suggestions in the text area
        StringBuilder sb = new StringBuilder();
        if (suggestions.containsKey(normalizedUserId)) {
            for (String groupName : suggestions.get(normalizedUserId)) {
                sb.append("You are not in group: ").append(groupName).append("\n");
            }
        } else {
            for(Group group: groups){
                sb.append("You are not in group: ").append(group.getGroupName()).append("\n");                
            }
        }

        suggestionsTextArea.setText(sb.toString());
    }

    private void displayGroupsForUser(String userId) {
        // Read the group data from the JSON file
        readGroupFromJSON readGroupJSON = new readGroupFromJSON();
        List<Map<String, Object>> groupListDB = readGroupJSON.getGroupListDB();

        // Convert the data into a list of Group objects
        List<Group> groups = suggestGroup.convertToGroupList(groupListDB);

        // Get the list of groups the user is already a member of
        List<String> userGroups = getGroupsForUser(userId, groups);

        // Display the groups in the text area
        StringBuilder sb = new StringBuilder();
        if (!userGroups.isEmpty()) {
            for (String groupName : userGroups) {
                sb.append("You are in group: ").append(groupName).append("\n");
            }
        } else {
            sb.append("You are not in any groups.");
        }

        suggestionsTextArea.setText(sb.toString());
    }

    public List<String> getGroupsForUser(String userId, List<Group> groups) {
        List<String> userGroups = new ArrayList<>();

        // Iterate over each group to check if the user is a member
        for (Group group : groups) {
            if (group.getMembers().contains(userId.toLowerCase())) {
                userGroups.add(group.getGroupName());
            }
        }

        return userGroups;
    }

    private void showOnline() {
        dispose();
        new FriendStatusWindow().showFrame();
    }
}



