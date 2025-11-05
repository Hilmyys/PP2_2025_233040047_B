/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modul06;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author THINKPAD
 */
public class ContohFlowLayout {
   public static void main(String[] args ) {
       JFrame frame = new JFrame("Contoh FlowLayout");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(300, 150);
       
       JPanel Panel =  new JPanel();
       
       Panel.add(new JButton("Tombol 1"));
       Panel.add(new JButton("Tombol 2"));
       Panel.add(new JButton("Tombol Tiga"));
       Panel.add(new JButton("Tombol Empat Panjang"));
       Panel.add(new JButton("Tombol 5"));
       
       frame.add(Panel);
       
       frame.setVisible(true);
   } 
}
