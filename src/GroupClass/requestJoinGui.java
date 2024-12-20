/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupClass;

import connect.hub.suggestGroup;
import GroupClass.Group;
import GroupClass.RequestJoin;
import GroupClass.readGroupFromJSON;
import connect.hub.User;
import java.awt.BorderLayout;
import java.util.List;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author rawan
 */
public class requestJoinGui extends JFrame {
    JPanel suggestionsPanel;
    String userId = User.generateUniqueId();

    public requestJoinGui() {
        // Create the main frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BorderLayout());

        // Create a panel to display suggestions
        suggestionsPanel = new JPanel();
        suggestionsPanel.setLayout(new BoxLayout(suggestionsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(suggestionsPanel);

        // Add the scroll pane to the frame
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Create a button to generate suggestions
        JButton suggestButton = new JButton("Generate Suggestions");
        suggestButton.addActionListener(e -> generateSuggestions());

        // Add the button to the frame
        getContentPane().add(suggestButton, BorderLayout.SOUTH);

        // Set the frame to be visible
        setVisible(true);
    }

    private void generateSuggestions() {
        // Read the group data from the JSON file
        readGroupFromJSON readGroupJSON = new readGroupFromJSON();
        List<Map<String, Object>> groupListDB = readGroupJSON.getGroupListDB();

        // Convert the data into a list of Group objects
        List<Group> groups = suggestGroup.convertToGroupList(groupListDB);

        // Generate group suggestions for users
        Map<String, List<String>> suggestions = suggestGroup.suggestGroups(groups);

        // Display the suggestions in the panel
        suggestionsPanel.removeAll();
        for (Map.Entry<String, List<String>> entry : suggestions.entrySet()) {
            String user = entry.getKey();
            for (String groupName : entry.getValue()) {
                JPanel suggestionPanel = new JPanel(new BorderLayout());
                JLabel suggestionLabel = new JLabel("User " + user + " is not in group: " + groupName);
                JButton requestButton = new JButton("Request to Join");
                requestButton.addActionListener(new RequestJoin(user, groupName, groupListDB));

                suggestionPanel.add(suggestionLabel, BorderLayout.CENTER);
                suggestionPanel.add(requestButton, BorderLayout.EAST);
                suggestionsPanel.add(suggestionPanel);
            }
        }
        suggestionsPanel.revalidate();
        suggestionsPanel.repaint();
    }
}
