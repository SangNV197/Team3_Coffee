/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.view.form;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class QuetMaQR extends javax.swing.JFrame implements Runnable,ThreadFactory{

    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private Executor executor = Executors.newSingleThreadExecutor((ThreadFactory)this);
    private JTextField localQrlbl;
    private JTextField localQrlbl2;
    private JTextField localQrtxt3;
    private JRadioButton localQrrdo4;
    private JRadioButton localQrrdo5;
    
    public static String dataStatic;
     public static String dataStatic2;
     public static String dataStatic3;
     public static String dataStatic4;
    public QuetMaQR(JTextField lbl,JTextField lbl2,JTextField txt3,JRadioButton rdo4,JRadioButton rdo5) {
        initComponents();
        setLocationRelativeTo(null);
        initWebcam();
        localQrlbl = lbl;
        localQrlbl2 = lbl2;
        localQrtxt3 = txt3;
        localQrrdo4 = rdo4;
        localQrrdo5 = rdo5;
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        txtDiachi = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 180, 140));

        jLabel1.setText("Tên:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, -1, -1));

        jLabel2.setText("Ngày sinh:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, -1, -1));

        jLabel3.setText("Giới tính: ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, -1, -1));

        jLabel4.setText("Địa chỉ:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 460, -1, -1));
        jPanel1.add(txtTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 220, -1));
        jPanel1.add(txtNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 220, -1));

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");
        jPanel1.add(rdoNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, -1, -1));

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");
        jPanel1.add(rdoNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 410, -1, -1));
        jPanel1.add(txtDiachi, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 450, 220, -1));

        jButton1.setText("Send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dataStatic = txtTen.getText();
        dataStatic2 = txtNgaySinh.getText();
        dataStatic3 = txtDiachi.getText();
        dataStatic4 = rdoNam.isSelected()==true?"Nam":"Nữ";
        QuanLyNhanVien nhanVien = new QuanLyNhanVien();
        nhanVien.setVisible(true);
         setVisible(false);
         webcam.close();
         System.out.println(dataStatic);
         localQrlbl.setText(dataStatic);
         localQrlbl2.setText(dataStatic2);
         localQrtxt3.setText(dataStatic3);
         if(dataStatic4.equalsIgnoreCase("Nam")){
             localQrrdo4.setSelected(true);
         }else{
             localQrrdo5.setSelected(true);
         }
    }//GEN-LAST:event_jButton1ActionPerformed

//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(QuetMaQR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(QuetMaQR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(QuetMaQR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(QuetMaQR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new QuetMaQR().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTextField txtDiachi;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables

    private void initWebcam() {
          Dimension size = WebcamResolution.QVGA.getSize();
          webcam = Webcam.getWebcams().get(0);
          webcam.setViewSize(size);
          
          panel = new WebcamPanel(webcam);
          panel.setPreferredSize(size);
          panel.setFPSDisplayed(true);
          
          jPanel2.add(panel,new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,180,140));
          
          executor.execute((Runnable)this);
    }

    @Override
    public void run() {
          do{
              try{
                  Thread.sleep(100);
              }catch(InterruptedException e){
                  e.printStackTrace();
              }
              
              Result result = null;
              BufferedImage img = null;
              
              if(webcam.isOpen()){
                  if((img=webcam.getImage()) == null){
                      continue;
                  }
              }
              
              LuminanceSource source = new BufferedImageLuminanceSource(img);
              BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
              
              try{
                  result = new MultiFormatReader().decode(bitmap);
              }catch(NotFoundException e){
                  
              }
              
              if(result!=null){
                  String input = result.getText();
                  String[] parts = input.split("\\|");
                  
                  txtTen.setText(parts[2]);
                  txtNgaySinh.setText(parts[3]);
                  
                  if(parts[4].equalsIgnoreCase("Nam")){
                      rdoNam.setSelected(true);
                  }else{
                      rdoNu.setSelected(true);
                  }
                  txtDiachi.setText(parts[5]);
              }
              
              
          }while (true);              
        
    }

    @Override
    public Thread newThread(Runnable r) {
         Thread t = new Thread(r, "Hello");
        t.setDaemon(true);
        return t;    }
}
