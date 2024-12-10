/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.FrontEndFriendManagement;

import connect.hub.BackEndFriendManagement.FriendManager;
import connect.hub.User;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingUtilities;


// Editor for the button
class AcceptButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String str;
    private boolean isClicked;
    private String userId;
    private ArrayList<User> pendingList;

    public AcceptButtonEditor(JCheckBox checkBox, String userId, ArrayList<User> pendingList) {
        super(checkBox);
        this.userId = userId;
        this.pendingList = pendingList;

        button = new JButton();
        button.setOpaque(true);
        button.addActionListener((ActionEvent e) -> {
            int selectedRow = ((JTable) SwingUtilities.getAncestorOfClass(JTable.class, button)).getSelectedRow();
            if (selectedRow != -1) {
                User friend = pendingList.get(selectedRow);
                FriendManager fm = FriendManager.getInstance();
                
                ((DefaultTableModel) ((JTable) SwingUtilities.getAncestorOfClass(JTable.class, button)).getModel()).removeRow(selectedRow);
                friendsList.remove(selectedRow);
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        label = (value == null) ? "Remove" : value.toString();
        button.setText(label);
        isClicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return label;
    }
}
