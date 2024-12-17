package NotificationsFrontEnd;

import NotificationsBackEnd.NotificationReadWriteManager;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class CloseButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String str;
    private DefaultTableModel tableModel;
    private ArrayList<Map<String, String>> notificationsList;

    public CloseButtonEditor(JCheckBox checkBox, DefaultTableModel tableModel) {
        super(checkBox);
        this.tableModel = tableModel;
        
        NotificationReadWriteManager manager = new NotificationReadWriteManager();
        this.notificationsList = manager.readToListOfMaps();

        button = new JButton();
        button.setOpaque(true);

        button.addActionListener((ActionEvent e) -> {
            JTable table = (JTable) SwingUtilities.getAncestorOfClass(JTable.class, button);
            if (table != null) {
                // Stop editing and confirm removal
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }

                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Confirm deletion
                    int result = JOptionPane.showConfirmDialog(
                        null, 
                        "Are you sure you want to close this notification?", 
                        "Confirm", 
                        JOptionPane.YES_NO_OPTION
                    );

                    if (result == JOptionPane.YES_OPTION) {
                        // Remove the notification from the list
                        Map<String, String> notificationToRemove = notificationsList.get(selectedRow);
                        notificationsList.remove(selectedRow);

                        // Update the JSON file
                        NotificationReadWriteManager writeManager = new NotificationReadWriteManager();
                        try {
                            writeManager.writeFromListOfMaps(notificationsList);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error updating file", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        // Remove the row from the table
                        tableModel.removeRow(selectedRow);

                        table.revalidate();
                        table.repaint();
                    }
                }
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        str = (value == null) ? "Close" : value.toString();
        button.setText(str);
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return str;
    }
}
