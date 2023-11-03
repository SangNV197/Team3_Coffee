/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import com.view.model.QuanLyKhuyenMai;
import java.util.ArrayList;
import model.GiamGia;
import model.KhuyenMai;
import repository.DAO_GiamGia;
import repository.DAO_KhuyenMai;

/**
 *
 * @author LENOVO T560
 */
public class GiamGiaService {

    private DAO_GiamGia dao_giamGia = new DAO_GiamGia();

    public GiamGiaService() {
    }

    public ArrayList<GiamGia> selectALL() {
        ArrayList<GiamGia> listSale = dao_giamGia.selectALl();
        return listSale;

    }

    public GiamGia selectByID(String id) {
        return dao_giamGia.selectByID(id);
    }

//    public ArrayList<GiamGia> selectLoaiKM() {
//        ArrayList<GiamGia> listSale = dao_giamGia.selectLoaiKM();
//        return listSale;
//    }
//
//    public ArrayList<GiamGia> selectTrangThai() {
//        ArrayList<GiamGia> listSale = dao_giamGia.selectTrangThai();
//        return listSale;
//    }
//
//    public int selectById(String maKM) {
//        return dao_giamGia.selectById(maKM);
//    }
//
//    public void saveKhuyenMai(GiamGia giamGia) {
//        dao_giamGia.save(giamGia);
//    }
//
//    public void delete(String id) {
//        dao_giamGia.delete(id);
//    }
//
//    public void updateKhuyenMai(String id, KhuyenMai km) {
//        dao_giamGia.update(id, km);
//    }
//
//    // Lọc dữ liệu theo loại khuyến mại hoặc trạng thái
//    public ArrayList<KhuyenMai> selectALLByLoaiKM(String loaiKM, String trangThai) {
//        return dao_giamGia.selectALLByLoaiKM(loaiKM, trangThai);
//    }

}
