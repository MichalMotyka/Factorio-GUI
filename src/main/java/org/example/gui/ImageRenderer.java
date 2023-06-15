package org.example.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ImageRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof ImageIcon) {
            JLabel label = new JLabel((ImageIcon) value);
            label.setOpaque(true);
            label.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            return label;
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}