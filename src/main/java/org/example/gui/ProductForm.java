package org.example.gui;

import org.example.model.Product;
import org.example.service.ImageData;
import org.example.service.ProductService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ProductForm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField quantField;
    private JTextField heigthField;
    private JButton dodajButton1;
    private JButton dodajButton;
    private JTable table1;
    private JTextField fileTextField;
    private JCheckBox dostępnyCheckBox;
    private JCheckBox aktywnyCheckBox;
    private JTextArea descriptionField;
    private JTextField autorField;
    private JTextField widthField;
    private JComboBox category;
    private String imageMain;
    private List<ImageData> images = new ArrayList<>();
    private boolean isImage;
    private DefaultTableModel tableModel;
    private String imageMainName;
    private final ProductService productService = new ProductService();

    public ProductForm(int mode, long id) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setLocationRelativeTo(null);
        textField1.setEnabled(false);
        FileChooser.setProductForm(this);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("");
        tableModel.addColumn("Nazwa");
        table1.setModel(tableModel);

        descriptionField.setLineWrap(true);
        autorField.setEnabled(false);
        fileTextField.setEnabled(false);
        ImageRenderer renderer = new ImageRenderer();
        table1.getColumnModel().getColumn(1).setCellRenderer(renderer);
        table1.setDefaultEditor(Object.class, null);

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        dodajButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isImage = true;
                FileChooser fileChooser = new FileChooser();

            }
        });
        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isImage = false;
                FileChooser fileChooser = new FileChooser();
            }
        });
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductService productService = new ProductService();
                if (mode == 0 && productService.addImage(imageMain)){
                    productService.addImages(images);
                    String[] imagesArray = new String[images.size()];
                    for (int i = 0; i < images.size(); i++){
                        imagesArray[i] = images.get(i).getName();
                    }
                    Product product = new Product(
                            0,
                            nameField.getText(),
                            Float.parseFloat(priceField.getText()),
                            Integer.parseInt(quantField.getText()),
                            aktywnyCheckBox.isSelected(),
                            dostępnyCheckBox.isSelected(),
                            imageMainName,
                            imagesArray,
                            descriptionField.getText(),
                            Float.parseFloat(widthField.getText()),
                            Float.parseFloat(heigthField.getText()),
                            null,
                            Objects.requireNonNull(category.getSelectedItem()).toString());
                    if(productService.createProduct(product)){
                       dispose();
                   }
                } else if (mode == 1) {
                    //TODO PIORTYTET:3 Jesli zostanie zwrócony bład to ponowne zatwierdzenie czyta wszystkie obrazy nawet te co nie musza byc wysłane
                    if (imageMain != null){
                        productService.addImage(imageMain);
                    }
                    imageMainName = fileTextField.getText();
                    images.forEach(value->{
                        if (value.getImage() != null){
                            productService.addImage(value.getImage().getAbsolutePath());
                        }
                    });
                        String[] imagesArray = new String[images.size()];
                        for (int i = 0; i < images.size(); i++){
                            imagesArray[i] = images.get(i).getName();
                        }
                        Product product = new Product(
                                Long.parseLong(textField1.getText()),
                                nameField.getText(),
                                Float.parseFloat(priceField.getText()),
                                Integer.parseInt(quantField.getText()),
                                aktywnyCheckBox.isSelected(),
                                dostępnyCheckBox.isSelected(),
                                imageMainName,
                                imagesArray,
                                descriptionField.getText(),
                                Float.parseFloat(widthField.getText()),
                                Float.parseFloat(heigthField.getText()),
                                null,
                                Objects.requireNonNull(category.getSelectedItem()).toString());
                        if(productService.editProduct(product)){
                            dispose();
                        }
                }


            }
        });

        table1.addMouseListener(new MouseAdapter() {
            private long lastClick = 0;

            @Override
            public void mouseClicked(MouseEvent e) {
                if (mode != 2 &&lastClick+1000 > System.currentTimeMillis()){
                    images.remove(table1.getSelectedRow());
                    tableModel.removeRow(table1.getSelectedRow());
                    table1.repaint();
                }
                lastClick = System.currentTimeMillis();
            }
        });

        main(mode,id);


        table1.addComponentListener(new ComponentAdapter() {
        });
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void main(int mode, long id) {
        if (mode >= 1){
            loadData(id);
        }
        if (mode == 2){
            textField1.setEditable(false);
            nameField.setEditable(false);
            priceField.setEditable(false);
            quantField.setEditable(false);
            heigthField.setEditable(false);
            widthField.setEditable(false);
            category.setEnabled(false);
            aktywnyCheckBox.setEnabled(false);
            dostępnyCheckBox.setEnabled(false);
            descriptionField.setEditable(false);
            dodajButton.setEnabled(false);
            dodajButton1.setEnabled(false);
            buttonOK.hide();
        }
        this.pack();
        this.setVisible(true);
    }

    public void addFile(File selectedFile) {
        if (isImage){
            fileTextField.setText(selectedFile.getName());
            imageMain = selectedFile.getAbsolutePath();
            imageMainName = selectedFile.getName();
            return;
        }
        File file = new File(selectedFile.getAbsolutePath());
        images.add(new ImageData(selectedFile.getName(),null,file));
        ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
        tableModel.addRow(new Object[]{icon,selectedFile.getName()});
        table1.setValueAt(icon,tableModel.getRowCount()-1,0);
    }
    private void loadData(long id){
        Product product = productService.getProductById(id);
        textField1.setText(String.valueOf(product.getId()));
        nameField.setText(product.getName());
        priceField.setText(String.valueOf(product.getPrice()));
        quantField.setText(String.valueOf(product.getQuantity()));
        heigthField.setText(String.valueOf(product.getHeight()));
        widthField.setText(String.valueOf(product.getWidth()));
        fileTextField.setText(product.getImage());
        aktywnyCheckBox.setSelected(product.isActive());
        aktywnyCheckBox.setEnabled(product.isActive());
        dostępnyCheckBox.setSelected(product.isAccessibility());
        dostępnyCheckBox.setEnabled(false);
        descriptionField.setText(product.getDescription());
        autorField.setText(product.getAuthor().getName());
        for (String name: product.getImages()) {
            images.add(new ImageData(name,null,null));
            tableModel.addRow(new Object[]{"", name});
        }
        category.setSelectedItem(product.getCategory());
    }
}
