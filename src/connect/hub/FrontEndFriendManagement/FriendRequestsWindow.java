/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.FrontEndFriendManagement;

import connect.hub.BackEndFriendManagement.FriendshipDatabase;
import connect.hub.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class FriendRequestsWindow extends JFrame implements ActionListener{
//  View and respond to friend requests. 
//  View and manage the friend list. 
//  View and send friend suggestions. 
//  Block or remove friends
    private User userInstance = User.getInstance();
    
    private JButton backButton = new JButton("Back");

    private ArrayList<User> pendingList;

    private DefaultTableModel pendingTableModel = new DefaultTableModel(new Object[]{"Username", "Accept", "Decline"}, 0);
    
    public void showFrame() {
        // FRAME
        this.setTitle("Friend Requests");
        this.setSize(600, 750);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(true);
        this.setLocation(400, 20);
        this.setLayout(null);

        this.backButton.setBounds(180, 600, 230, 60);
        this.add(this.backButton);
        this.backButton.addActionListener(this);

        // Reload the pending list
        FriendshipDatabase friendShip = new FriendshipDatabase(userInstance.getUserId());
        this.pendingList = friendShip.getReceivedPendingList();

        // Clear the existing table model and populate it again
        pendingTableModel.setRowCount(0); // Clear existing rows
        for (User user : this.pendingList) {
            String name = user.getUsername();
            pendingTableModel.addRow(new Object[]{name, "Accept", "Decline"});
        }

        JTable pendingTable = new JTable(pendingTableModel);

        // Add fresh button editors and renderers with the updated list
        pendingTable.getColumn("Accept").setCellRenderer(new ButtonRenderer());
        pendingTable.getColumn("Accept").setCellEditor(new AcceptButtonEditor(new JCheckBox(), userInstance.getUserId(), pendingList, pendingTableModel));
        pendingTable.getColumn("Decline").setCellRenderer(new ButtonRenderer1());
        pendingTable.getColumn("Decline").setCellEditor(new DeclineButtonEditor(new JCheckBox(), userInstance.getUserId(), pendingList, pendingTableModel));

        // Add the table to a scroll pane
        JScrollPane pendingScrollPane = new JScrollPane(pendingTable);
        pendingScrollPane.setBounds(50, 50, 500, 500);
        TitledBorder pendingTitledBorder = new TitledBorder("Friend Requests");
        pendingScrollPane.setBorder(pendingTitledBorder);
        this.add(pendingScrollPane);

        // Refresh the frame
        this.revalidate();
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==backButton){
            dispose();
        FriendManagementInterface fmi = new FriendManagementInterface();
        fmi.showFrame();
        }
    }
}