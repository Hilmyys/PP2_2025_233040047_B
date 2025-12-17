package Modul10.view;

import Modul10.model.koneksiDB;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;

public class MahasiswaApp extends JFrame {
    JTextField txtNama, txtNIM, txtJurusan, txtCari;
    JButton btnSimpan, btnEdit, btnHapus, btnClear, btnCari;
    JTable tableMahasiswa;
    DefaultTableModel model;

    public MahasiswaApp() {
        setTitle("Aplikasi MVC Mahasiswa");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // PANEL CARI (Latihan 3)
        JPanel panelAtas = new JPanel(new BorderLayout());
        JPanel panelCari = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCari.add(new JLabel("Cari Nama:"));
        txtCari = new JTextField(15);
        btnCari = new JButton("Cari");
        panelCari.add(txtCari); panelCari.add(btnCari);
        
        // PANEL FORM INPUT
        JPanel panelForm = new JPanel(new GridLayout(3, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panelForm.add(new JLabel("Nama:")); txtNama = new JTextField(); panelForm.add(txtNama);
        panelForm.add(new JLabel("NIM:")); txtNIM = new JTextField(); panelForm.add(txtNIM);
        panelForm.add(new JLabel("Jurusan:")); txtJurusan = new JTextField(); panelForm.add(txtJurusan);

        // PANEL TOMBOL
        JPanel panelTombol = new JPanel();
        btnSimpan = new JButton("Simpan"); btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus"); btnClear = new JButton("Clear");
        panelTombol.add(btnSimpan); panelTombol.add(btnEdit); panelTombol.add(btnHapus); panelTombol.add(btnClear);

        panelAtas.add(panelForm, BorderLayout.NORTH);
        panelAtas.add(panelTombol, BorderLayout.CENTER);
        panelAtas.add(panelCari, BorderLayout.SOUTH);
        add(panelAtas, BorderLayout.NORTH);

        // TABEL
        model = new DefaultTableModel(new Object[]{"No", "Nama", "NIM", "Jurusan"}, 0);
        tableMahasiswa = new JTable(model);
        add(new JScrollPane(tableMahasiswa), BorderLayout.CENTER);

        // --- EVENTS ---
        btnSimpan.addActionListener(e -> tambahData());
        btnEdit.addActionListener(e -> ubahData());
        btnHapus.addActionListener(e -> hapusData());
        btnCari.addActionListener(e -> loadData(txtCari.getText()));
        btnClear.addActionListener(e -> kosongkan());
        
        tableMahasiswa.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int r = tableMahasiswa.getSelectedRow();
                txtNama.setText(model.getValueAt(r, 1).toString());
                txtNIM.setText(model.getValueAt(r, 2).toString());
                txtJurusan.setText(model.getValueAt(r, 3).toString());
            }
        });

        loadData("");
    }

    private void loadData(String keyword) {
        model.setRowCount(0);
        try {
            Connection conn = koneksiDB.configDB();
            String sql = keyword.isEmpty() ? "SELECT * FROM mahasiswa" : "SELECT * FROM mahasiswa WHERE nama LIKE '%"+keyword+"%'";
            ResultSet res = conn.createStatement().executeQuery(sql);
            int no = 1;
            while(res.next()) {
                model.addRow(new Object[]{no++, res.getString("nama"), res.getString("nim"), res.getString("jurusan")});
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void tambahData() {
        if(txtNama.getText().isEmpty() || txtNIM.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Nama & NIM tidak boleh kosong!"); return;
        }
        try {
            Connection conn = koneksiDB.configDB();
            PreparedStatement cek = conn.prepareStatement("SELECT * FROM mahasiswa WHERE nim=?");
            cek.setString(1, txtNIM.getText());
            if(cek.executeQuery().next()) {
                JOptionPane.showMessageDialog(this, "NIM sudah ada!"); return;
            }
            
            PreparedStatement ps = conn.prepareStatement("INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?,?,?)");
            ps.setString(1, txtNama.getText()); ps.setString(2, txtNIM.getText()); ps.setString(3, txtJurusan.getText());
            ps.execute();
            JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan");
            loadData(""); kosongkan();
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Gagal: " + e.getMessage()); }
    }

    private void ubahData() {
        try {
            Connection conn = koneksiDB.configDB();
            PreparedStatement ps = conn.prepareStatement("UPDATE mahasiswa SET nama=?, jurusan=? WHERE nim=?");
            ps.setString(1, txtNama.getText()); ps.setString(2, txtJurusan.getText()); ps.setString(3, txtNIM.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Berhasil Diubah");
            loadData("");
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Gagal: " + e.getMessage()); }
    }

    private void hapusData() {
        if (txtNIM.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih data di tabel yang ingin dihapus!");
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        
        if (konfirmasi == JOptionPane.YES_OPTION) {
            try {
                Connection conn = koneksiDB.configDB();
                PreparedStatement ps = conn.prepareStatement("DELETE FROM mahasiswa WHERE nim=?");
                ps.setString(1, txtNIM.getText());
                ps.execute();
                
                JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus");
                
                loadData(""); 
                kosongkan();
            } catch (Exception e) { 
                JOptionPane.showMessageDialog(this, "Gagal Hapus: " + e.getMessage()); 
            }
        }
    }

    private void kosongkan() {
        txtNama.setText(""); txtNIM.setText(""); txtJurusan.setText("");
    }
}
  
