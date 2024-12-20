/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.List;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author rawan
 */



public class LoginWindow extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signUpButton;
    private UserServices userServices;
    private User currentUser;

    // Empty constructor for login in newsfeed
    public LoginWindow() {
        this.userServices = userServices;
        setTitle("Login");
        setSize(350, 250);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  
        getContentPane().setBackground(new Color(60, 63, 65));

        initComponents();
    }

    public LoginWindow(UserServices userServices) {
        this.userServices = userServices;
        setTitle("Login");
        setSize(350, 250);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  
        getContentPane().setBackground(new Color(60, 63, 65));

        initComponents();
    }

    private void initComponents() {
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        signUpButton = new JButton("Sign Up");

        //  Login Button
        loginButton.setBackground(new Color(0, 123, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setFocusPainted(false);

        //  SignUp Button
        signUpButton.setBackground(new Color(40, 167, 69)); 
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFont(new Font("Arial", Font.BOLD, 14));
        signUpButton.setFocusPainted(false);

        // listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                try {
                    currentUser = userServices.login(email, password);
                    JOptionPane.showMessageDialog(LoginWindow.this, "Login successful!");
                    dispose();
                    new NewsfeedPage().setVisible(true);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(LoginWindow.this, ex.getMessage(), "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSignUpWindow();
            }
        });

        // 
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().setBackground(new Color(240, 240, 240));  

       
        add(Box.createVerticalStrut(20)); 

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(emailLabel);

        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        emailField.setBackground(Color.WHITE);
        emailField.setForeground(Color.BLACK);
        emailField.setPreferredSize(new Dimension(250, 30)); 
        add(emailField);

        add(Box.createVerticalStrut(10));  

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(passwordLabel);

        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBackground(Color.WHITE);
        passwordField.setForeground(Color.BLACK);
        passwordField.setPreferredSize(new Dimension(250, 30));
        add(passwordField);

        add(Box.createVerticalStrut(20));  

        // Add  buttons///
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); 
        buttonPanel.setBackground(new Color(240, 240, 240));

        buttonPanel.add(loginButton);
        buttonPanel.add(signUpButton);
        add(buttonPanel);

        add(Box.createVerticalStrut(20));  
    }

    private void openSignUpWindow() {
        signupWindow signUpWindow = new signupWindow(userServices);
        this.dispose();
        signUpWindow.setVisible(true);
}


}