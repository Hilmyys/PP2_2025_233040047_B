package Modul10.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class koneksiDB {
    private static Connection mysqlconfig;
    
    public static Connection ConfigDB() throws SQLException {
        try {
            if (mysqlconfig == null) {
                String url = "jdbc:mysql://localhost:3306/kampus_db";
                String user = "root";
                String pass = "";
                
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                mysqlconfig = DriverManager.getConnection(url, user, pass);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Koneksi Gagal: " + e.getMessage());
        }
        return mysqlconfig;
    }
}