/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoUong_HoaDon_ThongKe_Model;

/**
 *
 * @author Sang
 */
public class HoaDonChiTiet {
    
    private String maHoaDon;
    
    private String tenSanPham;
    
    private int soLuong;
    
    private double thanhTien;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(String tenSanPham, int soLuong) {
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
    }

    public HoaDonChiTiet(String maHoaDon, String tenSanPham, int soLuong, double thanhTien) {
        this.maHoaDon = maHoaDon;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    @Override
    public String toString() {
        return "HoaDonChiTiet{" + "maHoaDon=" + maHoaDon + ", tenSanPham=" + tenSanPham + ", soLuong=" + soLuong + ", thanhTien=" + thanhTien + '}';
    }
}
