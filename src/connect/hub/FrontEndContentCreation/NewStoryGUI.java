/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.FrontEndContentCreation;

import connect.hub.BackEndContentCreation.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
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
public class NewStoryGUI {
    private String autherId;

    public NewStoryGUI(String autherId) {
        this.autherId = autherId;
    }
    public  void newStoryFrame() {
        JFrame frame = new JFrame("Create New Story");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 600); 
        frame.setLayout(new BorderLayout());
         frame.setLocationRelativeTo(null);
        Color facebookBlue = new Color(59, 89, 152);
        Color white = Color.WHITE;

        // Create a panel for the title bar
        JPanel titleBar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titleBar.setBackground(facebookBlue);
        JLabel titleLabel = new JLabel("Create New Story");
        titleLabel.setForeground(white);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleBar.add(titleLabel);

        // Create a panel for the story input
        JPanel storyPanel = new JPanel(new BorderLayout());
        JLabel storyLabel = new JLabel("Story Content:");
        storyLabel.setForeground(facebookBlue);
        storyLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextArea storyContentArea = new JTextArea(15, 40);
        storyContentArea.setLineWrap(true);
        storyContentArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(storyContentArea);
        storyPanel.add(storyLabel, BorderLayout.NORTH);
        storyPanel.add(scrollPane, BorderLayout.CENTER);

        // Create a panel for the optional image
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
        JButton submitButton = new JButton("Post Story");
        JButton cancelButton = new JButton("Cancel");
        submitButton.setBackground(facebookBlue);
        submitButton.setForeground(white);
        cancelButton.setBackground(facebookBlue);
        cancelButton.setForeground(white);
        Dimension buttonSize = new Dimension(150, 50); // Larger buttons for better usability
        submitButton.setPreferredSize(buttonSize);
        cancelButton.setPreferredSize(buttonSize);
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
            String contentText = storyContentArea.getText().trim();
            // Create the post object
            Stories newStory = new Stories(this.autherId, contentText, selectedImage[0]);
            StoreIntoJSON jsonFile = new StoreIntoJSON(newStory);
            jsonFile.addStoriesToJSON();
            JOptionPane.showMessageDialog(frame, "Post created successfully!");
            frame.dispose();
        });
        cancelButton.addActionListener(e -> frame.dispose());
        frame.add(titleBar, BorderLayout.NORTH);
        frame.add(storyPanel, BorderLayout.CENTER);
        frame.add(imagePanel, BorderLayout.WEST);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
