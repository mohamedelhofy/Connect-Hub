/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.FrontEndFriendManagement;

import connect.hub.BackEndFriendManagement.FriendManager;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;


// Editor for the button
class AddButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String label;
    private boolean isClicked;

    public AddButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener((ActionEvent e) -> {
            FriendManager fm = FriendManager.getInstance();
            
            fireEditingStopped();
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        label = (value == null) ? "Click" : value.toString();
        button.setText(label);
        isClicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isClicked) {
            JOptionPane.showMessageDialog(button, "Button clicked in row " + (button.getClientProperty("row")));
        }
        isClicked = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isClicked = false;
        return super.stopCellEditing();
    }
}
