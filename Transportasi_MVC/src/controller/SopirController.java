package controller;

import connection.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.SopirView;

public class SopirController {

    SopirView view;

    public SopirController(SopirView view) {
        this.view = view;
    }

    // ================= TAMPIL DATA =================

    public void tampilData() {

        DefaultTableModel model =
                (DefaultTableModel)
                view.getTable().getModel();

        model.setRowCount(0);

        try {

            Connection con =
                    Koneksi.getConnection();

            String sql =
                    "SELECT * FROM sopir";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("no_sim"),
                    rs.getString("no_hp")
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

    // ================= TAMBAH =================

    public void tambah() {

        try {

            String nama = JOptionPane.showInputDialog(
                    "Masukkan Nama Sopir"
            );

            String sim = JOptionPane.showInputDialog(
                    "Masukkan No SIM"
            );

            String hp = JOptionPane.showInputDialog(
                    "Masukkan No HP"
            );

            Connection con =
                    Koneksi.getConnection();

            String sql =
                    "INSERT INTO sopir VALUES(NULL,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, nama);
            ps.setString(2, sim);
            ps.setString(3, hp);

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

    // ================= EDIT =================

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

            String nama =
                    JOptionPane.showInputDialog(
                            "Edit Nama",
                            view.getTable()
                                    .getValueAt(row, 1)
                    );

            String sim =
                    JOptionPane.showInputDialog(
                            "Edit SIM",
                            view.getTable()
                                    .getValueAt(row, 2)
                    );

            String hp =
                    JOptionPane.showInputDialog(
                            "Edit HP",
                            view.getTable()
                                    .getValueAt(row, 3)
                    );

            Connection con =
                    Koneksi.getConnection();

            String sql =
                    "UPDATE sopir "
                    + "SET nama=?, no_sim=?, no_hp=? "
                    + "WHERE id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, nama);
            ps.setString(2, sim);
            ps.setString(3, hp);
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

    // ================= HAPUS =================

    public void hapus() {

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

            Connection con =
                    Koneksi.getConnection();

            String sql =
                    "DELETE FROM sopir WHERE id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, id);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(
                    null,
                    "Data berhasil dihapus!"
            );

            tampilData();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage()
            );
        }
    }

    // ================= CARI =================

    public void cari() {

        DefaultTableModel model =
                (DefaultTableModel)
                view.getTable().getModel();

        model.setRowCount(0);

        try {

            Connection con =
                    Koneksi.getConnection();

            String sql =
                    "SELECT * FROM sopir "
                    + "WHERE nama LIKE ? "
                    + "OR no_sim LIKE ?";

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

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("no_sim"),
                    rs.getString("no_hp")
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
}