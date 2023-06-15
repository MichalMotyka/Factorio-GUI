package org.example.gui;

import org.example.service.LoginService;
import org.example.service.ProductService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField login;
    private JPasswordField passwd;
    private JButton zalogujButton;
    private JLabel loginlabel;
    private JLabel haslo;
    private JButton buttonCancel;

    public Login() {
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        zalogujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginService loginService = new LoginService();
                if (loginService.login(login.getText(),passwd.getText())){
                    dispose();
                    menu.main(null);
                }
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
        Login dialog = new Login();
        dialog.pack();
        dialog.setVisible(true);
    }
}
