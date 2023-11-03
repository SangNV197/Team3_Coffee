/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SingletonClass;

import java.util.ArrayList;
import model.HoaDon;
import service.HoaDonService;

/**
 *
 * @author 84374
 */
public class LstHoaDonDangPhaChe_singleton {

    private static LstHoaDonDangPhaChe_singleton single_instance = null;
    private static service.HoaDonService service = new HoaDonService();
    // Declaring a variable of type String
    public ArrayList<HoaDon> lstHoaDonDangPhaChe;

    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private LstHoaDonDangPhaChe_singleton() {
        lstHoaDonDangPhaChe = new ArrayList<>();
    }

    // Static method
    // Static method to create instance of Singleton class
    public static synchronized LstHoaDonDangPhaChe_singleton getInstance() {
        if (single_instance == null) {
            single_instance = new LstHoaDonDangPhaChe_singleton();
        }
        return single_instance;
    }
}
