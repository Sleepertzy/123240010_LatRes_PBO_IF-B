/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.KendaraanController;
import java.awt.Font;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class KendaraanView extends JFrame {

    JTextField txtId;
    JTextField txtPlat;
    JTextField txtMerk;
    JTextField txtCari;

    JComboBox<String> cbJenis;

    JTable table;

    JButton btnTambah;
    JButton btnEdit;
    JButton btnHapus;
    JButton btnMenu;
    JButton btnCari;

    public KendaraanView() {

        setTitle("Kelola Data Kendaraan");
        setSize(750, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ===== JUDUL =====

        JLabel title = new JLabel("Tabel Data Kendaraan");

        title.setFont(new Font("Arial", Font.BOLD, 24));

        title.setBounds(20, 20, 300, 30);

        add(title);

        // ===== SEARCH =====

        JLabel lblCari = new JLabel("Cari:");

        lblCari.setBounds(450, 25, 50, 25);

        add(lblCari);

        txtCari = new JTextField();

        txtCari.setBounds(490, 25, 130, 25);

        add(txtCari);

        btnCari = new JButton("Cari Data");

        btnCari.setBounds(630, 25, 90, 25);

        add(btnCari);

        // ===== TABLE =====

        table = new JTable();

        table.setModel(
                new DefaultTableModel(
                        new Object[][]{},
                        new String[]{
                            "ID",
                            "Plat Nomor",
                            "Jenis",
                            "Merk"
                        }
                )
        );

        JScrollPane sp = new JScrollPane(table);

        sp.setBounds(10, 80, 710, 280);

        add(sp);

        // ===== BUTTON =====

        btnTambah = new JButton("Tambah");

        btnTambah.setBounds(170, 390, 90, 30);

        add(btnTambah);

        btnEdit = new JButton("Edit");

        btnEdit.setBounds(280, 390, 90, 30);

        add(btnEdit);

        btnHapus = new JButton("Hapus");

        btnHapus.setBounds(390, 390, 90, 30);

        add(btnHapus);

        btnMenu = new JButton("Kembali ke Menu");

        btnMenu.setBounds(500, 390, 150, 30);

        add(btnMenu);

        // ===== FIELD HIDDEN =====

        txtId = new JTextField();

        txtPlat = new JTextField();

        txtMerk = new JTextField();

        cbJenis = new JComboBox<>();

        cbJenis.addItem("Truk");
        cbJenis.addItem("Pick-up");
        cbJenis.addItem("Van");

        // ===== CONTROLLER =====

        KendaraanController controller =
                new KendaraanController(this);

        controller.tampilData();

        // ===== EVENT =====

        btnCari.addActionListener(e -> {

            controller.cari();
        });

        btnTambah.addActionListener(e -> {

            controller.tambah();
        });

        btnEdit.addActionListener(e -> {

            controller.edit();
        });

        btnHapus.addActionListener(e -> {

            controller.hapus();
        });

        btnMenu.addActionListener(e -> {

            dispose();
        });

        // ===== TABLE CLICK =====

        table.addMouseListener(
                new java.awt.event.MouseAdapter() {

            public void mouseClicked(
                    java.awt.event.MouseEvent evt
            ) {

                int row = table.getSelectedRow();

                txtId.setText(
                        table.getValueAt(row, 0).toString()
                );

                txtPlat.setText(
                        table.getValueAt(row, 1).toString()
                );

                cbJenis.setSelectedItem(
                        table.getValueAt(row, 2).toString()
                );

                txtMerk.setText(
                        table.getValueAt(row, 3).toString()
                );
            }
        });
    }

    // ===== GETTER =====

    public JTable getTable() {
        return table;
    }

    public JTextField getTxtId() {
        return txtId;
    }

    public JTextField getTxtPlat() {
        return txtPlat;
    }

    public JTextField getTxtMerk() {
        return txtMerk;
    }

    public JTextField getTxtCari() {
        return txtCari;
    }

    public JComboBox<String> getCbJenis() {
        return cbJenis;
    }
}