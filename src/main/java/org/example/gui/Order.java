package org.example.gui;

import org.example.service.OrderService;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Order extends JDialog {
    private JPanel contentPane;
    private JTabbedPane tabbedPane1;
    private JButton wyszukajButton;
    private JTextField textField1;
    private JTable table1;
    private JTable table2;
    private JTable table3;
    private JButton buttonOK;
    private OrderService orderService;

    public Order() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        tabbedPane1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int index = tabbedPane1.getSelectedIndex();
                if (index == 0){

                }else if (index == 1){

                }else{

                }
            }
        });
    }

    public static void main(String[] args) {
        Order dialog = new Order();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
