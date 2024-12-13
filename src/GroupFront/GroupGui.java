/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupFront;

import GroupClass.Group;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Compu City
 */
public class GroupGui extends JFrame {

    public GroupGui(Group group) {
        setTitle("Group Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600); 
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); 

        String photoPath = group.getGroupPhotoPath(); 
        ImageIcon groupPhoto = new ImageIcon(photoPath);
        JLabel photoLabel = new JLabel();
        photoLabel.setIcon(new ImageIcon(groupPhoto.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
        photoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel groupNameLabel = new JLabel(group.getGroupName()); 
        groupNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        groupNameLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JTextArea groupDescriptionArea = new JTextArea("This is a description of the group. "+ group.getGroupDescription() );
        groupDescriptionArea.setLineWrap(true);
        groupDescriptionArea.setWrapStyleWord(true);
        groupDescriptionArea.setFont(new Font("Arial", Font.PLAIN, 16));
        groupDescriptionArea.setEditable(false); 
        groupDescriptionArea.setBackground(getBackground()); 
        JScrollPane descriptionScrollPane = new JScrollPane(groupDescriptionArea);
        descriptionScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        descriptionScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(groupNameLabel, BorderLayout.NORTH);
        add(photoLabel, BorderLayout.CENTER);
        add(descriptionScrollPane, BorderLayout.NORTH);
    }

}
