/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

/**
 *
 * @author 84374
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class ImageInScrollPane extends JPanel {

    public static final String IMAGE_PATH = "https://en.wikipedia.org/wiki/IMG_Academy#/media/File:IMG_Academy_Logo.svg";
    private static final int PREF_W = 500;
    private static final int PREF_H = 400;

    public ImageInScrollPane() throws IOException {
        String path = "C:\\Users\\84374\\Documents\\NetBeansProjects\\CoffeeShop\\src\\main\\java\\com\\view\\icon\\coffe.png";
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        String dir = absolutePath;
        ImageIcon oriImgIcon = new ImageIcon(dir);
        Image image = oriImgIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(500, 620, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        ImageIcon imageIcon = new ImageIcon(newimg);
        JLabel label = new JLabel();
        label.setIcon(imageIcon);
        JScrollPane scrollPane = new JScrollPane(label);
        setLayout(new BorderLayout());
        add(scrollPane);
    }

    @Override
    public Dimension getPreferredSize() {
        if (isPreferredSizeSet()) {
            return super.getPreferredSize();
        }
        return new Dimension(PREF_W, PREF_H);
    }

    private static void createAndShowGui() {
        ImageInScrollPane mainPanel = null;
        try {
            mainPanel = new ImageInScrollPane();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        JFrame frame = new JFrame("ImageInScrollPane");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }
}
