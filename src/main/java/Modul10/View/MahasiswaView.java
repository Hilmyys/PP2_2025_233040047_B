package Modul10.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MahasiswaView extends JFrame {
    // Komponen GUI dibuat public atau ada getter agar bisa diakses Controller
    public JTextField txtNama, txtNIM, txtJurusan, txtCari;
    public JButton btnSimpan, btnEdit, btnHapus, btnClear, btnCari;
    public JTable tableMahasiswa;
    public DefaultTableModel model;

    public MahasiswaView() {
        setTitle("Aplikasi CRUD Mahasiswa MVC");
        setSize(700, 500); // Diperlebar sedikit untuk fitur cari
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel Form
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panelForm.add(new JLabel("Nama:"));
        txtNama = new JTextField();
        panelForm.add(txtNama);

        panelForm.add(new JLabel("NIM:"));
        txtNIM = new JTextField();
        panelForm.add(txtNIM);

        panelForm.add(new JLabel("Jurusan:"));
        txtJurusan = new JTextField();
        panelForm.add(txtJurusan);

        // Panel Tombol CRUD
        JPanel panelTombol = new JPanel(new FlowLayout());
        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");
        
        panelTombol.add(btnSimpan);
        panelTombol.add(btnEdit);
        panelTombol.add(btnHapus);
        panelTombol.add(btnClear);

        // LATIHAN 3: Panel Pencarian
        JPanel panelCari = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        txtCari = new JTextField(15);
        btnCari = new JButton("Cari");
        panelCari.add(new JLabel("Cari Nama:"));
        panelCari.add(txtCari);
        panelCari.add(btnCari);

        // Gabung Panel Form, Tombol, dan Cari
        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelForm, BorderLayout.NORTH);
        
        JPanel panelGabungTombol = new JPanel(new BorderLayout());
        panelGabungTombol.add(panelTombol, BorderLayout.CENTER);
        panelGabungTombol.add(panelCari, BorderLayout.SOUTH);
        
        panelAtas.add(panelGabungTombol, BorderLayout.SOUTH);
        add(panelAtas, BorderLayout.NORTH);

        // Tabel
        model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Nama");
        model.addColumn("NIM");
        model.addColumn("Jurusan");

        tableMahasiswa = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableMahasiswa);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    // Helper methods untuk mengambil data dari form
    public String getNamaInput() { return txtNama.getText(); }
    public String getNimInput() { return txtNIM.getText(); }
    public String getJurusanInput() { return txtJurusan.getText(); }
    public String getCariInput() { return txtCari.getText(); }
    
    public void clearForm() {
        txtNama.setText("");
        txtNIM.setText("");
        txtJurusan.setText("");
    }
}
