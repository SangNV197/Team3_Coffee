/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import model.GiamGia;
import model.KhachHang;
import ultilities.DBConnection1;

/**
 *
 * @author 84374
 */
public class DAO_GiamGia {

    final String INSERT_SQL = "INSERT INTO dbo.GiamGia(MaGiamGia,PhamTram,MoTa)VALUES(?,?,?)";
    final String UPDATE_SQL = "UPDATE dbo.GiamGia SET PhamTram=? WHERE MaGiamGia=?";
    final String DELETE_SQL = "DELETE FROM [dbo].[GiamGia] WHERE [MaGiamGia] = ?";
    final String SELECT_BY_SQL = "SELECT * FROM [dbo].[GiamGia] WHERE[MaGiamGia] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[GiamGia];";

    public DAO_GiamGia() {
    }

    public ArrayList<GiamGia> selectALl() {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<GiamGia> lstGiamGia = new ArrayList<>();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQL);
            while (rs.next()) {
                lstGiamGia.add(new GiamGia(rs.getString("MaGiamGia"), rs.getNString("TenKhuyenMai"), rs.getString("LoaiKhuyenMai"), rs.getInt("GiaTri"),
                        rs.getDate("NgayTao"),
                        rs.getDate("NgayKetThuc"),
                        rs.getNString("TrangThai")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstGiamGia;
    }

    public GiamGia selectByID(String id) {
        DBConnection1 dbConn = new DBConnection1();
        GiamGia giamGia = new GiamGia();
        ArrayList<GiamGia> lstGiamGia = new ArrayList<>();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_BY_SQL, id);
            while (rs.next()) {
                lstGiamGia.add(new GiamGia(rs.getString("MaGiamGia"), rs.getNString("TenKhuyenMai"), rs.getString("LoaiKhuyenMai"), rs.getInt("GiaTri"),
                        rs.getDate("NgayTao"),
                        rs.getDate("NgayKetThuc"),
                        rs.getNString("TrangThai")));
                giamGia = lstGiamGia.get(0);
                break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return giamGia;
    }

//    public void save(GiamGia giamGia) {
//        DBConnection1 dbConn = new DBConnection1();
//        try {
//            dbConn.ExcuteSQL(INSERT_SQL, giamGia.getMaGiamGia(), giamGia.getPhanTram(), giamGia.getMoTa());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public void update(GiamGia giamGia) {
//        DBConnection1 dbConn = new DBConnection1();
//        try {
//            dbConn.ExcuteSQL(UPDATE_SQL, giamGia.getPhanTram(), giamGia.getMaGiamGia());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public void delete(String maGiamGia) {
//        DBConnection1 dbConn = new DBConnection1();
//        try {
//            dbConn.ExcuteSQL(DELETE_SQL, maGiamGia);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

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
//
//    }
}
