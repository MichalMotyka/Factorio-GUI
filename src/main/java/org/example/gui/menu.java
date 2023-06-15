package org.example.gui;

import org.example.model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class menu extends JDialog {
    private JPanel contentPane;
    private JButton magazynButton;
    private JButton zamowieniaButton;
    private JButton fakturyButton;
    private JButton xButton;
    private JButton uzytkownicyButton;
    private JButton buttonOK;
    private JButton buttonCancel;
    private Point startPoint;
    public menu() {
        setContentPane(contentPane);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        xButton.setBorderPainted(false);
        xButton.setOpaque(false);
        xButton.setFocusPainted(false);
        xButton.setBackground(new Color(0,0,0,0));

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        xButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        contentPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
            }
        });
        contentPane.addMouseListener(new MouseAdapter() {
        });
        contentPane.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int deltaX = e.getX() - startPoint.x;
                int deltaY = e.getY() - startPoint.y;

                int componentX = getX() + deltaX;
                int componentY = getY() + deltaY;

                setLocation(componentX, componentY);
            }
        });
        magazynButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Magazyn.main(null);
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        menu dialog = new menu();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
