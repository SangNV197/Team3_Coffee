/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import model.ChiTietDoUong;
import model.HoaDon;
import model.HoaDonChiTiet;
import ultilities.DBConnection1;

/**
 *
 * @author 84374
 */
public class DAO_HoaDonChiTiet {

    final String INSERT_SQL = "INSERT INTO dbo.HoaDonChiTiet(IdHoaDon,IdChiTietDoUong,SoLuong)VALUES(?,?,?)";
    final String UPDATE_SQL = "UPDATE dbo.HoaDonChiTiet SET SoLuong=? WHERE IdHoaDon=?,IdChiTietDoUong=?";
    final String DELETE_SQL = "DELETE FROM [dbo].[HoaDonChiTiet] WHERE IdHoaDon=?,IdChiTietDoUong=?";
    final String SELECT_BY_SQL = "SELECT * FROM [dbo].[HoaDonChiTiet] WHERE IdHoaDon=?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[HoaDonChiTiet];";
    final String SELECT_TOP1DESC = "SELECT TOP 1*FROM dbo.HoaDonChiTiet ORDER BY NUMORDER DESC ;";

    public ArrayList<HoaDonChiTiet> selectALl() {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<HoaDonChiTiet> lstHoaDonChiTiet = new ArrayList<>();
        DAO_HoaDon dao_HoaDon = new DAO_HoaDon();
        DAO_ChiTietDoUongMaster dao_ChiTietDoUong = new DAO_ChiTietDoUongMaster();

        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQL);
            while (rs.next()) {
                //get hoaDon
                HoaDon hoaDon = new HoaDon();
                hoaDon = dao_HoaDon.selectByID(rs.getString("IdHoaDon"));
                //get chiTietDoUong
                ChiTietDoUong chiTietDoUong = new ChiTietDoUong();
                chiTietDoUong = dao_ChiTietDoUong.selectByID(rs.getString("IdChiTietDoUong"));
                lstHoaDonChiTiet.add(new HoaDonChiTiet(hoaDon, chiTietDoUong, rs.getInt("SoLuong"), rs.getString("NgayTao")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHoaDonChiTiet;
    }

    public ArrayList<HoaDonChiTiet> selectByID(String id) {
        DBConnection1 dbConn = new DBConnection1();
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        DAO_HoaDon dao_HoaDon = new DAO_HoaDon();
        DAO_ChiTietDoUongMaster dao_ChiTietDoUong = new DAO_ChiTietDoUongMaster();
        ArrayList<HoaDonChiTiet> lstHoaDonChiTiet = new ArrayList<>();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_BY_SQL, id);
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon = dao_HoaDon.selectByID(rs.getString("IdHoaDon"));
                ChiTietDoUong chiTietDoUong = new ChiTietDoUong();
                chiTietDoUong = dao_ChiTietDoUong.selectByID(rs.getString("IdChiTietDoUong"));
                lstHoaDonChiTiet.add(new HoaDonChiTiet(hoaDon, chiTietDoUong, rs.getInt("SoLuong"), rs.getString("NgayTao")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHoaDonChiTiet;
    }

    public void save(HoaDonChiTiet hoaDonChiTiet) {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<HoaDonChiTiet> lstHoaDonChiTiet = new ArrayList<>();
        HoaDonChiTiet hdChiTiet;
        HoaDon hd;
        try {
            dbConn.ExcuteSQL(INSERT_SQL, hoaDonChiTiet.getHoaDon().getId(), hoaDonChiTiet.getChiTietDoUong().getId(), hoaDonChiTiet.getSoLuong());
            ResultSet rs = dbConn.getDataFromQuery(SELECT_TOP1DESC);
//            while(rs.next()){
//                
//                hdChiTiet = new HoaDonChiTiet(hoaDon, chiTietDoUong, 0, INSERT_SQL)
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(HoaDonChiTiet hoaDonChiTiet) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(UPDATE_SQL, hoaDonChiTiet.getSoLuong(), hoaDonChiTiet.getHoaDon().getId(), hoaDonChiTiet.getChiTietDoUong().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void delete(HoaDonChiTiet hoaDonChiTiet) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(DELETE_SQL,hoaDonChiTiet.getHoaDon().getId(),hoaDonChiTiet.getChiTietDoUong().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
