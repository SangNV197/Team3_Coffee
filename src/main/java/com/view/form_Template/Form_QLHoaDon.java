/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.view.form_Template;

import DoUong_HoaDon_ThongKe_Model.HoaDonChiTiet;
import DoUong_HoaDon_ThongKe_Model.LichSuHoaDon;
import DoUong_HoaDon_ThongKe_Service.LichSuHoaDonService;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Sang
 */
public class Form_QLHoaDon extends javax.swing.JPanel {

    private LichSuHoaDonService lichSuHoaDonService = new LichSuHoaDonService();
    private DefaultTableModel model = new DefaultTableModel();
    ArrayList<LichSuHoaDon> lstLichSuHoaDon = new ArrayList<>();
    ArrayList<HoaDonChiTiet> lstHoaDonChiTiet = new ArrayList<>();
    int index;

    public Form_QLHoaDon() {
        initComponents();

        loadData();

    }

    // Load dữ liệu lên table
    public void loadData() {
        try {
            model = (DefaultTableModel) tblLichSuHoaDon.getModel();
            model.setRowCount(0);
            lstLichSuHoaDon = lichSuHoaDonService.getAll();
            for (LichSuHoaDon lichSuHoaDon : lstLichSuHoaDon) {
                model.addRow(new Object[]{
                    lichSuHoaDon.getMaHoaDon(),
                    lichSuHoaDon.getTenNhanVien(),
                    lichSuHoaDon.getTimeTao(),
                    lichSuHoaDon.getTimeThanhToan(),
                    lichSuHoaDon.getSoLuong(),
                    lichSuHoaDon.getTongTienHoaDon() + " đ",
                    lichSuHoaDon.getChietKhau() + " %",
                    lichSuHoaDon.tienThucNhan() + " đ",
                    lichSuHoaDon.hienThiTrangThai()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Load dữ liệu theo thời gian truyền vào
    public void loadDataTheoTime(Date d1, Date d2) {
        try {
            model = (DefaultTableModel) tblLichSuHoaDon.getModel();
            model.setRowCount(0);
            lstLichSuHoaDon = lichSuHoaDonService.getByTime(d1, d2);
            for (LichSuHoaDon lichSuHoaDon : lstLichSuHoaDon) {
                model.addRow(new Object[]{
                    lichSuHoaDon.getMaHoaDon(),
                    lichSuHoaDon.getTenNhanVien(),
                    lichSuHoaDon.getTimeTao(),
                    lichSuHoaDon.getTimeThanhToan(),
                    lichSuHoaDon.getSoLuong(),
                    lichSuHoaDon.getTongTienHoaDon() + " đ",
                    lichSuHoaDon.getChietKhau() + " %",
                    lichSuHoaDon.tienThucNhan() + " đ",
                    lichSuHoaDon.hienThiTrangThai()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Load dữ liệu theo mã để tìm kiếm theo mã
    public void loadDataTheoMa(String maHoaDon) {
        try {
            model = (DefaultTableModel) tblLichSuHoaDon.getModel();
            model.setRowCount(0);
            lstLichSuHoaDon = lichSuHoaDonService.getByMa(maHoaDon);
            for (LichSuHoaDon lichSuHoaDon : lstLichSuHoaDon) {
                model.addRow(new Object[]{
                    lichSuHoaDon.getMaHoaDon(),
                    lichSuHoaDon.getTenNhanVien(),
                    lichSuHoaDon.getTimeTao(),
                    lichSuHoaDon.getTimeThanhToan(),
                    lichSuHoaDon.getSoLuong(),
                    lichSuHoaDon.getTongTienHoaDon() + " đ",
                    lichSuHoaDon.getChietKhau() + " %",
                    lichSuHoaDon.tienThucNhan() + " đ",
                    lichSuHoaDon.hienThiTrangThai()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Load dữ liệu theo mã để tìm ra hóa đơn chi tiết của mã đó của mã đó
    public void loadDataHoaDon(String maHoaDon) {
        try {
            model = (DefaultTableModel) tblHoaDonChiTiet.getModel();
            model.setRowCount(0);
            lstHoaDonChiTiet = lichSuHoaDonService.loadDataByMa(maHoaDon);
            int i = 1;
            for (HoaDonChiTiet hoaDonChiTiet : lstHoaDonChiTiet) {
                model.addRow(new Object[]{
                    i++,
                    hoaDonChiTiet.getTenSanPham(),
                    hoaDonChiTiet.getSoLuong(),
                    hoaDonChiTiet.getThanhTien()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mouseClick() {
        index = tblLichSuHoaDon.getSelectedRow();
        lblMaHD.setText(tblLichSuHoaDon.getValueAt(index, 0).toString());
        lblTenNV.setText(tblLichSuHoaDon.getValueAt(index, 1).toString());
        lblTGTao.setText(tblLichSuHoaDon.getValueAt(index, 2).toString());
        lblTGThanhToan.setText(tblLichSuHoaDon.getValueAt(index, 3).toString());
        lblSoLuongSP.setText(tblLichSuHoaDon.getValueAt(index, 4).toString());
        lblTongTienHoaDon.setText(tblLichSuHoaDon.getValueAt(index, 5).toString());
        lblChietKhau.setText(tblLichSuHoaDon.getValueAt(index, 6).toString());
        lblThucNhan.setText(tblLichSuHoaDon.getValueAt(index, 7).toString());
        lblTrangThai.setText(tblLichSuHoaDon.getValueAt(index, 8).toString());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnTimKiemTheoNgay = new javax.swing.JButton();
        dateNgayBatDau = new com.toedter.calendar.JDateChooser();
        dateNgayKetThuc = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtTimTheoMa = new javax.swing.JTextField();
        btnLoad = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLichSuHoaDon = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblHoaDonChiTiet = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblMaHD = new javax.swing.JLabel();
        lblTenNV = new javax.swing.JLabel();
        lblTGTao = new javax.swing.JLabel();
        lblTGThanhToan = new javax.swing.JLabel();
        lblTongTienHoaDon = new javax.swing.JLabel();
        lblThucNhan = new javax.swing.JLabel();
        lblChietKhau = new javax.swing.JLabel();
        lblSoLuongSP = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        lblTrangThai = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        jTextField2.setText("jTextField2");

        jScrollPane3.setViewportView(jTextPane1);

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Từ ngày:");

        jLabel3.setText("Đến ngày:");

        btnTimKiemTheoNgay.setText("Tìm kiếm");
        btnTimKiemTheoNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemTheoNgayActionPerformed(evt);
            }
        });

        dateNgayBatDau.setDateFormatString("dd-MM-yyyy");
        dateNgayBatDau.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        dateNgayKetThuc.setDateFormatString("dd-MM-yyyy");
        dateNgayKetThuc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel4.setText("Tìm theo mã hóa đơn:");

        txtTimTheoMa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimTheoMaKeyReleased(evt);
            }
        });

        btnLoad.setText("Load Data");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(dateNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(dateNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btnTimKiemTheoNgay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(txtTimTheoMa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(422, 422, 422)
                .addComponent(btnLoad)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(btnTimKiemTheoNgay))
                        .addComponent(dateNgayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dateNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtTimTheoMa, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btnLoad)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lịch sử", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblLichSuHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Tên nhân viên", "TG tạo", "TG thanh toán", "Tổng số lượng", "Tiền hóa đơn", "Khuyến mại", "Thực nhận", "Trạng thái thanh toán"
            }
        ));
        tblLichSuHoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblLichSuHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLichSuHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblLichSuHoaDon);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Tên sản phẩm", "Số lượng", "Thành tiền"
            }
        ));
        jScrollPane5.setViewportView(tblHoaDonChiTiet);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("HÓA ĐƠN CHI TIẾT");

        jLabel15.setForeground(new java.awt.Color(51, 51, 255));
        jLabel15.setText("Mã hóa đơn:");

        jLabel16.setForeground(new java.awt.Color(51, 51, 255));
        jLabel16.setText("TG tạo:");

        jLabel17.setForeground(new java.awt.Color(51, 51, 255));
        jLabel17.setText("TG thanh toán:");

        jLabel18.setForeground(new java.awt.Color(51, 51, 255));
        jLabel18.setText("Tổng số lượng:");

        jLabel19.setForeground(new java.awt.Color(51, 51, 255));
        jLabel19.setText("Thực nhận:");

        jLabel20.setForeground(new java.awt.Color(51, 51, 255));
        jLabel20.setText("Khuyến mại:");

        jLabel21.setForeground(new java.awt.Color(51, 51, 255));
        jLabel21.setText("Tên nhân viên:");

        jLabel22.setForeground(new java.awt.Color(51, 51, 255));
        jLabel22.setText("Tổng tiền hóa đơn:");

        lblMaHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMaHD.setText("...");

        lblTenNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTenNV.setText("...");

        lblTGTao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTGTao.setText("...");

        lblTGThanhToan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTGThanhToan.setText("...");

        lblTongTienHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTongTienHoaDon.setText("...");

        lblThucNhan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblThucNhan.setForeground(new java.awt.Color(255, 51, 102));
        lblThucNhan.setText("...");

        lblChietKhau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblChietKhau.setText("...");

        lblSoLuongSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSoLuongSP.setText("...");

        jLabel32.setForeground(new java.awt.Color(51, 51, 255));
        jLabel32.setText("Trạng thái");

        lblTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTrangThai.setText("...");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(jLabel19)
                                .addGap(46, 46, 46)
                                .addComponent(lblThucNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel21))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblTGTao, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                                    .addComponent(lblMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTGThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel22)
                                            .addComponent(jLabel20)
                                            .addComponent(jLabel32))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblSoLuongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblTongTienHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblChietKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel18))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSoLuongSP)
                            .addComponent(jLabel15)
                            .addComponent(lblMaHD)
                            .addComponent(jLabel18))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTongTienHoaDon)
                            .addComponent(lblTenNV)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(lblTGTao)
                            .addComponent(jLabel20)
                            .addComponent(lblChietKhau))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(lblTGThanhToan)
                            .addComponent(jLabel32)
                            .addComponent(lblTrangThai))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(lblThucNhan)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblLichSuHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLichSuHoaDonMouseClicked
        // TODO add your handling code here:

//        if (evt.getClickCount() >= 1) {
        mouseClick();
        String maHoaDon = lblMaHD.getText();
        loadDataHoaDon(maHoaDon);
//        }

    }//GEN-LAST:event_tblLichSuHoaDonMouseClicked

