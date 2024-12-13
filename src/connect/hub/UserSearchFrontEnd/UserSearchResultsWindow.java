/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.UserSearchFrontEnd;

import connect.hub.FrontEndFriendManagement.AcceptButtonEditor;
import connect.hub.FrontEndFriendManagement.AddButtonEditor;
import connect.hub.FrontEndFriendManagement.BlockButtonEditor;
import connect.hub.FrontEndFriendManagement.DeclineButtonEditor;
import connect.hub.FrontEndFriendManagement.RemoveButtonEditor;
import connect.hub.User;
import connect.hub.UserSearchBackEnd.SearchedListsDatabase;
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
public class UserSearchResultsWindow extends JFrame implements ActionListener{
    private User userInstance = User.getInstance();
    private String keyWord;
    
    private JButton refreshButton = new JButton("Refresh");

    private ArrayList<User> searchedFriendsList;
    private ArrayList<User> searchedReceivedPendingList;
    private ArrayList<User> searchedSentPendingList;
    private ArrayList<User> searchedSuggestedList;

    private DefaultTableModel searchedFriendsTableModel = new DefaultTableModel(new Object[]{"Username", "Profile", "Remove", "Block"}, 0);
    private DefaultTableModel searchedReceivedPendingTableModel = new DefaultTableModel(new Object[]{"Username", "Profile", "Accept", "Decline", "Block"}, 0);
    private DefaultTableModel searchedSentPendingTableModel = new DefaultTableModel(new Object[]{"Username", "Profile", "Block"}, 0);
    private DefaultTableModel searchedSuggestedTableModel = new DefaultTableModel(new Object[]{"Username", "Profile", "Add", "Block"}, 0);
    
