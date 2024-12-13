/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.FrontEndContentCreation;

import connect.hub.BackEndContentCreation.Post;
import connect.hub.BackEndContentCreation.StoreIntoJSON;
import connect.hub.NewsfeedPage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author رحمه صبرى
 */
public class NewPostGUI {
    private String autherId;
    Post newPost;
    public NewPostGUI(String autherId) {
        this.autherId = autherId;
    }
    
    public Post newPostFrame(){
        JFrame frame = new JFrame("Create New Post");
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 600); 
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        Color facebookBlue = new Color(59, 89, 152);
        Color white = Color.WHITE;

        JPanel titleBar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titleBar.setBackground(facebookBlue);
        JLabel titleLabel = new JLabel("Create New Post");
        titleLabel.setForeground(white);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleBar.add(titleLabel);
        JPanel textPanel = new JPanel(new BorderLayout());
        JLabel textLabel = new JLabel("Post Content:");
        textLabel.setForeground(facebookBlue);
        textLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        JTextArea postContentArea = new JTextArea(10, 40);
        postContentArea.setLineWrap(true);
        postContentArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(postContentArea);
        textPanel.add(textLabel, BorderLayout.NORTH);
        textPanel.add(scrollPane, BorderLayout.CENTER);
        //creat optional image
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        imagePanel.setBackground(white);
        JLabel imageLabel = new JLabel("Attach Image:");
        JButton uploadButton = new JButton("Upload Image");
        JLabel fileNameLabel = new JLabel("No file selected");
        imageLabel.setForeground(facebookBlue);
        fileNameLabel.setForeground(facebookBlue);
        imagePanel.add(imageLabel);
        imagePanel.add(uploadButton);
        imagePanel.add(fileNameLabel);

        // Create a panel for the submit and cancel buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(white);
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");
        Dimension buttonSize = new Dimension(120, 40);
        submitButton.setPreferredSize(buttonSize);
        cancelButton.setPreferredSize(buttonSize);
        submitButton.setBackground(facebookBlue);
        submitButton.setForeground(white);
        cancelButton.setBackground(facebookBlue);
        cancelButton.setForeground(white);
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);

        final Image[] selectedImage = {null}; // To hold the selected image

        uploadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg", "gif");
            fileChooser.setFileFilter(filter);

            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    selectedImage[0] = ImageIO.read(selectedFile);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error reading the image file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action listener for the submit button
        submitButton.addActionListener(e -> {
            String contentText = postContentArea.getText().trim();
            // Create the post object
            newPost = new Post(this.autherId, contentText, selectedImage[0]);
            StoreIntoJSON jsonFile = new StoreIntoJSON(newPost);
            jsonFile.addPostsToJSON();
            JOptionPane.showMessageDialog(frame, "Post created successfully!");
            frame.dispose();
            new NewsfeedPage().setVisible(true); 
        });
         //  here we need to back to the newsFeed
        cancelButton.addActionListener(e -> 
        {
            frame.dispose();
            new NewsfeedPage().setVisible(true);    
        });
        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new NewsfeedPage().setVisible(true);
                frame.dispose(); 
            }
        });
        // Add components to the frame
        frame.add(titleBar, BorderLayout.NORTH);
        frame.add(textPanel, BorderLayout.CENTER);
        frame.add(imagePanel, BorderLayout.WEST);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
        return newPost;
    }
    
    
}
