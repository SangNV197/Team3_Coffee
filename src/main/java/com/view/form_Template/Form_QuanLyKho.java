/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.view.form_Template;
import java.math.BigDecimal;
import model.NhanVien;
import service.INhapKhoService;
import service.NhapKhoService;
import viewModel.QLNhapKho;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author MSI-G8
 */
public class Form_QuanLyKho extends javax.swing.JPanel {

    private INhapKhoService nhapkhoService = new NhapKhoService();
    List<QLNhapKho> ds = new ArrayList<>();
    
    /**
     * Creates new form Form_QuanLyKho
     */
    public Form_QuanLyKho() {
        initComponents();
        
        loadCBB();
        loadTable();
    }

    public void loadTable() {
        DefaultTableModel dtm = (DefaultTableModel) tblTable.getModel();
        dtm.setRowCount(0);
//        DecimalFormat decimalFormat = new DecimalFormat("#,### VND");
        SimpleDateFormat spd = new SimpleDateFormat("dd/MM/yyyy");
        ds = nhapkhoService.getALL();
        for (QLNhapKho nk : ds) {

//           String donGiaFormat = decimalFormat.format(nk.getDonGia());
            Object[] rowData = {nk.getId(), nk.getNhanVien().getId(), nk.getTenSP(),
                spd.format(nk.getNgayNhap()), nk.getDonVi(), nk.getSoLuong(), nk.getDonGia()};
            dtm.addRow(rowData);
        }
    }

    public void loadCBB() {
        List<NhanVien> ds = nhapkhoService.getCBBNV();
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbtenNV.getModel();
        model.removeAllElements();
        for (NhanVien nv : ds) {
            model.addElement(nv.getHoTen());
        }
    }

    private QLNhapKho getFrom() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String tenSP = txtTenSP.getText();
        String nNhapp = txtnNhap.getText();
        String dVi = txtdVi.getText();
        String sLuong = txtsoLuong.getText();
        String dGiaa = txtdGia.getText();

        if (tenSP.trim().equals("") || nNhapp.trim().equals("") || dVi.trim().equals("")
                || sLuong.trim().equals("") || dGiaa.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được để trống");
            return null;
        }

        if (tenSP.trim().length() != tenSP.length()) {
        JOptionPane.showMessageDialog(this, "Tên không hợp lệ");
        return null;
         }
         
        if(!tenSP.matches("^[\\p{L}\\p{M}0-9 ]+$")){
            JOptionPane.showMessageDialog(this, " Tên không hợp lệ");
            return null;
        }
        
