/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoUong_HoaDon_ThongKe_Service;

import DoUong_HoaDon_ThongKe_Model.HoaDonChiTiet;
import DoUong_HoaDon_ThongKe_Model.LichSuHoaDon;
import DoUong_HoaDon_ThongKe_Repository.DAO_LichSuHoaDon;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Sang
 */
public class LichSuHoaDonService {
    
    private DAO_LichSuHoaDon dAO_LichSuHoaDon = new DAO_LichSuHoaDon();
    
    public ArrayList<LichSuHoaDon> getAll(){
        return dAO_LichSuHoaDon.getAll();
    }
    
    public ArrayList<HoaDonChiTiet> getAlll(){
        return dAO_LichSuHoaDon.getAllll();
    }
    
    public void selectByMaHD(String  maHoaDon){
        dAO_LichSuHoaDon.selectByMaHD(maHoaDon);
    }
    
    public ArrayList<LichSuHoaDon> getByTime(Date d1, Date d2){
        return dAO_LichSuHoaDon.getByTime(d1, d2);
    }
    
    public ArrayList<HoaDonChiTiet> loadDataByMa(String maHoaDon){
        return dAO_LichSuHoaDon.loadDataByMa(maHoaDon);
    }

    public ArrayList<LichSuHoaDon> getByMa(String maHoaDon){
        return dAO_LichSuHoaDon.getByMa(maHoaDon);
    }
}
