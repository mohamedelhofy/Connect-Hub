/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupFront;

import GroupClass.Group;
import connect.hub.BackEndContentCreation.Post;
import connect.hub.FrontEndContentCreation.NewPostGUI;
import connect.hub.FrontEndContentCreation.PostScrollingPage;
import connect.hub.NewsfeedPage;
import connect.hub.User;
import java.awt.*;

import java.awt.event.*;
import java.util.List;
import javax.swing.*;


/**
 *
 * @author Compu City
 */

public class GroupMemberGui extends JFrame {
    User user = User.getInstance(); 

    public GroupMemberGui(Group group) {
        setTitle(group.getGroupName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
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

        JTextArea groupDescriptionArea = new JTextArea("This is a description of the group. " + group.getGroupDescription());
        groupDescriptionArea.setLineWrap(true);
        groupDescriptionArea.setWrapStyleWord(true);
        groupDescriptionArea.setFont(new Font("Arial", Font.PLAIN, 16));
        groupDescriptionArea.setEditable(false);
        groupDescriptionArea.setBackground(getBackground());
        JScrollPane descriptionScrollPane = new JScrollPane(groupDescriptionArea);
        descriptionScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        descriptionScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton addPost = new JButton("Add Post");
        addPost.setFont(new Font("Arial", Font.PLAIN, 18));
        addPost.addActionListener(e -> {
                dispose();
                NewPostGUI newPostFrame = new NewPostGUI(user.getUserId());
                Post post = newPostFrame.newPostFrame();
                List<String> postId =group.getPostsId();
                postId.add(post.getContentId());
                group.setPostsId(postId);
        });
        JButton posts = new JButton("Posts");
        posts.setFont(new Font("Arial", Font.PLAIN, 18));
        posts.addActionListener(e -> {
            dispose();
            PostScrollingPage postScrollingPage = new PostScrollingPage();
            postScrollingPage.createAndShowGUI();
        });


        buttonPanel.add(addPost);
        buttonPanel.add(posts);
        add(descriptionScrollPane, BorderLayout.BEFORE_FIRST_LINE);
        add(photoLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new NewsfeedPage().setVisible(true);
                dispose();
            }
        });
    }

}
