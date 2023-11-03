/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SingletonClass;

/**
 *
 * @author 84374
 */
public class IdHD_singleton {

    private static IdHD_singleton single_instance = null;

    // Declaring a variable of type String
    public String id;
    public String maHD;

    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private IdHD_singleton() {
        id=null;
        maHD=null;
    }

    // Static method
    // Static method to create instance of Singleton class
    public static synchronized IdHD_singleton getInstance() {
        if (single_instance == null) {
            single_instance = new IdHD_singleton();
        }
        return single_instance;
    }

}
