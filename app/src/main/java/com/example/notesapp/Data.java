package com.example.notesapp;

public class Data {
    private int id;
    private String judul;
    private String catatan;

    public int getId(){
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String s) {
        this.catatan = catatan;
    }
}
