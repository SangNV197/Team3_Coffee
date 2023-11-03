/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author 84374
 */
public class KhachHang {
     private String id;
     private String ma;
     private String ten;
     private String TenDem;
     private String Ho;
     private Date DOB;
     private String sdt;
     private String diaChi;
     private String ngayTao;

    public KhachHang() {
    }

    public KhachHang(String id, String ma, String ten, String TenDem, String Ho, Date DOB, String sdt, String diaChi, String ngayTao) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.TenDem = TenDem;
        this.Ho = Ho;
        this.DOB = DOB;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.ngayTao = ngayTao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTenDem() {
        return TenDem;
    }

    public void setTenDem(String TenDem) {
        this.TenDem = TenDem;
    }

    public String getHo() {
        return Ho;
    }

    public void setHo(String Ho) {
        this.Ho = Ho;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    @Override
    public String toString() {
        return "KhachHang{" + "id=" + id + ", ma=" + ma + ", ten=" + ten + ", TenDem=" + TenDem + ", Ho=" + Ho + ", DOB=" + DOB + ", sdt=" + sdt + ", diaChi=" + diaChi + ", ngayTao=" + ngayTao + '}';
    }
}
