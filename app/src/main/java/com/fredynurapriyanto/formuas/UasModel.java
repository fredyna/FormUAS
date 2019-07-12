package com.fredynurapriyanto.formuas;

public class UasModel {
    private String nik;
    private String nama;
    private String kelas;
    private String jam;

    public UasModel(String nik, String nama, String kelas, String jam) {
        this.nik = nik;
        this.nama = nama;
        this.kelas = kelas;
        this.jam = jam;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }
}
