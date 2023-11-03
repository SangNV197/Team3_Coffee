/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.view.form;

import static com.github.sarxos.webcam.WebcamUtils.capture;
import com.view.DAO1.NhanVienDao;
import com.view.DAO1.NhanVienService;
import com.view.Helper.Ximage;
import com.view.model.CapBac;
import com.view.model.NhanVien;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Lê Chấn Khang
 */
public class QuanLyNhanVien extends javax.swing.JPanel {

    ArrayList<NhanVien> listNhanVien = new ArrayList<>();
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
    private NhanVienDao nhanVienDao = new NhanVienDao();
    private NhanVienService nhanVienService = new NhanVienService();
    //
    ArrayList<CapBac> listCapBac = new ArrayList<>();
    //
    static String url = null;
    byte[] imgBytes = new byte[5000];
    int count = -1;
    String qr;

    public QuanLyNhanVien() {
        initComponents();
        loadCbo();
        loadData();

        this.txtTen.setText(QuetMaQR.dataStatic);
        this.txtNgaySinh.setText(QuetMaQR.dataStatic2);
    }

    private void loadCbo() {

        comboBoxModel = (DefaultComboBoxModel) cboVaitro.getModel();
        comboBoxModel.removeAllElements();
        listCapBac = nhanVienDao.getCboCapbac();
        for (CapBac capBac : listCapBac) {
            comboBoxModel.addElement(capBac.getTen().toString());
        }
    }

    private void loadData() {
        defaultTableModel = (DefaultTableModel) tblForm.getModel();
        defaultTableModel.setRowCount(0);
        listNhanVien = nhanVienDao.selectALl();
        for (NhanVien nv : listNhanVien) {
            defaultTableModel.addRow(new Object[]{
                nv.getMa(),
                nv.getTen(),
                nv.getGioiTinh(),
                nv.getNgaySinh(),
                nv.getDiaChi(),
                nv.getSdt(),
                nv.getTaiKhoan(),
                nv.getMatKhau(),
                nv.getCapBac().getTen(),
                nv.getTrangThai() == 1 ? "Làm việc" : "Nghỉ việc",
                nv.getImg()
            });
        }
    }

    //Hinh
    public void convertURLToBytes() throws IOException {
        String url = lblURL.getText();
        BufferedImage bImage = ImageIO.read(new File(url.toString()));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos);
        imgBytes = bos.toByteArray();
    }
