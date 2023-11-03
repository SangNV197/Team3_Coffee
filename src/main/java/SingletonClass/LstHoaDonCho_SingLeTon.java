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
public class LstHoaDonCho_SingLeTon {
    private static LstHoaDonCho_SingLeTon single_instance = null;
    private static service.HoaDonService  service = new HoaDonService();
    // Declaring a variable of type String
    public ArrayList<HoaDon> lstHoaDonCho;

    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private LstHoaDonCho_SingLeTon() {
         lstHoaDonCho = new ArrayList<>();
    }

    // Static method
    // Static method to create instance of Singleton class
    public static synchronized LstHoaDonCho_SingLeTon getInstance() {
        if (single_instance == null) {
            single_instance = new LstHoaDonCho_SingLeTon();
        }
        return single_instance;
    }
}
