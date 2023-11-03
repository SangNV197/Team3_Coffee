/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SingletonClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author 84374
 */
public class CountProduct_singleton {

    private static CountProduct_singleton single_instance = null;

    // Declaring a variable of type String
    public int count = 0;

    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private CountProduct_singleton() {
        count = 0;
    }

    // Static method
    // Static method to create instance of Singleton class
    public static synchronized CountProduct_singleton getInstance() {
        if (single_instance == null) {
            single_instance = new CountProduct_singleton();
        }
        return single_instance;
    }
    
    
}
