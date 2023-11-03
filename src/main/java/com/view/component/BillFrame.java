/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.view.component;

import SingletonClass.LstChiTietDoUong_singleton;
import SingletonClass.LstHoaDonChiTiet_singleton;
import SingletonClass.LstHoaDonCho_SingLeTon;
import SingletonClass.LstHoaDonDangPhaChe_singleton;
import SingletonClass.LstHoaDon_singleton;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.GiamGia;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.KhuyenMai;
import service.GiamGiaService;
import service.HoaDonChiTietService;
import service.HoaDonService;
import service.SaleService;
import viewModel.HoaDonChiTietNoIMG;

/**
 *
 * @author 84374
 */
public class BillFrame extends javax.swing.JFrame {

    /**
     * Creates new form BillFrame
     */
    private String LocalId;
    HoaDonService hoaDonService = new HoaDonService();
    HoaDonChiTietService hoaDonChiTietService = new HoaDonChiTietService();
    GiamGiaService giamGiaService = new GiamGiaService();
    double totalCheck = 0;
    double localMoneyTake = 0;
    double discountPer = 0;
    double checkAfterDiscount = 0;
    JTable localTblHoaDon;
    JTable localTblHoaDonDangPhaChe;
    JTable localTblHoaDonCho;

    public BillFrame(String id, JTable tblHoaDon, JTable tblHoaDonDangPhaChe, JTable tblHoaDonCho) {
        initComponents();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        LocalId = id;
        localTblHoaDon = tblHoaDon;
        localTblHoaDonDangPhaChe = tblHoaDonDangPhaChe;
        localTblHoaDonCho = tblHoaDonCho;
        loadData();
    }

    public void loadData() {
        int stt = 0;
        double cellCheck = 0;
        int checkStage = 0;
        HoaDon hoaDon = null;
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        for (HoaDon hd : LstHoaDon_singleton.getInstance().lstHoaDon) {
            if (hd.getId().equalsIgnoreCase(LocalId)) {
                hoaDon = hd;
                checkStage = 1;
                break;
            }
        }

        if (checkStage == 0) {
            for (HoaDon hdCho : LstHoaDonCho_SingLeTon.getInstance().lstHoaDonCho) {
                if (hdCho.getId().equalsIgnoreCase(LocalId)) {
                    hoaDon = hdCho;
                    break;
                }
            }
        }
        String checkStt;
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tblDrinkDetail.getModel();
        model.setRowCount(0);
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
        for (HoaDonChiTietNoIMG hoaDonChiTietNoIMG : LstHoaDonChiTiet_singleton.getInstance().lstHoaDonChiTietNoIMG) {
            if (hoaDonChiTietNoIMG.getHoaDon().getId().equalsIgnoreCase(LocalId)) {
                stt++;
                cellCheck = Double.valueOf(hoaDonChiTietNoIMG.getSoLuong()) * Double.valueOf(hoaDonChiTietNoIMG.getChiTietDoUongNoIMG().getGiaBan());
                totalCheck += cellCheck;
                model.addRow(new Object[]{stt, hoaDonChiTietNoIMG.getChiTietDoUongNoIMG().getTenDoUong(), hoaDonChiTietNoIMG.getSoLuong(),
                    formatter.format(hoaDonChiTietNoIMG.getChiTietDoUongNoIMG().getGiaBan()), formatter.format(cellCheck)});
            }
        }
        lblTotalCheck.setText(formatter.format(totalCheck)+ " VNĐ");
        if (hoaDon.getMaGiamGia().getMaKM() != null) {
            System.out.println("test discount");
            txtDiscount.setText(String.valueOf(hoaDon.getMaGiamGia().getMaKM()));
            applyDiscount();
        } else {
            txtDiscount.setText("");
        }
        if (hoaDon.getMoneyTake() != null) {
            txtEnterMoney.setText(String.valueOf(hoaDon.getMoneyTake()));
            calMoneyChange();
        } else {
            txtEnterMoney.setText("");
        }

    }

