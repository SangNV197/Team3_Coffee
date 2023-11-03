/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoUong_HoaDon_ThongKe_Service;

import DoUong_HoaDon_ThongKe_Model.ChiTietDoUong;
import DoUong_HoaDon_ThongKe_Repository.DAO_ChiTietDoUong;
import DoUong_HoaDon_ThongKe_Repository.DAO_LoaiDoUong;
import DoUong_HoaDon_ThongKe_Model.LoaiDoUong;
import java.util.ArrayList;

/**
 *
 * @author Sang
 */
public class ChiTietDoUongService {
    
    DAO_LoaiDoUong dAO_LoaiDoUong  = new DAO_LoaiDoUong();
    DAO_ChiTietDoUong dAO_ChiTietDoUong = new DAO_ChiTietDoUong();

    public ArrayList<LoaiDoUong> getListLoaiDoUong(){
        ArrayList<LoaiDoUong> lstLoaiDoUong  = new ArrayList<>();
        lstLoaiDoUong = dAO_LoaiDoUong.selectALl();
        return lstLoaiDoUong;
    }
    
    public void saveLoaiDoUong(LoaiDoUong loaiDoUong){
        dAO_LoaiDoUong.save(loaiDoUong);
    }
    
    
     public ArrayList<ChiTietDoUong> getListChiTietDoUong(){
        ArrayList<ChiTietDoUong> lstChiTietDoUong  = new ArrayList<>();
        lstChiTietDoUong = dAO_ChiTietDoUong.selectALl();
        return lstChiTietDoUong;
    }
     
     public ArrayList<ChiTietDoUong> getTimKiem(String tenDoUong,String idLoaiDoUong,double giaBatDau,double giaKetThuc ){
        ArrayList<ChiTietDoUong> lstChiTietDoUong  = new ArrayList<>();
        lstChiTietDoUong = dAO_ChiTietDoUong.selectByFlexibleCondition(tenDoUong, idLoaiDoUong,giaBatDau,giaKetThuc);
        return lstChiTietDoUong;
    }
     
    
    public void saveChiTietDoUong(ChiTietDoUong chiTietDoUong){
        dAO_ChiTietDoUong.save(chiTietDoUong);
    }
    
    public void updateChiTietDoUong(ChiTietDoUong chiTietDoUong){
        dAO_ChiTietDoUong.update(chiTietDoUong);
    }
    
    public void deleteChiTietDoUong(String  tenDoUong){
        dAO_ChiTietDoUong.delete(tenDoUong);
    }
}
