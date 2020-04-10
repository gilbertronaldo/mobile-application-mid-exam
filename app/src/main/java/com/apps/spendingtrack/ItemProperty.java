package com.apps.spendingtrack;

public class ItemProperty {
    private String id;
    private String nama;
    private String nominal;
    private String tanggal;

    public ItemProperty(String id, String nama, String nominal, String tanggal) {
        this.id = id;
        this.nama = nama;
        this.nominal = nominal;
        this.tanggal = tanggal;
    }

    public ItemProperty() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
