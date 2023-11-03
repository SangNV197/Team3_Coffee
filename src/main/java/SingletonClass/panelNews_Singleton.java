/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SingletonClass;

import com.view.component.NewsPanel;

/**
 *
 * @author 84374
 */
public class panelNews_Singleton {
        private static panelNews_Singleton single_instance = null;

    // Declaring a variable of type String
    public NewsPanel paneNews;

    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private panelNews_Singleton() {
         paneNews = new NewsPanel(LstNews_singleton.getInstance().lstNews);
    }

    // Static method
    // Static method to create instance of Singleton class
    public static synchronized panelNews_Singleton getInstance() {
        if (single_instance == null) {
            single_instance = new panelNews_Singleton();
        }
        return single_instance;
    }
}
