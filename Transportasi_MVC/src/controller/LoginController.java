/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import connection.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import view.LoginView;
import view.MenuView;

public class LoginController {

    LoginView view;

    public LoginController(LoginView view) {
        this.view = view;
    }

    public void login() {

        String username = view.getTxtUsername().getText();
        String password = view.getTxtPassword().getText();

        if (username.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Username dan Password wajib diisi!"
            );

            return;
        }

        try {

            Connection con = Koneksi.getConnection();

            String sql = "SELECT * FROM users "
                    + "WHERE username=? AND password=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                JOptionPane.showMessageDialog(
                        null,
                        "Login Berhasil!"
                );

                MenuView menu =
                        new MenuView(username);

                menu.setVisible(true);

                view.dispose();

            } else {

                JOptionPane.showMessageDialog(
                        null,
                        "Username atau Password salah!"
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage()
            );
        }
    }
}