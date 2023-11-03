/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import com.view.model.QuanLyKhuyenMai;
import java.util.ArrayList;
import model.KhuyenMai;
import repository.DAO_KhuyenMai;

/**
 *
 * @author LENOVO T560
 */
public class SaleService {

    private DAO_KhuyenMai khuyenMaiRepository;

    public SaleService() {
        khuyenMaiRepository = new DAO_KhuyenMai();
    }

    public ArrayList<KhuyenMai> selectALL() {
        ArrayList<KhuyenMai> listSale = khuyenMaiRepository.selectALl();
        return listSale;

    }

    public ArrayList<KhuyenMai> selectLoaiKM() {
        ArrayList<KhuyenMai> listSale = khuyenMaiRepository.selectLoaiKM();
        return listSale;
    }

    public ArrayList<KhuyenMai> selectTrangThai() {
        ArrayList<KhuyenMai> listSale = khuyenMaiRepository.selectTrangThai();
        return listSale;
    }

    public KhuyenMai selectByID(String id) {
        return khuyenMaiRepository.selectByID(id);
    }

    public int selectById(String maKM) {
        return khuyenMaiRepository.selectById(maKM);
    }

    public void saveKhuyenMai(KhuyenMai km) {
        khuyenMaiRepository.save(km);
    }

    public void delete(String id) {
        khuyenMaiRepository.delete(id);
    }

    public void updateKhuyenMai(String id, KhuyenMai km) {
        khuyenMaiRepository.update(id, km);
    }

    // Lọc dữ liệu theo loại khuyến mại hoặc trạng thái
    public ArrayList<KhuyenMai> selectALLByTrangThai(String trangThai) {
        return khuyenMaiRepository.selectALLByTrangThai(trangThai);
    }

}
