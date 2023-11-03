/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoUong_HoaDon_ThongKe_Model;

import DoUong_HoaDon_ThongKe_Model.LoaiDoUong;

/**
 *
 * @author Sang
 */
public class ChiTietDoUong {
    
    private String id;
    
    private String tenDoUong;
    
    private double giaNhap;
    
    private double giaBan;
    
    private String moTa;
    
    private byte[] hinhAnh;
    
    private LoaiDoUong loaiDoUong;
    
    private int Status;

    public ChiTietDoUong() {
    }

    public ChiTietDoUong(String id, String tenDoUong, double giaNhap, double giaBan, String moTa, byte[] hinhAnh, LoaiDoUong loaiDoUong, int Status) {
        this.id = id;
        this.tenDoUong = tenDoUong;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
        this.loaiDoUong = loaiDoUong;
        this.Status = Status;
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

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "ChiTietDoUong{" + "id=" + id + ", tenDoUong=" + tenDoUong + ", giaNhap=" + giaNhap + ", giaBan=" + giaBan + ", moTa=" + moTa + ", hinhAnh=" + hinhAnh + ", loaiDoUong=" + loaiDoUong + ", Status=" + Status + '}';
    }

}
