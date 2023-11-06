package org.example.gui;

import org.example.gui.controller.ProductTabelController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class Magazyn extends JDialog {
    private JPanel contentPane;
    private JButton wyszukajButton;
    private JTextField textField1;
    private JTable table1;
    private JButton dodajButton;
    private JButton edytujButton;
    private JComboBox comboBox1;
    private JButton buttonOK;
    private JButton buttonCancel;
    private String[] columnNames = {"","ID","Nazwa","Ilość"};
    private ProductTabelController productTabelController;
    private boolean chooseMode = false;
    private OrderForm orderForm;
    private final Magazyn magazyn;

    public Magazyn() {
        magazyn = this;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(contentPane);
        setModal(true);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonOK);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nazwa");
        tableModel.addColumn("Ilość");
        table1.setDefaultEditor(Object.class, null);
        table1.setModel(tableModel);
        productTabelController = new ProductTabelController(table1,tableModel);
        productTabelController.loadData();



        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductForm productForm = new ProductForm(0,0,magazyn);
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            private long lastClick = 0;

            @Override
            public void mouseClicked(MouseEvent e) {
                if (lastClick+1000 > System.currentTimeMillis()){
                    long id = (long) table1.getValueAt(table1.getSelectedRow(),0);
                    if (!chooseMode){
                        ProductForm productForm = new ProductForm(2,id,magazyn);
                    }else {
                        orderForm.addProduct(id);
                    }

                }
                lastClick = System.currentTimeMillis();
            }
        });
        edytujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long id = (long) table1.getValueAt(table1.getSelectedRow(),0);
                ProductForm productForm = new ProductForm(1,id,magazyn);
            }
        });
        wyszukajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean active = null;
                if (comboBox1.getSelectedItem().equals("Nie")){
                    active = false;
                }else if(comboBox1.getSelectedItem().equals("Tak")){
                    active = true;
                }
                productTabelController.searchdata(textField1.getText(),active);
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean active = null;
                if (comboBox1.getSelectedItem().equals("Nie")){
                    active = false;
                }else if(comboBox1.getSelectedItem().equals("Tak")){
                    active = true;
                }
                productTabelController.searchdata(textField1.getText(),active);
            }
        });
    }

    public static void main(String[] args) {
        Magazyn dialog = new Magazyn();
        dialog.pack();
        dialog.setVisible(true);
    }

    public void chooseProduct(OrderForm orderForm) {
        comboBox1.setSelectedItem("Tak");
        comboBox1.setEnabled(false);
        chooseMode = true;
        this.orderForm = orderForm;
        this.pack();
        this.setVisible(true);
    }

    public ProductTabelController getProductTabelController(){
        return productTabelController;
    }
}
