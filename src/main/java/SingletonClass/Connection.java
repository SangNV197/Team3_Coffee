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
public class Connection {
    private static Connection single_instance = null;
  
    // Declaring a variable of type String
    public String url;
    private static final String FILE_CONFIG = "\\configureApp.properties";
    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private Connection()
    {   
            Properties properties = new Properties();
            InputStream inputStream = null;
        try {
            String currentDir = System.getProperty("user.dir");
            inputStream = new FileInputStream(currentDir + FILE_CONFIG);
            // load properties from file
            properties.load(inputStream);
            // get property by name
            url=properties.getProperty("url");
        } catch (IOException e) {
            e.printStackTrace();}
             
    }
  
    // Static method
    // Static method to create instance of Singleton class
    public static synchronized Connection getInstance()
    {
        if (single_instance == null)
            single_instance = new Connection();
  
        return single_instance;
    }
}