    public void showFrame(String keyWord) {
        this.keyWord = keyWord;        
        // FRAME
        this.setTitle("Search");
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(true);
        this.setLocation(400, 20);
        this.setLayout(null);

        // Reload the lists
        SearchedListsDatabase database = new SearchedListsDatabase(userInstance.getUserId());
        this.searchedFriendsList = database.getSearchedFriendsList(keyWord);
        this.searchedReceivedPendingList = database.getSearchedReceivedPendingList(keyWord);
        this.searchedSentPendingList = database.getSearchedSentPendingList(keyWord);
        this.searchedSuggestedList = database.getSearchedSuggestedList(keyWord);

        // Clear the existing table model and populate it again
        searchedFriendsTableModel.setRowCount(0); // Clear existing rows
        for (User user : this.searchedFriendsList) {
            String name = user.getUsername();
            searchedFriendsTableModel.addRow(new Object[]{name, "Profile", "Remove", "Block"});
        }
        searchedReceivedPendingTableModel.setRowCount(0); // Clear existing rows
        for (User user : this.searchedReceivedPendingList) {
            String name = user.getUsername();
            searchedReceivedPendingTableModel.addRow(new Object[]{name, "Profile", "Accept", "Decline", "Block"});
        }
        searchedSentPendingTableModel.setRowCount(0); // Clear existing rows
        for (User user : this.searchedSentPendingList) {
            String name = user.getUsername();
            searchedSentPendingTableModel.addRow(new Object[]{name, "Profile", "Block"});
        }
        searchedSuggestedTableModel.setRowCount(0); // Clear existing rows
        for (User user : this.searchedSuggestedList) {
            String name = user.getUsername();
            searchedSuggestedTableModel.addRow(new Object[]{name, "Profile", "Add", "Block"});
        }

        JTable searchedFriendsTable = new JTable(searchedFriendsTableModel);
        JTable searchedReceivedPendingTable = new JTable(searchedReceivedPendingTableModel);
        JTable searchedSentPendingTable = new JTable(searchedSentPendingTableModel);
        JTable searchedSuggestedTable = new JTable(searchedSuggestedTableModel);

        // Add fresh button editors and renderers with the updated list
        searchedFriendsTable.getColumn("Profile").setCellRenderer(new ButtonRenderer());
        searchedFriendsTable.getColumn("Profile").setCellEditor(new ProfileButtonEditor(new JCheckBox(), searchedFriendsList)); 
        searchedFriendsTable.getColumn("Remove").setCellRenderer(new ButtonRenderer());
        searchedFriendsTable.getColumn("Remove").setCellEditor(new RemoveButtonEditor(new JCheckBox(), userInstance.getUserId(), searchedFriendsList, searchedFriendsTableModel));
        searchedFriendsTable.getColumn("Block").setCellRenderer(new ButtonRenderer());
        searchedFriendsTable.getColumn("Block").setCellEditor(new BlockButtonEditor(new JCheckBox(), userInstance.getUserId(), searchedFriendsList, searchedFriendsTableModel));

        searchedReceivedPendingTable.getColumn("Profile").setCellRenderer(new ButtonRenderer());
        searchedReceivedPendingTable.getColumn("Profile").setCellEditor(new ProfileButtonEditor(new JCheckBox(), searchedReceivedPendingList)); 
        searchedReceivedPendingTable.getColumn("Accept").setCellRenderer(new ButtonRenderer());
        searchedReceivedPendingTable.getColumn("Accept").setCellEditor(new AcceptButtonEditor(new JCheckBox(), userInstance.getUserId(), searchedReceivedPendingList, searchedReceivedPendingTableModel));
        searchedReceivedPendingTable.getColumn("Decline").setCellRenderer(new ButtonRenderer());
        searchedReceivedPendingTable.getColumn("Decline").setCellEditor(new DeclineButtonEditor(new JCheckBox(), userInstance.getUserId(), searchedReceivedPendingList, searchedReceivedPendingTableModel));
        searchedReceivedPendingTable.getColumn("Block").setCellRenderer(new ButtonRenderer());
        searchedReceivedPendingTable.getColumn("Block").setCellEditor(new BlockButtonEditor(new JCheckBox(), userInstance.getUserId(), searchedReceivedPendingList, searchedReceivedPendingTableModel));

        searchedSentPendingTable.getColumn("Profile").setCellRenderer(new ButtonRenderer());
        searchedSentPendingTable.getColumn("Profile").setCellEditor(new ProfileButtonEditor(new JCheckBox(), searchedSentPendingList)); 
        searchedSentPendingTable.getColumn("Block").setCellRenderer(new ButtonRenderer());
        searchedSentPendingTable.getColumn("Block").setCellEditor(new BlockButtonEditor(new JCheckBox(), userInstance.getUserId(), searchedSentPendingList, searchedSentPendingTableModel));

        searchedSuggestedTable.getColumn("Profile").setCellRenderer(new ButtonRenderer());
        searchedSuggestedTable.getColumn("Profile").setCellEditor(new ProfileButtonEditor(new JCheckBox(), searchedSuggestedList)); 
        searchedSuggestedTable.getColumn("Add").setCellRenderer(new ButtonRenderer());
        searchedSuggestedTable.getColumn("Add").setCellEditor(new AddButtonEditor(new JCheckBox(), userInstance.getUserId(), searchedSuggestedList, searchedSuggestedTableModel));
        searchedSuggestedTable.getColumn("Block").setCellRenderer(new ButtonRenderer());
        searchedSuggestedTable.getColumn("Block").setCellEditor(new BlockButtonEditor(new JCheckBox(), userInstance.getUserId(), searchedSuggestedList, searchedSuggestedTableModel));

        // Add the table to a scroll pane
        JScrollPane ScrollPane1 = new JScrollPane(searchedFriendsTable);
        ScrollPane1.setBounds(50, 50, 400, 400);
        TitledBorder TitledBorder1 = new TitledBorder("Friends");
        ScrollPane1.setBorder(TitledBorder1);
        this.add(ScrollPane1);

        JScrollPane ScrollPane2 = new JScrollPane(searchedReceivedPendingTable);
        ScrollPane2.setBounds(500, 50, 400, 400);
        TitledBorder TitledBorder2 = new TitledBorder("Received Friend Requests");
        ScrollPane2.setBorder(TitledBorder2);
        this.add(ScrollPane2);

        JScrollPane ScrollPane3 = new JScrollPane(searchedSentPendingTable);
        ScrollPane3.setBounds(50, 500, 400, 400);
        TitledBorder TitledBorder3 = new TitledBorder("Sent Friend Requests");
        ScrollPane3.setBorder(TitledBorder3);
        this.add(ScrollPane3);

        JScrollPane ScrollPane4 = new JScrollPane(searchedSuggestedTable);
        ScrollPane4.setBounds(500, 500, 400, 400);
        TitledBorder TitledBorder4 = new TitledBorder("Other Users");
        ScrollPane4.setBorder(TitledBorder4);
        this.add(ScrollPane4);

        // Refresh the frame
        this.revalidate();
        this.repaint();
        
        this.refreshButton.setBounds(400, 10, 100, 40);
        this.add(this.refreshButton);
        this.refreshButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.refreshButton){
            dispose();
            UserSearchResultsWindow resultsWindow = new UserSearchResultsWindow();
            resultsWindow.showFrame(keyWord);
        }
    }

}