/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupFront;

import GroupClass.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author رحمه صبرى
 */
public class GroupSearchGUI {

    public static void main(String[] args) {
        readGroupFromJSON readGroup = new readGroupFromJSON();
        List<Map<String, Object>> groupList = readGroup.getGroupListDB();
        JFrame frame = new JFrame("Group Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 100);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setLocationRelativeTo(null);
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout(5, 5));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create a JTextField for the search bar
        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(250, 30));
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 14));

        // Add padding around the JTextField
        searchField.setBorder(BorderFactory.createCompoundBorder(
                searchField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        // Create a JButton with a search icon
        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(null); // Allow the button to size itself based on its content
        searchButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        searchButton.setToolTipText("Click to perform search");

        // Add an ActionListener to the button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                boolean found = false;

                for (Map<String, Object> group : groupList) {
                    String groupName = ((String) group.get("groupName"));
                    if (groupName.contains(searchText)) {
                        
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    JOptionPane.showMessageDialog(frame, "No group found with the name: " + searchText);
                }

            }
        });

        // Add the search field and button to the panel
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        // Add the search panel to the frame
        frame.add(searchPanel, BorderLayout.NORTH);

        // Make the frame visible
        frame.setVisible(true);

    }
}
