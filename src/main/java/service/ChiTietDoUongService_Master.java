/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.ChiTietDoUong;
import model.LoaiDoUong;
import repository.DAO_ChiTietDoUongMaster;
import repository.DAO_ChiTietDoUongTai;
import repository.DAO_LoaiDoUongMaster;

/**
 *
 * @author ADMIN
 */
public class ChiTietDoUongService_Master {

    DAO_LoaiDoUongMaster dAO_LoaiDoUong = new DAO_LoaiDoUongMaster();
    DAO_ChiTietDoUongMaster dAO_ChiTietDoUong = new DAO_ChiTietDoUongMaster();
    DAO_ChiTietDoUongTai dAO_ChiTietDoUongTai = new DAO_ChiTietDoUongTai(); 

    public ArrayList<LoaiDoUong> getListLoaiDoUong() {
        ArrayList<LoaiDoUong> lstLoaiDoUong = new ArrayList<>();
        lstLoaiDoUong = dAO_LoaiDoUong.selectALl();
        return lstLoaiDoUong;
    }

    public void saveLoaiDoUong(LoaiDoUong loaiDoUong) {
        dAO_LoaiDoUong.save(loaiDoUong);
    }

    public ArrayList<ChiTietDoUong> getListChiTietDoUong() {
        ArrayList<ChiTietDoUong> lstChiTietDoUong = new ArrayList<>();
        lstChiTietDoUong = dAO_ChiTietDoUong.selectALl();
        return lstChiTietDoUong;
    }

    public ArrayList<ChiTietDoUong> getTimKiem(String tenDoUong, String idLoaiDoUong, double giaBatDau, double giaKetThuc) {
        ArrayList<ChiTietDoUong> lstChiTietDoUong = new ArrayList<>();
        lstChiTietDoUong = dAO_ChiTietDoUong.selectByFlexibleCondition(tenDoUong, idLoaiDoUong, giaBatDau, giaKetThuc);
        return lstChiTietDoUong;
    }

    // Láy giá trị theo mã Giảm giá
    public ArrayList<ChiTietDoUong> selectAllByMaGiamGia(String maGiamGia) {
        return dAO_ChiTietDoUongTai.selectAllByMaGiamGia(maGiamGia);
    }

    public void saveChiTietDoUong(ChiTietDoUong chiTietDoUong) {
        dAO_ChiTietDoUong.save(chiTietDoUong);
    }

    public void updateChiTietDoUong(ChiTietDoUong chiTietDoUong) {
        dAO_ChiTietDoUong.update(chiTietDoUong);
    }

    public void deleteChiTietDoUong(String tenDoUong) {
        dAO_ChiTietDoUong.delete(tenDoUong);
    }

}
