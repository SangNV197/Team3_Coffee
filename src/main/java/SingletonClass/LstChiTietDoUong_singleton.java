/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SingletonClass;

import java.util.ArrayList;
import model.ChiTietDoUong;
import service.ChiTietDoUongService_Master;

/**
 *
 * @author 84374
 */
public class LstChiTietDoUong_singleton {

    private static LstChiTietDoUong_singleton single_instance = null;
    private static service.ChiTietDoUongService_Master  service = new ChiTietDoUongService_Master();
    // Declaring a variable of type String
    public ArrayList<ChiTietDoUong> lstChiTietDoUongs;

    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private LstChiTietDoUong_singleton() {
         lstChiTietDoUongs = service.getListChiTietDoUong();
    }

    // Static method
    // Static method to create instance of Singleton class
    public static synchronized LstChiTietDoUong_singleton getInstance() {
        if (single_instance == null) {
            single_instance = new LstChiTietDoUong_singleton();
        }
        return single_instance;
    }
}