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
import javax.swing.table.DefaultTableModel;
import view.KendaraanView;

public class KendaraanController {

    KendaraanView view;

    public KendaraanController(KendaraanView view) {
        this.view = view;
    }

    public void tampilData() {

        DefaultTableModel model =
                (DefaultTableModel)
                view.getTable().getModel();

        model.setRowCount(0);

        try {

            Connection con =
                    Koneksi.getConnection();

            String sql =
                    "SELECT * FROM kendaraan";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("plat_nomor"),
                    rs.getString("jenis"),
                    rs.getString("merk")
                };

                model.addRow(row);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage()
            );
        }
    }

    public void tambah() {

    try {

        String plat = JOptionPane.showInputDialog(
                "Masukkan Plat Nomor"
        );

        String[] pilihan = {
            "Truk",
            "Pick-up",
            "Van"
        };

        String jenis = (String) JOptionPane.showInputDialog(
                null,
                "Pilih Jenis Kendaraan",
                "Jenis",
                JOptionPane.QUESTION_MESSAGE,
                null,
                pilihan,
                pilihan[0]
        );

        String merk = JOptionPane.showInputDialog(
                "Masukkan Merk Kendaraan"
        );

        Connection con =
                Koneksi.getConnection();

        String sql =
                "INSERT INTO kendaraan VALUES(NULL,?,?,?)";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setString(1, plat);
        ps.setString(2, jenis);
        ps.setString(3, merk);

        ps.executeUpdate();

        JOptionPane.showMessageDialog(
                null,
                "Data berhasil ditambah!"
        );

        tampilData();

        } catch (Exception e) {

        JOptionPane.showMessageDialog(
                null,
                e.getMessage()
            );
        }
    }

    public void edit() {

    try {

        int row =
                view.getTable().getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    null,
                    "Pilih data terlebih dahulu!"
            );

            return;
        }

        String id =
                view.getTable()
                        .getValueAt(row, 0)
                        .toString();

        String plat =
                JOptionPane.showInputDialog(
                        "Edit Plat Nomor",
                        view.getTable()
                                .getValueAt(row, 1)
                );

        String[] pilihan = {
            "Truk",
            "Pick-up",
            "Van"
        };

        String jenis = (String) JOptionPane.showInputDialog(
                null,
                "Edit Jenis Kendaraan",
                "Jenis",
                JOptionPane.QUESTION_MESSAGE,
                null,
                pilihan,
                view.getTable()
                        .getValueAt(row, 2)
                        .toString()
        );

        String merk =
                JOptionPane.showInputDialog(
                        "Edit Merk",
                        view.getTable()
                                .getValueAt(row, 3)
                );

        Connection con =
                Koneksi.getConnection();

        String sql =
                "UPDATE kendaraan "
                + "SET plat_nomor=?, jenis=?, merk=? "
                + "WHERE id=?";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setString(1, plat);
        ps.setString(2, jenis);
        ps.setString(3, merk);
        ps.setString(4, id);

        ps.executeUpdate();

        JOptionPane.showMessageDialog(
                null,
                "Data berhasil diubah!"
        );

        tampilData();

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                null,
                e.getMessage()
            );
        }
    }

    public void hapus() {

        try {

            Connection con =
                    Koneksi.getConnection();

            String sql =
                    "DELETE FROM kendaraan WHERE id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    view.getTxtId().getText()
            );

            ps.executeUpdate();

            JOptionPane.showMessageDialog(
                    null,
                    "Data berhasil dihapus!"
            );

            tampilData();
            reset();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage()
            );
        }
    }

    public void cari() {

        DefaultTableModel model =
                (DefaultTableModel)
                view.getTable().getModel();

        model.setRowCount(0);

        try {

            Connection con =
                    Koneksi.getConnection();

            String sql =
                    "SELECT * FROM kendaraan "
                    + "WHERE plat_nomor LIKE ? "
                    + "OR merk LIKE ?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    "%" + view.getTxtCari().getText() + "%"
            );

            ps.setString(
                    2,
                    "%" + view.getTxtCari().getText() + "%"
            );

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("plat_nomor"),
                    rs.getString("jenis"),
                    rs.getString("merk")
                };

                model.addRow(row);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage()
            );
        }
    }

    public void reset() {

        view.getTxtId().setText("");
        view.getTxtPlat().setText("");
        view.getTxtMerk().setText("");
        view.getTxtCari().setText("");
    }
}