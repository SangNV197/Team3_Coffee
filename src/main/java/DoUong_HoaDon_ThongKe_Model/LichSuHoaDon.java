/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoUong_HoaDon_ThongKe_Model;

import java.util.Date;



/**
 *
 * @author ADMIN
 */
public class LichSuHoaDon {
    
    private String maHoaDon;
    
    private String tenNhanVien;
    
    private String timeTao;
    
    private String timeThanhToan;
    
    private int tinhTrangThanhToan;
    
    private int soLuong;
    
    private double tongTienHoaDon;
    
    private double chietKhau;
    
    private double tongTienThuVe;
    
    private int trangThai;

    public LichSuHoaDon() {
    }

    public LichSuHoaDon(String maHoaDon, String tenNhanVien, String timeTao, String timeThanhToan, int soLuong, double tongTienHoaDon, double chietKhau, int trangThai) {
        this.maHoaDon = maHoaDon;
        this.tenNhanVien = tenNhanVien;
        this.timeTao = timeTao;
        this.timeThanhToan = timeThanhToan;
        this.soLuong = soLuong;
        this.tongTienHoaDon = tongTienHoaDon;
        this.chietKhau = chietKhau;
        this.trangThai = trangThai;
    }
    
    public LichSuHoaDon(String maHoaDon, String tenNhanVien, String timeTao, String timeThanhToan, int tinhTrangThanhToan, int soLuong, double tongTienHoaDon, double chietKhau, double tongTienThuVe, int trangThai) {
        this.maHoaDon = maHoaDon;
        this.tenNhanVien = tenNhanVien;
        this.timeTao = timeTao;
        this.timeThanhToan = timeThanhToan;
        this.tinhTrangThanhToan = tinhTrangThanhToan;
        this.soLuong = soLuong;
        this.tongTienHoaDon = tongTienHoaDon;
        this.chietKhau = chietKhau;
        this.tongTienThuVe = tongTienThuVe;
        this.trangThai = trangThai;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getTimeTao() {
        return timeTao;
    }

    public void setTimeTao(String timeTao) {
        this.timeTao = timeTao;
    }

    public String getTimeThanhToan() {
        return timeThanhToan;
    }

    public void setTimeThanhToan(String timeThanhToan) {
        this.timeThanhToan = timeThanhToan;
    }

    public int getTinhTrangThanhToan() {
        return tinhTrangThanhToan;
    }

    public void setTinhTrangThanhToan(int tinhTrangThanhToan) {
        this.tinhTrangThanhToan = tinhTrangThanhToan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getTongTienHoaDon() {
        return tongTienHoaDon;
    }

    public void setTongTienHoaDon(double tongTienHoaDon) {
        this.tongTienHoaDon = tongTienHoaDon;
    }

    public double getChietKhau() {
        return chietKhau;
    }

    public void setChietKhau(double chietKhau) {
        this.chietKhau = chietKhau;
    }

    public double getTongTienThuVe() {
        return tongTienThuVe;
    }

    public void setTongTienThuVe(double tongTienThuVe) {
        this.tongTienThuVe = tongTienThuVe;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "LichSuHoaDon{" + "maHoaDon=" + maHoaDon + ", tenNhanVien=" + tenNhanVien + ", timeTao=" + timeTao + ", timeThanhToan=" + timeThanhToan + ", tinhTrangThanhToan=" + tinhTrangThanhToan + ", soLuong=" + soLuong + ", tongTienHoaDon=" + tongTienHoaDon + ", chietKhau=" + chietKhau + ", tongTienThuVe=" + tongTienThuVe + ", trangThai=" + trangThai + '}';
    }

    
    public double tienThucNhan(){
        if (chietKhau == 0) {
            return tongTienHoaDon;
        } else {
            return (tongTienHoaDon*(1-(chietKhau/100)));
        }
    }
    
    
    public String hienThiTrangThai(){
        if (trangThai == 1) {
            return "Hoàn thành";
        } else {
            return "Chưa hoàn thành";
        }
    }
    
  
    
}
