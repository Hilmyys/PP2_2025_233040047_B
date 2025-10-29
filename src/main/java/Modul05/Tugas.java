/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modul05;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author THINKPAD
 */
public class Tugas {
    public static void main(String [] args ) {
        SwingUtilities.invokeLater(new Runnable (){
            public void run() {
                JFrame frame =  new JFrame ("Contoh BorderLayout");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                frame.setLayout(new BorderLayout());
                
                JLabel label = new JLabel("Label ada di Atas(NORTH)");
                
                JButton buttonNorth =  new JButton("WEST");
                JButton buttonSouth =  new JButton("Tombol ada di Bawah (SOUTH)");
                JButton buttonWest =  new JButton("WEST");
                JButton buttonEast =  new JButton("EAST");
                JButton buttonCenter =  new JButton("CENTER");
                
                buttonSouth.addActionListener(e -> {
                    label.setText("Tombol di SOUTH diklik!");
                });
                
                buttonWest.addActionListener(e -> {
                    label.setText("Tombol di WEST diklik!");
                });
                
                buttonEast.addActionListener(e -> {
                    label.setText("Tombol di EAST diklik!");
                });
                
                buttonCenter.addActionListener(e -> {
                    label.setText("Tombol di CENTER diklik!");
                });
                
                frame.add(label, BorderLayout.NORTH);
                frame.add(buttonSouth, BorderLayout.SOUTH);
                frame.add(buttonWest, BorderLayout.WEST);
                frame.add(buttonEast, BorderLayout.EAST);
                frame.add(buttonCenter, BorderLayout.CENTER);
                
//                frame.add(new JButton("WEST"), BorderLayout.WEST);
//                frame.add(new JButton("EAST"), BorderLayout.EAST);
//                frame.add(new JButton("Center"), BorderLayout.CENTER);
                
                frame.setVisible(true);  
            }
        });
    }
}
