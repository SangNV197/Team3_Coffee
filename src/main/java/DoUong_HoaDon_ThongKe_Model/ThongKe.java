/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoUong_HoaDon_ThongKe_Model;

/**
 *
 * @author ADMIN
 */
public class ThongKe {
    
    private double tongDoanhThu;
    
    private int tongHoaDon;
    
    private int tongSoLuong;

    public ThongKe() {
    }

    public ThongKe(double tongDoanhThu, int tongHoaDon, int tongSoLuong) {
        this.tongDoanhThu = tongDoanhThu;
        this.tongHoaDon = tongHoaDon;
        this.tongSoLuong = tongSoLuong;
    }

    public ThongKe(double tongDoanhThu) {
        this.tongDoanhThu = tongDoanhThu;
    }

    public ThongKe(int tongHoaDon) {
        this.tongHoaDon = tongHoaDon;
    }

    public double getTongDoanhThu() {
        return tongDoanhThu;
    }

    public void setTongDoanhThu(double tongDoanhThu) {
        this.tongDoanhThu = tongDoanhThu;
    }

    public int getTongHoaDon() {
        return tongHoaDon;
    }

    public void setTongHoaDon(int tongHoaDon) {
        this.tongHoaDon = tongHoaDon;
    }

    public int getTongSoLuong() {
        return tongSoLuong;
    }

    public void setTongSoLuong(int tongSoLuong) {
        this.tongSoLuong = tongSoLuong;
    }

    @Override
    public String toString() {
        return "ThongKe{" + "tongDoanhThu=" + tongDoanhThu + ", tongHoaDon=" + tongHoaDon + ", tongSoLuong=" + tongSoLuong + '}';
    }
}
