/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author LENOVO T560
 */
public class TaiKhoan {

    private String idNV;
    private String maNV;
    private String tenNV;
    private String tenDemNV;
    private String hoNV;
    private String gioiTinh;
    private Date ngaySinh;
    private String diaChi;
    private String soDT;
    private String matKhau;
    private CapBac capBac;
    private String trangThai;
    private String taiKhoan;

    public TaiKhoan() {
    }

    public TaiKhoan(String idNV, String maNV, String tenNV, String tenDemNV, String hoNV, String gioiTinh, Date ngaySinh, String diaChi, String soDT, String matKhau, CapBac capBac, String trangThai) {
        this.idNV = idNV;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.tenDemNV = tenDemNV;
        this.hoNV = hoNV;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.soDT = soDT;
        this.matKhau = matKhau;
        this.capBac = capBac;
        this.trangThai = trangThai;
    }

    public TaiKhoan(String idNV, String maNV, String tenNV, String tenDemNV, String hoNV, String gioiTinh, Date ngaySinh, String diaChi, String soDT, String matKhau, CapBac capBac, String trangThai, String taiKhoan) {
        this.idNV = idNV;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.tenDemNV = tenDemNV;
        this.hoNV = hoNV;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.soDT = soDT;
        this.matKhau = matKhau;
        this.capBac = capBac;
        this.trangThai = trangThai;
        this.taiKhoan = taiKhoan;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }
    
    

    public String getIdNV() {
        return idNV;
    }

    public void setIdNV(String idNV) {
        this.idNV = idNV;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getTenDemNV() {
        return tenDemNV;
    }

    public void setTenDemNV(String tenDemNV) {
        this.tenDemNV = tenDemNV;
    }

    public String getHoNV() {
        return hoNV;
    }

    public void setHoNV(String hoNV) {
        this.hoNV = hoNV;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public CapBac getCapBac() {
        return capBac;
    }

    public void setCapBac(CapBac capBac) {
        this.capBac = capBac;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

   
    
    

}
