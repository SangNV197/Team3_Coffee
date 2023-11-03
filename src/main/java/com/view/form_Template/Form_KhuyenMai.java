/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.view.form_Template;

import DoUong_HoaDon_ThongKe_Model.LichSuHoaDon;
import DoUong_HoaDon_ThongKe_Model.LoaiDoUong;
import DoUong_HoaDon_ThongKe_Service.LoaiDoUongService;

import com.view.form_Template.*;
import DoUong_HoaDon_ThongKe_Model.LoaiDoUong;
import DoUong_HoaDon_ThongKe_Service.LichSuHoaDonService;
import com.view.form.ThemSanPhamGiamGiaJDialog;
import model.ChiTietDoUong;

import com.view.model.QuanLyTaiKhoan;
import java.awt.Checkbox;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.CapBac;
import model.HoaDon;
import model.KhuyenMai;
import model.TaiKhoan;
import service.ChiTietDoUongService_Master;
import service.HoaDonService;
import service.SaleService;
import service.TaoTaiKhoanService;

/**
 *
 * @author LENOVO T560
 */
public class Form_KhuyenMai extends javax.swing.JPanel {

    // Service Sale
    private SaleService service = new SaleService();
    private ArrayList<KhuyenMai> listSale = new ArrayList<>();
    // Service HoaDon
    private HoaDonService hoaDonService = new HoaDonService();
    ArrayList<HoaDon> listHoaDon = new ArrayList<>();

    public Form_KhuyenMai() {
        initComponents();
        fillToTableKhuyenMai();
        fillComboboxLoaiKM();
        fillComboboxTrangThai();
        loadData();
    }

    // Chức năng load dữ liệu lên bảng Khuyến mại
    private void fillToTableKhuyenMai() {
        DefaultTableModel model = (DefaultTableModel) tblKhuyenMai.getModel();
        model.setRowCount(0);
        listSale = service.selectALL();
        for (KhuyenMai km : listSale) {
            model.addRow(new Object[]{km.getMaKM(), km.getTenKM(), km.getLoaiKM(), km.getGiaTri(), km.getTrangThai()});
        }
    }

    private void fillToTableKhuyenMaiByTrangThai(String trangThai, ArrayList<KhuyenMai> listKM) {
        DefaultTableModel model = (DefaultTableModel) tblKhuyenMai.getModel();
        model.setRowCount(0);
        listKM = service.selectALLByTrangThai(trangThai);
        for (KhuyenMai km : listKM) {
            model.addRow(new Object[]{km.getMaKM(), km.getTenKM(), km.getLoaiKM(), km.getGiaTri(), km.getTrangThai()});
        }
    }

    // Chức năng load dữ liệu loại Khuyến mại lên Combobox
    private void fillComboboxLoaiKM() {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cboLoaiKM.getModel();
        comboBoxModel.removeAllElements();
        listSale = service.selectLoaiKM();
        for (KhuyenMai km : listSale) {
            comboBoxModel.addElement(km.getLoaiKM());
        }
    }

