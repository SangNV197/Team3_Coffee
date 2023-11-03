/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DoUong_HoaDon_ThongKe_Repository111;

import DoUong_HoaDon_ThongKe_Model.LoaiDoUong;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public interface iLoaiDoUong {
    
    ArrayList<LoaiDoUong> selectALl();

    LoaiDoUong selectByID(String id);

    void save(LoaiDoUong loaiDoUong);

    void update(LoaiDoUong loaiDoUong);

    void delete(String id);
    
}