    public void updateMoneyTake() {
        int checkStage = 0;
        hoaDonService.updateMoneyTake(LocalId, localMoneyTake);
        for (HoaDon hd : LstHoaDon_singleton.getInstance().lstHoaDon) {
            if (hd.getId().equalsIgnoreCase(LocalId)) {
                hd.setMoneyTake(BigDecimal.valueOf(localMoneyTake));
                checkStage = 1;
                break;
            }
        }

        if (checkStage == 0) {
            for (HoaDon hdCho : LstHoaDonCho_SingLeTon.getInstance().lstHoaDonCho) {
                if (hdCho.getId().equalsIgnoreCase(LocalId)) {
                    hdCho.setMoneyTake(BigDecimal.valueOf(localMoneyTake));
                    break;
                }
            }
        }
    }

    public void updateSttCheckBill() {
        hoaDonService.updateSttCheckBill(1, LocalId);
        int checkStage = 0;
        for (HoaDon hd : LstHoaDon_singleton.getInstance().lstHoaDon) {
            if (hd.getId().equalsIgnoreCase(LocalId)) {
                hd.setTinhTrangThanhToan(1);
                checkStage = 1;
                break;
            }
        }
        if (checkStage == 0) {
            for (HoaDon hdCho : LstHoaDonCho_SingLeTon.getInstance().lstHoaDonCho) {
                if (hdCho.getId().equalsIgnoreCase(LocalId)) {
                    hdCho.setTinhTrangThanhToan(1);
                    break;
                }
            }
        }
    }

    public void updateDiscount() {
        try {
            if (!txtDiscount.getText().strip().equals("")) {
                hoaDonService.updateDiscount(txtDiscount.getText().strip(), LocalId);
                int checkStage = 0;
                for (HoaDon hd : LstHoaDon_singleton.getInstance().lstHoaDon) {
                    if (hd.getId().equalsIgnoreCase(LocalId)) {
                        GiamGia giamGia = giamGiaService.selectByID(txtDiscount.getText().strip());
                        hd.setMaGiamGia(giamGia);
                        checkStage = 1;
                        break;
                    }
                }

                if (checkStage == 0) {
                    for (HoaDon hdCho : LstHoaDonCho_SingLeTon.getInstance().lstHoaDonCho) {
                        if (hdCho.getId().equalsIgnoreCase(LocalId)) {
                            GiamGia giamGia = giamGiaService.selectByID(txtDiscount.getText().strip());
                            hdCho.setMaGiamGia(giamGia);
                            break;
                        }
                    }
                }
            } else {
                hoaDonService.updateDiscount(null, LocalId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Mã giảm giá không đúng !");
        }

    }

    public void loadHoaDonTbl() {
        int stt = 0;
        LstHoaDon_singleton.getInstance().lstHoaDon = hoaDonService.getListHoaDon();
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) localTblHoaDon.getModel();
        model.setRowCount(0);
        String thanhToanStt;
        String phaCheStt;
        for (HoaDon hoaDon : LstHoaDon_singleton.getInstance().lstHoaDon) {
            stt++;
            if (hoaDon.getTinhTrangThanhToan() == 0) {
                thanhToanStt = "Chưa TT";
            } else {
                thanhToanStt = "Đã TT";
            }
            if (hoaDon.getTrangThaiPhaChe() == 0) {
                phaCheStt = "Chưa pha";
            } else {
                phaCheStt = "Đã pha";
            }

            model.addRow(new Object[]{stt, hoaDon.getMa(), hoaDon.getBan().getTen(), thanhToanStt, phaCheStt});
        }
    }

    public void loadHoaDonDangPhaChe() {
        int stt = 0;
        LstHoaDonDangPhaChe_singleton.getInstance().lstHoaDonDangPhaChe = hoaDonService.getHoaDonDangPhaChe();
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) localTblHoaDonDangPhaChe.getModel();
        model.setRowCount(0);
        String thanhToanStt;
        String phaCheStt;
        for (HoaDon hoaDon : LstHoaDonDangPhaChe_singleton.getInstance().lstHoaDonDangPhaChe) {
            stt++;
            if (hoaDon.getTinhTrangThanhToan() == 0) {
                thanhToanStt = "Chưa TT";
            } else {
                thanhToanStt = "Đã TT";
            }
            if (hoaDon.getTrangThaiPhaChe() == 0) {
                phaCheStt = "Chưa pha";
            } else {
                phaCheStt = "Đã pha";
            }

            model.addRow(new Object[]{stt, hoaDon.getMa(), hoaDon.getBan().getTen(), thanhToanStt, phaCheStt});
        }
    }

