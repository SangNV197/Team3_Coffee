/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoUong_HoaDon_ThongKe_Service;

import DoUong_HoaDon_ThongKe_Repository.DAO_LoaiDoUong;
import DoUong_HoaDon_ThongKe_Repository.iLoaiDoUong;
import java.util.ArrayList;

/**
 *
 * @author Sang
 */


public class LoaiDoUongService implements iLoaiDoUong {
    private DAO_LoaiDoUong repository = new DAO_LoaiDoUong();
    @Override
    public ArrayList<DoUong_HoaDon_ThongKe_Model.LoaiDoUong> selectALl() {
        return repository.selectALl();
    }

    @Override
    public DoUong_HoaDon_ThongKe_Model.LoaiDoUong selectByID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void save(DoUong_HoaDon_ThongKe_Model.LoaiDoUong loaiDoUong) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
