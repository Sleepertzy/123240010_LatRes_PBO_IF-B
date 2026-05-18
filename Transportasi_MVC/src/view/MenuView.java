/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;

public class MenuView extends JFrame {

    JLabel lblWelcome;

    public MenuView(String username) {

        setTitle("Menu");
        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);

        lblWelcome =
                new JLabel(
                        "Selamat Datang, "
                        + username + "!"
                );

        lblWelcome.setBounds(80, 20, 250, 30);
        add(lblWelcome);

        JButton btnKendaraan =
                new JButton("Data Kendaraan");

        btnKendaraan.setBounds(100, 70, 180, 40);
        add(btnKendaraan);

        JButton btnSopir =
                new JButton("Data Sopir");

        btnSopir.setBounds(100, 130, 180, 40);
        add(btnSopir);

        JButton btnLogout =
                new JButton("Logout");

        btnLogout.setBounds(100, 190, 180, 40);
        add(btnLogout);

        btnKendaraan.addActionListener(e -> {

            new KendaraanView().setVisible(true);
        });

        btnSopir.addActionListener(e -> {

            new SopirView().setVisible(true);
        });

        btnLogout.addActionListener(e -> {

            new LoginView().setVisible(true);
            dispose();
        });
    }
}