    private void btnTimKiemTheoNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemTheoNgayActionPerformed
//        String d1 = ((JTextField) dateNgayBatDau.getDateEditor().getUiComponent()).getText();
//        String d2 = ((JTextField) dateNgayKetThuc.getDateEditor().getUiComponent()).getText();
        Date d1 = new Date(dateNgayBatDau.getDate().getTime());
        Date d2 = new Date(dateNgayKetThuc.getDate().getTime());
        loadDataTheoTime(d1, d2);
    }//GEN-LAST:event_btnTimKiemTheoNgayActionPerformed

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        // TODO add your handling code here:
        loadData();
        lblMaHD.setText("...");
        lblTenNV.setText("...");
        lblTGTao.setText("...");
        lblTGThanhToan.setText("...");;
        lblSoLuongSP.setText("...");;
        lblTongTienHoaDon.setText("...");;
        lblChietKhau.setText("...");
        lblThucNhan.setText("...");;
        lblTrangThai.setText("...");
    }//GEN-LAST:event_btnLoadActionPerformed

    private void txtTimTheoMaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimTheoMaKeyReleased
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblLichSuHoaDon.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tblLichSuHoaDon.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(txtTimTheoMa.getText()));
    }//GEN-LAST:event_txtTimTheoMaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnTimKiemTheoNgay;
    private com.toedter.calendar.JDateChooser dateNgayBatDau;
    private com.toedter.calendar.JDateChooser dateNgayKetThuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel lblChietKhau;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblSoLuongSP;
    private javax.swing.JLabel lblTGTao;
    private javax.swing.JLabel lblTGThanhToan;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JLabel lblThucNhan;
    private javax.swing.JLabel lblTongTienHoaDon;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JTable tblHoaDonChiTiet;
    private javax.swing.JTable tblLichSuHoaDon;
    private javax.swing.JTextField txtTimTheoMa;
    // End of variables declaration//GEN-END:variables

}
