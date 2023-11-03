/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SingletonClass;

import java.util.ArrayList;
import model.HoaDonChiTiet;
import service.HoaDonChiTietService;
import viewModel.HoaDonChiTietNoIMG;

/**
 *
 * @author 84374
 */
public class LstHoaDonChiTiet_singleton {
    private static LstHoaDonChiTiet_singleton single_instance = null;
    // Declaring a variable of type String
    public ArrayList<HoaDonChiTietNoIMG> lstHoaDonChiTietNoIMG;

    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private LstHoaDonChiTiet_singleton() {
         lstHoaDonChiTietNoIMG = new ArrayList<>();
    }

    // Static method
    // Static method to create instance of Singleton class
    public static synchronized LstHoaDonChiTiet_singleton getInstance() {
        if (single_instance == null) {
            single_instance = new LstHoaDonChiTiet_singleton();
        }
        return single_instance;
    }
}
