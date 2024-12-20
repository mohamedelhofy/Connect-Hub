/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.FrontEndFriendManagement;

import connect.hub.NewsfeedPage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Lenovo
 */
public class FriendManagementInterface extends JFrame implements ActionListener{
    private JButton requestsButton = new JButton("Friend Requests");
    private JButton friendsButton = new JButton("Friends");
    private JButton suggestedButton = new JButton("Suggested Friends");
    
    public void showFrame(){
        // FRAME
        this.setTitle("Friend Requests");
        this.setSize(500, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(true);
        this.setLocation(400, 20);
        this.setLayout(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new NewsfeedPage().setVisible(true);
                dispose();
            }
        });
        // buttons
        this.requestsButton.setBounds(130, 100, 230, 60);
        this.add(this.requestsButton);
        this.requestsButton.addActionListener(this);

        this.friendsButton.setBounds(130, 200, 230, 60);
        this.add(this.friendsButton);
        this.friendsButton.addActionListener(this);

        this.suggestedButton.setBounds(130, 300, 230, 60);
        this.add(this.suggestedButton);
        this.suggestedButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==requestsButton){
            dispose();
            FriendRequestsWindow frw = new FriendRequestsWindow();
            frw.showFrame();
        }
        if(e.getSource()==friendsButton){
            dispose();
            FriendsWindow frw = new FriendsWindow();
            frw.showFrame();
        }
        if(e.getSource()==suggestedButton){
            dispose();
            SuggestedWindow sw = new SuggestedWindow();
            sw.showFrame();
        }
    }
}