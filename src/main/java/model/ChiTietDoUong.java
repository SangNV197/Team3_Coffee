/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 84374
 */
public class ChiTietDoUong {

    private String id;

    private String tenDoUong;

    private double giaNhap;

    private double giaBan;

    private String moTa;

    private byte[] hinhAnh;

    private LoaiDoUong loaiDoUong;

    private KhuyenMai khuyenMai;

    public KhuyenMai getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(KhuyenMai khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public ChiTietDoUong() {
    }

    public ChiTietDoUong(String id, String tenDoUong, double giaNhap, double giaBan, String moTa, byte[] hinhAnh, LoaiDoUong loaiDoUong) {
        this.id = id;
        this.tenDoUong = tenDoUong;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
        this.loaiDoUong = loaiDoUong;
    }

    public ChiTietDoUong(String id, String tenDoUong, double giaNhap, double giaBan, String moTa, byte[] hinhAnh, LoaiDoUong loaiDoUong, KhuyenMai khuyenMai) {
        this.id = id;
        this.tenDoUong = tenDoUong;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
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

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public LoaiDoUong getLoaiDoUong() {
        return loaiDoUong;
    }

    public void setLoaiDoUong(LoaiDoUong loaiDoUong) {
        this.loaiDoUong = loaiDoUong;
    }

    @Override
    public String toString() {
        return "ChiTietDoUong{" + "id=" + id + ", tenDoUong=" + tenDoUong + ", giaNhap=" + giaNhap + ", giaBan=" + giaBan + ", moTa=" + moTa + ", hinhAnh=" + hinhAnh + ", loaiDoUong=" + loaiDoUong + '}';
    }

}
