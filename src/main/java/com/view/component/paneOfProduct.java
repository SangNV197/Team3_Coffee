/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.view.component;

import SingletonClass.LstChiTietDoUong_singleton;
import model.ChiTietDoUong;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import model.HoaDon;
import viewModel.ChiTietDoUongNoIMG;

/**
 *
 * @author 84374
 */
public class paneOfProduct extends JPanel {
//        setLayout(new BorderLayout());
//        add(BorderLayout.WEST, new JScrollPane(paneOfProduct1));
//        paneOfProduct1.setVisible(true);

    static ArrayList<ProductCell> lstCell = new ArrayList<>();
    static ChiTietDoUong drinkDetail = new ChiTietDoUong();
    static ChiTietDoUongNoIMG drinkDetailNoIMG = new ChiTietDoUongNoIMG();
    static HoaDon localHoaDon = new HoaDon();
    static JTable localTbl = new JTable();
    static JLabel localLblCheck = new JLabel();

    public paneOfProduct(ArrayList<ChiTietDoUong> lstPerson, JTable tbl, HoaDon hoaDon, JLabel lblTotalCheck) {

        GridLayout grid = new GridLayout(Integer.valueOf(lstPerson.size() / 3) + 1, 3);
        grid.setVgap(-5);
        grid.setHgap(0);
        this.setLayout(grid);
        localHoaDon = hoaDon;
        localTbl = tbl;
        localLblCheck = lblTotalCheck;
        for (int i = 0; i < lstPerson.size(); i++) {
            System.out.println("test");
            ProductCell cell = new ProductCell(lstPerson.get(i).getHinhAnh(), lstPerson.get(i).getTenDoUong(), lstPerson.get(i).getGiaBan(), lstPerson.get(i).getMoTa());
            lstCell.add(cell);
            lstCell.get(i).setName(String.valueOf(i));
            lstCell.get(i).addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // index để truyền vào hàm để trả về một sản phẩm
                    int index = Integer.valueOf(e.getComponent().getName());
                    //
                    System.out.println("index: " + index);
                    System.out.println("test localHoaDon: " + localHoaDon.getId());
                    drinkDetail = LstChiTietDoUong_singleton.getInstance().lstChiTietDoUongs.get(index);
                    drinkDetailNoIMG = new ChiTietDoUongNoIMG(drinkDetail.getId(), drinkDetail.getTenDoUong(), drinkDetail.getGiaNhap(),
                             drinkDetail.getGiaBan(), drinkDetail.getMoTa(), drinkDetail.getLoaiDoUong(), drinkDetail.getKhuyenMai());
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            new EnterAmountFrame(drinkDetailNoIMG, localTbl, localLblCheck).setVisible(true);
                        }
                    });
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    cell.getLblNameProduct().setForeground(Color.blue);
                    cell.getLblPriceProduct().setForeground(Color.blue);
                    cell.getLblDes().setForeground(Color.blue);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    cell.getLblNameProduct().setForeground(Color.red);
                    cell.getLblPriceProduct().setForeground(Color.red);
                    cell.getLblDes().setForeground(Color.red);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    cell.getLblNameProduct().setForeground(Color.red);
                    cell.getLblPriceProduct().setForeground(Color.red);
                    cell.getLblDes().setForeground(Color.red);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    cell.getLblNameProduct().setForeground(Color.black);
                    cell.getLblPriceProduct().setForeground(Color.black);
                    cell.getLblDes().setForeground(Color.black);
                }
            });
            this.add(lstCell.get(i));
        }
    }

    public static void MoveToHDChiTiet(int count) {
        //////lấy id sản phẩm rồi chuyển sang hóa đơn chi tiết;
    }

}
