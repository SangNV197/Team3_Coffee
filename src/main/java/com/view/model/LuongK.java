/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.view.model;

/**
 *
 * @author Lê Chấn Khang
 */
public class LuongK {
    private String id;
    private String ten;
    private int tongGio;
    private int luongPartime;
    private double luong;

    public LuongK() {
    }

    public LuongK(String id, String ten, int tongGio, int luongPartime, double luong) {
        this.id = id;
        this.ten = ten;
        this.tongGio = tongGio;
        this.luongPartime = luongPartime;
        this.luong = luong;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getTongGio() {
        return tongGio;
    }

    public void setTongGio(int tongGio) {
        this.tongGio = tongGio;
    }

    public int getLuongPartime() {
        return luongPartime;
    }

    public void setLuongPartime(int luongPartime) {
        this.luongPartime = luongPartime;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    @Override
    public String toString() {
        return "LuongK{" + "id=" + id + ", ten=" + ten + ", tongGio=" + tongGio + ", luongPartime=" + luongPartime + ", luong=" + luong + '}';
    }

    
}
