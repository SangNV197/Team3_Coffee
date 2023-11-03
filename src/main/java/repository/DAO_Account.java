/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.TaiKhoan;

/**
 *
 * @author LENOVO T560
 */
public class DAO_Account {

    //Khởi tạo giá trị
    private Connection connection;

    public DAO_Account() throws Exception {
        connection = ultilities.Utilitys.getConnection();
    }

    // Lấy dữ liệu tài khỏan
    public ArrayList<TaiKhoan> getAccount() {
        ArrayList<TaiKhoan> listAcount = new ArrayList<>();
        try {
            String sql = "SELECT Ma,MatKhau FROM NHANVIEN  ";
            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setMaNV(rs.getString("Ma"));
                tk.setMatKhau(rs.getString("MatKhau"));
                listAcount.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listAcount;
    }

    // Hàm Xác thực tài khoản
    public TaiKhoan xacThucTaiKhoan(String username) throws Exception { // Tìm theo tên tài khoản 
        List<TaiKhoan> listAccount = getAccount();
        TaiKhoan accountFind = null;
        for (TaiKhoan tk : listAccount) {
            if (tk.getMaNV().equals(username)) {
                accountFind = tk;
                break;
            }
        }
        return accountFind;
    }
}
