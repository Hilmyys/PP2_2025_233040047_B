/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modul08.controller;

import Modul08.model.PersegiPanjangModel;
import Modul08.view.PersegiPanjangView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author THINKPAD
 */
public class PersegiPanjangController {
    
    private PersegiPanjangModel model;
    private PersegiPanjangView view;
    
    public PersegiPanjangController(PersegiPanjangModel model, PersegiPanjangView view) {
        this.model = model;
        this.view = view;
        
        this.view.addHitungListener(new HitungListener());
        this.view.addResetListener(new ResetListener());
    }
   
    
    class HitungListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double p = view.getPanjang();
                double l = view.getLebar();
                
                model.setPanjang(p);
                model.setLebar(l);
                
                model.hitungLuas();
 
                model.hitungKeliling();
                
                
                view.setHasilLuas(model.getLuas());
                view.setHasilKeliling(model.getKeliling()); 
                
            } catch (NumberFormatException ex) {
                view.tampilkanPesanError("Masukan angka yang valid!");
            }
        }
    }
    
    class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.clearInput();
        }
    }
}
