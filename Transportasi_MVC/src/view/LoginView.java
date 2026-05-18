/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.LoginController;
import javax.swing.*;

public class LoginView extends JFrame {

    JTextField txtUsername;
    JPasswordField txtPassword;
    JButton btnLogin;

    public LoginView() {

        setTitle("Login");
        setSize(300, 200);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel l1 = new JLabel("Username");
        l1.setBounds(20, 20, 100, 30);
        add(l1);

        txtUsername = new JTextField();
        txtUsername.setBounds(120, 20, 120, 30);
        add(txtUsername);

        JLabel l2 = new JLabel("Password");
        l2.setBounds(20, 60, 100, 30);
        add(l2);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(120, 60, 120, 30);
        add(txtPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(90, 110, 100, 30);
        add(btnLogin);

        btnLogin.addActionListener(e -> {

            LoginController controller =
                    new LoginController(this);

            controller.login();
        });
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }
}