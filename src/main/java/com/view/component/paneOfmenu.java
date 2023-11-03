/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.view.component;

import SingletonClass.LstChiTietDoUong_singleton;
import model.ChiTietDoUong;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import model.HoaDon;
import org.apache.poi.ss.usermodel.ShapeTypes;
import viewModel.ChiTietDoUongNoIMG;

/**
 *
 * @author 84374
 */
public class paneOfmenu extends JPanel {
//        setLayout(new BorderLayout());
//        add(BorderLayout.WEST, new JScrollPane(paneOfProduct1));
//        paneOfProduct1.setVisible(true);

    static ArrayList<menuCell> lstCell = new ArrayList<>();
    String path = "src\\main\\java\\com\\view\\icon\\menufhd.jpg";
    File file = new File(path);
    String absolutePath = file.getAbsolutePath();
    String dir = absolutePath;
    ImageIcon oriImgIcon = new ImageIcon(dir);
    Image image = oriImgIcon.getImage(); // transform it
    static GridBagLayout gridBag = new GridBagLayout();
    static GridBagConstraints c = new GridBagConstraints();
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, 650, 800, null);
    }
    
    public paneOfmenu(ArrayList<ChiTietDoUong> lstMenu, byte[] img) {
        lstCell.clear();
        paneOfmenu.this.removeAll();
        int count = 0;
        ArrayList<Integer> lstFirstAppear = new ArrayList<>();
        this.setLayout(gridBag);
        JLabel lbl = new JLabel();
        lbl.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl.setForeground(new java.awt.Color(239, 204, 162));
        lbl.setText("Menu");
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        this.add(lbl, c);
        
        for (int i = 0; i < lstMenu.size(); i++) {
            if (i > 0) {
                if (lstMenu.get(i).getLoaiDoUong().getId().equals(lstMenu.get(i - 1).getLoaiDoUong().getId())) {
                    
                } else {
                    lstFirstAppear.add(i);
                }
            }
        }
        
        for (int i = 1; i < Integer.valueOf(lstMenu.size() / 2) + 3 + lstFirstAppear.size(); i++) {
            for (int j = 0; j < 2; j++) {
                if (i == 1 && j == 0) {
                    JLabel tittle = new JLabel();
                    tittle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                    tittle.setForeground(new java.awt.Color(239, 204, 162));
                    tittle.setText(lstMenu.get(0).getLoaiDoUong().getTenLoaiDoUong());
                    tittle.setHorizontalAlignment(SwingConstants.CENTER);
                    c.weighty = 1;
                    c.weightx = 1;
                    c.anchor = GridBagConstraints.LAST_LINE_START;
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 0;
                    c.gridy = 0;
                    c.gridwidth = 1;
                    c.insets = new Insets(1, 1, -20, 1);
                    this.add(tittle, c);
                }
                try {
                    for (int x = 0; x < lstFirstAppear.size(); x++) {
                        if (lstMenu.get(count).getId().equals(lstMenu.get(lstFirstAppear.get(x)).getId())) {
                            JLabel tit = new JLabel();
                            tit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                            tit.setForeground(new java.awt.Color(239, 204, 162));
                            tit.setText(lstMenu.get(lstFirstAppear.get(x)).getLoaiDoUong().getTenLoaiDoUong());
                            tit.setHorizontalAlignment(SwingConstants.CENTER);
                            c.weighty = 1;
                            c.weightx = 1;
                            c.anchor = GridBagConstraints.LAST_LINE_START;
                            c.fill = GridBagConstraints.HORIZONTAL;
                            c.gridx = j;
                            c.gridy = i - 1;
                            c.gridwidth = 1;
                            c.insets = new Insets(1, 1, -20, 1);
                            this.add(tit, c);
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                try {
                    menuCell cell = new menuCell(lstMenu.get(count).getTenDoUong(), lstMenu.get(count).getGiaBan());
                    lstCell.add(cell);
                    lstCell.get(count).setName(String.valueOf(i));
                    c.weighty = 1;
                    c.weightx = 1;
                    c.anchor = GridBagConstraints.LAST_LINE_START;
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = j;
                    c.gridy = i;
                    c.gridwidth = 1;
                    c.insets = new Insets(1, 1, 1, 30);
                    this.add(lstCell.get(count), c);
                    count++;
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
                
            }
        }
        this.repaint();
        this.revalidate();
        this.updateUI();
    }
    
    public void updateDetail(ArrayList<ChiTietDoUong> lstMenu) {
        
    }
}
