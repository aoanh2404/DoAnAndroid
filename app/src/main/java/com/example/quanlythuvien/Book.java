package com.example.quanlythuvien;

import java.io.Serializable;

public class Book implements Serializable {
    private String id;
    private String tensach;
    private String tacgia;
    private String nxb;
    private int sotrang;

    public Book(){
    }

    public Book(String id, String tensach, String tacgia, String nxb, int sotrang) {
        this.id = id;
        this.tensach = tensach;
        this.tacgia = tacgia;
        this.nxb = nxb;
        this.sotrang = sotrang;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }

    public int getSotrang() {
        return sotrang;
    }

    public void setSotrang(int sotrang) {
        this.sotrang = sotrang;
    }


}
