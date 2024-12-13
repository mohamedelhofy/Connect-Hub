/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.UserSearchFrontEnd;

import connect.hub.BackEndFriendManagement.FriendManager;
import connect.hub.User;
import connect.hub.profileGui;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 *
 * @author Lenovo
 */
public class ProfileButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String str;

    public ProfileButtonEditor(JCheckBox checkBox, ArrayList<User> pendingList) {
        super(checkBox);

        button = new JButton();
        button.setOpaque(true);
        button.addActionListener((ActionEvent e) -> {
            JTable table = (JTable) SwingUtilities.getAncestorOfClass(JTable.class, button);
            if (table != null) {
                // Stop editing before making changes
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }

                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Process the friend request
                    User user = pendingList.get(selectedRow);
                    profileGui p = new profileGui(user.getUserId());
                            
                    // Revalidate the table to avoid potential inconsistencies
                    table.revalidate();
                    table.repaint();
                }
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        str = (value == null) ? "Accept" : value.toString();
        button.setText(str);
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return str;
    }
}