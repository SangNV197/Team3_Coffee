/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 84374
 */
public class HoaDonChiTiet {
    private HoaDon hoaDon;
    private ChiTietDoUong chiTietDoUong;
    private int soLuong;
    private String ngayTao;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(HoaDon hoaDon, ChiTietDoUong chiTietDoUong, int soLuong, String ngayTao) {
        this.hoaDon = hoaDon;
        this.chiTietDoUong = chiTietDoUong;
        this.soLuong = soLuong;
        this.ngayTao = ngayTao;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public ChiTietDoUong getChiTietDoUong() {
        return chiTietDoUong;
    }

    public void setChiTietDoUong(ChiTietDoUong chiTietDoUong) {
        this.chiTietDoUong = chiTietDoUong;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    
}
