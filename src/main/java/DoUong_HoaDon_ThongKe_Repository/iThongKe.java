/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DoUong_HoaDon_ThongKe_Repository;

import DoUong_HoaDon_ThongKe_Model.BieuDoThongKe;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public interface iThongKe {
    
    long getTongDoanhThu();
    
    int getTongHoaDon();
    
    int getTongSanPham();
    
    long getTongDonhThuTheoNgayChon(Date d1, Date d2);
    
    int getTongHoaDonTheoNgayChon(Date d1, Date d2);
    
    int getTongSanPhamTheoNgayChon(Date d1, Date d2);
    
    ArrayList<BieuDoThongKe> getBieuDo();
    
    ArrayList<BieuDoThongKe> getBieuDoTheoNgay(Date d1, Date d2);
}
