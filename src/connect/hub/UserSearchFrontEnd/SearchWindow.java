/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.UserSearchFrontEnd;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 *
 * @author Lenovo
 */
public class SearchWindow extends JFrame implements ActionListener{
    private JTextField textField = new JTextField();
    private JButton searchButton = new JButton("Search");
    
    public void showFrame(){
        // FRAME
        this.setTitle("Search");
        this.setSize(600, 250);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(350, 150);
        this.setLayout(null);
        
        // COMPONENTS        
        this.textField.setBounds(45, 50, 500, 50);
        this.textField.setFont(new Font("Arial", Font.PLAIN, 16));
        this.add(this.textField);
        
        this.searchButton.setBounds(240, 120, 100, 40);
        this.add(this.searchButton);
        this.searchButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.searchButton){
            String keyWord = textField.getText().toString();
            if(keyWord.equals("")){
                JOptionPane.showMessageDialog(null, "Search bar is empty", "Message", JOptionPane.ERROR_MESSAGE);
            }
            else{
                UserSearchResultsWindow resultsWindow = new UserSearchResultsWindow();
                resultsWindow.showFrame(keyWord);
            }
        }
    }
}
