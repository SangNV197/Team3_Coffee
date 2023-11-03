/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DoUong_HoaDon_ThongKe_Repository;

import DoUong_HoaDon_ThongKe_Model.ChiTietDoUong;
import java.util.ArrayList;

/**
 *
 * @author Sang
 */
public interface iChiTietDoUong {

    ArrayList<ChiTietDoUong> selectALl();
    
    ArrayList<ChiTietDoUong> selectByFlexibleCondition(String tenDoUong, String idLoaiDoUong,double giaBatDau,double giaKetThuc);
    ChiTietDoUong selectByID(String id);
    

    void save(ChiTietDoUong chiTietDoUong);

    void update(ChiTietDoUong chiTietDoUong);

    void delete(String tenDoUong);

}
