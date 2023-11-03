/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.HoaDonChiTiet;
import repository.DAO_HoaDonChiTiet;

/**
 *
 * @author 84374
 */
public class HoaDonChiTietService {

    DAO_HoaDonChiTiet dao_HoaDonChiTiet = new DAO_HoaDonChiTiet();

    public void saveHoaDon(HoaDonChiTiet hoaDonChiTiet) {
        dao_HoaDonChiTiet.save(hoaDonChiTiet);
    }

    public ArrayList<HoaDonChiTiet> getListHoaDonChiTiet() {
        ArrayList<HoaDonChiTiet> lstHoaDonChiTiet = new ArrayList<>();
        lstHoaDonChiTiet = dao_HoaDonChiTiet.selectALl();
        return lstHoaDonChiTiet;
    }

    public ArrayList<HoaDonChiTiet> getListHoaDonChiTietByID(String id) {
        ArrayList<HoaDonChiTiet> lstHoaDonChiTiet = new ArrayList<>();
        lstHoaDonChiTiet = dao_HoaDonChiTiet.selectByID(id);
        return lstHoaDonChiTiet;
    }

//     public ArrayList<HoaDon> getTimKiem(String tenDoUong,String idLoaiDoUong,double giaBatDau,double giaKetThuc ){
//        ArrayList<ChiTietDoUong> lstChiTietDoUong  = new ArrayList<>();
//        lstChiTietDoUong = dAO_ChiTietDoUong.selectByFlexibleCondition(tenDoUong, idLoaiDoUong,giaBatDau,giaKetThuc);
//        return lstChiTietDoUong;
//    }
    public void updateHoaDon(HoaDonChiTiet hoaDonChiTiet) {
        dao_HoaDonChiTiet.update(hoaDonChiTiet);
    }

    public void deleteChiTietDoUong(HoaDonChiTiet hoaDonChiTiet) {
        dao_HoaDonChiTiet.delete(hoaDonChiTiet);
    }
}
