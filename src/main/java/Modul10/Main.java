package Modul10;

import Modul10.view.MahasiswaApp;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Memanggil aplikasi MahasiswaApp untuk dijalankan
        SwingUtilities.invokeLater(() -> {
            new MahasiswaApp().setVisible(true);
        });
    }
}