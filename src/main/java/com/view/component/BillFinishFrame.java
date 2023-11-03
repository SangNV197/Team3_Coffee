/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.view.component;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.KhuyenMai;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import service.HoaDonChiTietService;
import service.HoaDonService;

/**
 *
 * @author 84374
 */
public class BillFinishFrame extends javax.swing.JFrame {

    /**
     * Creates new form BillFrame
     */
    private String LocalId;
    private HoaDonService hoaDonService = new HoaDonService();
    private HoaDonChiTietService hoaDonChiTietService = new HoaDonChiTietService();
    private ArrayList<HoaDonChiTiet> lstHoaDonChiTiet;
    private HoaDon hoaDon;
    private double totalCheck = 0;
    private double totalCheckWithDiscount = 0;
    private double localMoneyTake;
    private double moneyChange = 0;

    public BillFinishFrame(String id) {
        initComponents();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        LocalId = id;
        loadData();
    }

    public void loadData() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        int stt = 0;
        double cellCheck = 0;
        hoaDon = hoaDonService.getHoaDonByID(LocalId);
        String checkStt;
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tblDrinkDetail.getModel();
        model.setRowCount(0);
        lstHoaDonChiTiet = hoaDonChiTietService.getListHoaDonChiTietByID(LocalId);
        lblMaHD.setText(hoaDon.getMa());
        lblBan.setText(hoaDon.getBan().getTen());
        if (hoaDon.getTinhTrangThanhToan() == 0) {
            checkStt = "Chưa thanh toán";
        } else {
            checkStt = "Đã thanh toán";
        }
        lblCheckStt.setText(checkStt);
        lblDate.setText(String.valueOf(hoaDon.getNgayTao()));
        lblTime.setText(hoaDon.getThoiGian());
        for (HoaDonChiTiet hoaDonChiTiet : lstHoaDonChiTiet) {
            stt++;
            cellCheck = Double.valueOf(hoaDonChiTiet.getSoLuong()) * Double.valueOf(hoaDonChiTiet.getChiTietDoUong().getGiaBan());
            totalCheck += cellCheck;
            model.addRow(new Object[]{stt, hoaDonChiTiet.getChiTietDoUong().getTenDoUong(), hoaDonChiTiet.getSoLuong(),
                hoaDonChiTiet.getChiTietDoUong().getGiaBan(), cellCheck});
        }
        lblTotalCheck.setText(formatter.format(totalCheck));
        moneyChange = Double.parseDouble(String.valueOf(hoaDon.getMoneyTake())) - totalCheck;
        System.out.println(totalCheck);
        lblMoneyChange.setText(formatter.format(moneyChange));

