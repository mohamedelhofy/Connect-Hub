/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupFront;

import GroupClass.Group;
import GroupClass.GroupAdmin;
import GroupClass.GroupPrimaryAdmin;
import connect.hub.BackEndContentCreation.Post;
import connect.hub.BackEndContentCreation.ReadFromJSON;
import connect.hub.FrontEndContentCreation.NewPostGUI;
import connect.hub.FrontEndContentCreation.PostScrollingPage;
import connect.hub.NewsfeedPage;
import connect.hub.User;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import org.json.JSONException;
/**
 *
 * @author Compu City
 */
public class GroupPrimaryAdminGui extends JFrame {
    User user = User.getInstance(); 

    public GroupPrimaryAdminGui(GroupPrimaryAdmin group) {
        setTitle(group.getGroupName());
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        buttonPanel.setLayout(new GridLayout(5, 2, 10, 10));

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
        JButton editPost = new JButton("Edit Posts");
        editPost.setFont(new Font("Arial", Font.PLAIN, 18));
        editPost.addActionListener(e -> {
            dispose();
            new EditPost((GroupPrimaryAdmin) group,false);
        });       
        JButton deletePost = new JButton("Delete Posts");
        deletePost.setFont(new Font("Arial", Font.PLAIN, 18));
        deletePost.addActionListener(e -> {
            dispose();
            new DeletePost((GroupPrimaryAdmin) group,false);
        });
        JButton removeMember = new JButton("Remove Member");
        removeMember.setFont(new Font("Arial", Font.PLAIN, 18));
        removeMember.addActionListener(e -> {
            dispose();
            new RemoveMember((GroupPrimaryAdmin) group,false);
        });
        JButton approveMember = new JButton("Approve Member");
        approveMember.setFont(new Font("Arial", Font.PLAIN, 18));
        approveMember.addActionListener(e -> {
            dispose();
            new ApproveMember((GroupPrimaryAdmin) group,false);
        });
        JButton declineMember = new JButton("Decline Member");
        declineMember.setFont(new Font("Arial", Font.PLAIN, 18));
        declineMember.addActionListener(e -> {
            dispose();
            new DeclineMember((GroupPrimaryAdmin) group,false);
        });
        JButton promoteMember = new JButton("Promote Member");
        promoteMember.setFont(new Font("Arial", Font.PLAIN, 18));
        promoteMember.addActionListener(e -> {
            dispose();
            new PromoteMember((GroupPrimaryAdmin) group,false);
        });
        JButton demoteMember = new JButton("Demote Member");
        demoteMember.setFont(new Font("Arial", Font.PLAIN, 18));
        demoteMember.addActionListener(e -> {
            dispose();
            new DemoteMember((GroupPrimaryAdmin) group,false);
        });        
        buttonPanel.add(addPost);
        buttonPanel.add(posts);
        buttonPanel.add(editPost);
        buttonPanel.add(deletePost);
        buttonPanel.add(removeMember);
        buttonPanel.add(approveMember);
        buttonPanel.add(declineMember);
        buttonPanel.add(promoteMember);
        buttonPanel.add(demoteMember);
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