    // Chức năng load dữ liệu trạng thái khuyến mãi lên Combobox
    private void fillComboboxTrangThai() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboStatus.getModel();
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cboTrangThai.getModel();
        model.removeAllElements();
        comboBoxModel.removeAllElements();
        model.addElement("Tất cả");
        listSale = service.selectTrangThai();
        for (KhuyenMai km : listSale) {
            model.addElement(km.getTrangThai());
            comboBoxModel.addElement(km.getTrangThai());
        }
    }

    // Load dữ liệu lên table
    public void loadData() {
        try {
            DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
            model.setRowCount(0);
            listHoaDon = hoaDonService.selectHoaDonBySale();
            String hoTenNV = "";
            for (HoaDon hd : listHoaDon) {
                hoTenNV = hd.getNhanVien().getTen();
                model.addRow(new Object[]{
                    hd.getMa(),
                    hd.getNgayTao(),
                    hd.getTinhTrangThanhToan() == 1 ? "Đã thanh toán" : "Chưa thanh toán",
                    hd.getMaGiamGia().getMaKM(),
                    hoTenNV
                });
                ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadDataByMaKM() {
        try {

            int row = tblKhuyenMai.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, ":Vui lòng chọn vào 1 dòng trên table");
                return;
            }

            String maKM = tblKhuyenMai.getValueAt(row, 0).toString();

            DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
            model.setRowCount(0);
            listHoaDon = hoaDonService.selectHoaDonByMaGiamGia(maKM);
            String hoTenNV = "";
            for (HoaDon hd : listHoaDon) {
                hoTenNV = hd.getNhanVien().getHo() + " " + hd.getNhanVien().getTenDem() + " " + hd.getNhanVien().getTen();
                model.addRow(new Object[]{
                    hd.getMa(),
                    hd.getNgayTao(),
                    hd.getTinhTrangThanhToan() == 1 ? "Đã thanh toán" : "Chưa thanh toán",
                    hd.getMaGiamGia().getMaKM(),
                    hoTenNV
                });
                ;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Chức năng set các giá trị trong bảng khuyến mãi lên form
    private void setForm() {
        int row = tblKhuyenMai.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn vào 1 mã khuyến mãi trên table");
            return;
        }
        String loaiKM = tblKhuyenMai.getValueAt(row, 2).toString();

        listSale = service.selectLoaiKM();
        KhuyenMai khuyenMai = service.selectByID(tblKhuyenMai.getValueAt(row, 0).toString());
        txtNgayBatDau.setDate(khuyenMai.getNgayBatDau());
        txtNgayKetThuc.setDate(khuyenMai.getNgayKetThuc());

        String trangThai = tblKhuyenMai.getValueAt(row, 4).toString();
        if (loaiKM.equals("%")) {
            cboLoaiKM.setSelectedIndex(0);
        } else {
            cboLoaiKM.setSelectedIndex(1);
        }

        cboTrangThai.setSelectedItem(trangThai);

        txtMaKM.setText(tblKhuyenMai.getValueAt(row, 0).toString());
        txtMaKM.setEditable(false);
        txtTenKM.setText(tblKhuyenMai.getValueAt(row, 1).toString());
        txtGiaTriKM.setText(tblKhuyenMai.getValueAt(row, 3).toString());
    }

    // Chức năng lấy giá trị từ form 
    private KhuyenMai getFomr() {
        try {
            KhuyenMai km = new KhuyenMai();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY");
            km.setMaKM(txtMaKM.getText().trim());
            km.setTenKM(txtTenKM.getText().trim());
            String loaiGiaTri = "";
            loaiGiaTri = cboLoaiKM.getSelectedItem().toString();
            km.setLoaiKM(loaiGiaTri);
            km.setGiaTri(Integer.parseInt(txtGiaTriKM.getText().trim()));
            km.setNgayBatDau(txtNgayBatDau.getDate());
            km.setNgayKetThuc(txtNgayKetThuc.getDate());
            km.setTrangThai(cboTrangThai.getSelectedItem().toString());

            return km;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
//
    // Chức năng đăng ký form

    private void addKhuyenMai() {
        if (validateForm()) {
            KhuyenMai km = getFomr();

            if (km == null) {
                JOptionPane.showMessageDialog(this, "Lỗi trống dữ liệu");
                return;
            }

            int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thêm?", "Add", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                {
                    service.saveKhuyenMai(km);
                    JOptionPane.showMessageDialog(this, "Thêm khuyến mãi thành công");
                    fillToTableKhuyenMai();
                    clearForm();
                }
            }

        }
    }

    // Chức năng update Khuyen Mai
    private void updateKhuyenMai() {

        int row = tblKhuyenMai.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 khuyến mãi trên table");
            return;
        }

        if (validateFormUpdate()) {
            String maKM = tblKhuyenMai.getValueAt(row, 0).toString();
            KhuyenMai km = getFomr();
            if (km == null) {
                JOptionPane.showMessageDialog(this, "Lỗi trống dữ liệu");
                return;
            }
            int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa?", "Update", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                service.updateKhuyenMai(maKM, km);
                JOptionPane.showMessageDialog(this, "Sửa khuyến mãi thành công");
                fillToTableKhuyenMai();
                clearForm();
            }

        }

    }

    // Chức năng delete KHuyến mại
    private void deleteKhuyeMai() {

        int row = tblKhuyenMai.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 khuyến mãi trên table");
            return;
        }

        int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Delete", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            String maKhuyenMai = tblKhuyenMai.getValueAt(row, 0).toString();
            service.delete(maKhuyenMai);
            JOptionPane.showMessageDialog(this, "Xóa khuyến mãi thành công");
            fillToTableKhuyenMai();
            clearForm();
        }
    }

    // Chức năng tìm kiếm trong bảng khuyến mại
    private void searchKhuyenMai() {
        DefaultTableModel model = (DefaultTableModel) tblKhuyenMai.getModel();
        TableRowSorter<DefaultTableModel> search = new TableRowSorter<>(model);
        tblKhuyenMai.setRowSorter(search);
        search.setRowFilter(javax.swing.RowFilter.regexFilter(txtTimKM.getText()));
    }

    // Chức năng tìm kiếm
    private void searchHoaDon() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        TableRowSorter<DefaultTableModel> search = new TableRowSorter<>(model);
        tblHoaDon.setRowSorter(search);
        search.setRowFilter(javax.swing.RowFilter.regexFilter(txtTimHD.getText()));
    }

    //Chức năng validate Form
    private boolean validateForm() {
        // Validate để trống trường dữ liệu
        if (txtMaKM.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào mã khuyến mại");
            return false;
        }

        if (txtTenKM.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào tên khuyến mại");
            return false;
        }
        if (txtGiaTriKM.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá trị khuyến mại");
            return false;
        }
        if (txtNgayBatDau.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào ngày bắt đầu khuyến mại");
            return false;
        }
        if (txtNgayKetThuc.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào ngày kết thúc khuyến mại");
            return false;
        }

        if (String.valueOf(txtNgayBatDau.getDate()).equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào ngày bắt đầu khuyến mại");
            return false;
        }

        if (String.valueOf(txtNgayKetThuc.getDate()).equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào ngày kết thúc khuyến mại");
            return false;
        }

        // Check trùng mã khuyến mại không trùng nhau
        int count = service.selectById(txtMaKM.getText().trim());
        if (count > 0) {
            JOptionPane.showMessageDialog(this, "Mã khuyến mại đã tồn tại vui lòng nhập lại");
            return false;
        }
        // Check mã khuyến mại chứa ký tự đặc biệt
        String maKM = txtMaKM.getText().trim();
        if (!isValidSaleId(maKM)) {
            JOptionPane.showMessageDialog(this, "Mã khuyến mại không hợp lệ, vui lòng nhập lại");
            return false;
        }
        // Validate Tên nhân viên đúng định dạng không chứa ký tự đặc biệt hoặc số
        if (!isValidEmployeeName(txtTenKM.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Tên khuyến mại không hợp lệ, vui lòng nhập lại");
            return false;
        }
        // Validate giá trị khuyến mãi
        try {
            Integer giaTriKM = Integer.parseInt(txtGiaTriKM.getText());
            if (giaTriKM > 50) {
                JOptionPane.showMessageDialog(this, "Giá trị Khuyến mãi phải là phải nhỏ hơn 50");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Giá trị Khuyến mãi phải là số");
            return false;
        }

        // Tạo Regex hợp lệ cho ngày bắt đầu
        String dobRegex = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(19|20)\\d{2}$";

        Date ngayBatDau = txtNgayBatDau.getDate();
        String dob = new SimpleDateFormat("dd-MM-yyyy").format(ngayBatDau);

        if (!dob.matches(dobRegex)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không hợp lệ");
            return false;
        }

        Date ngayKetThuc = txtNgayKetThuc.getDate();
        String dobEnd = new SimpleDateFormat("dd-MM-yyyy").format(ngayKetThuc);

        if (!dobEnd.matches(dobRegex)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc không hợp lệ");
            return false;
        }

        return true;
    }

    private boolean validateFormUpdate() {
        // Validate để trống trường dữ liệu
        if (txtMaKM.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào mã khuyến mại");
            return false;
        }

        if (txtTenKM.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào tên khuyến mại");
            return false;
        }
        if (txtGiaTriKM.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá trị khuyến mại");
            return false;
        }
        if (txtNgayBatDau.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào ngày bắt đầu khuyến mại");
            return false;
        }
        if (txtNgayKetThuc.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào ngày kết thúc khuyến mại");
            return false;
        }

        // Validate Tên nhân viên đúng định dạng không chứa ký tự đặc biệt hoặc số
        if (!isValidEmployeeName(txtTenKM.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Tên khuyến mại không hợp lệ, vui lòng nhập lại");
            return false;
        }

        try {
            Integer giaTriKM = Integer.parseInt(txtGiaTriKM.getText());
            if (giaTriKM > 50) {
                JOptionPane.showMessageDialog(this, "Giá trị Khuyến mãi phải là phải nhỏ hơn 50");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Giá trị Khuyến mãi phải là số");
            return false;
        }

        // Tạo Regex hợp lệ cho ngày bắt đầu
        String dobRegex = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(19|20)\\d{2}$";

        Date ngayBatDau = txtNgayBatDau.getDate();
        String dob = new SimpleDateFormat("dd-MM-yyyy").format(ngayBatDau);

        if (!dob.matches(dobRegex)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không hợp lệ");
            return false;
        }

        Date ngayKetThuc = txtNgayKetThuc.getDate();
        String dobEnd = new SimpleDateFormat("dd-MM-yyyy").format(ngayKetThuc);

        if (!dobEnd.matches(dobRegex)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc không hợp lệ");
            return false;
        }

        return true;
    }

    // Regex ID Employee in Java, 
    private boolean isValidSaleId(String employeeId) {
        //        String regex = "^[a-zA-Z][a-zA-Z0-9]*$";
        String regex = "^KM[a-zA-Z0-9]*$";
        return employeeId.matches(regex);
    }

    // Regex Name Employee in Java
    private boolean isValidEmployeeName(String employeeName) {
        String vietnameseRegex = "^[\\p{Lu}][\\p{L}\\s]*$";
        return employeeName.matches(vietnameseRegex);
    }

    //    // Chức năng Clear Form
    private void clearForm() {
        txtMaKM.setText("");
        txtTenKM.setText("");
        txtNgayBatDau.setDate(null);
        txtNgayKetThuc.setDate(null);
        txtGiaTriKM.setText("");
        cboLoaiKM.setSelectedIndex(0);
        cboTrangThai.setSelectedIndex(0);
        txtMaKM.setEditable(true);

    }

    // Lọc dữ liệu theo ComboBox
    private void setDataTabeleByStatus() {
        cboLoaiKM.setSelectedItem("Tất cả");
        Object selectedStatus = cboStatus.getSelectedItem(); // Lấy giá trị được chọn
        if (selectedStatus != null) {
            String trangThai = selectedStatus.toString();

            if (trangThai.equals("Ngừng hoạt động") || trangThai.equals("Đang hoạt động")) {
                ArrayList<KhuyenMai> listKM = service.selectALLByTrangThai(trangThai);
                fillToTableKhuyenMaiByTrangThai(trangThai, listKM);
            } else {
                fillToTableKhuyenMai();
                loadData();
            }
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        txtMaKM = new javax.swing.JTextField();
        cboTrangThai = new javax.swing.JComboBox<>();
        txtTenKM = new javax.swing.JTextField();
        cboLoaiKM = new javax.swing.JComboBox<>();
        jLabel41 = new javax.swing.JLabel();
        txtGiaTriKM = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txtNgayBatDau = new com.toedter.calendar.JDateChooser();
        btnXoa = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        txtNgayKetThuc = new com.toedter.calendar.JDateChooser();
        btnCapNhat = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        btnThem1 = new javax.swing.JButton();
        btnCapNhat1 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        txtTimKM = new javax.swing.JTextField();
        btnTimKM = new javax.swing.JButton();
        cboStatus = new javax.swing.JComboBox<>();
        jLabel57 = new javax.swing.JLabel();
        txtTimHD = new javax.swing.JTextField();
        btnTimSp = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblKhuyenMai = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel12.setText("jLabel12");

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel11.setForeground(new java.awt.Color(255, 255, 255));
        jPanel11.setName(""); // NOI18N

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel41.setText("SALE");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel42.setText("Mã khuyến mại");

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel43.setText("Tên khuyến mại");

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel44.setText("Ngày kết thúc");

        txtNgayBatDau.setDateFormatString("dd-MM-yyyy");

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel45.setText("Loại khuyến mại");

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel46.setText("Trạng thái");

        txtNgayKetThuc.setDateFormatString("dd-MM-yyyy");

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCapNhat.setText("Xóa form");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel47.setText("Ngày bắt đầu");

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel48.setText("Giá trị");

        btnThem1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThem1.setText("Thêm");
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });

        btnCapNhat1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCapNhat1.setText("Cập nhật");
        btnCapNhat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhat1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42)
                            .addComponent(jLabel43))
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel48)
                            .addComponent(jLabel45)
                            .addComponent(jLabel47)
                            .addComponent(jLabel44)
                            .addComponent(jLabel46))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaKM)
                    .addComponent(txtTenKM)
                    .addComponent(cboLoaiKM, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtGiaTriKM)
                    .addComponent(txtNgayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCapNhat1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jPanel11Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCapNhat, btnCapNhat1, btnThem1, btnXoa});

        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel41)
                .addGap(60, 60, 60)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42))
                        .addGap(21, 21, 21)
                        .addComponent(txtTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboLoaiKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGiaTriKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel48)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel43)))
                .addGap(29, 29, 29)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47))
                .addGap(23, 23, 23)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44))
                .addGap(25, 25, 25)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel12.setForeground(new java.awt.Color(255, 255, 255));

        jLabel54.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel54.setText("Tìm khuyến mãi:");

        jLabel56.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel56.setText("Trạng thái");

        txtTimKM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKMKeyReleased(evt);
            }
        });

        btnTimKM.setText("Tìm");
        btnTimKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKMActionPerformed(evt);
            }
        });

        cboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboStatusActionPerformed(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel57.setText("Tìm kiếm:");

        txtTimHD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimHDKeyReleased(evt);
            }
        });

        btnTimSp.setText("Tìm");
        btnTimSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimSpActionPerformed(evt);
            }
        });

        tblKhuyenMai.setBackground(new java.awt.Color(255, 255, 255));
        tblKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KM", "Tên KM", "Loại KM", "Giá trị", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tblKhuyenMai);

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "TG tạo", "Tình trạng thanh toán", "Mã KM", "Họ tên nhân viên"
            }
        ));
        tblHoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel12Layout.createSequentialGroup()
                                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel12Layout.createSequentialGroup()
                                            .addComponent(txtTimKM, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnTimKM, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(161, 161, 161)
                                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel56)
                                        .addComponent(cboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel12Layout.createSequentialGroup()
                                    .addComponent(txtTimHD, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnTimSp, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 19, Short.MAX_VALUE))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(jLabel56))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTimKM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTimKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabel57)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimSp))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        deleteKhuyeMai();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void txtTimKMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKMKeyReleased
        // TODO add your handling code here:
        searchKhuyenMai();
    }//GEN-LAST:event_txtTimKMKeyReleased

    private void btnTimKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKMActionPerformed
        // TODO add your handling code here:
        searchKhuyenMai();
    }//GEN-LAST:event_btnTimKMActionPerformed

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        // TODO add your handling code here:
        addKhuyenMai();
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void txtTimHDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimHDKeyReleased
        // TODO add your handling code here:
        searchHoaDon();
    }//GEN-LAST:event_txtTimHDKeyReleased

    private void btnTimSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimSpActionPerformed
        searchHoaDon();

    }//GEN-LAST:event_btnTimSpActionPerformed

    private void btnCapNhat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhat1ActionPerformed
        // TODO add your handling code here:
        updateKhuyenMai();
    }//GEN-LAST:event_btnCapNhat1ActionPerformed

    private void tblKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhuyenMaiMouseClicked
        // TODO add your handling code here:
        setForm();
        loadDataByMaKM();


    }//GEN-LAST:event_tblKhuyenMaiMouseClicked

    // Sự kiện lọc dữ liệu theo trạng thái
    private void cboStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboStatusActionPerformed
        // TODO add your handling code here:
        setDataTabeleByStatus();
    }//GEN-LAST:event_cboStatusActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked

    }//GEN-LAST:event_tblHoaDonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnCapNhat1;
    private javax.swing.JButton btnThem1;
    private javax.swing.JButton btnTimKM;
    private javax.swing.JButton btnTimSp;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboLoaiKM;
    private javax.swing.JComboBox<String> cboStatus;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblKhuyenMai;
    private javax.swing.JTextField txtGiaTriKM;
    private javax.swing.JTextField txtMaKM;
    private com.toedter.calendar.JDateChooser txtNgayBatDau;
    private com.toedter.calendar.JDateChooser txtNgayKetThuc;
    private javax.swing.JTextField txtTenKM;
    private javax.swing.JTextField txtTimHD;
    private javax.swing.JTextField txtTimKM;
    // End of variables declaration//GEN-END:variables
}
