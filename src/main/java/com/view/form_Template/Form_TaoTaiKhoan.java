/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.view.form_Template;

import com.view.model.QuanLyTaiKhoan;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.CapBac;
import model.TaiKhoan;
import service.TaoTaiKhoanService;

/**
 *
 * @author LENOVO T560
 */
public class Form_TaoTaiKhoan extends javax.swing.JPanel {

    private TaoTaiKhoanService service = new TaoTaiKhoanService();
    private ArrayList<TaiKhoan> listAccount = service.findAll();
    private ArrayList<CapBac> listCapBac = new ArrayList<>();

    public Form_TaoTaiKhoan() {
        initComponents();
        fillToComboBox();

    }

    // Chức năng fillToComboBox
    private void fillToComboBox() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboCapBac.getModel();
        model.removeAllElements();
        listCapBac = service.getTenCapBac();
        for (CapBac cb : listCapBac) {
            model.addElement(cb.getTenCB());
        }
    }

    // Chức năng lấy giá trị từ form 
    private TaiKhoan getFomr() {

        String hoTen = txtHoTenNV.getText();

        String gioiTinh = "";
        TaiKhoan createAcount = new TaiKhoan();
        createAcount.setTaiKhoan(txtUsername.getText());
        createAcount.setTenNV(hoTen);
        if (rdoNam.isSelected()) {
            gioiTinh = "Nam";
        } else {
            gioiTinh = "Nữ";
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date ngaySinh = txtNgaySinh.getDate();
            createAcount.setNgaySinh(ngaySinh);
        } catch (Exception e) {
            e.printStackTrace();
        }
        createAcount.setMaNV("''");

        createAcount.setGioiTinh(gioiTinh);
        createAcount.setDiaChi(txtDiaChi.getText());
        createAcount.setSoDT(txtSoDienThoai.getText());
        createAcount.setMatKhau(String.valueOf((txtPassword.getPassword())));
        int count = cboCapBac.getSelectedIndex();
        CapBac capBac = listCapBac.get(count);
        createAcount.setCapBac(capBac);

        return createAcount;

    }

    // Chức năng đăng ký form
    private void createAccount() {
        if (validateForm()) {
            int choice = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn tạo tài khoản?", "Create Account?", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {

                TaiKhoan createAcount = getFomr();

                if (createAcount == null) {
                    JOptionPane.showMessageDialog(this, "Lỗi trống dữ liệu");
                    return;
                }
                service.save(createAcount);
                JOptionPane.showMessageDialog(this, "Đăng ký tài khoản thành công");
                clearForm();
            }
        }

    }

    private boolean validateForm() {
        // Validate để trống trường dữ liệu
        if (txtUsername.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào tên tài khoản");
            return false;
        }

        // Kiểm tra tên tài khoản không chứa ký tự đặc biệt
        if (!isValidUsername(txtUsername.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Tên tài khoản không được chứa ký tự đặc biệt");
            return false;
        }

        // Kiểm tra tên tài khoản đã tồn tại hay chưa
        if (isUsernameAlreadyExists(txtUsername.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Tên tài khoản đã tồn tại");
            return false;
        }

        if (txtHoTenNV.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào họ tên nhân viên");
            return false;
        }
        if (String.valueOf(txtPassword.getPassword()).trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào mật khẩu");
            return false;
        }
        if (String.valueOf(txtNgaySinh.getDate()).trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào ngày sinh");
            return false;
        }
        if (txtSoDienThoai.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào số điện thoại");
            return false;
        }
        if (txtDiaChi.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào địa chỉ");
            return false;
        }

        // Validate Tên nhân viên đúng định dạng không chứa ký tự đặc biệt hoặc số
        if (!isValidEmployeeName(txtHoTenNV.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Họ tên nhân viên không hợp lệ, vui lòng nhập lại");
            return false;
        }

        // Validate Phone đúng định dạng
        String[] phoneNumbers = txtSoDienThoai.getText().trim().split(",");
        for (String phoneNumber : phoneNumbers) {
            if (!isValidPhoneNumber(phoneNumber.trim())) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ, vui lòng nhập lại số điện thoại hợp lệ");
                return false;
            }
        }
        String phoneNumber = txtSoDienThoai.getText().trim();
        if (isPhoneNumberExists(phoneNumber)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại, vui lòng nhập lại số điện thoại");
            return false;
        }
        // Validate Password đúng định dạng
        if (!isValidPassword(String.valueOf(txtPassword.getPassword()))) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không đủ mạnh. Vui lòng sử dụng ít nhất 8 ký tự và bao gồm cả chữ hoa, chữ thường và số");
            return false;
        }

        return true;
    }

    // Regex Phone in Java, ValidatePhone
    private boolean isValidPhoneNumber(String phoneNumber) {
        // Validate the phone number using a regular expression
        String regex = "^(\\+?84|0)(3[2-9]|5[2689]|7[06789]|8[1-689]|9[0-9])(\\d{7})$";
        return phoneNumber.matches(regex);
    }

    // Check số điện thoại đã tồn tại
    private boolean isPhoneNumberExists(String phoneNumber) {
        ArrayList<String> existingPhoneNumbers = service.getAllPhoneNumbers();
        return existingPhoneNumbers.contains(phoneNumber);

    }

    // Regex Password in Java, isValidPassword
    private boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
        return password.matches(regex);
    }

    // Regex ID Employee in Java, 
    private boolean isValidEmployeeId(String employeeId) {
//        String regex = "^[a-zA-Z][a-zA-Z0-9]*$";
        String regex = "^NV[a-zA-Z0-9]*$";
        return employeeId.matches(regex);
    }

    // Regex Name Employee in Java
    private boolean isValidEmployeeName(String employeeName) {
        String vietnameseRegex = "^[\\p{Lu}][\\p{L}\\s]*$";
        return employeeName.matches(vietnameseRegex);
    }

    // Kiểm tra tên tài khoản không chứa ký tự đặc biệt
    private boolean isValidUsername(String username) {
        return username.matches("^[a-zA-Z0-9]+$");
    }

    private boolean isUsernameAlreadyExists(String username) {
        boolean exists = service.checkUsernameExists(username);
        return exists;
    }

    // Chức năng Clear Form
    private void clearForm() {
        txtUsername.setText("");
        txtHoTenNV.setText("");
        txtPassword.setText("");
        txtNgaySinh.setDate(null);
        txtSoDienThoai.setText("");
        txtDiaChi.setText("");
        cboCapBac.setSelectedIndex(0);

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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        txtHoTenNV = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cboCapBac = new javax.swing.JComboBox<>();
        btnDangKy1 = new javax.swing.JButton();
        btnClearForm = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Tài khoản");

        jLabel3.setText("Họ tên nhân viên");

        jLabel4.setText("Giới tính");

        jLabel8.setText("Mật khẩu");

        txtPassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        rdoNam.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNam);
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        rdoNu.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        txtHoTenNV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        txtUsername.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel5.setText("Ngày sinh");

        jLabel6.setText("Số điện thoại");

        txtSoDienThoai.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtSoDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoDienThoaiActionPerformed(evt);
            }
        });

        jLabel7.setText("Địa chỉ");

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel9.setText("Cấp bậc");

        cboCapBac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnDangKy1.setBackground(new java.awt.Color(0, 153, 255));
        btnDangKy1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDangKy1.setForeground(new java.awt.Color(255, 255, 255));
        btnDangKy1.setText("SIGN UP");
        btnDangKy1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnDangKy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangKy1ActionPerformed(evt);
            }
        });

        btnClearForm.setBackground(new java.awt.Color(0, 153, 255));
        btnClearForm.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnClearForm.setForeground(new java.awt.Color(255, 255, 255));
        btnClearForm.setText("Clear Form");
        btnClearForm.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnClearForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearFormActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 255));
        jLabel1.setText("Tạo tài khoản");

        txtNgaySinh.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(17, 17, 17))
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtHoTenNV)
                                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSoDienThoai)
                            .addComponent(txtDiaChi)
                            .addComponent(cboCapBac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnDangKy1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnClearForm, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(212, 212, 212))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel5)
                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoNu)
                    .addComponent(rdoNam)
                    .addComponent(jLabel4))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboCapBac, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDangKy1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClearForm, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 265, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(196, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSoDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoDienThoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoDienThoaiActionPerformed

    private void btnClearFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearFormActionPerformed
        clearForm();
    }//GEN-LAST:event_btnClearFormActionPerformed

    private void btnDangKy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangKy1ActionPerformed
        // TODO add your handling code here:
        createAccount();
        
    }//GEN-LAST:event_btnDangKy1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearForm;
    private javax.swing.JButton btnDangKy1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboCapBac;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTenNV;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
