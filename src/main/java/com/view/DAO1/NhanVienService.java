/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.view.DAO1;

import com.view.model.NhanVien;

/**
 *
 * @author Lê Chấn Khang
 */
public class NhanVienService {
    
    private NhanVienDao repository = new NhanVienDao();
    
      public String them(NhanVien nhanVien) {
        if(repository.addNew(nhanVien)){
            return "Thêm thành công";
        }else{
            return "Thêm thất bại";
        }
    }
      
      public void saveNhanVien(NhanVien nhanVien) {
        repository.save(nhanVien);
    }

       public void updateNhanVien(NhanVien nhanVien) {
        repository.update(nhanVien);
    }
    
      public String xoaNhanVien(String ma){
          if(repository.deleteNew(ma)){
              return "Xóa thành công";
          }else{
              return "Xóa thất bại";
          }
      }
      
      public String doiMatKhau(String tk,NhanVien nhanVien){
          if(repository.updatePass(tk, nhanVien)){
              return "Đổi mật khẩu thành công";
          }else{
              return "Thất bại";
          }
      }
}
