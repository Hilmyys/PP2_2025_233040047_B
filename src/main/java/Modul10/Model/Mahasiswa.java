package Modul10.model;

public class Mahasiswa {
    private String nama, nim, jurusan;
    public Mahasiswa(String nama, String nim, String jurusan) {
        this.nama = nama; this.nim = nim; this.jurusan = jurusan;
    }
    public String getNama() { return nama; }
    public String getNim() { return nim; }
    public String getJurusan() { return jurusan; }
}