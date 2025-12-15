package Modul10; // Ini tetap Modul10 karena ada di root

// IMPORT SEMUA KOMPONEN DARI FOLDER LAIN
import Modul10.MahasiswaView.MahasiswaView;
import Modul10.MahasiswaDAO.MahasiswaDAO;
import Modul10.MahasiswaController.MahasiswaController;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MahasiswaView view = new MahasiswaView();
            MahasiswaDAO dao = new MahasiswaDAO();
            new MahasiswaController(view, dao);
            view.setVisible(true);
        });
    }
}