package org.example.gui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.io.File;

public class FileChooser extends JDialog {
    private JPanel contentPane;
    private JFileChooser fileChooser;
    private JButton buttonOK;
    private JButton buttonCancel;
    private static ProductForm productForm;

    public static void setProductForm(ProductForm productForm1){
        productForm = productForm1;
    }

    public FileChooser() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("IMAGE FILES","jpg","png");
        fileChooser.setFileFilter(filter);



        // call onCancel() on ESCAPE
        int result = fileChooser.showOpenDialog(contentPane);
        if (result == JFileChooser.APPROVE_OPTION) {
            productForm.addFile(fileChooser.getSelectedFile());
            dispose();
        }
//        openButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JFileChooser.APPROVE_OPTION
//                productForm.addFile(fileChooser.getSelectedFile());
//            }
//        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }


    public  void main(String[] args) {
        FileChooser dialog = this;
        dialog.pack();
        dialog.setVisible(true);
        dialog.dispose();
    }
}
