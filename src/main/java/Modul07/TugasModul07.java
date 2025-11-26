/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modul07;

/**
 *
 * @author THINKPAD
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TugasModul07 extends JFrame {
    private JTextField txtNama;
    private JTextField txtNilai;
    private JComboBox<String> cmbMatkul;
    private JTable tableData;
    private DefaultTableModel tableModel;
    private JTabbedPane tabbedPane;

    public TugasModul07() {
        setTitle("Aplikasi Manajemen Nilai Siswa");
        setSize(500, 430);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Input Data", createInputPanel());
        tabbedPane.addTab("Daftar Nilai", createTablePanel());

        add(tabbedPane);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Nama Siswa:"));
        txtNama = new JTextField();
        panel.add(txtNama);

        panel.add(new JLabel("Mata Pelajaran:"));
        String[] matkul = {
                "Matematika Dasar",
                "Bahasa Indonesia",
                "Algoritma dan Pemrograman I",
                "Praktikum Pemrograman II"
        };
        cmbMatkul = new JComboBox<>(matkul);
        panel.add(cmbMatkul);

        panel.add(new JLabel("Nilai (0-100):"));
        txtNilai = new JTextField();
        panel.add(txtNilai);

        JButton btnReset = new JButton("Reset Data");
        JButton btnSimpan = new JButton("Simpan Data");
        
        panel.add(btnReset);
        panel.add(btnSimpan);
        

        btnSimpan.addActionListener(e -> prosesSimpan());

        btnReset.addActionListener(e -> resetInput());

        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] kolom = {"Nama Siswa", "Mata Pelajaran", "Nilai", "Grade"};
        tableModel = new DefaultTableModel(kolom, 0);
        tableData = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(tableData);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton btnHapus = new JButton("Hapus Data");
        bottomPanel.add(btnHapus);

        btnHapus.addActionListener(e -> hapusData());

        panel.add(bottomPanel, BorderLayout.CENTER);

        return panel;
    }

    private void prosesSimpan() {
        String nama = txtNama.getText().trim();
        String matkul = (String) cmbMatkul.getSelectedItem();
        String strNilai = txtNilai.getText().trim();

        if (nama.length() < 3) {
            JOptionPane.showMessageDialog(this,
                    "Nama minimal 3 karakter!",
                    "Error Validasi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int nilai;
        try {
            nilai = Integer.parseInt(strNilai);
            if (nilai < 0 || nilai > 100) {
                JOptionPane.showMessageDialog(this,
                        "Nilai harus antara 0 - 100!",
                        "Error Validasi",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Nilai harus berupa angka!",
                    "Error Validasi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        String grade;
        
        switch (nilai / 10) {
            case 10: 
            case 9: 
            case 8:  
                grade = "A";
                break;
            case 7:  
                grade = "AB";
                break;
            case 6:  
                grade = "B";
                break;
            case 5:  
                grade = "BC";
                break;
            case 4:  
                grade = "C";
                break;
            case 3:  
                grade = "D";
                break;
            default: 
                grade = "E";
                break;
        }

       
        Object[] row = {nama, matkul, nilai, grade};
        tableModel.addRow(row);

        
        resetInput(); 

        JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan!");
        tabbedPane.setSelectedIndex(1); 
    }

    
    private void resetInput() {
        txtNama.setText("");
        txtNilai.setText("");
        cmbMatkul.setSelectedIndex(0);
    }

    // Method Hapus Data 
    private void hapusData() {
        int selectedRow = tableData.getSelectedRow();

        if (selectedRow > -1) {
            
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Pilih baris yang ingin dihapus!",
                    "Tidak ada data dipilih",
                    JOptionPane.WARNING_MESSAGE);
        }
    }


    public static void main(String[] args) {
     
        SwingUtilities.invokeLater(() -> new TugasModul07().setVisible(true));
    }
}