//    public void showDetail() {
//        count = tblForm.getSelectedRow();
//        txtMa.setText(listNhanVien.get(count).getMa());
//        txtTen.setText(listNhanVien.get(count).getTen());
//        rdoNam.setSelected(listNhanVien.get(count).getGioiTinh().equalsIgnoreCase("Nam"));
//        rdoNu.setSelected(listNhanVien.get(count).getGioiTinh().equalsIgnoreCase("Nữ"));
//        txtNgaySinh.setText(listNhanVien.get(count).getNgaySinh());
//        txtDiachi.setText(listNhanVien.get(count).getDiaChi());
//        txtSDT.setText(listNhanVien.get(count).getSdt());
//        txtTK.setText(listNhanVien.get(count).getTaiKhoan());
//        txtMK.setText(listNhanVien.get(count).getMatKhau());
//
//        cboVaitro.setSelectedItem(listNhanVien.get(count).getIdCB());
//        //txtTrangthai.setText(Integer.parseInt(listNhanVien.get(count).getTrangThai()));
//        //txtTrangthai.setText(listNhanVien.get(count).getTrangThai() + "");
//        if (listNhanVien.get(count).getTrangThai() == 1) {
//            rdoLamviec.setSelected(true);
//        } else {
//            rdoNghiviec.setSelected(true);
//        }
//        //rdoLamviec.setSelected(listNhanVien.get(count).getTrangThai()==1?"Nam":"Nữ");
//
//        lblAvatar.setText("");
//        ImageIcon oriImgIcon = new ImageIcon(listNhanVien.get(count).getImg());
//        Image image = oriImgIcon.getImage(); // transform it
//        Image newimg = image.getScaledInstance(140, 150, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
//        ImageIcon imageIcon = new ImageIcon(newimg);
//        lblAvatar.setIcon(imageIcon);
//
//    }

    public void save() {
        try {
            convertURLToBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String ten = txtTen.getText();
        String gioiTinh;
        if (rdoNam.isSelected()) {
            gioiTinh = "Nam";
        } else {
            gioiTinh = "Nữ";
        }
        String ma = txtMa.getText();
        String ngaySinh = txtNgaySinh.getText();
        String diaChi = txtDiachi.getText();
        String sdt = txtSDT.getText();
        String taiKhoan = txtTK.getText();
        String matKhau = txtMK.getText();
//        CapBac cb = (CapBac) cboVaitro.getSelectedItem();
//        String vaiTro = cb.getId();
        int count = cboVaitro.getSelectedIndex();
        CapBac capBac = listCapBac.get(count);
        Integer trangThai;
        if (rdoLamviec.isSelected()) {
            trangThai = 1;
        } else {
            trangThai = 0;
        }

        NhanVien nhanVien = new NhanVien(ma, ten, gioiTinh, ngaySinh, diaChi, sdt, taiKhoan, matKhau, capBac, trangThai, imgBytes);

        try {
            nhanVienDao.addNew(nhanVien);
            JOptionPane.showMessageDialog(this, "Thêm thành công !");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Thêm thất bại !");
        }
        loadData();
    }

    public void update() {
        count = tblForm.getSelectedRow();
        System.out.println(imgBytes);
        String mes = "";
        try {
            convertURLToBytes();
        } catch (Exception e) {
            e.printStackTrace();
            mes += "\n Lỗi nhập ảnh";
        }
        System.out.println(lblURL.getText());
        System.out.println(!lblURL.getText().equals(url));
        if (!lblURL.getText().equals("#URL")) {

            String ma = txtMa.getText();
            String ten = txtTen.getText();
            String gioiTinh;
            if (rdoNam.isSelected()) {
                gioiTinh = "Nam";
            } else {
                gioiTinh = "Nữ";
            }
            String ngaySinh = txtNgaySinh.getText();
            String diaChi = txtDiachi.getText();
            String sdt = txtSDT.getText();
            String taiKhoan = txtTK.getText();
            String matKhau = txtMK.getText();
//            CapBac cb = (CapBac) cboVaitro.getSelectedItem();
//            String vaiTro = cb.getId();
            Integer trangThai;
            if (rdoLamviec.isSelected()) {
                trangThai = 1;
            } else {
                trangThai = 0;
            }

            NhanVien nhanVien = new NhanVien(listNhanVien.get(count).getMa(), ten, gioiTinh, ngaySinh, diaChi, sdt, taiKhoan, matKhau, listCapBac.get(cboVaitro.getSelectedIndex()), trangThai, imgBytes);
            try {
                nhanVienDao.update(nhanVien);
                JOptionPane.showMessageDialog(this, "Cập nhật thành công !");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại !");
            }
            loadData();
        } else {
            mes += "\n Update thất bại !";
            JOptionPane.showMessageDialog(this, mes);
        }
    }

    public void save(NhanVien nhanVien) {
        try {
            nhanVienService.saveNhanVien(nhanVien);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(NhanVien nhanVien) {
        try {
            nhanVienService.updateNhanVien(nhanVien);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblForm = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTK = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMK = new javax.swing.JTextField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDiachi = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cboVaitro = new javax.swing.JComboBox<>();
        lblAvatar = new javax.swing.JLabel();
        btnChooseFile = new javax.swing.JButton();
        lblURL = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        rdoLamviec = new javax.swing.JRadioButton();
        rdoNghiviec = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        tblForm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Tên", "Giới tính", "Ngày sinh", "Địa chỉ", "Số điện thoại", "Tài khoản", "Mật khẩu", "Vai trò", "Trang thai"
            }
        ));
        tblForm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFormMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblForm);

        jLabel1.setText("Mã NV");

        txtMa.setEnabled(false);

        jLabel2.setText("Tên");

        jLabel3.setText("Tài khoản");

        jLabel4.setText("Mật khẩu");

        jLabel5.setText("Giới tính");

        rdoNam.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNam);
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        rdoNu.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        jLabel6.setText("Ngày sinh");

        jLabel7.setText("Địa chỉ");

        jLabel8.setText("Số điện thoại");

        jLabel9.setText("Vai trò");

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        jButton3.setText("Xóa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Làm mới");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel10.setText("Trạng thái");

        lblAvatar.setText("#Avartar here");
        lblAvatar.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 204, 204), null));
        lblAvatar.setMaximumSize(new java.awt.Dimension(80, 40));
        lblAvatar.setMinimumSize(new java.awt.Dimension(80, 40));

        btnChooseFile.setText("Chọn ảnh");
        btnChooseFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseFileActionPerformed(evt);
            }
        });

        lblURL.setBackground(new java.awt.Color(0, 0, 255));
        lblURL.setForeground(new java.awt.Color(0, 0, 255));
        lblURL.setText("#URL");

        jLabel11.setText("Avatar:");

        jButton1.setText("Quét mã QR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        rdoLamviec.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdoLamviec);
        rdoLamviec.setText("Làm việc");

        rdoNghiviec.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdoNghiviec);
        rdoNghiviec.setText("Nghỉ việc");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("QUẢN LÝ NHÂN VIÊN");

        jLabel13.setText("Tìm kiếm");

        jButton2.setText("Tìm kiếm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(366, 366, 366))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(478, 478, 478)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblURL, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addComponent(btnChooseFile)))
                                .addGap(46, 46, 46)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel1)
                                                .addComponent(jLabel2)
                                                .addComponent(jLabel3))
                                            .addGap(25, 25, 25)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtTen)
                                                .addComponent(txtTK)
                                                .addComponent(txtMa, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                                .addComponent(txtMK, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(btnThem)
                                            .addGap(25, 25, 25)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(25, 25, 25)
                                                .addComponent(rdoNam)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(rdoNu)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(137, 138, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel8)
                                                    .addComponent(jLabel7)
                                                    .addComponent(jLabel6))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(29, 29, 29)
                                                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtDiachi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel10)
                                                    .addComponent(jLabel9))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(rdoLamviec)
                                                    .addComponent(cboVaitro, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jButton4)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton3)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnSua)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(rdoNghiviec)
                                            .addComponent(jButton1)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jLabel11)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboVaitro, txtDiachi, txtMK, txtMa, txtNgaySinh, txtSDT, txtTK, txtTen});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnSua, btnThem, jButton1, jButton2, jButton3, jButton4});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2)
                            .addComponent(jLabel13))
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtDiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboVaitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(txtMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9)))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblURL))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(rdoNam)
                                    .addComponent(rdoNu)
                                    .addComponent(jLabel10)
                                    .addComponent(rdoLamviec)
                                    .addComponent(rdoNghiviec))))
                        .addGap(54, 54, 54))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnChooseFile)
                        .addGap(52, 52, 52)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(jButton4)
                    .addComponent(jButton3)
                    .addComponent(btnSua)
                    .addComponent(jButton1))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboVaitro, txtDiachi, txtMK, txtMa, txtNgaySinh, txtSDT, txtTK, txtTen});

    }// </editor-fold>//GEN-END:initComponents

    private void tblFormMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFormMouseClicked
        // TODO add your handling code here:

        lblURL.setText("#url");
        count = tblForm.getSelectedRow();
        if (listNhanVien.get(count).getImg() != null) {
            ImageIcon oriImgIcon = new ImageIcon(listNhanVien.get(count).getImg());
            Image image = oriImgIcon.getImage(); // transform it
            Image newimg = image.getScaledInstance(145, 140, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            ImageIcon imageIcon = new ImageIcon(newimg);
            lblAvatar.setText("");
            lblAvatar.setIcon(imageIcon);
            txtMa.setText(tblForm.getValueAt(count, 0).toString());
            txtTen.setText(tblForm.getValueAt(count, 1).toString());
            String gioiTinh = tblForm.getValueAt(count, 2).toString();
            if (gioiTinh.equalsIgnoreCase("Nam")) {
                rdoNam.setSelected(true);
            } else {
                rdoNu.setSelected(true);
            }
            txtNgaySinh.setText(tblForm.getValueAt(count, 3).toString());
            txtDiachi.setText(tblForm.getValueAt(count, 4).toString());
            txtSDT.setText(tblForm.getValueAt(count, 5).toString());
            txtTK.setText(tblForm.getValueAt(count, 6).toString());
            txtMK.setText(tblForm.getValueAt(count, 7).toString());
            cboVaitro.setSelectedItem(tblForm.getValueAt(count, 8).toString());
            String trangThai = tblForm.getValueAt(count, 9).toString();
            if (trangThai.equalsIgnoreCase("Làm việc")) {
                rdoLamviec.setSelected(true);
            } else {
                rdoNghiviec.setSelected(true);
            }
        } else {
            lblAvatar.setIcon(null);
            lblAvatar.setText("Ảnh");
            txtMa.setText(tblForm.getValueAt(count, 0).toString());
            txtTen.setText(tblForm.getValueAt(count, 1).toString());
            String gioiTinh = tblForm.getValueAt(count, 2).toString();
            if (gioiTinh.equalsIgnoreCase("Nam")) {
                rdoNam.setSelected(true);
            } else {
                rdoNu.setSelected(true);
            }
            txtNgaySinh.setText(tblForm.getValueAt(count, 3).toString());
            txtDiachi.setText(tblForm.getValueAt(count, 4).toString());
            txtSDT.setText(tblForm.getValueAt(count, 5).toString());
            txtTK.setText(tblForm.getValueAt(count, 6).toString());
            txtMK.setText(tblForm.getValueAt(count, 7).toString());
            cboVaitro.setSelectedItem(tblForm.getValueAt(count, 8).toString());
            String trangThai = tblForm.getValueAt(count, 9).toString();
            if (trangThai.equalsIgnoreCase("Làm việc")) {
                rdoLamviec.setSelected(true);
            } else {
                rdoNghiviec.setSelected(true);
            }
        }

//        int index = tblForm.getSelectedRow();
//        NhanVien nhanVien = nhanVienDao.getList().get(index);
//        String ma= tblForm.getValueAt(index, 0).toString();
//        String ten= tblForm.getValueAt(index, 1).toString();
//        String gioiTinh= tblForm.getValueAt(index, 2).toString();
//        String ngaySinh= tblForm.getValueAt(index, 3).toString();
//        String diaChi= tblForm.getValueAt(index, 4).toString();
//        String sdt= tblForm.getValueAt(index, 5).toString();
//        String taiKhoan= tblForm.getValueAt(index, 6).toString();
//        String matKhau= tblForm.getValueAt(index, 7).toString();
//        String vaiTro= tblForm.getValueAt(index, 8).toString();
//        String trangThai= tblForm.getValueAt(index, 9).toString();
//        //String img = tblForm.getValueAt(index, 10).toString();
//        
//        txtMa.setText(ma);
//        txtTen.setText(ten);
//        if(gioiTinh.equalsIgnoreCase("Nam")){
//            rdoNam.setSelected(true);
//        }else{
//            rdoNu.setSelected(true);
//        }
//       txtNgaySinh.setText(ngaySinh);
//       txtDiachi.setText(diaChi);
//       txtSDT.setText(sdt);
//       txtTK.setText(taiKhoan);
//       txtMK.setText(matKhau);
//       txtTrangthai.setText(trangThai);
//      
//        lblAvatar.setText("");
//        ImageIcon  oriImgIcon = new ImageIcon(listNhanVien.get(count).getImg());
//        Image image = oriImgIcon.getImage(); // transform it
//        Image newimg = image.getScaledInstance(79,120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
//        ImageIcon imageIcon = new ImageIcon(newimg);
//        lblAvatar.setIcon(imageIcon);
//       
//       int i=0;
//        while (true) {            
//            String name = cboVaitro.getItemAt(i).toString();
//            if(name.equalsIgnoreCase(vaiTro)){
//                cboVaitro.setSelectedIndex(i);
//            }
//            i++;
//        }
        //showDetail();

    }//GEN-LAST:event_tblFormMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        txtMa.setText("");
        txtTen.setText("");
        txtNgaySinh.setText("");
        txtDiachi.setText("");
        txtSDT.setText("");
        txtTK.setText("");
        txtMK.setText("");
        loadData();
        //txtTrangthai.setText("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:

