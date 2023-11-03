/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.view.model;

import java.sql.Date;

/**
 *
 * @author Lê Chấn Khang
 */
public class ChamCongK {
    private String idNV;
    private String ten;
    private Date ngay;
    private String caLamViec;
    private String gioVao;
    private String gioRa;
    private int soGioLam;

    public ChamCongK() {
    }

    public ChamCongK(String idNV, String ten, Date ngay, String caLamViec, String gioVao, String gioRa, int soGioLam) {
        this.idNV = idNV;
        this.ten = ten;
        this.ngay = ngay;
        this.caLamViec = caLamViec;
        this.gioVao = gioVao;
        this.gioRa = gioRa;
        this.soGioLam = soGioLam;
    }

    public String getIdNV() {
        return idNV;
    }

    public void setIdNV(String idNV) {
        this.idNV = idNV;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public String getCaLamViec() {
        return caLamViec;
    }

    public void setCaLamViec(String caLamViec) {
        this.caLamViec = caLamViec;
    }

    public String getGioVao() {
        return gioVao;
    }

    public void setGioVao(String gioVao) {
        this.gioVao = gioVao;
    }

    public String getGioRa() {
        return gioRa;
    }

    public void setGioRa(String gioRa) {
        this.gioRa = gioRa;
    }

    public int getSoGioLam() {
        return soGioLam;
    }

    public void setSoGioLam(int soGioLam) {
        this.soGioLam = soGioLam;
    }

    @Override
    public String toString() {
        return "ChamCongK{" + "idNV=" + idNV + ", ten=" + ten + ", ngay=" + ngay + ", caLamViec=" + caLamViec + ", gioVao=" + gioVao + ", gioRa=" + gioRa + ", soGioLam=" + soGioLam + '}';
    }

    
    
    
    
}
