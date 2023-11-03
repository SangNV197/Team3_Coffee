/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.ChiTietDoUong;
import model.LoaiDoUong;
import repository.DAO_ChiTietDoUongMaster;
import repository.DAO_ChiTietDoUongNoImg;
import repository.DAO_ChiTietDoUongTai;
import repository.DAO_LoaiDoUongMaster;
import viewModel.ChiTietDoUongNoIMG;

/**
 *
 * @author ADMIN
 */
public class ChiTietDoUongNoIMGService {

    DAO_LoaiDoUongMaster dAO_LoaiDoUong = new DAO_LoaiDoUongMaster();
    DAO_ChiTietDoUongNoImg dAO_ChiTietDoUongNoImg = new DAO_ChiTietDoUongNoImg();
    DAO_ChiTietDoUongTai dAO_ChiTietDoUongTai = new DAO_ChiTietDoUongTai(); 



    public void saveLoaiDoUong(LoaiDoUong loaiDoUong) {
        dAO_LoaiDoUong.save(loaiDoUong);
    }

    public ArrayList<ChiTietDoUongNoIMG> getListChiTietDoUong() {
        ArrayList<ChiTietDoUongNoIMG> lstChiTietDoUong = new ArrayList<>();
        lstChiTietDoUong = dAO_ChiTietDoUongNoImg.selectALl();
        return lstChiTietDoUong;
    }
//    public ArrayList<LoaiDoUong> getListLoaiDoUong() {
//        ArrayList<LoaiDoUong> lstLoaiDoUong = new ArrayList<>();
//        lstLoaiDoUong = dAO_LoaiDoUong.selectALl();
//        return lstLoaiDoUong;
//    }
//    public ArrayList<ChiTietDoUong> getTimKiem(String tenDoUong, String idLoaiDoUong, double giaBatDau, double giaKetThuc) {
//        ArrayList<ChiTietDoUong> lstChiTietDoUong = new ArrayList<>();
//        lstChiTietDoUong = dAO_ChiTietDoUongNoImg.selectByFlexibleCondition(tenDoUong, idLoaiDoUong, giaBatDau, giaKetThuc);
//        return lstChiTietDoUong;
//    }

    // Láy giá trị theo mã Giảm giá
//    public ArrayList<ChiTietDoUong> selectAllByMaGiamGia(String maGiamGia) {
//        return dAO_ChiTietDoUongTai.selectAllByMaGiamGia(maGiamGia);
//    }

//    public void saveChiTietDoUong(ChiTietDoUong chiTietDoUong) {
//        dAO_ChiTietDoUong.save(chiTietDoUong);
    }

//    public void updateChiTietDoUong(ChiTietDoUong chiTietDoUong) {
//        dAO_ChiTietDoUong.update(chiTietDoUong);
//    }
//
//    public void deleteChiTietDoUong(String tenDoUong) {
//        dAO_ChiTietDoUong.delete(tenDoUong);
//    }

