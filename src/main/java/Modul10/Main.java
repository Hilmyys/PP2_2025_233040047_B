package Modul10;

import Modul10.View.MahasiswaView;
import Modul10.Model.MahasiswaDAO;
import Modul10.Controller.MahasiswaController;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {

                MahasiswaView view = new MahasiswaView();

                MahasiswaDAO dao = new MahasiswaDAO();
                
                new MahasiswaController(view, dao);
               
                view.setVisible(true);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}