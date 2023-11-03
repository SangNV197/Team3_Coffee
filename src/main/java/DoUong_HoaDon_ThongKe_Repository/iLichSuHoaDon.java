/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DoUong_HoaDon_ThongKe_Repository;

import DoUong_HoaDon_ThongKe_Model.HoaDonChiTiet;
import DoUong_HoaDon_ThongKe_Model.LichSuHoaDon;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Sang
 */
public interface iLichSuHoaDon {
    
    ArrayList<LichSuHoaDon> getAll();
    
    ArrayList<HoaDonChiTiet> getAllll();
    
    void selectByMaHD(String maHoaDon);
    
    ArrayList<LichSuHoaDon> getByTime(Date d1, Date d2);
    
    ArrayList<HoaDonChiTiet> loadDataByMa(String maHoaDon);
    
    ArrayList<LichSuHoaDon> getByMa(String maHoaDon);

}
