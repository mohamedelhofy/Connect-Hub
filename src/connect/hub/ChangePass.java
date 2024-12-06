/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Compu City
 */
public class ChangePass extends JFrame {
    private User user=new User();
    private Hashing hash=new Hashing();

    public ChangePass() {
        setTitle("Profile Management");
        setSize(500, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        JLabel passLabel = new JLabel("new password:");
        passLabel.setBounds(20, 30, 100, 20);
        add(passLabel);
        JTextField passField = new JTextField();
        passField.setBounds(120, 30, 310, 20); 
        add(passField);
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(200, 70, 100, 30); // Centered save button
        saveButton.addActionListener(e ->  {
           try {
               user.setHashedPassword(hash.hash(passField.getText()));
           } catch (NoSuchAlgorithmException ex) {
               Logger.getLogger(ChangePass.class.getName()).log(Level.SEVERE, null, ex);
           }
        });
        add(saveButton);
    }
    
    
}
