/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.HoaDonChiTiet;
import repository.DAO_HoaDonChiTiet;
import repository.DAO_HoaDonChiTietNoIMG;
import viewModel.HoaDonChiTietNoIMG;

/**
 *
 * @author 84374
 */
public class HoaDonChiTietNoIMGService {

    DAO_HoaDonChiTietNoIMG dao_HoaDonChiTiet = new DAO_HoaDonChiTietNoIMG();

    public HoaDonChiTietNoIMG  saveHoaDon(HoaDonChiTietNoIMG hoaDonChiTiet) {
        return dao_HoaDonChiTiet.save(hoaDonChiTiet);
    }

    public ArrayList<HoaDonChiTietNoIMG> getListHoaDonChiTiet() {
        ArrayList<HoaDonChiTietNoIMG> lstHoaDonChiTiet = new ArrayList<>();
        lstHoaDonChiTiet = dao_HoaDonChiTiet.selectALl();
        return lstHoaDonChiTiet;
    }

    public ArrayList<HoaDonChiTietNoIMG> getListHoaDonChiTietByID(String id) {
        ArrayList<HoaDonChiTietNoIMG> lstHoaDonChiTiet = new ArrayList<>();
        lstHoaDonChiTiet = dao_HoaDonChiTiet.selectByID(id);
        return lstHoaDonChiTiet;
    }

//     public ArrayList<HoaDon> getTimKiem(String tenDoUong,String idLoaiDoUong,double giaBatDau,double giaKetThuc ){
//        ArrayList<ChiTietDoUong> lstChiTietDoUong  = new ArrayList<>();
//        lstChiTietDoUong = dAO_ChiTietDoUong.selectByFlexibleCondition(tenDoUong, idLoaiDoUong,giaBatDau,giaKetThuc);
//        return lstChiTietDoUong;
//    }
    public void updateHoaDon(HoaDonChiTietNoIMG hoaDonChiTiet) {
        dao_HoaDonChiTiet.update(hoaDonChiTiet);
        
    }

    public void deleteChiTietDoUong(HoaDonChiTietNoIMG hoaDonChiTiet) {
        dao_HoaDonChiTiet.delete(hoaDonChiTiet);
    }
}
