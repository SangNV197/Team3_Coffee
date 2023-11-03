/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.TaiKhoan;
import repository.DAO_Account;
import ultilities.Utilitys;

/**
 *
 * @author LENOVO T560
 */
public class AccountService {

    private DAO_Account repositoryAccount;

    public AccountService() throws Exception {
        repositoryAccount = new DAO_Account();
    }

    // Hàm Xác thực tài khoản
    public Integer xacThucTaiKhoan(TaiKhoan account) throws Exception {
        TaiKhoan accountFind = repositoryAccount.xacThucTaiKhoan(account.getMaNV());


        //Kiểm tra tài khoản có tồn tại hay không nếu có thì lấy tiếp mật khẩu
        if (accountFind != null) {

            String matKhau = account.getMatKhau();
            boolean resultCheckPwd = accountVerification(matKhau, accountFind.getMatKhau());
            if (resultCheckPwd) {
                return 1; // tìm thấy tài khoản và mật khẩu
            } else {
                return -1; // Không tìm thấy mật khẩu
            }
        }
        return 0; // Không tìm thấy tên tài khoản (mã CBs)
    }

    // Hàm check Password đã Hash
    public boolean accountVerification(String pwdRaw, String pwdHash) {
        return Utilitys.checkPwd(pwdRaw, pwdHash);
    }

}
