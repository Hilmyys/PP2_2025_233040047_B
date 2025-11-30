/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modul08.main;


import modul08.controller.PersegiPanjangController;
import modul08.model.PersegiPanjangModel;
import modul08.view.PersegiPanjangView;
     
/**
 *
 * @author THINKPAD
 */
public class Main {
    public static void main(String[] args) {
        PersegiPanjangModel model =  new PersegiPanjangModel();
        
        PersegiPanjangView view = new PersegiPanjangView();
        
        PersegiPanjangController controller =  new PersegiPanjangController(model, view);
        
        view.setVisible(true);
    }
}
