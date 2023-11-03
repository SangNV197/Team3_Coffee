/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.view.form_Template;

//import com.view.DAO1.NhanVienService;
import java.awt.Image;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.CapBac;
import model.ChamCong;
import model.NhanVien;
import service.ChamCongService;
import service.NhanVienService;

/**
 *
 * @author LENOVO T560
 */
public class Form_ChamCong extends javax.swing.JPanel implements Runnable {

    // Ngày giờ hiện tại
    Date now = new Date();
    NhanVienService nhanVineService = new NhanVienService();
    ChamCongService chamCongService = new ChamCongService();
//    CapBac capBacService = new 

    public Form_ChamCong() {
        initComponents();
        setForm();
        Thread t = new Thread(this);
        t.start();
    }

    public void run() {

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            lblDongHo.setText(getTime());
        }
    }

    public String getTime() {
        Date now = new Date();
        String pattern = "HH:mm:ss a dd-MM-yyyy ";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(now);

    }

    private void setForm() {
        // Lấy ngày và thời gian hiện tại
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Tháng trong Java được đếm từ 0 (0 - 11)
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        String ngay = String.format("%02d-%02d-%04d", day, month, year);
        String gioVao = String.format("%02d:%02d:%02d", hour, minute, second);

        txtNgay.setText(ngay);

    }

    private void setTimeGioRa() {
        // Lấy ngày và thời gian hiện tại
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        String gioRa = String.format("%02d:%02d:%02d", hour, minute, second);
        txtGioRa.setText(gioRa);
    }
    
     private void setTimeGioVao() {
        // Lấy ngày và thời gian hiện tại
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

         String gioVao = String.format("%02d:%02d:%02d", hour, minute, second);
        txtGioVao.setText(gioVao);
    }

    private void chamCongGioVao() {
        if (validateData()) {
            setTimeGioVao();
            NhanVien nv = nhanVineService.selectByMa(txtMaNhanVien.getText().trim());

            lblHoTenNV.setText(nv.getTen());
            lblGioiTinh.setText(nv.getGioiTinh());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date ngaySinh = nv.getDob();
            String ngaySinFormat = simpleDateFormat.format(ngaySinh);
            lblNgaySinh.setText(ngaySinFormat);
            lblDiaChi.setText(nv.getDiaChi());
            lblSoDienThoai.setText(nv.getSdt());
            lblChucVu.setText(nv.getCapBac().getTenCB());

            byte[] hinhAnh = nv.getImg();
            lblHinhAnh2.setText("");
            ImageIcon oriImgIcon = new ImageIcon(hinhAnh);
            Image image = oriImgIcon.getImage(); // transform it
            Image newimg = image.getScaledInstance(140, 150, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            ImageIcon imageIcon = new ImageIcon(newimg);
            lblHinhAnh2.setIcon(imageIcon);

            ChamCong chamCong = getFormGioVao();
            if (chamCong == null) {
                JOptionPane.showMessageDialog(this, "Lỗi để trống dữ liệu");
            }

            int choice = JOptionPane.showConfirmDialog(this, "Xác thực chấm công giờ vào", "Accuracy?", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {

                chamCongService.save(chamCong);
                JOptionPane.showMessageDialog(this, "Chấm công giờ vào thành công");
                clearForm();
            } else {
                clearForm();
            }
        }

    }

    private void chamCongGioRa() {
        if (validateData()) {
            //Lấy giờ hiện tại
            setTimeGioRa();
            NhanVien nv = nhanVineService.selectByMa(txtMaNhanVien.getText().trim());
            lblHoTenNV.setText(nv.getTen());
            lblGioiTinh.setText(nv.getGioiTinh());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date ngaySinh = nv.getDob();
            String ngaySinFormat = simpleDateFormat.format(ngaySinh);
            lblNgaySinh.setText(ngaySinFormat);
            lblDiaChi.setText(nv.getDiaChi());
            lblSoDienThoai.setText(nv.getSdt());
            lblChucVu.setText(nv.getCapBac().getTenCB());

            byte[] hinhAnh = nv.getImg();
            lblHinhAnh2.setText("");
            ImageIcon oriImgIcon = new ImageIcon(hinhAnh);
            Image image = oriImgIcon.getImage(); // transform it
            Image newimg = image.getScaledInstance(140, 150, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            ImageIcon imageIcon = new ImageIcon(newimg);
            lblHinhAnh2.setIcon(imageIcon);

            ChamCong chamCong = getFormGioRa();
            if (chamCong == null) {
                JOptionPane.showMessageDialog(this, "Lỗi để trống dữ liệu");
            }

            int choice = JOptionPane.showConfirmDialog(this, "Xác thực chấm công giờ ra", "Accuracy?", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {

                String idChamCong = chamCong.getIdChamCong();
                chamCongService.update(idChamCong, chamCong);
                JOptionPane.showMessageDialog(this, "Chấm công giờ Ra thành công");
                clearForm();
            } else {
                clearForm();
            }

        }

    }

    private ChamCong getFormGioVao() {
        ChamCong chamCong = new ChamCong();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);

        NhanVien nv = nhanVineService.selectByMa(txtMaNhanVien.getText().trim());
        if (nv == null) {
            JOptionPane.showMessageDialog(this, "Lỗi để trốn dữ liệu");
            return null;
        }
        chamCong.setNv(nv);

        // Đặt giờ vào vào thuộc tính của đối tượng chamCong dưới dạng Date
        Date gioVao = calendar.getTime();
        Time gioVaoTime = new Time(gioVao.getTime());
        chamCong.setGioVao(gioVaoTime);
        Date ngay = calendar.getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String ngayString = dateFormat.format(ngay);

        try {
            // Chuyển đổi chuỗi thành kiểu dữ liệu Date
            Date ngayTaoDate = dateFormat.parse(ngayString);
            chamCong.setNgay(ngayTaoDate);

        } catch (ParseException ex) {
            Logger.getLogger(Form_ChamCong.class.getName()).log(Level.SEVERE, null, ex);
        }

        return chamCong;

    }

    private ChamCong getFormGioRa() {
        ChamCong chamCong = new ChamCong();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        NhanVien nv = nhanVineService.selectByMa(txtMaNhanVien.getText().trim());
        if (nv == null) {
            JOptionPane.showMessageDialog(this, "Lỗi để trốn dữ liệu");
            return null;
        }
        chamCong.setNv(nv);
        Date gioVao = calendar.getTime();
        Time gioVaoTime = new Time(gioVao.getTime());
        ChamCong chamCong2 = chamCongService.selectByID(gioVaoTime);
        chamCong.setIdChamCong(chamCong2.getIdChamCong());
        chamCong.setGioVao(chamCong2.getGioVao());

        Time gioRaTime = new Time(new Date().getTime());
        chamCong.setGioRa(gioRaTime);
        chamCong.setNgay(chamCong2.getNgay());

        return chamCong;

    }

    // vALIDATE DỮ LIỆU
    private boolean validateData() {

        if (txtMaNhanVien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhâp vào mã nhân viên");
            return false;
        }
        return true;
    }

    private void clearForm() {
        lblHoTenNV.setText("#Name");
        lblGioiTinh.setText("#Reger");
        lblNgaySinh.setText("#DateOfBirth");
        lblDiaChi.setText("#Address");
        lblSoDienThoai.setText("#Phone");
        lblChucVu.setText("#Positor");

        lblHinhAnh2.setIcon(null);
        lblHinhAnh2.setText("Ảnh");
        txtMaNhanVien.setText("");
        
        txtGioVao.setText("00:00 AM");
        txtGioRa.setText("00:00 AM");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblHinhAnh2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblHoTenNV = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblNgaySinh = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblSoDienThoai = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblChucVu = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        ChamCongGioRa = new javax.swing.JButton();
        btnChamCongGioVao = new javax.swing.JButton();
        lblDongHo = new javax.swing.JLabel();
        txtMaNhanVien = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNgay = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtGioVao = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtGioRa = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        lblHinhAnh2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinhAnh2.setText("Ảnh");

        jLabel2.setText("Họ tên nhân viên :");

        lblHoTenNV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblHoTenNV.setText("#Name");

        jLabel8.setText("Giới tính :");

        lblGioiTinh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblGioiTinh.setText("#Renger");

        jLabel10.setText("Ngày sinh :");

        lblNgaySinh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNgaySinh.setText("#Date birthday");

        jLabel12.setText("Địa chỉ :");

        lblDiaChi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDiaChi.setText("#Address");

        jLabel14.setText("Số điện thoại :");

        lblSoDienThoai.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSoDienThoai.setText("#Phone");

        jLabel16.setText("Vị trí :");

        lblChucVu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblChucVu.setText("#Employee");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblHinhAnh2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel8))
                                .addGap(28, 28, 28))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(65, 65, 65)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGioiTinh)
                            .addComponent(lblHoTenNV)
                            .addComponent(lblNgaySinh)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel16)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblChucVu))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14)
                                .addComponent(jLabel12))
                            .addGap(47, 47, 47)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblSoDienThoai)
                                .addComponent(lblDiaChi)))))
                .addGap(301, 301, 301))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lblHoTenNV))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(lblGioiTinh))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(lblNgaySinh))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(lblDiaChi))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(lblSoDienThoai))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(lblChucVu))
                            .addComponent(jLabel16)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(lblHinhAnh2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        ChamCongGioRa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ChamCongGioRa.setText("Chấm công giờ ra");
        ChamCongGioRa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChamCongGioRaActionPerformed(evt);
            }
        });

        btnChamCongGioVao.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnChamCongGioVao.setText("Chấm công giờ vào");
        btnChamCongGioVao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChamCongGioVaoActionPerformed(evt);
            }
        });

        lblDongHo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDongHo.setText("00:00:00 AM");

        jLabel3.setText("Mã nhân viên :");

        jLabel4.setText("Ngày :");

        txtNgay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtNgay.setText("24-07-2023");

        jLabel5.setText("Giờ vào :");

        txtGioVao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtGioVao.setText("07:00:00 AM");

        jLabel6.setText("Giờ ra :");

        txtGioRa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtGioRa.setText("00:00:00 AM");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblDongHo, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGioVao, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGioRa))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnChamCongGioVao)
                        .addGap(11, 11, 11)
                        .addComponent(ChamCongGioRa)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDongHo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNgay))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(txtGioVao))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtGioRa))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChamCongGioVao, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChamCongGioRa, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(391, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(135, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnChamCongGioVaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChamCongGioVaoActionPerformed
        // TODO add your handling code here:
        chamCongGioVao();
    }//GEN-LAST:event_btnChamCongGioVaoActionPerformed

    private void ChamCongGioRaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChamCongGioRaActionPerformed
        // TODO add your handling code here:
        chamCongGioRa();
    }//GEN-LAST:event_ChamCongGioRaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ChamCongGioRa;
    private javax.swing.JButton btnChamCongGioVao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblDongHo;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHinhAnh2;
    private javax.swing.JLabel lblHoTenNV;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblSoDienThoai;
    private javax.swing.JLabel txtGioRa;
    private javax.swing.JLabel txtGioVao;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JLabel txtNgay;
    // End of variables declaration//GEN-END:variables
}
