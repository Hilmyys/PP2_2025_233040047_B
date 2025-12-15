package Modul10.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MahasiswaDAO {
    
    public List<Mahasiswa> getAllMahasiswa() {
        return getMahasiswaByQuery("SELECT * FROM mahasiswa");
    }

    public List<Mahasiswa> cariData(String keyword) {
        return getMahasiswaByQuery("SELECT * FROM mahasiswa WHERE nama LIKE '%" + keyword + "%'");
    }

    private List<Mahasiswa> getMahasiswaByQuery(String sql) {
        List<Mahasiswa> list = new ArrayList<>();
        try {
            
            Connection conn = koneksiDB.ConfigDB(); 
            
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                list.add(new Mahasiswa(
                    res.getString("nama"),
                    res.getString("nim"),
                    res.getString("jurusan")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean isNimExists(String nim) {
        boolean exists = false;
        try {
            Connection conn = koneksiDB.ConfigDB();
            String sql = "SELECT * FROM mahasiswa WHERE nim = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nim);
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public void insertMahasiswa(Mahasiswa m) throws SQLException {
        Connection conn = koneksiDB.ConfigDB();
        String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, m.getNama());
        pst.setString(2, m.getNim());
        pst.setString(3, m.getJurusan());
        pst.execute();
    }

    public void updateMahasiswa(Mahasiswa m) throws SQLException {
        Connection conn = koneksiDB.ConfigDB();
        String sql = "UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, m.getNama());
        pst.setString(2, m.getJurusan());
        pst.setString(3, m.getNim());
        pst.executeUpdate();
    }

    public void deleteMahasiswa(String nim) throws SQLException {
        Connection conn = koneksiDB.ConfigDB();
        String sql = "DELETE FROM mahasiswa WHERE nim = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, nim);
        pst.execute();
    }
}