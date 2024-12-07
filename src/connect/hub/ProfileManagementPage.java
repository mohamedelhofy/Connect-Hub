/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub;

/**
 *
 * @author Compu City
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;

public class ProfileManagementPage extends JFrame {

    private JTextField bioField;
    private JTextField profilePhotoField;
    private JTextField coverPhotoField;
    private JButton saveButton;
    private User user= User.getInstance();
    private ProfilesData dataProfile=new ProfilesData(user.getUserId());
    private Map<String, Object> userProfile=dataProfile.getData();

    public ProfileManagementPage() {
        setTitle("Profile Management");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel userNameLabel = new JLabel("Hello, User!");  // we need to add user name if possible
        userNameLabel.setBounds(190, 10, 120, 20); 
        add(userNameLabel);

        JLabel bioLabel = new JLabel("Bio:");
        bioLabel.setBounds(20, 50, 100, 20);
        add(bioLabel);
        bioField = new JTextField();
        bioField.setBounds(120, 50, 310, 20); 
        add(bioField);

        JLabel profilePhotoLabel = new JLabel("Profile Photo:");
        profilePhotoLabel.setBounds(20, 90, 150, 20);
        add(profilePhotoLabel);

        profilePhotoField = new JTextField();
        profilePhotoField.setBounds(120, 90, 200, 20);
        add(profilePhotoField);

        JButton browseProfileButton = new JButton("Select");
        browseProfileButton.setBounds(330, 90, 100, 20);
        add(browseProfileButton);

        browseProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setDialogTitle("Select Profile Photo");
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                        "Image Files", "jpg", "png", "jpeg", "bmp", "gif"));

                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    userProfile.put("profile photo",selectedFile.getAbsolutePath() );
                }
            }
        });

        // Cover Photo Section
        JLabel coverPhotoLabel = new JLabel("Cover Photo:");
        coverPhotoLabel.setBounds(20, 130, 150, 20);
        add(coverPhotoLabel);

        coverPhotoField = new JTextField();
        coverPhotoField.setBounds(120, 130, 200, 20);
        add(coverPhotoField);

        JButton browseCoverButton = new JButton("Select");
        browseCoverButton.setBounds(330, 130, 100, 20);
        add(browseCoverButton);

        browseCoverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setDialogTitle("Select Cover Photo");

                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                        "Image Files", "jpg", "png", "jpeg", "bmp", "gif"));
                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    userProfile.put("cover photo",selectedFile.getAbsolutePath() );
                }
            }
        });

        saveButton = new JButton("Save");
        saveButton.setBounds(200, 200, 100, 30); // Centered save button
        saveButton.addActionListener(e ->  {
            userProfile.put("bio",bioField.getText() );
            dataProfile.setData(userProfile);
        });
        add(saveButton);
    }
}

