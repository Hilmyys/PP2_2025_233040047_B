package Modul10.Controller; 

import Modul10.Model.Mahasiswa;
import Modul10.Model.MahasiswaDAO;
import Modul10.View.MahasiswaView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class MahasiswaController {
    private MahasiswaView view;
    private MahasiswaDAO dao;

    // CONSTRUCTOR
    public MahasiswaController(MahasiswaView view, MahasiswaDAO dao) {
        this.view = view;
        this.dao = dao;

        // Listeners
        view.btnSimpan.addActionListener(e -> simpanData());
        view.btnEdit.addActionListener(e -> editData());
        view.btnHapus.addActionListener(e -> hapusData());
        view.btnClear.addActionListener(e -> view.clearForm());
        view.btnCari.addActionListener(e -> cariData());

        view.tableMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.tableMahasiswa.getSelectedRow();
                view.txtNama.setText(view.model.getValueAt(row, 1).toString());
                view.txtNIM.setText(view.model.getValueAt(row, 2).toString());
                view.txtJurusan.setText(view.model.getValueAt(row, 3).toString());
            }
        });

        loadData();
    }

    private void loadData() {
        List<Mahasiswa> list = dao.getAllMahasiswa();
        updateTable(list);
    }

    private void cariData() {
        String keyword = view.getCariInput();
        List<Mahasiswa> list = dao.cariData(keyword);
        updateTable(list);
    }

    private void updateTable(List<Mahasiswa> list) {
        view.model.setRowCount(0);
        int no = 1;
        for (Mahasiswa m : list) {
            view.model.addRow(new Object[]{
                no++, m.getNama(), m.getNim(), m.getJurusan()
            });
        }
    }

    private void simpanData() {
        String nama = view.getNamaInput();
        String nim = view.getNimInput();
        String jurusan = view.getJurusanInput();

        if (nama.isEmpty() || nim.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Data tidak boleh kosong!");
            return;
        }

        if (dao.isNimExists(nim)) {
            JOptionPane.showMessageDialog(view, "NIM sudah ada di database! Input ditolak.");
            return;
        }

        try {
            dao.insertMahasiswa(new Mahasiswa(nama, nim, jurusan));
            JOptionPane.showMessageDialog(view, "Data Berhasil Disimpan");
            loadData();
            view.clearForm();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Gagal Simpan: " + e.getMessage());
        }
    }

    private void editData() {
        if (view.getNamaInput().isEmpty() || view.getNimInput().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Data tidak boleh kosong!");
            return;
        }

        try {
            dao.updateMahasiswa(new Mahasiswa(view.getNamaInput(), view.getNimInput(), view.getJurusanInput()));
            JOptionPane.showMessageDialog(view, "Data Berhasil Diubah");
            loadData();
            view.clearForm();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Gagal Edit: " + e.getMessage());
        }
    }

    private void hapusData() {
        try {
            String nim = view.getNimInput();
            if (nim.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Pilih data dahulu!");
                return;
            }
            dao.deleteMahasiswa(nim);
            JOptionPane.showMessageDialog(view, "Data Berhasil Dihapus");
            loadData();
            view.clearForm();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Gagal Hapus: " + e.getMessage());
        }
    }
}