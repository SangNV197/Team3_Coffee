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
public class LstHoaDon_singleton {
    private static LstHoaDon_singleton single_instance = null;
    private static service.HoaDonService  service = new HoaDonService();
    // Declaring a variable of type String
    public ArrayList<HoaDon> lstHoaDon;

    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private LstHoaDon_singleton() {
         lstHoaDon = new ArrayList<>();
    }

    // Static method
    // Static method to create instance of Singleton class
    public static synchronized LstHoaDon_singleton getInstance() {
        if (single_instance == null) {
            single_instance = new LstHoaDon_singleton();
        }
        return single_instance;
    }
}
