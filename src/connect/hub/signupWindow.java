/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub;

/**
 *
 * @author rawan
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;

public class signupWindow extends JFrame {
    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField dobField;
    private JButton signUpButton;
    private UserServices userServices;

    public signupWindow(UserServices userServices) {
        this.userServices = userServices;
        setTitle("Sign Up");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        emailField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        dobField = new JTextField();
        signUpButton = new JButton("Sign Up");

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSignUp();
            }
        });

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("Date of Birth (YYYY-MM-DD):"));
        add(dobField);
        add(signUpButton);
    }

    private void performSignUp() {
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
       Date dob =new Date();
        try {
            User user = new User(email ,username , password , dob );
            user.setEmail(email);
            user.setUsername(username);
            user.setHashedPassword(password); 
            user.setDOB(dob);
            user.setStatus(false);

            userServices.signUp(user);
            JOptionPane.showMessageDialog(this, "Sign up successful!");
            dispose(); 
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
        ///////////////////////// here newsfeed will start
    }
}
