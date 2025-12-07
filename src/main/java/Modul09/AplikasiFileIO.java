package Modul09;

import javax.swing.*;
import java.awt.*;
import java.io.*;


public class AplikasiFileIO extends JFrame{
    private JTextArea textArea;
    private JButton btnOpenText, btnSaveText, btnAppendText; // Perubahan 1: Tambah variabel btnAppendText
    private JButton btnSaveBinary, btnLoadBinary;
    private JFileChooser fileChooser;
    
    public AplikasiFileIO() {
        super("Tutorial File IO & Exception Handing");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        //inisialisasi Komponen
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        fileChooser =  new JFileChooser();
        
        // Panel Tombol
        JPanel buttonPanel = new JPanel();
        btnOpenText = new JButton("Buka Text");
        btnSaveText =  new JButton("Simpan Text");
        btnAppendText = new JButton("Tambah Text"); // Perubahan 2: Inisialisasi tombol baru
        btnSaveBinary = new JButton("Simpan Config (Binary)");
        btnLoadBinary = new JButton("Muat Config (Binary)");
        
        buttonPanel.add(btnOpenText);
        buttonPanel.add(btnSaveText);
        buttonPanel.add(btnAppendText); // Perubahan 3: Masukkan tombol ke panel
        buttonPanel.add(btnSaveBinary);
        buttonPanel.add(btnLoadBinary);
        
        //Layout
        
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Event Handling
        
        btnOpenText.addActionListener(e -> bukaFileText());
        
        btnSaveText.addActionListener(e -> simpanFileTeks());
        
        btnAppendText.addActionListener(e -> tambahFileTeks()); // Perubahan 4: Event handling tombol baru
        
        btnSaveBinary.addActionListener(e -> simpanConfigBinary());
        
        btnLoadBinary.addActionListener(e -> muatConfigBinary());
   
        //Auto-read last_notes.txt
        try {
            File autoFile = new File("last_notes.txt");
            // Cek apakah file ada sebelum mencoba membaca
            if (autoFile.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(autoFile));
                String line;
                // Membaca baris per baris dan menambahkannya ke textArea
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
                reader.close();
            }
        } catch (Exception e) {
            // Diam saja (silent catch) jika terjadi error atau file bermasalah
        }
    }
    
    // Perubahan 5: Method baru untuk Append Text
    private void tambahFileTeks() {
        // Kita gunakan showSaveDialog atau showOpenDialog agar user bisa memilih file mana yang mau ditambah
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // Parameter 'true' pada FileWriter menandakan mode APPEND
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "Text berhasil ditambahkan ke bagian bawah file");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menambahkan text: " + ex.getMessage());
            }
        }
    }
    
    private void bukaFileText() {
        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file =  fileChooser.getSelectedFile();
            BufferedReader reader = null;
            
            try {
                //Membuka stream
                reader = new BufferedReader(new FileReader(file));
                textArea.setText("");
                
                String line;
                
                while ((line = reader.readLine()) != null) {
                    textArea.append(line +"]\n");
                }
                
                JOptionPane.showMessageDialog(this, "File berhasil dimuat");
                
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "File tidak ditemukan:" + ex.getMessage());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal membaca file:" + ex.getMessage());;
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    private void simpanFileTeks() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file =  fileChooser.getSelectedFile();
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "File berhasil disimpan");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan file:" + ex.getMessage());
            }   
        }
    }
    
    private void simpanConfigBinary() {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("Config.bin"))) {
            int fontSize = textArea.getFont().getSize();
            dos.writeInt(fontSize);
            
            JOptionPane.showMessageDialog(this, "Ukuran font (" + fontSize + ") disimpan ke config.bin");
        } catch (IOException ex) {
             JOptionPane.showMessageDialog(this, "Gagal menyimpan binary: " + ex.getMessage());
        }
    }
    
    private void muatConfigBinary() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream("Config.bin"))) {
            int fontSize = dis.readInt();
            
            textArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize));
            JOptionPane.showMessageDialog(this, "Font diubah menjadi ukuran:" + fontSize);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "File Config.bin belum dibuat!");
        } catch (IOException ex) {
             JOptionPane.showMessageDialog(this, "Gagal membaca binary: " + ex.getMessage());
        }
    }
    
    public static void main (String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AplikasiFileIO().setVisible(true);
        });
    }
}
