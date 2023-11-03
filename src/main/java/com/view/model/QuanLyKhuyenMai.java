/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.view.model;

/**
 *
 * @author LENOVO T560
 */
public class QuanLyKhuyenMai {

    private String maSP;
    private String tenSP;
    private Double giaSPCu;
    private Double giaSPMoi;

    public QuanLyKhuyenMai() {
    }

    public QuanLyKhuyenMai(String maSP, String tenSP, Double giaSPCu, Double giaSPMoi) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.giaSPCu = giaSPCu;
        this.giaSPMoi = giaSPMoi;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public Double getGiaSPCu() {
        return giaSPCu;
    }

    public void setGiaSPCu(Double giaSPCu) {
        this.giaSPCu = giaSPCu;
    }

    public Double getGiaSPMoi() {
        return giaSPMoi;
    }

    public void setGiaSPMoi(Double giaSPMoi) {
        this.giaSPMoi = giaSPMoi;
    }
    
    

}
