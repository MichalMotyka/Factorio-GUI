package org.example.gui.controller;

import org.example.model.Product;
import org.example.model.ProductRow;
import org.example.service.ProductService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ProductTabelController {
    private JTable jTable;
    private DefaultTableModel defaultTableModel;
    private static ProductService productService;


    public ProductTabelController(JTable table, DefaultTableModel defaultTableModel){
        this.jTable = table;
        this.defaultTableModel = defaultTableModel;
        if (productService == null){
            productService = new ProductService();
        }
    }

    public void loadData(){
        ProductRow[] products = productService.getAllProducts();
        for (ProductRow product : products){
            this.defaultTableModel.addRow(new Object[]{product.getId(),product.getName(),product.getQuantity()});
        }
    }


    void addRow(){

    }

    public void searchdata(String text,Boolean acitve) {
        ProductRow[] products = productService.searchProduct(text,acitve);
        for (int x = this.defaultTableModel.getRowCount()-1;x >=0;x--){
            this.defaultTableModel.removeRow(x);
        }
        for (ProductRow product : products){
            this.defaultTableModel.addRow(new Object[]{product.getId(),product.getName(),product.getQuantity()});
        }
    }
}
