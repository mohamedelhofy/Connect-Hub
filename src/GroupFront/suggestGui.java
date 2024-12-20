/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupFront;

import connect.hub.suggestGroup;
import GroupClass.Group;
import GroupClass.readGroupFromJSON;
import java.awt.BorderLayout;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author rawan
 */

 public class suggestGui extends JFrame {

    private JTextArea textArea;

    public suggestGui() {
        // Create the main frame
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);

        // Create a text area to display suggestions///////////
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Add the scroll pane to the frame//////////////
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Create a button to generate suggestions////////////////
        JButton suggestButton = new JButton("Generate Suggestions");
        suggestButton.addActionListener(e -> generateSuggestions());

        // Add the button to the frame///////////
        getContentPane().add(suggestButton, BorderLayout.SOUTH);

        // Set the frame to be visible///////////
        setVisible(true);
    }

    public void generateSuggestions() {
        // Read the group data from the JSON file
        readGroupFromJSON readGroupJSON = new readGroupFromJSON();
        List<Map<String, Object>> groupListDB = readGroupJSON.getGroupListDB();

        // Convert the data into a list of Group objects
        List<Group> groups = suggestGroup.convertToGroupList(groupListDB);

        // Generate group suggestions for users
        Map<String, List<String>> suggestions = suggestGroup.suggestGroups(groups);
        
        // Display the suggestions in the text area
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : suggestions.entrySet()) {
            sb.append("User ").append(entry.getKey()).append(" is not in the following groups: ")
              .append(String.join(", ", entry.getValue())).append("\n");
        }
        textArea.setText(sb.toString());
}

}