    public void loadHoaDonChoTbl() {
        int stt = 0;
        LstHoaDonCho_SingLeTon.getInstance().lstHoaDonCho = hoaDonService.getListHoaDonCho();
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) localTblHoaDonCho.getModel();
        model.setRowCount(0);
        String thanhToanStt;
        String phaCheStt;
        for (HoaDon hoaDon : LstHoaDonCho_SingLeTon.getInstance().lstHoaDonCho) {
            stt++;
            if (hoaDon.getTinhTrangThanhToan() == 0) {
                thanhToanStt = "Chưa TT";
            } else {
                thanhToanStt = "Đã TT";
            }
            if (hoaDon.getTrangThaiPhaChe() == 0) {
                phaCheStt = "Chưa pha";
            } else {
                phaCheStt = "Đã pha";
            }
            model.addRow(new Object[]{stt, hoaDon.getMa(), hoaDon.getBan().getTen(), thanhToanStt, phaCheStt});
        }
    }

    public void changeDataToTbl() {
        HoaDon hoaDon = hoaDonService.getHoaDonByID(LocalId);
        int checkHoaDon = 0;
        int checkHoaDonCho = 0;
        int checkHoaDonDangPhaChe = 0;
        String maHd = hoaDon.getMa();
        Integer[] arr = {localTblHoaDon.getRowCount(), localTblHoaDonCho.getRowCount(), localTblHoaDonDangPhaChe.getRowCount()};
        int max = Collections.max(Arrays.asList(arr));
        System.out.println("max: " + max);
        for (int i = 0; i < max; i++) {
            if (checkHoaDon == 0 && i < localTblHoaDon.getRowCount()) {
                String maHDTblHoaDon = (String) localTblHoaDon.getValueAt(i, 1);
                System.out.println("loop 1: " + maHDTblHoaDon);
                System.out.println("id" + LocalId);
                if (maHDTblHoaDon.equalsIgnoreCase(maHd)) {
                    System.out.println("case 1");
                    localTblHoaDon.setValueAt("Đã TT", i, 3);
                    checkHoaDon = 1;
                }
            }
            if (checkHoaDonDangPhaChe == 0 && i <localTblHoaDonDangPhaChe.getRowCount()) {
                String maHDTblHoaDonDangPhaChe = (String) localTblHoaDonDangPhaChe.getValueAt(i, 1);
                System.out.println("loop 3:" + maHDTblHoaDonDangPhaChe);
                System.out.println("id" + LocalId);
                if (maHDTblHoaDonDangPhaChe.equalsIgnoreCase(maHd)) {
                    System.out.println("case 3");
                    localTblHoaDonDangPhaChe.setValueAt("Đã TT", i, 3);
                    checkHoaDonDangPhaChe = 1;
                }
            }
            if (checkHoaDonCho == 0 && i < localTblHoaDonCho.getRowCount()) {
                String maHDTblHoaDonCho = (String) localTblHoaDonCho.getValueAt(i, 1);
                System.out.println("loop2: " + maHDTblHoaDonCho);
                System.out.println("id" + LocalId);
                if (maHDTblHoaDonCho.equalsIgnoreCase(maHd)) {
                    System.out.println("case2");
                    localTblHoaDonCho.setValueAt("Đã TT", i, 3);
                    checkHoaDonCho = 1;
                    break;
                }
            }

            

            if (checkHoaDon == 1 && checkHoaDonCho == 1) {
                break;
            }
        }
    }

    public void applyDiscount() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        GiamGia giamGia = giamGiaService.selectByID(txtDiscount.getText());
        discountPer = Double.valueOf(giamGia.getGiaTri()) / 100;
        checkAfterDiscount = totalCheck - totalCheck * discountPer;
        lblFinalCash.setText(formatter.format(checkAfterDiscount) + " VNĐ");
    }

    public void calMoneyChange() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        if (checkAfterDiscount == 0) {
            double moneyTake = Double.valueOf(txtEnterMoney.getText());
            localMoneyTake = moneyTake;
            double moneyChange = moneyTake - totalCheck;
            lblMoneyChange.setText(formatter.format(moneyChange) + " VNĐ");
        } else {
            double moneyTake = Double.valueOf(txtEnterMoney.getText());
            localMoneyTake = moneyTake;
            double moneyChange = moneyTake - totalCheck + totalCheck * discountPer;
            lblMoneyChange.setText(formatter.format(moneyChange) + " VNĐ");
        }
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
        txtEnterMoney = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btnCaculating = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        lblMoneyChange = new javax.swing.JLabel();
        btnCheck = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtDiscount = new javax.swing.JTextField();
        btnAddDiscount = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        lblFinalCash = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();

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

        jLabel14.setText("Số tiền nhận:");

        btnCaculating.setText("Tính tiền");
        btnCaculating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaculatingActionPerformed(evt);
            }
        });

        jLabel15.setText("Số tiền trả lại:");

        lblMoneyChange.setText("...............................");

        btnCheck.setText("Thanh Toán");
        btnCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckActionPerformed(evt);
            }
        });

        jLabel7.setText("Mã giảm giá:");

        btnAddDiscount.setText("Áp mã ");
        btnAddDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDiscountActionPerformed(evt);
            }
        });

        jLabel8.setText("Thực thu: ");

        lblFinalCash.setText("..................................");

        btnCancel.setText("Hủy");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDate))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(lblBan)))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(32, 32, 32)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTime)
                    .addComponent(lblCheckStt))
                .addGap(38, 38, 38))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCheck)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel12)
                                                .addGap(24, 24, 24)
                                                .addComponent(lblTotalCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel15)
                                                .addGap(18, 18, 18)
                                                .addComponent(lblMoneyChange, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(133, 133, 133))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel6))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblMaHD)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel7))
                                        .addComponent(jLabel8))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblFinalCash)
                                        .addComponent(txtDiscount, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                                        .addComponent(txtEnterMoney))
                                    .addGap(30, 30, 30)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnAddDiscount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnCaculating, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addContainerGap(16, Short.MAX_VALUE))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAddDiscount, btnCaculating, btnCancel, btnCheck});

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
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(lblDate)
                    .addComponent(lblTime))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lblTotalCheck))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddDiscount))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblFinalCash))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtEnterMoney, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCaculating))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lblMoneyChange))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCheck)
                    .addComponent(btnCancel))
                .addGap(28, 28, 28))
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCaculatingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaculatingActionPerformed
        // TODO add your handling code here:
        calMoneyChange();

    }//GEN-LAST:event_btnCaculatingActionPerformed

    private void btnCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckActionPerformed
        // TODO add your handling code here:
        try {
            localMoneyTake = Double.valueOf(txtEnterMoney.getText());
            updateMoneyTake();
            updateSttCheckBill();
            updateDiscount();
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new BillFinishFrame(LocalId).setVisible(true);
                }
            });
            changeDataToTbl();
            this.dispose();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, " Vui lòng nhập đầy đủ thông tin thanh toán !");
        }

    }//GEN-LAST:event_btnCheckActionPerformed

    private void btnAddDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDiscountActionPerformed
        // TODO add your handling code here:
        applyDiscount();
    }//GEN-LAST:event_btnAddDiscountActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddDiscount;
    private javax.swing.JButton btnCaculating;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCheck;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBan;
    private javax.swing.JLabel lblCheckStt;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblFinalCash;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblMoneyChange;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTotalCheck;
    private javax.swing.JTable tblDrinkDetail;
    private javax.swing.JTextField txtDiscount;
    private javax.swing.JTextField txtEnterMoney;
    // End of variables declaration//GEN-END:variables
}
