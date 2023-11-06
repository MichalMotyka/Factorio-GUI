package org.example.gui;

import org.example.model.Document;
import org.example.model.DocumentType;
import org.example.model.Status;
import org.example.service.OrderService;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.Arrays;

public class Order extends JDialog {
    private JPanel contentPane;
    private JTabbedPane tabbedPane1;
    private JButton wyszukajButton;
    private JTextField textField1;
    private JTable table1;
    private JTable table2;
    private JComboBox comboBox1;
    private JTable table3;
    private JButton dodajButton;
    private JButton edytujButton;
    private JButton przesuńButton;
    private JButton buttonOK;
    private OrderService orderService = new OrderService();
    DefaultTableModel model;

    public Order() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        przesuńButton.setEnabled(false);
        edytujButton.setEnabled(false);
        setTableModel();

        tabbedPane1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int index = tabbedPane1.getSelectedIndex();
                if (index == 0){

                }else if (index == 1){

                }else{

                }
            }
        });
        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderForm.main(null);
            }
        });

        loadData();
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTableValues();
            }
        });
        tabbedPane1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                updateTableValues();
            }
        });
        wyszukajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTableValues();
            }
        });
        przesuńButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row1 = table1.getSelectedRow();
                int row2 = table2.getSelectedRow();
                int row3 = table3.getSelectedRow();
                if (table1.isCellSelected(row1,0)){
                    orderService.updateStatus((String) table1.getValueAt(row1,0));
                } else if (table2.isCellSelected(row2,0)) {
                    orderService.updateStatus((String) table2.getValueAt(row2,0));
                }else if (table3.isCellSelected(row3,0)) {
                    orderService.updateStatus((String) table3.getValueAt(row3,0));
                }
            }
        });
        edytujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row1 = table1.getSelectedRow();
                int row2 = table2.getSelectedRow();
                int row3 = table3.getSelectedRow();

                if (table1.isCellSelected(row1,0)){
                    String id = (String) table1.getValueAt(table1.getSelectedRow(),0);
                    OrderForm orderForm = new OrderForm();
                    orderForm.openLoad(id,true);
                } else if (table2.isCellSelected(row2,0)) {
                    String id = (String) table2.getValueAt(table2.getSelectedRow(),0);
                    OrderForm orderForm = new OrderForm();
                    orderForm.openLoad(id,true);
                }else if (table3.isCellSelected(row3,0)) {
                    String id = (String) table3.getValueAt(table3.getSelectedRow(),0);
                    OrderForm orderForm = new OrderForm();
                    orderForm.openLoad(id,true);
                }
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            private long lastClick = 0;
            @Override
            public void mouseClicked(MouseEvent e) {
                przesuńButton.setEnabled(table1.isRowSelected(table1.getSelectedRow()));
                edytujButton.setEnabled(table1.isRowSelected(table1.getSelectedRow()) && comboBox1.getSelectedIndex() != 3);
                if (lastClick+500 > System.currentTimeMillis()){
                    String id = (String) table1.getValueAt(table1.getSelectedRow(),0);
                    OrderForm orderForm = new OrderForm();
                    orderForm.openLoad(id,false);
                }
                lastClick = System.currentTimeMillis();
            }
        });
        table2.addMouseListener(new MouseAdapter() {
            private long lastClick = 0;
            @Override
            public void mousePressed(MouseEvent e) {
                przesuńButton.setEnabled(table2.isRowSelected(table2.getSelectedRow()));
                edytujButton.setEnabled(table2.isRowSelected(table2.getSelectedRow()) && comboBox1.getSelectedIndex() != 3);
                if (lastClick+500 > System.currentTimeMillis()){
                    String id = (String) table2.getValueAt(table2.getSelectedRow(),0);
                    OrderForm orderForm = new OrderForm();
                    orderForm.openLoad(id,false);
                }
                lastClick = System.currentTimeMillis();
            }
        });
        table3.addMouseListener(new MouseAdapter() {
            private long lastClick = 0;
            @Override
            public void mousePressed(MouseEvent e) {
                przesuńButton.setEnabled(table3.isRowSelected(table3.getSelectedRow()));
                edytujButton.setEnabled(table3.isRowSelected(table3.getSelectedRow()) && comboBox1.getSelectedIndex() != 3);
                if (lastClick + 500 > System.currentTimeMillis()){
                    String id = (String) table3.getValueAt(table3.getSelectedRow(),0);
                    OrderForm orderForm = new OrderForm();
                    orderForm.openLoad(id,false);
                }
                lastClick = System.currentTimeMillis();
            }
        });
        tabbedPane1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                przesuńButton.setEnabled(false);
                edytujButton.setEnabled(false);
            }
        });
    }


    public static void main(String[] args) {
        Order dialog = new Order();
        dialog.pack();
        dialog.setVisible(true);
    }

    private void loadData(){
       Document[] documents = orderService.getDocuments(textField1.getText(),getStatus(comboBox1.getSelectedIndex()),getDocument(tabbedPane1.getSelectedIndex()),null);;
       if(documents != null){
           Arrays.stream(documents).toList().forEach(value-> model.addRow(new Object[]{value.getUid(),value.getStatus(),value.getCreateDate()}));
       }
    }

    private Status getStatus(int value){
        Status[] values = Status.values();
        for (Status element : values) {
            if (element.ordinal() == value) {
                return element;
            }
        }
        return null;
    }

    private DocumentType getDocument(int value){
        DocumentType[] values = DocumentType.values();
        for (DocumentType element : values) {
            if (element.ordinal() == value) {
                return element;
            }
        }
        return null;
    }


    private void setTableModel(){
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Status");
        model.addColumn("Data założenia");
        table1.setDefaultEditor(Object.class, null);
        table2.setDefaultEditor(Object.class, null);
        table3.setDefaultEditor(Object.class, null);
        table1.setModel(model);
        table2.setModel(model);
        table3.setModel(model);
    }

    private void updateTableValues(){
        Document[] documents = orderService.getDocuments(textField1.getText(),getStatus(comboBox1.getSelectedIndex()),getDocument(tabbedPane1.getSelectedIndex()),null);
        setTableModel();
        if (documents != null){
            Arrays.stream(documents).toList().forEach(value->{
                model.addRow(new Object[]{value.getUid(),value.getStatus(),value.getCreateDate()});
            });
        }
    }
}

