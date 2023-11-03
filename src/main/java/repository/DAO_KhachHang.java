/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import model.KhachHang;
import ultilities.DBConnection1;

/**
 *
 * @author 84374
 */
public class DAO_KhachHang {

    final String INSERT_SQL = "INSERT INTO dbo.KhachHang(Ma,Ten,TenDem,Ho,NgaySinh,Sdt,DiaChi)VALUES(?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE dbo.KhachHang SET Ten=?, TenDem=?,Ho=?,NgaySinh=?,Sdt=?,DiaChi=? WHERE Id=?";
    final String DELETE_SQL = "DELETE FROM [dbo].[KhachHang] WHERE [Id] = ?";
    final String SELECT_BY_SQL = "SELECT * FROM [dbo].[KhachHang] WHERE [Id] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[KhachHang];";

    public DAO_KhachHang() {
    }

      public ArrayList<KhachHang> selectALl() {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<KhachHang> lstKhachHang = new ArrayList<>();
        DAO_LoaiDoUongMaster dAO_LoaiDoUong = new DAO_LoaiDoUongMaster();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQL);
            while (rs.next()) {
                lstKhachHang.add(new KhachHang(rs.getString("Id"), rs.getString("Ma"), rs.getNString("Ten"), rs.getNString("TenDem"),rs.getNString("Ho"),rs.getDate("NgaySinh"),rs.getString("Sđt"),rs.getString("DiaChi"),rs.getString("NgayTao")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstKhachHang;
    }


    public KhachHang selectByID(String id) {
        DBConnection1 dbConn = new DBConnection1();
        KhachHang khachHang = new KhachHang();
        ArrayList<KhachHang> lstKhachHang = new ArrayList<>();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_BY_SQL, id);
            while (rs.next()) {
                lstKhachHang.add(new KhachHang(rs.getString("Id"), rs.getString("Ma"), rs.getNString("Ten"), rs.getNString("TenDem"),rs.getNString("Ho"),rs.getDate("NgaySinh"),rs.getString("Sđt"),rs.getString("DiaChi"),rs.getString("NgayTao")));
                khachHang = lstKhachHang.get(0);
                break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return khachHang;
    }


    public void save(KhachHang khachHang) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(INSERT_SQL, khachHang.getTen(),khachHang.getTenDem(),khachHang.getHo(),khachHang.getDOB(),khachHang.getSdt(),khachHang.getDiaChi());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void update(KhachHang khachHang) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(UPDATE_SQL, khachHang.getTen(),khachHang.getTenDem(),khachHang.getHo(),khachHang.getDOB(),khachHang.getSdt(),khachHang.getDiaChi(),khachHang.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void delete(String id) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(DELETE_SQL, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    public ArrayList<ChiTietDoUong> selectByFlexibleCondition(String tenDoUong, String idLoaiDoUong, double giaBatDau, double giaKetThuc) {
//        DBConnection1 dbConn = new DBConnection1();
//        ArrayList<ChiTietDoUong> lstChiTietDoUong = new ArrayList<>();
//        DAO_LoaiDoUongMaster dAO_LoaiDoUong = new DAO_LoaiDoUongMaster();
//        try {
//            System.out.println(idLoaiDoUong);
//            ResultSet rs = dbConn.getDataFromQuery(SELECT_BY_MULIPLECONDITION2, tenDoUong, idLoaiDoUong);
//            while (rs.next()) {
//                System.out.println("test");
//                LoaiDoUong loaiDoUong = dAO_LoaiDoUong.selectByID(rs.getString("idLoaiDoUong"));
//                lstChiTietDoUong.add(new ChiTietDoUong(rs.getString("id"), rs.getString("TenDoUong"), rs.getDouble("GiaNhap"), rs.getDouble("GiaBan"), rs.getString("MoTa"), rs.getBytes("HinhAnh"), loaiDoUong));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return lstChiTietDoUong;
//    }
}
