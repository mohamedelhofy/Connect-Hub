/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NotificationsFrontEnd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Lenovo
 */
public class NotificationsWindow extends JFrame implements ActionListener{
    private JButton requestsButton = new JButton("Received Friend Requests");
    
    public void showFrame(){
        // FRAME
        this.setTitle("Received Friend Requests");
        this.setSize(500, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(true);
        this.setLocation(400, 20);
        this.setLayout(null);

        // buttons
        this.requestsButton.setBounds(130, 100, 230, 60);
        this.add(this.requestsButton);
        this.requestsButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==requestsButton){
            FriendRequestsNotificationWindow frw = new FriendRequestsNotificationWindow();
            frw.showFrame();
        }
    }
}