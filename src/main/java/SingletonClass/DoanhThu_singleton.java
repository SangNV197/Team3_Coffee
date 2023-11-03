/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SingletonClass;

import java.math.BigDecimal;

/**
 *
 * @author 84374
 */
public class DoanhThu_singleton {

    private static DoanhThu_singleton single_instance = null;

    // Declaring a variable of type String
    public BigDecimal  doanhThuNow ;

    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private DoanhThu_singleton() { 
        doanhThuNow = null;
    }

    // Static method
    // Static method to create instance of Singleton class
    public static synchronized DoanhThu_singleton getInstance() {
        if (single_instance == null) {
            single_instance = new DoanhThu_singleton();
        }
        return single_instance;
    }
}
