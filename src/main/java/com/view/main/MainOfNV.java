/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view.main;

import com.view.event.EventMenuSelected;
import com.view.form.DangKyDVForm;
import com.view.form.ThongBaoForm;
import com.view.form.LichHocForm;
import com.view.form.DiemForm;
import com.view.form.QuanLyNhanVien;
import com.view.form.WalletForm;
import com.view.form_Template.Container;

import com.view.form_Template.Form_BanHang;
import com.view.form_Template.Form_GiaoCa;
//import com.view.form_Template.Form_GiaoCa1;
import com.view.form_Template.Form_KhuyenMai;
import com.view.form_Template.Form_QLDoUong;
import com.view.form_Template.Form_QLHoaDon;
import com.view.form_Template.Form_QuanLyKho;
import com.view.form_Template.Form_TaoTaiKhoan;
import com.view.form_Template.Form_ThongBao;
import com.view.form_Template.Form_ThongKe;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import static java.awt.SystemColor.menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 *
 */
public class MainOfNV extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    private WalletForm home;
    private Form_ThongBao formThongbao;
    private Form_BanHang formBanHang;
    private Form_TaoTaiKhoan form_TaoTaiKhoan;
    private Form_QLDoUong form_QLDoUong;
    private Form_KhuyenMai form_KhuyenMai;
    private com.view.component.paneOfProduct paneOfProduct2;
    private QuanLyNhanVien formQLNhanVien;
    private Form_QLHoaDon formQLHoaDon;
    private Form_ThongKe form_ThongKe;
    private Form_QuanLyKho form_QuanLyKho;
    private Form_GiaoCa form_GiaoCa;

    public MainOfNV() {
        initComponents();
        home = new WalletForm();
        Thread t1 = new Thread(
                () -> {
                    formThongbao = new Form_ThongBao();
                    setForm(formThongbao);
                }
        );
        Thread t2 = new Thread(
                () -> {
                    formBanHang = new Form_BanHang();
                }
        );
        Thread t3 = new Thread(
                () -> {
                    form_KhuyenMai = new Form_KhuyenMai();
                    form_GiaoCa = new Form_GiaoCa();
                }
        );
        Thread t4 = new Thread(
                () -> {
                    form_TaoTaiKhoan = new Form_TaoTaiKhoan();
                    form_QuanLyKho = new Form_QuanLyKho();
                }
        );
        Thread t5 = new Thread(
                () -> {
                    form_QLDoUong = new Form_QLDoUong();
                }
        );
        Thread t6 = new Thread(
                () -> {
                    formQLHoaDon = new Form_QLHoaDon();
                }
        );
        Thread t7 = new Thread(
                () -> {
                    form_ThongKe = new Form_ThongKe();
                }
        );
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
//        formThongbao = new Form_ThongBao();
//        formBanHang = new Form_BanHang();
//        form_KhuyenMai = new Form_KhuyenMai();
//        formQLNhanVien = new QuanLyNhanVien();
//        form_TaoTaiKhoan = new Form_TaoTaiKhoan();
//        form_QLDoUong = new Form_QLDoUong();
//        formQLHoaDon = new Form_QLHoaDon();
//        form_ThongKe = new Form_ThongKe();
//        form_GiaoCa = new Form_GiaoCa();
        setBackground(new Color(0, 0, 0, 0));
        menuOfNV1.initMoving(MainOfNV.this);
        menuOfNV1.addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                if (index == 5) {
                    setForm(form_TaoTaiKhoan);
                } else if (index == 6) {
                    setForm(form_QuanLyKho);
                } else if (index == 0) {
                    setForm(formThongbao);
                } else if (index == 1) {
                    setForm(formBanHang);
                } else if (index == 2) {
                    setForm(form_GiaoCa);
                } else if (index == 3) {
                    setForm(formQLHoaDon);
                } else if (index == 4) {
                    setForm(form_ThongKe);
                } else if (index == 7) {
                    MessageFrame messageFrame = new MessageFrame();
                    messageFrame.show();
                    messageFrame.setMessage("Bạn có chắc chắn muốn đăng xuất không?");
                    messageFrame.setButtonOK(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dispose();
                            messageFrame.dispose();
                            new LoginFrame().setVisible(true);
                        }
                    });

                    messageFrame.setButtonCancel(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            messageFrame.dispose();
                        }
                    });
                }
            }
        });

        //  set when system open start with home form
//        setForm(formThongbao);
    }

    private void setForm(JComponent com) {
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        mainPanel = new javax.swing.JPanel();
        menuOfNV1 = new com.view.component.MenuOfNV();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        mainPanel.setOpaque(false);
        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(menuOfNV1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1072, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuOfNV1, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel mainPanel;
    private com.view.component.MenuOfNV menuOfNV1;
    // End of variables declaration//GEN-END:variables
}
