/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.ChiTietDoUong;
import model.LoaiDoUong;
import model.TinTuc;
import repository.DAO_ChiTietDoUongMaster;
import repository.DAO_ChiTietDoUongTai;
import repository.DAO_LoaiDoUongMaster;
import repository.TinTucRepository;

/**
 *
 * @author ADMIN
 */
public class TinTucService {

    TinTucRepository DAO_TinTuc = new TinTucRepository();

    public ArrayList<TinTuc> getListTinTuc() {
        ArrayList<TinTuc> lstTinTuc = new ArrayList<>();
        lstTinTuc = DAO_TinTuc.selectALl();
        return lstTinTuc;
    }

    public void saveTinTuc(TinTuc tinTuc) {
        DAO_TinTuc.save(tinTuc);
    }
    
    public TinTuc selectByID(String id){
        return DAO_TinTuc.selectByID(id);
    }
    public void update(TinTuc tinTuc){
        DAO_TinTuc.update(tinTuc);
    }
    public void delete(String id){
        DAO_TinTuc.delete(id);
    }
    // Láy giá trị theo mã Giảm giá
}
