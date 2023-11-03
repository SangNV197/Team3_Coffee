/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

import model.KhuyenMai;
import model.LoaiDoUong;

/**
 *
 * @author 84374
 */
public class ChiTietDoUongNoIMG {

    private String id;

    private String tenDoUong;

    private double giaNhap;

    private double giaBan;

    private String moTa;

    private LoaiDoUong loaiDoUong;

    private KhuyenMai khuyenMai;

    public ChiTietDoUongNoIMG() {
    }

    public ChiTietDoUongNoIMG(String id, String tenDoUong, double giaNhap, double giaBan, String moTa, LoaiDoUong loaiDoUong, KhuyenMai khuyenMai) {
        this.id = id;
        this.tenDoUong = tenDoUong;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.moTa = moTa;
        this.loaiDoUong = loaiDoUong;
        this.khuyenMai = khuyenMai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenDoUong() {
        return tenDoUong;
    }

    public void setTenDoUong(String tenDoUong) {
        this.tenDoUong = tenDoUong;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public LoaiDoUong getLoaiDoUong() {
        return loaiDoUong;
    }

    public void setLoaiDoUong(LoaiDoUong loaiDoUong) {
        this.loaiDoUong = loaiDoUong;
    }

    public KhuyenMai getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(KhuyenMai khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    @Override
    public String toString() {
        return "ChiTietDoUongNoIMG{" + "id=" + id + ", tenDoUong=" + tenDoUong + ", giaNhap=" + giaNhap + ", giaBan=" + giaBan + ", moTa=" + moTa + ", loaiDoUong=" + loaiDoUong + ", khuyenMai=" + khuyenMai + '}';
    }

}
