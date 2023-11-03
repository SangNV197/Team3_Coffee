/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.view.component;

import SingletonClass.IdHD_singleton;
import SingletonClass.LstHoaDonChiTiet_singleton;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.ChiTietDoUong;
import model.HoaDon;
import model.HoaDonChiTiet;
import service.HoaDonChiTietNoIMGService;
import service.HoaDonChiTietService;
import service.HoaDonService;
import viewModel.ChiTietDoUongNoIMG;
import viewModel.HoaDonChiTietNoIMG;

/**
 *
 * @author 84374
 */
public class UpdateAmountFrame extends javax.swing.JFrame {

    /**
     * Creates new form EnterAmountFrame
     */
    static HoaDonChiTietNoIMG localHoaDonChiTietNoIMG = new HoaDonChiTietNoIMG();
    HoaDonChiTietNoIMGService hoaDonChiTietService = new HoaDonChiTietNoIMGService();
    HoaDonService hoaDonService = new HoaDonService();
    ArrayList<HoaDonChiTietNoIMG> lstDetailBillBuffer = new ArrayList<>();
    JTable localTbl = new JTable();
    JLabel localLbl = new JLabel();
    String idHoaDon = null;
    String idDrinkDetail = null;
    int amount = 0;

    public UpdateAmountFrame(HoaDonChiTietNoIMG hoaDonChiTietNoIMG, JTable tbl, JLabel lbl) {
        initComponents();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        lblDrinkName.setText(hoaDonChiTietNoIMG.getChiTietDoUongNoIMG().getTenDoUong());
        lblMaHoaDon.setText(hoaDonChiTietNoIMG.getHoaDon().getMa());
        localHoaDonChiTietNoIMG = hoaDonChiTietNoIMG;
        localTbl = tbl;
        localLbl = lbl;
    }

//    public void addDrinkDetail() {
//        int stt = 0;
//        double cellCheck = 0;
//        double totalCheck = 0;
//        DefaultTableModel model = new DefaultTableModel();
//        model = (DefaultTableModel) localTbl.getModel();
//        model.setRowCount(0);
//        HoaDon hoaDon = new HoaDon();
//        HoaDonChiTietNoIMG hoaDonChiTietNoIMGAdded = new HoaDonChiTietNoIMG();
//        hoaDon = hoaDonService.getHoaDonByID(IdHD_singleton.getInstance().id);
//        HoaDonChiTietNoIMG hoaDonChiTietNoIMG = new HoaDonChiTietNoIMG();
//        hoaDonChiTietNoIMG.setHoaDon(hoaDon);
//        hoaDonChiTietNoIMG.setChiTietDoUongNoIMG(localDrinkDetailNoIMG);
//        hoaDonChiTietNoIMG.setSoLuong(Integer.valueOf(txtAmount.getText()));
//        hoaDonChiTietNoIMGAdded = hoaDonChiTietService.saveHoaDon(hoaDonChiTietNoIMG);
//        if (hoaDonChiTietNoIMGAdded != null) {
//            LstHoaDonChiTiet_singleton.getInstance().lstHoaDonChiTietNoIMG.add(hoaDonChiTietNoIMGAdded);
//            for (HoaDonChiTietNoIMG hdChiTiet : LstHoaDonChiTiet_singleton.getInstance().lstHoaDonChiTietNoIMG) {
//                if (hdChiTiet.getHoaDon().getId().equalsIgnoreCase(IdHD_singleton.getInstance().id)) {
//                    stt++;
//                    cellCheck = Double.valueOf(hdChiTiet.getSoLuong()) * Double.valueOf(hdChiTiet.getChiTietDoUongNoIMG().getGiaBan());
//                    totalCheck += cellCheck;
//                    model.addRow(new Object[]{stt, hdChiTiet.getChiTietDoUongNoIMG().getTenDoUong(), hdChiTiet.getSoLuong(),
//                        hdChiTiet.getChiTietDoUongNoIMG().getGiaBan(), cellCheck});
//                }
//            }
//        } else {
//            for (HoaDonChiTietNoIMG hdChiTiet : LstHoaDonChiTiet_singleton.getInstance().lstHoaDonChiTietNoIMG) {
//                if (hdChiTiet.getHoaDon().getId().equalsIgnoreCase(IdHD_singleton.getInstance().id)) {
//                    stt++;
//                    cellCheck = Double.valueOf(hdChiTiet.getSoLuong()) * Double.valueOf(hdChiTiet.getChiTietDoUongNoIMG().getGiaBan());
//                    totalCheck += cellCheck;
//                    model.addRow(new Object[]{stt, hdChiTiet.getChiTietDoUongNoIMG().getTenDoUong(), hdChiTiet.getSoLuong(),
//                        hdChiTiet.getChiTietDoUongNoIMG().getGiaBan(), cellCheck});
//                }
//            }
//            JOptionPane.showMessageDialog(null, "Sản phẩm thêm vào không được trùng lặp");
//        }
//        localLbl.setText(String.valueOf(totalCheck));
//    }
    public void reloadTbl() {
        lstDetailBillBuffer = new ArrayList<>();
        double cellCheck = 0;
        double totalCheck = 0;
        int stt = 0;
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) localTbl.getModel();
        model.setRowCount(0);
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        for (HoaDonChiTietNoIMG hdChiTiet : LstHoaDonChiTiet_singleton.getInstance().lstHoaDonChiTietNoIMG) {
            if (hdChiTiet.getHoaDon().getId().equalsIgnoreCase(IdHD_singleton.getInstance().id)) {
                stt++;
                cellCheck = Double.valueOf(hdChiTiet.getSoLuong()) * Double.valueOf(hdChiTiet.getChiTietDoUongNoIMG().getGiaBan());
                totalCheck += cellCheck;
                model.addRow(new Object[]{stt, hdChiTiet.getChiTietDoUongNoIMG().getTenDoUong(), hdChiTiet.getSoLuong(),
                    formatter.format(hdChiTiet.getChiTietDoUongNoIMG().getGiaBan()), formatter.format(cellCheck)});
                lstDetailBillBuffer.add(hdChiTiet);
                System.out.println(hdChiTiet.getSoLuong());
            }
        }
        localLbl.setText(formatter.format(totalCheck) + "VNĐ");
    }

    public void updateDetailBill() {
        localHoaDonChiTietNoIMG.setSoLuong(Integer.valueOf(txtAmount.getText()));
        hoaDonChiTietService.updateHoaDon(localHoaDonChiTietNoIMG);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblDrinkName = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnAddDrink = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblMaHoaDon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("Nhập số lượng");

        jLabel2.setText("Tên sản phẩm:");

        lblDrinkName.setText("#NameOfProductHere ");

        jLabel4.setText("Số lượng:");

        btnAddDrink.setText("Sửa");
        btnAddDrink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDrinkActionPerformed(evt);
            }
        });

        jButton2.setText("Hủy");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Mã hóa đơn:");

        lblMaHoaDon.setText("#idHere");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addGap(28, 28, 28))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDrinkName, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMaHoaDon))
                        .addGap(0, 19, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddDrink)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblMaHoaDon))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblDrinkName))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddDrink)
                    .addComponent(jButton2))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnAddDrinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDrinkActionPerformed
        // TODO add your handling code here:
        updateDetailBill();
        reloadTbl();
        this.dispose();
    }//GEN-LAST:event_btnAddDrinkActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddDrink;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDrinkName;
    private javax.swing.JLabel lblMaHoaDon;
    private javax.swing.JTextField txtAmount;
    // End of variables declaration//GEN-END:variables
}
