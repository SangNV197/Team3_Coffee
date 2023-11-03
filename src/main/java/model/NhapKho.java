/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author MSI-G8
 */
public class NhapKho {
    private String id;
    private NhanVien nhanVien;
    private String tenSP;
    private Date ngayNhap;
    private String donVi;
    private int soLuong;
    private BigDecimal donGia;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    

    public NhapKho() {
    }

    public NhapKho(String id, NhanVien nhanVien, String tenSP, Date ngayNhap, String donVi, int soLuong, BigDecimal donGia) {
        this.id = id;
        this.nhanVien = nhanVien;
        this.tenSP = tenSP;
        this.ngayNhap = ngayNhap;
        this.donVi = donVi;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    

    @Override
    public String toString() {
        return "id=" + id + ", nhanVien=" + nhanVien + ", tenSP=" + tenSP + ", ngayNhap=" + 
                ngayNhap + ", donVi=" + donVi + ", soLuong=" + soLuong + ", donGia=" + donGia;
    }

}
