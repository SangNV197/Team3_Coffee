/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SingletonClass;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import model.ChiTietDoUong;
import model.News;
import model.TinTuc;
import service.ChiTietDoUongService_Master;
import service.TinTucService;

/**
 *
 * @author 84374
 */
public class LstNews_singleton {
    private static LstNews_singleton single_instance = null;
//    private static service.ChiTietDoUongService_Master  service = new ChiTietDoUongService_Master();
    // Declaring a variable of type String
    public ArrayList<TinTuc> lstNews;
    public TinTucService service;
    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private LstNews_singleton() {
        service = new TinTucService(); 
        lstNews = service.getListTinTuc();
//        String dir = null;
//        String path = "src\\main\\java\\com\\view\\icon\\coffe.png";
//        File file = new File(path);
//        String absolutePath = file.getAbsolutePath();
//        dir = absolutePath;
//        byte[] imgByte = new byte[5000];
//        try {
//            BufferedImage bImage = ImageIO.read(new File(dir));
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            ImageIO.write(bImage, "jpg", bos);
//            imgByte = bos.toByteArray();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    // Static method
    // Static method to create instance of Singleton class
    public static synchronized LstNews_singleton getInstance() {
        if (single_instance == null) {
            single_instance = new LstNews_singleton();
        }
        return single_instance;
    }

}
