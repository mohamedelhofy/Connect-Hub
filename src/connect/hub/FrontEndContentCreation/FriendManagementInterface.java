/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub;

import javax.swing.JFrame;

/**
 *
 * @author Lenovo
 */
public class FriendManagementInterface extends JFrame{
//Displays friend requests with options to accept/decline.
//Shows friend list with options to block/remove.
//Displays friend suggestions with a button to send friend requests.
//Shows online/offline statuses.
    
    public void showFrame(){
        this.setTitle("Friend Management");
        this.setSize(800, 400);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(250, 200);
        this.setLayout(null);
    }
}
