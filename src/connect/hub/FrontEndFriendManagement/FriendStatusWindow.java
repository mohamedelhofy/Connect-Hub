/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.FrontEndFriendManagement;
import connect.hub.BackEndFriendManagement.FriendshipDatabase;
import connect.hub.NewsfeedPage;
import connect.hub.User;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class FriendStatusWindow extends JFrame{
    User userInstance = User.getInstance();
    private ArrayList<User> friendsList;
    private DefaultTableModel tableModel;
    
    public void showFrame(){
        // FRAME
        this.setTitle("Friends Status");
        this.setSize(1000, 1000);
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
        FriendshipDatabase friendShip = new FriendshipDatabase(userInstance.getUserId()); 
        this.friendsList = friendShip.getFriendList();
        
        tableModel = new DefaultTableModel(new Object[]{"Username", "Status"}, 0);
        String status;
        for (User friend : this.friendsList) {
            if(friend.isOnline())
                status = "Online";
            else
                status = "Offline";
            String userName = friend.getUsername();
            tableModel.addRow(new Object[]{userName, status});
        }
        JTable table = new JTable(tableModel);

        // Create table and set its model
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 50, 900, 900);
        TitledBorder titledBorder = new TitledBorder("Friends");
        scrollPane.setBorder(titledBorder);
        this.add(scrollPane);
        this.setVisible(true);
    }
}