        lblTotalCheck.setText(formatter.format(totalCheck) + " VNĐ");
        if (hoaDon.getMaGiamGia().getMaKM() != null) {
            totalCheckWithDiscount = totalCheck - totalCheck * (Double.valueOf(hoaDon.getMaGiamGia().getGiaTri()) / 100);
            lblDiscountPer.setText(String.valueOf(hoaDon.getMaGiamGia().getGiaTri()) + hoaDon.getMaGiamGia().getLoaiKM());
            lblCheckAfterDiscount.setText(formatter.format(totalCheck - totalCheck * (Double.valueOf(hoaDon.getMaGiamGia().getGiaTri()) / 100)) + " VNĐ");
        } else {
            lblDiscountPer.setText("Không");
            lblCheckAfterDiscount.setText(formatter.format(totalCheck) + " VNĐ");
            totalCheckWithDiscount = totalCheck;
        }
        if (hoaDon.getMoneyTake() != null) {
            lblMoneyTake.setText(formatter.format(hoaDon.getMoneyTake()) + " VNĐ");
            if (hoaDon.getMaGiamGia().getGiaTri() != null) {
                moneyChange = Double.parseDouble(String.valueOf(hoaDon.getMoneyTake())) - totalCheck + totalCheck * (Double.valueOf(hoaDon.getMaGiamGia().getGiaTri()) / 100);
            } else {
                moneyChange = Double.parseDouble(String.valueOf(hoaDon.getMoneyTake())) - totalCheck;
            }
            lblMoneyChange.setText(formatter.format(moneyChange) + " VNĐ");
        } else {
            lblMoneyTake.setText("");
        }
    }

    public static String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    public static String toKhongDau(String str) {
        try {
            String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll(" ", " ").replaceAll("đ", "d");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public void WriteInvoice() {
        //get the page
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        PDDocument invc = new PDDocument();
        PDPage newPage = new PDPage();
        invc.addPage(newPage);
        int n = 0;
        double cellCheckPrint = 0;
        String ttThanhToan = null;
        if (hoaDon.getTinhTrangThanhToan() == 1) {
            ttThanhToan = "Da thanh toan";
        } else {
            ttThanhToan = "Chua thanh toan";
        }
        PDPage mypage = invc.getPage(0);
        try {
            //Prepare Content Stream
            PDPageContentStream cs = new PDPageContentStream(invc, mypage);

            //Writing Single Line text
            //Writing the Invoice title
            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(420, 750);
            cs.showText(hoaDon.getMa());
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 18);
            cs.newLineAtOffset(220, 690);
            cs.showText("HOA DON THANH TOAN");
            cs.endText();

            //Writing Multiple Lines
            //writing the customer details
            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.setLeading(20f);
            cs.newLineAtOffset(150, 610);
            cs.showText("So ban: ");
            cs.newLine();
            cs.showText("Ma NV: ");
            cs.newLine();
            cs.showText("Ten NV: ");
            cs.newLine();
            cs.showText("Ngay vao: ");
            cs.newLine();
            cs.showText("Gio vao: ");
            cs.newLine();
            cs.showText("Thanh toan: ");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.setLeading(20f);
            cs.newLineAtOffset(260, 610);
            cs.showText(String.valueOf(hoaDon.getBan().getIdBan()));
            cs.newLine();
            cs.showText(hoaDon.getNhanVien().getMa());
            cs.newLine();
            cs.showText(toKhongDau(hoaDon.getNhanVien().getTen()));
            cs.newLine();
            cs.showText(hoaDon.getNgayTao().toString());
            cs.newLine();
            cs.showText(hoaDon.getThoiGian());
            cs.newLine();
            cs.showText(ttThanhToan);
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(150, 480);
            cs.showText("San pham");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(250, 480);
            cs.showText("Don gia");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(320, 480);
            cs.showText("So luong");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(390, 480);
            cs.showText("Thanh tien");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
            cs.setLeading(20f);
            cs.newLineAtOffset(150, 440);
            for (HoaDonChiTiet hoaDonChiTiet : lstHoaDonChiTiet) {
                String ten = toKhongDau(hoaDonChiTiet.getChiTietDoUong().getTenDoUong()).toString();
                cs.showText(ten);
                cs.newLine();
                n++;
            }
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
            cs.setLeading(20f);
            cs.newLineAtOffset(250, 440);
            for (HoaDonChiTiet hoaDonChiTiet : lstHoaDonChiTiet) {
                cs.showText(formatter.format(hoaDonChiTiet.getChiTietDoUong().getGiaBan()));
                cs.newLine();
            }
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
            cs.setLeading(20f);
            cs.newLineAtOffset(320, 440);
            for (HoaDonChiTiet hoaDonChiTiet : lstHoaDonChiTiet) {
                cs.showText(String.valueOf(hoaDonChiTiet.getSoLuong()));
                cs.newLine();
            }
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
            cs.setLeading(20f);
            cs.newLineAtOffset(390, 440);
            for (HoaDonChiTiet hoaDonChiTiet : lstHoaDonChiTiet) {
                cellCheckPrint = Double.valueOf(hoaDonChiTiet.getSoLuong()) * Double.valueOf(hoaDonChiTiet.getChiTietDoUong().getGiaBan());
                cs.showText(formatter.format(cellCheckPrint));
                cs.newLine();
            }
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.setLeading(20f);
            cs.newLineAtOffset(280, (420 - (20 * n)));
            cs.showText("Tong tien: ");
            cs.newLine();
            cs.showText("Giam gia: ");
            cs.newLine();
            cs.showText("Thuc thu: ");
            cs.newLine();
            cs.showText("Khach dua: ");
            cs.newLine();
            cs.showText("Tien thua: ");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.setLeading(20f);
            cs.newLineAtOffset(380, (420 - (20 * n)));
            cs.showText(formatter.format(totalCheck) + " VND");
            cs.newLine();
            cs.showText(hoaDon.getMaGiamGia().getGiaTri() + "%");//giamgia
            cs.newLine();
            cs.showText(formatter.format(totalCheckWithDiscount) + " VND");//thucthu
            cs.newLine();
            cs.showText(formatter.format(hoaDon.getMoneyTake()) + " VND");//khachdua
            cs.newLine();
            cs.showText(formatter.format(moneyChange) + " VND");//tienthua
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.setLeading(20f);
            cs.newLineAtOffset(250, (220 - (20 * n)));
            cs.showText("COFFEE CODER");
            cs.newLine();
            cs.showText("Dia chi: Ha Noi");
            cs.newLine();
            cs.showText("So dien thoai: 0374223222");
            cs.newLine();
            cs.showText("Email: Phuclocub@gmail.com");
            cs.endText();
            
            //Close the content stream
            cs.close();
            //Save the PDF
            invc.save(hoaDon.getMa()+".pdf");
            JOptionPane.showMessageDialog(null, "In hóa đơn thành công");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printInvoice() {
        WriteInvoice();
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDrinkDetail = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        lblMaHD = new javax.swing.JLabel();
        lblBan = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblCheckStt = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblTotalCheck = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblMoneyChange = new javax.swing.JLabel();
        btnCheck = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lblDiscountPer = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblCheckAfterDiscount = new javax.swing.JLabel();
        lblMoneyTake = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("HÓA ĐƠN THANH TOÁN");

        jLabel2.setText("Bàn:");

        jLabel3.setText("Ngày:");

        jLabel4.setText("Thời gian:");

        jLabel5.setText("Thanh toán: ");

        tblDrinkDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên", "SL", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDrinkDetail);
        if (tblDrinkDetail.getColumnModel().getColumnCount() > 0) {
            tblDrinkDetail.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblDrinkDetail.getColumnModel().getColumn(2).setPreferredWidth(30);
        }

        jLabel6.setText("Mã  HĐ:");

        lblMaHD.setText("#MaHD");

        lblBan.setText("#ban");

        lblDate.setText("#Ngay");

        lblCheckStt.setText("#checkStt");

        lblTime.setText("#time");

        jLabel12.setText("Tổng cộng: ");

        lblTotalCheck.setText("#totalCheck");

        jLabel15.setText("Số tiền trả lại:");

        lblMoneyChange.setText("#moneyChange");

        btnCheck.setText("In Hóa Đơn");
        btnCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckActionPerformed(evt);
            }
        });

        jLabel7.setText("Giảm giá:");

        lblDiscountPer.setText(".....");

        jLabel9.setText("Thực thu:");

        lblCheckAfterDiscount.setText("#money");

        lblMoneyTake.setText("#moneyChange");

        jLabel16.setText("khách đưa:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblDiscountPer))
                            .addComponent(btnCheck))
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMaHD)
                        .addGap(45, 45, 45))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMoneyTake, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMoneyChange, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lblCheckAfterDiscount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblTotalCheck, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblDate))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(lblBan)))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(32, 32, 32)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTime)
                            .addComponent(lblCheckStt)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel1)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblMaHD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(lblBan)
                    .addComponent(lblCheckStt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(lblDate)
                    .addComponent(lblTime))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTotalCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(jLabel7)
                        .addComponent(lblDiscountPer)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblCheckAfterDiscount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMoneyTake)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMoneyChange)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addComponent(btnCheck)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckActionPerformed
        // TODO add your handling code here:
        printInvoice();
    }//GEN-LAST:event_btnCheckActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCheck;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBan;
    private javax.swing.JLabel lblCheckAfterDiscount;
    private javax.swing.JLabel lblCheckStt;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDiscountPer;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblMoneyChange;
    private javax.swing.JLabel lblMoneyTake;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTotalCheck;
    private javax.swing.JTable tblDrinkDetail;
    // End of variables declaration//GEN-END:variables
}
