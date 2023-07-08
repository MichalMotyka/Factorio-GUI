package org.example.gui;

import org.example.model.Document;
import org.example.model.DocumentType;
import org.example.model.Product;
import org.example.model.Status;
import org.example.service.OrderService;
import org.example.service.ProductService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class OrderForm extends JDialog {
    private JPanel contentPane;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JPanel zamfield;
    private JTextField textField9;
    private JTextField textField8;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField12;
    private JTextField textField13;
    private JTextField textField14;
    private JTextField textField15;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTable table1;
    private JButton anulujButton;
    private JButton zatwierdźButton;
    private JButton dodajProduktButton;
    private JCheckBox opłataPobraniowaCheckBox;
    private JComboBox comboBox2;
    private JButton buttonOK;
    DefaultTableModel model;
    ProductService productService = new ProductService();
    OrderService orderService = new OrderService();
    List<Product> productList = new ArrayList<>();
    private String documentNumber = null;

    public OrderForm() {
        changeType();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        model = new DefaultTableModel();
        model.addColumn("Produkt");
        model.addColumn("Ilość");
        table1.setModel(model);
        table1.setDefaultEditor(Object.class,null);
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             changeType();
            }
        });
        dodajProduktButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Magazyn magazyn = new Magazyn();
                magazyn.chooseProduct(OrderForm.this);
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = table1.getSelectedRow();
                String name = table1.getValueAt(row,0).toString();
                int quantity = Integer.parseInt(table1.getValueAt(row,1).toString());
                removeProduct(name,quantity,row);
            }
        });
        zatwierdźButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Document response = orderService.createDocument(
                        new Document(
                            0,
                            DocumentType.valueOf(comboBox1.getSelectedItem().toString()),
                            Status.NEW,
                            null,
                            null,
                            null,
                            null,
                            textArea2.getText(),
                            productList
                        )
                );
                if (response != null){
                    dispose();
                    JOptionPane.showMessageDialog(null,"Pomyślnie utworzono dokument od numerze "+response.getUid());
                }
            }
        });
        anulujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }

    public static void main(String[] args) {
        OrderForm dialog = new OrderForm();
        dialog.pack();
        dialog.setVisible(true);
    }

    public void openLoad(String id){
        documentNumber = id;
        loadData();
        this.pack();
        this.setVisible(true);
    }

    private void loadData(){
        Document document = orderService.getExtendedDocument(documentNumber);
        comboBox1.setSelectedItem(document.getDocumentType());
        textField1.setText(document.getUid());
        textField2.setText(document.getCreateDate());
        textField4.setText(document.getEndDate());
        textField3.setText(document.getStatus().name());
        textField5.setText(document.getAuthor().getName());
        textArea2.setText(document.getDescription());
        document.getProductList().forEach(value->{
            model.addRow(new Object[]{value.getName(),value.getQuantity()});
        });
        if (documentNumber != null){
            comboBox1.setEnabled(false);
            textArea2.setEditable(false);
            dodajProduktButton.setEnabled(false);
            zatwierdźButton.setVisible(false);
        }
    }

    private void changeType(){
        System.out.println(getSize());
        if (comboBox1.getSelectedItem().equals("ZAM") && !zamfield.isVisible()){
            zamfield.setVisible(true);
            contentPane.setSize(490,455);
            setBounds(10,10,503,630);
        }else if (zamfield.isVisible() && !comboBox1.getSelectedItem().equals("ZAM")){
            zamfield.setVisible(false);
            contentPane.setSize(490,400);
            setBounds(10,10,503,355);
        }
    }

    public void addProduct(long id) {
        Product product = productService.getProductById(id);
        product.setQuantity(1);
        if (model.getRowCount() == 0){
            productList.add(product);
            model.addRow(new Object[]{product.getName(),product.getQuantity()});
        }else {
            for (int x = 0; model.getRowCount() > x; x++){
                if (productList.get(x).getId() == product.getId()){
                    productList.get(x).setQuantity(productList.get(x).getQuantity()+1);
                    table1.setValueAt(productList.get(x).getQuantity(),x,1);
                }else if (model.getRowCount() == x+1){
                    productList.add(product);
                    model.addRow(new Object[]{product.getName(),product.getQuantity()});
                }
            }
        }
    }
    private void removeProduct(String name, long quantity,int row){
        Product product;
        for (int x = 0; x < productList.size(); x++){
            product = productList.get(x);
            if (product.getQuantity() == quantity && product.getName().equals(name) && product.getQuantity() > 0){
                table1.setValueAt(quantity-1,row,1);
                productList.get(x).setQuantity(productList.get(x).getQuantity()-1);
                if (productList.get(x).getQuantity() <= 0){
                    model.removeRow(x);
                    productList.remove(x);
                }
            }
        }
    }
}
