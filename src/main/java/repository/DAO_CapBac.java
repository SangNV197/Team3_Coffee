/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import model.CapBac;
import ultilities.DBConnection1;

/**
 *
 * @author 84374
 */
public class DAO_CapBac {

    final String INSERT_SQL = "INSERT INTO dbo.CapBac(Ma,Ten,LuongPartime)VALUES(?,?,?)";
    final String UPDATE_SQL = "UPDATE dbo.CapBac SET Ma=?,Ten=?,LuongPartime=? WHERE Id=?";
    final String DELETE_SQL = "DELETE FROM [dbo].[CapBac] WHERE [Id] = ?";
    final String SELECT_BY_SQL = "SELECT * FROM [dbo].[CapBac] WHERE[Id] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[CapBac];";

    public DAO_CapBac() {
    }

    public ArrayList<CapBac> selectALl() {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<CapBac> lstCapBac = new ArrayList<>();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQL);
            while (rs.next()) {
                lstCapBac.add(new CapBac(rs.getString("Id"), rs.getString("Ma"), rs.getNString("Ten"), rs.getBigDecimal("LuongPartime"), rs.getString("NgayTao")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstCapBac;
    }

    public CapBac selectByID(String id) {
        DBConnection1 dbConn = new DBConnection1();
        CapBac capBac = new CapBac();
        ArrayList<CapBac> lstCapBac = new ArrayList<>();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_BY_SQL, id);
            while (rs.next()) {
                lstCapBac.add(new CapBac(rs.getString("Id"), rs.getString("Ma"), rs.getNString("Ten"), rs.getBigDecimal("LuongPartime"), rs.getString("NgayTao")));
                capBac = lstCapBac.get(0);
                break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return capBac;
    }

    public void save(CapBac capBac) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(INSERT_SQL, capBac.getMaCB(), capBac.getTenCB(),capBac.getLuongPastTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(CapBac capBac) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(UPDATE_SQL, capBac.getMaCB(),capBac.getTenCB(),capBac.getLuongPastTime(),capBac.getIdCB());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String idCB) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(DELETE_SQL, idCB);
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
//
//    }
}
