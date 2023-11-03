/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SingletonClass;

import java.util.ArrayList;
import model.KhuyenMai;
import service.SaleService;

/**
 *
 * @author 84374
 */
public class LstDiscount_singleton {

    private static LstDiscount_singleton single_instance = null;
    private static SaleService service = new SaleService();
    // Declaring a variable of type String
    public ArrayList<KhuyenMai> lstKhuyenMai;

    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private LstDiscount_singleton() {
        lstKhuyenMai = new ArrayList<>();
    }

    // Static method
    // Static method to create instance of Singleton class
    public static synchronized LstDiscount_singleton getInstance() {
        if (single_instance == null) {
            single_instance = new LstDiscount_singleton();
        }
        return single_instance;
    }
}
