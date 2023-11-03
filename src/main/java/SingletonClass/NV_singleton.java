/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SingletonClass;

import model.NhanVien;



/**
 *
 * @author 84374
 */
public class NV_singleton {

    private static NV_singleton single_instance = null;

    // Declaring a variable of type String
    public NhanVien nv;

    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private NV_singleton() {
        nv = new NhanVien();
    }

    // Static method
    // Static method to create instance of Singleton class
    public static synchronized NV_singleton getInstance() {
        if (single_instance == null) {
            single_instance = new NV_singleton();
        }
        return single_instance;
    }
}
