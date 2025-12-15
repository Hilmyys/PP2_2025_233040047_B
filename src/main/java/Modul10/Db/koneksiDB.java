package Modul10.Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksiDB { // Pastikan nama class sama dengan nama file
    private static Connection mysqlconfig;
    
    // PERHATIKAN NAMA METHOD INI
    public static Connection getConnection() { 
        try {
            if (mysqlconfig == null) {
                // ... kodingan koneksi ...
                mysqlconfig = DriverManager.getConnection("jdbc:mysql://localhost:3306/kampus_db", "root", "");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mysqlconfig;
    }
}