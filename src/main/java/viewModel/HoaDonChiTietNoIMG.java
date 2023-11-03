/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

import model.HoaDon;

/**
 *
 * @author 84374
 */
public class HoaDonChiTietNoIMG {

    private HoaDon hoaDon;
    private ChiTietDoUongNoIMG chiTietDoUongNoIMG;
    private int soLuong;
    private String ngayTao;

    public HoaDonChiTietNoIMG() {
    }

    public HoaDonChiTietNoIMG(HoaDon hoaDon, ChiTietDoUongNoIMG chiTietDoUongNoIMG, int soLuong, String ngayTao) {
        this.hoaDon = hoaDon;
        this.chiTietDoUongNoIMG = chiTietDoUongNoIMG;
        this.soLuong = soLuong;
        this.ngayTao = ngayTao;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public ChiTietDoUongNoIMG getChiTietDoUongNoIMG() {
        return chiTietDoUongNoIMG;
    }

    public void setChiTietDoUongNoIMG(ChiTietDoUongNoIMG chiTietDoUongNoIMG) {
        this.chiTietDoUongNoIMG = chiTietDoUongNoIMG;
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

    @Override
    public String toString() {
        return "HoaDonChiTietNoIMG{" + "hoaDon=" + hoaDon + ", chiTietDoUongNoIMG=" + chiTietDoUongNoIMG + ", soLuong=" + soLuong + ", ngayTao=" + ngayTao + '}';
    }
    
}