//        try {
//            convertURLToBytes();
//        } catch (Exception e) {
//            imgBytes = new byte[5000];
//            e.printStackTrace();
//        }
//        
//        String ma = txtMa.getText();
//        String ten = txtTen.getText();
//        String gioiTinh;
//        if(rdoNam.isSelected()){
//            gioiTinh="Nam";
//        }else{
//            gioiTinh="Nữ";
//        }
//        String ngaySinh = txtNgaySinh.getText();
//        String diaChi = txtDiachi.getText();
//        String sdt = txtSDT.getText();
//        String taiKhoan = txtTK.getText();
//        String matKhau = txtMK.getText();
//        int count = cboVaitro.getSelectedIndex();
//        CapBac capBac = listCapBac.get(count);
//        Integer trangThai;
//        if(rdoLamviec.isSelected()){
//            trangThai=1;
//        }else{
//            trangThai=0;
//        }
//        
//        NhanVien nhanVien = new NhanVien("", ma, ten, gioiTinh, ngaySinh, diaChi, sdt, taiKhoan, matKhau, capBac, trangThai, imgBytes);
////        save(nhanVien);
////        loadData();
////        JOptionPane.showMessageDialog(this, "Sửa thành công !");
//        JOptionPane.showMessageDialog(this, nhanVienService.them(nhanVien));
//        loadData();
        if (txtTen.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên");
            return;
        }
        if (txtTK.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tài khoản");
            return;
        }
        if (txtMK.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập mật khẩu");
            return;
        }
        if (txtNgaySinh.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập ngày sinh");
            return;
        }
        if (txtDiachi.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập địa chỉ");
            return;
        }
        if (txtSDT.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập số điện thoại");
            return;
        }
        save();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnChooseFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseFileActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChooseFileFrame(url, lblURL).setVisible(true);
            }
        });
    }//GEN-LAST:event_btnChooseFileActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không");
        if (hoi != JOptionPane.YES_OPTION) {
            return;
        }

        String ma = txtMa.getText();
        JOptionPane.showMessageDialog(this, nhanVienService.xoaNhanVien(ma));
        loadData();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                QuetMaQR maQR = new QuetMaQR(txtTen, txtNgaySinh, txtDiachi, rdoNam, rdoNu);
                maQR.setVisible(true);
            }
        });


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DefaultTableModel ob = (DefaultTableModel) tblForm.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        tblForm.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(txtSearch.getText()));
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChooseFile;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cboVaitro;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAvatar;
    private javax.swing.JLabel lblURL;
    private javax.swing.JRadioButton rdoLamviec;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNghiviec;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblForm;
    private javax.swing.JTextField txtDiachi;
    private javax.swing.JTextField txtMK;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTK;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables

}
