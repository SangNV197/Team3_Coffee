/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import com.view.model.QuanLyTaiKhoan;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CapBac;
import model.HoaDon;
import model.TaiKhoan;
//import org.mindrot.jbcrypt.BCrypt;
import repository.DAO_TaoTaiKhoan;
import ultilities.Utilitys;

/**
 *
 * @author LENOVO T560
 */
public class TaoTaiKhoanService {

    private DAO_TaoTaiKhoan repositoryTK;

    public TaoTaiKhoanService() {
        try {
            repositoryTK = new DAO_TaoTaiKhoan();
        } catch (Exception ex) {
            Logger.getLogger(TaoTaiKhoanService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<TaiKhoan> findAll() {
//        ArrayList<TaiKhoan> listQLTK = new ArrayList<>();
        ArrayList<TaiKhoan> listAcount = repositoryTK.findAll();
//        for (TaiKhoan tk : listAcount) {
//            QuanLyTaiKhoan qlTK = new QuanLyTaiKhoan(tk.getMaNV(), tk.getTenNV(), tk.getGioiTinh(), tk.getNgaySinh(), tk.getDiaChi(), tk.getSoDT(), tk.getMatKhau(), tk.getCapBac().getIdCB(), tk.getTrangThai());
//            listQLTK.add(listAcount);
//        }
        return listAcount;
    }

    // Lấy giá trị theo mã
    public int selectById(String maNV) {
        int count = repositoryTK.selectById(maNV);
        return count;
    }

    public TaiKhoan selectByUserName(String username) {
        TaiKhoan account = repositoryTK.selectByUserName(username);
        return account;
    }

    public ArrayList<CapBac> getTenCapBac() {
        ArrayList<CapBac> listCapBac = repositoryTK.getTenCapBac();
        return listCapBac;
    }

    public ArrayList<String> getAllPhoneNumbers() {
        ArrayList<String> listPhones = repositoryTK.getAllPhoneNumbers();
        return listPhones;
    }

//    public void save(TaiKhoan acount) {
//        repositoryTK.save(acount);
//    }
    // Hàm check Password đã Hash
//    public boolean accountVerification(String pwdRaw, String pwdHash) {
//        return Utilitys.checkPwd(pwdRaw, pwdHash);
//    }
//
//    // Hàm check Password đã Hash
//    public boolean accountVerification(String pwdRaw, String pwdHash) {
//        return Utilitys.checkPwd(pwdRaw, pwdHash);
//    }
    public void save(TaiKhoan acount) {
        repositoryTK.save(acount);
    }

    public boolean checkUsernameExists(String username) {
        return repositoryTK.checkUsernameExists(username);
    }
    

}
