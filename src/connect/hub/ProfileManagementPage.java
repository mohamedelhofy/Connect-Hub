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

public class ProfileManagementPage extends JFrame {
    private JTextField bioField;
    private JTextField profilePhotoField;
    private JTextField coverPhotoField;
    private JButton saveButton;

    public ProfileManagementPage( String userId) {
        setTitle("Profile Management");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel bioLabel = new JLabel("Bio:");
        bioLabel.setBounds(20, 20, 100, 20);
        add(bioLabel);

        bioField = new JTextField();
        bioField.setBounds(120, 20, 200, 20);
        add(bioField);

        JLabel profilePhotoLabel = new JLabel("Profile Photo Path:");
        profilePhotoLabel.setBounds(20, 60, 150, 20);
        add(profilePhotoLabel);

        profilePhotoField = new JTextField();
        profilePhotoField.setBounds(170, 60, 150, 20);
        add(profilePhotoField);

        JLabel coverPhotoLabel = new JLabel("Cover Photo Path:");
        coverPhotoLabel.setBounds(20, 100, 150, 20);
        add(coverPhotoLabel);

        coverPhotoField = new JTextField();
        coverPhotoField.setBounds(170, 100, 150, 20);
        add(coverPhotoField);

        saveButton = new JButton("Save");
        saveButton.setBounds(150, 150, 80, 30);
        add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bio = bioField.getText();
                String profilePhotoPath = profilePhotoField.getText();
                String coverPhotoPath = coverPhotoField.getText();

                JOptionPane.showMessageDialog(null, "Profile updated successfully!");
            }
        });
    }

    public static void main(String[] args) {
         new ProfileManagementPage( "1").setVisible(true);
    }
}

