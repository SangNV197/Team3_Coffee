/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.NhanVien;
import repository.DAO_NhanVien;

/**
 *
 * @author LENOVO T560
 */
public class NhanVienService {

    private DAO_NhanVien repositoryNhanVien;

    public NhanVienService() {
        repositoryNhanVien = new DAO_NhanVien();
    }

    public ArrayList<NhanVien> selectALl() {
        ArrayList<NhanVien> list = repositoryNhanVien.selectALl();
        return list;
    }

    public NhanVien selectByID(String id) {
        return repositoryNhanVien.selectByID(id);
    }

    public NhanVien selectByIDNhanVien(String id) {
        return repositoryNhanVien.selectByIDNhanVien(id);
    }

    public ArrayList<NhanVien> selectCBONhanVien() {
        return repositoryNhanVien.selectCBONhanVien();
    }

    public String selectByTenNhanVien(String tenNV) {
        return repositoryNhanVien.selectByTenNhanVien(tenNV);
    }
     
    public NhanVien selectByMa(String ma){
        return repositoryNhanVien.selectByMa(ma);
    }
}
