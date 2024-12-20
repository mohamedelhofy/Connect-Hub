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
import javax.swing.table.DefaultTableModel;

public class BlockButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String str;

    public BlockButtonEditor(JCheckBox checkBox, String userId, ArrayList<User> pendingList, DefaultTableModel tableModel) {
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
                    User friend = pendingList.get(selectedRow);
                    FriendManager fm = FriendManager.getInstance();
                    fm.blockFriend(userId, friend.getUserId());

                    // Remove the row from the list and table model
                    pendingList.remove(selectedRow);
                    tableModel.removeRow(selectedRow);

                    // Revalidate the table to avoid potential inconsistencies
                    table.revalidate();
                    table.repaint();
                }
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        str = (value == null) ? "Block" : value.toString();
        button.setText(str);
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return str;
    }
}