        try {
            int soLuong = Integer.parseInt(sLuong);
            BigDecimal dGia = new BigDecimal(dGiaa);
            DecimalFormat dfDonGia = new DecimalFormat("#,##0.0");
            String donGiaFormat = dfDonGia.format(dGia);
            
            Double donGioDouble = dfDonGia.parse(donGiaFormat).doubleValue();
            BigDecimal dGiaFomat = BigDecimal.valueOf(donGioDouble);
            
            
            Date nNhap = new Date(sdf.parse(nNhapp).getTime());
            int cout = cbbtenNV.getSelectedIndex();
            NhanVien nv = nhapkhoService.getCBBNV().get(cout);

            QLNhapKho nk = new QLNhapKho();
            nk.setNhanVien(nv);
            nk.setTenSP(tenSP);
            nk.setNgayNhap(nNhap);
            nk.setDonVi(dVi);
            nk.setSoLuong(soLuong);
            nk.setDonGia(dGiaFomat);
            return nk;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ");
            return null;
        }

       
    }
    

    public void moueclick() {
        int row = tblTable.getSelectedRow();
        NhanVien nv = nhapkhoService.timTheoID(tblTable.getValueAt(row, 1).toString());
//        DecimalFormat decimalFormat = new DecimalFormat("#,### VND");
//        String dGia = decimalFormat.format(nk.getDonGia());

        txtID.setText(tblTable.getValueAt(row, 0).toString());
        cbbtenNV.setSelectedItem(nv.getHoTen());
        txtTenSP.setText(tblTable.getValueAt(row, 2).toString());
        txtnNhap.setText(tblTable.getValueAt(row, 3).toString());
        txtdVi.setText(tblTable.getValueAt(row, 4).toString());
        txtsoLuong.setText(tblTable.getValueAt(row, 5).toString());
        txtdGia.setText(tblTable.getValueAt(row, 6).toString());
    
    }

    public void clear() {
        cbbtenNV.setSelectedIndex(0);
        txtTenSP.setText("");
        txtnNhap.setText("");
        txtdVi.setText("");
        txtsoLuong.setText("");
        txtdGia.setText("");
    }

    private void updateTimKiem(List<QLNhapKho> ketQua) {
        DefaultTableModel dtm = (DefaultTableModel) tblTable.getModel();
        dtm.setRowCount(0); // Xóa các dòng hiện tại
        SimpleDateFormat spd = new SimpleDateFormat("dd/MM/yyyy");
        for (QLNhapKho nk : ketQua) {
            Object[] row = {nk.getId(), nk.getNhanVien().getId(), nk.getTenSP(), spd.format(nk.getNgayNhap()),
                nk.getDonVi(), nk.getSoLuong(), nk.getDonGia()
            };
            dtm.addRow(row);
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

        jPanel2 = new javax.swing.JPanel();
        jPanel437 = new javax.swing.JPanel();
        jLabel559 = new javax.swing.JLabel();
        jScrollPane30 = new javax.swing.JScrollPane();
        tblTable = new javax.swing.JTable();
        jPanel438 = new javax.swing.JPanel();
        txtSsearchKey = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel439 = new javax.swing.JPanel();
        jPanel440 = new javax.swing.JPanel();
        jLabel560 = new javax.swing.JLabel();
        txtGiaMin = new javax.swing.JTextField();
        txtGiaMax = new javax.swing.JTextField();
        jLabel561 = new javax.swing.JLabel();
        jLabel562 = new javax.swing.JLabel();
        jPanel441 = new javax.swing.JPanel();
        jPanel442 = new javax.swing.JPanel();
        jLabel563 = new javax.swing.JLabel();
        jTextField187 = new javax.swing.JTextField();
        jTextField188 = new javax.swing.JTextField();
        jLabel564 = new javax.swing.JLabel();
        jLabel565 = new javax.swing.JLabel();
        jPanel443 = new javax.swing.JPanel();
        jPanel444 = new javax.swing.JPanel();
        jLabel566 = new javax.swing.JLabel();
        jTextField189 = new javax.swing.JTextField();
        jTextField190 = new javax.swing.JTextField();
        jLabel567 = new javax.swing.JLabel();
        jLabel568 = new javax.swing.JLabel();
        jPanel445 = new javax.swing.JPanel();
        jPanel446 = new javax.swing.JPanel();
        jLabel569 = new javax.swing.JLabel();
        jTextField191 = new javax.swing.JTextField();
        jTextField192 = new javax.swing.JTextField();
        jLabel570 = new javax.swing.JLabel();
        jLabel571 = new javax.swing.JLabel();
        jPanel449 = new javax.swing.JPanel();
        jPanel450 = new javax.swing.JPanel();
        jLabel575 = new javax.swing.JLabel();
        txtSoLuongMin = new javax.swing.JTextField();
        txtSoLuongMax = new javax.swing.JTextField();
        jLabel576 = new javax.swing.JLabel();
        jLabel577 = new javax.swing.JLabel();
        jPanel447 = new javax.swing.JPanel();
        jPanel448 = new javax.swing.JPanel();
        jLabel572 = new javax.swing.JLabel();
        txtNgayBatDau = new javax.swing.JTextField();
        txtNgayKetThuc = new javax.swing.JTextField();
        jLabel573 = new javax.swing.JLabel();
        jLabel574 = new javax.swing.JLabel();
        btnTKiem = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtdGia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtsoLuong = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        txtnNhap = new javax.swing.JTextField();
        cbbtenNV = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtdVi = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        btnNhapKho = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel437.setBackground(new java.awt.Color(255, 255, 255));
        jPanel437.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel437.setForeground(new java.awt.Color(255, 255, 255));
        jPanel437.setMaximumSize(new java.awt.Dimension(1014, 600));
        jPanel437.setMinimumSize(new java.awt.Dimension(1014, 600));
        jPanel437.setPreferredSize(new java.awt.Dimension(1014, 600));
        jPanel437.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel559.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel559.setForeground(new java.awt.Color(255, 0, 0));
        jLabel559.setText("KHO NGUYÊN LIỆU");
        jPanel437.add(jLabel559, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 173, 26));

        tblTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ID Nhân Viên", "Tên Sản Phẩm", "Ngày Nhập", "Đơn vị", "Số Lượng", "Đơn Giá"
            }
        ));
        tblTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTableMouseClicked(evt);
            }
        });
        jScrollPane30.setViewportView(tblTable);

        jPanel437.add(jScrollPane30, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 320, 670, 260));

        jPanel438.setBackground(new java.awt.Color(255, 255, 255));
        jPanel438.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtSsearchKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSsearchKeyActionPerformed(evt);
            }
        });
        jPanel438.add(txtSsearchKey, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 260, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Tìm kiếm");
        jPanel438.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, -1));

        jPanel439.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel439.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel440.setBackground(new java.awt.Color(51, 153, 255));
        jPanel440.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel560.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel560.setForeground(new java.awt.Color(255, 255, 255));
        jLabel560.setText("Đơn giá");
        jPanel440.add(jLabel560, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 110, 20));

        jPanel439.add(jPanel440, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, 6, 260, 30));

        txtGiaMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaMinActionPerformed(evt);
            }
        });
        jPanel439.add(txtGiaMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 170, -1));
        jPanel439.add(txtGiaMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 170, -1));

        jLabel561.setText("Từ:");
        jPanel439.add(jLabel561, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 30));

        jLabel562.setText("Đến:");
        jPanel439.add(jLabel562, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jPanel441.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel442.setBackground(new java.awt.Color(51, 153, 255));
        jPanel442.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel563.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel563.setForeground(new java.awt.Color(255, 255, 255));
        jLabel563.setText("Số lượng");
        jPanel442.add(jLabel563, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 110, 20));

        jPanel441.add(jPanel442, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, 6, 260, 30));

        jTextField187.setText("jTextField1");
        jTextField187.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField187ActionPerformed(evt);
            }
        });
        jPanel441.add(jTextField187, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 170, -1));

        jTextField188.setText("jTextField2");
        jPanel441.add(jTextField188, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 170, -1));

        jLabel564.setText("Từ");
        jPanel441.add(jLabel564, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 56, -1, 30));

        jLabel565.setText("Từ");
        jPanel441.add(jLabel565, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jPanel439.add(jPanel441, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 260, 200));

        jPanel443.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel444.setBackground(new java.awt.Color(51, 153, 255));
        jPanel444.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel566.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel566.setForeground(new java.awt.Color(255, 255, 255));
        jLabel566.setText("Số lượng");
        jPanel444.add(jLabel566, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 110, 20));

        jPanel443.add(jPanel444, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, 6, 260, 30));

        jTextField189.setText("jTextField1");
        jTextField189.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField189ActionPerformed(evt);
            }
        });
        jPanel443.add(jTextField189, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 170, -1));

        jTextField190.setText("jTextField2");
        jPanel443.add(jTextField190, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 170, -1));

        jLabel567.setText("Từ");
        jPanel443.add(jLabel567, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 56, -1, 30));

        jLabel568.setText("Từ");
        jPanel443.add(jLabel568, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jPanel445.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel446.setBackground(new java.awt.Color(51, 153, 255));
        jPanel446.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel569.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel569.setForeground(new java.awt.Color(255, 255, 255));
        jLabel569.setText("Số lượng");
        jPanel446.add(jLabel569, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 110, 20));

        jPanel445.add(jPanel446, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, 6, 260, 30));

        jTextField191.setText("jTextField1");
        jTextField191.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField191ActionPerformed(evt);
            }
        });
        jPanel445.add(jTextField191, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 170, -1));

        jTextField192.setText("jTextField2");
        jPanel445.add(jTextField192, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 170, -1));

        jLabel570.setText("Từ");
        jPanel445.add(jLabel570, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 56, -1, 30));

        jLabel571.setText("Từ");
        jPanel445.add(jLabel571, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jPanel443.add(jPanel445, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 260, 200));

        jPanel439.add(jPanel443, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 260, 200));

        jPanel438.add(jPanel439, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, -1, 130));

        jPanel449.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel449.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel450.setBackground(new java.awt.Color(51, 153, 255));
        jPanel450.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel575.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel575.setForeground(new java.awt.Color(255, 255, 255));
        jLabel575.setText("Số lượng");
        jPanel450.add(jLabel575, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 110, 20));

        jPanel449.add(jPanel450, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, 6, 260, 30));

        txtSoLuongMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongMinActionPerformed(evt);
            }
        });
        jPanel449.add(txtSoLuongMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 170, -1));
        jPanel449.add(txtSoLuongMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 170, -1));

        jLabel576.setText("Từ:");
        jPanel449.add(jLabel576, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 30));

        jLabel577.setText("Đến:");
        jPanel449.add(jLabel577, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jPanel438.add(jPanel449, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 260, 130));

        jPanel447.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel447.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel448.setBackground(new java.awt.Color(51, 153, 255));
        jPanel448.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel572.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel572.setForeground(new java.awt.Color(255, 255, 255));
        jLabel572.setText("Ngày nhập");
        jPanel448.add(jLabel572, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 110, 20));

        jPanel447.add(jPanel448, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, 6, 260, 30));

        txtNgayBatDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayBatDauActionPerformed(evt);
            }
        });
        jPanel447.add(txtNgayBatDau, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 170, -1));
        jPanel447.add(txtNgayKetThuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 170, -1));

        jLabel573.setText("Từ:");
        jPanel447.add(jLabel573, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel574.setText("Đến:");
        jPanel447.add(jLabel574, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jPanel438.add(jPanel447, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 260, 120));

        btnTKiem.setText("Tìm Kiếm");
        btnTKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKiemActionPerformed(evt);
            }
        });
        jPanel438.add(btnTKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 510, 101, -1));

        jPanel437.add(jPanel438, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 260, 540));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel4.add(txtdGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, 180, -1));

        jLabel4.setText("Ngày Nhập: ");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 129, 79, -1));
        jPanel4.add(txtsoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, 180, -1));

        jLabel7.setText("Đơn Giá: ");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, 56, -1));
        jPanel4.add(txtTenSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 86, 191, -1));
        jPanel4.add(txtnNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 126, 191, -1));

        cbbtenNV.setToolTipText("");
        jPanel4.add(cbbtenNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 46, 191, -1));

        jLabel6.setText("Số Lượng: ");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, -1, -1));
        jPanel4.add(txtdVi, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, 180, -1));

        jLabel2.setText("Tên Nhân Viên:");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 49, -1, -1));

        jLabel3.setText("Tên Sản Phẩm:");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 89, 90, -1));

        jLabel5.setText("Đơn Vị: ");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 58, -1));

        jLabel8.setText("ID -");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 9, -1, -1));

        txtID.setEditable(false);
        txtID.setEnabled(false);
        jPanel4.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 6, 280, -1));

        jPanel437.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 590, -1));

        btnClear.setText("Làm mới");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        jPanel437.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 280, 90, -1));

        btnNhapKho.setText("Nhập Kho");
        btnNhapKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapKhoActionPerformed(evt);
            }
        });
        jPanel437.add(btnNhapKho, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 280, -1, -1));

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel437.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 280, 90, -1));

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel437.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 280, 90, -1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jPanel437, javax.swing.GroupLayout.PREFERRED_SIZE, 997, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jPanel437, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTableMouseClicked
        moueclick();
    }//GEN-LAST:event_tblTableMouseClicked

    private void btnTKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKiemActionPerformed
        String tenSp = txtSsearchKey.getText().trim().equals("") ? "%" : txtSsearchKey.getText();
        SimpleDateFormat spd = new SimpleDateFormat("dd/MM/yyyy");
        Date ngayBatDau = null;
        Date ngayKetThuc = null;
        try {
            ngayBatDau = new Date(spd.parse(txtNgayBatDau.getText()).getTime());
        } catch (Exception e) {
        }
        try {
            ngayKetThuc = new Date(spd.parse(txtNgayKetThuc.getText()).getTime());
        } catch (Exception e) {
        }
        int soLuongMin = 0;
        try {
            soLuongMin = Integer.parseInt(txtSoLuongMin.getText());
        } catch (Exception e) {
        }
        int soLuongMax = 99999999;
        try {
            soLuongMax = Integer.parseInt(txtSoLuongMax.getText());
        } catch (Exception e) {
        }
        int giaMax = 99999999;
        try {
            giaMax = Integer.parseInt(txtGiaMax.getText());
        } catch (Exception e) {
        }
        int giaMin = 0;
        try {
            giaMin = Integer.parseInt(txtGiaMin.getText());
        } catch (Exception e) {
        }
        updateTimKiem(nhapkhoService.timTheoTungTruong(tenSp, ngayBatDau, ngayKetThuc, soLuongMin, soLuongMax, giaMin, giaMax));
    }//GEN-LAST:event_btnTKiemActionPerformed

    private void txtSsearchKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSsearchKeyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSsearchKeyActionPerformed

    private void txtGiaMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaMinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaMinActionPerformed

    private void jTextField187ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField187ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField187ActionPerformed

    private void jTextField189ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField189ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField189ActionPerformed

    private void jTextField191ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField191ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField191ActionPerformed

    private void txtSoLuongMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongMinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongMinActionPerformed

    private void btnNhapKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapKhoActionPerformed
        try {
            QLNhapKho nk = getFrom();
            if (nk != null) {
                JOptionPane.showMessageDialog(this, "Nhập thành công");
                nhapkhoService.them(nk);
                loadTable();
                clear();
            } else {
                JOptionPane.showMessageDialog(this, "Nhập không thành công");
            }

        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnNhapKhoActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        try {
            QLNhapKho nv = getFrom();
            int row = tblTable.getSelectedRow();
            String id = tblTable.getValueAt(row, 0).toString();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Chọn 1 dòng để xóa");
                return;
            }
            if (nv != null) {
                JOptionPane.showMessageDialog(this, "Sửa thành công");
                nhapkhoService.sua(id, nv);
                loadTable();
                clear();
            } else {
                JOptionPane.showMessageDialog(this, "Sửa không thành công");
            }

        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int row = tblTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn 1 dòng để xóa");
            return;
        }
        String id = tblTable.getValueAt(row, 0).toString();
        System.out.println(id);
        boolean ketQua = nhapkhoService.xoa(id);
        if (ketQua == true) {
            JOptionPane.showMessageDialog(this, "Xóa ko thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thành công");
            loadTable();
            clear();
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void txtNgayBatDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayBatDauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayBatDauActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnNhapKho;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbtenNV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel559;
    private javax.swing.JLabel jLabel560;
    private javax.swing.JLabel jLabel561;
    private javax.swing.JLabel jLabel562;
    private javax.swing.JLabel jLabel563;
    private javax.swing.JLabel jLabel564;
    private javax.swing.JLabel jLabel565;
    private javax.swing.JLabel jLabel566;
    private javax.swing.JLabel jLabel567;
    private javax.swing.JLabel jLabel568;
    private javax.swing.JLabel jLabel569;
    private javax.swing.JLabel jLabel570;
    private javax.swing.JLabel jLabel571;
    private javax.swing.JLabel jLabel572;
    private javax.swing.JLabel jLabel573;
    private javax.swing.JLabel jLabel574;
    private javax.swing.JLabel jLabel575;
    private javax.swing.JLabel jLabel576;
    private javax.swing.JLabel jLabel577;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel437;
    private javax.swing.JPanel jPanel438;
    private javax.swing.JPanel jPanel439;
    private javax.swing.JPanel jPanel440;
    private javax.swing.JPanel jPanel441;
    private javax.swing.JPanel jPanel442;
    private javax.swing.JPanel jPanel443;
    private javax.swing.JPanel jPanel444;
    private javax.swing.JPanel jPanel445;
    private javax.swing.JPanel jPanel446;
    private javax.swing.JPanel jPanel447;
    private javax.swing.JPanel jPanel448;
    private javax.swing.JPanel jPanel449;
    private javax.swing.JPanel jPanel450;
    private javax.swing.JScrollPane jScrollPane30;
    private javax.swing.JTextField jTextField187;
    private javax.swing.JTextField jTextField188;
    private javax.swing.JTextField jTextField189;
    private javax.swing.JTextField jTextField190;
    private javax.swing.JTextField jTextField191;
    private javax.swing.JTextField jTextField192;
    private javax.swing.JTable tblTable;
    private javax.swing.JTextField txtGiaMax;
    private javax.swing.JTextField txtGiaMin;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNgayBatDau;
    private javax.swing.JTextField txtNgayKetThuc;
    private javax.swing.JTextField txtSoLuongMax;
    private javax.swing.JTextField txtSoLuongMin;
    private javax.swing.JTextField txtSsearchKey;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtdGia;
    private javax.swing.JTextField txtdVi;
    private javax.swing.JTextField txtnNhap;
    private javax.swing.JTextField txtsoLuong;
    // End of variables declaration//GEN-END:variables
}
