/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.ChiTietDoUong;
import model.HoaDon;
import model.HoaDonChiTiet;
import ultilities.DBConnection1;
import viewModel.ChiTietDoUongNoIMG;
import viewModel.HoaDonChiTietNoIMG;

/**
 *
 * @author 84374
 */
public class DAO_HoaDonChiTietNoIMG {

    final String INSERT_SQL = "INSERT INTO dbo.HoaDonChiTiet(IdHoaDon,IdChiTietDoUong,SoLuong)VALUES(?,?,?)";
    final String UPDATE_SQL = "UPDATE dbo.HoaDonChiTiet SET SoLuong=? WHERE IdHoaDon=? and IdChiTietDoUong=?";
    final String DELETE_SQL = "DELETE FROM [dbo].[HoaDonChiTiet] WHERE IdHoaDon=? and IdChiTietDoUong=?";
    final String SELECT_BY_SQL = "SELECT * FROM [dbo].[HoaDonChiTiet] WHERE IdHoaDon=?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[HoaDonChiTiet] where Ngay = ?;";
    final String SELECT_TOP1DESC = "SELECT TOP 1*FROM dbo.HoaDonChiTiet ORDER BY NUMORDER DESC ;";
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    java.util.Date date = new java.util.Date();
    java.sql.Date sqlDateNow = new java.sql.Date(date.getTime());

    public ArrayList<HoaDonChiTietNoIMG> selectALl() {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<HoaDonChiTietNoIMG> lstHoaDonChiTiet = new ArrayList<>();
        DAO_HoaDon dao_HoaDon = new DAO_HoaDon();
        DAO_ChiTietDoUongNoImg dao_ChiTietDoUongNoIMG = new DAO_ChiTietDoUongNoImg();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQL, sqlDateNow);
            while (rs.next()) {
                System.out.println("bucketloader");
                //get hoaDon
                HoaDon hoaDon = new HoaDon();
                hoaDon = dao_HoaDon.selectByID(rs.getString("IdHoaDon"));
                //get chiTietDoUong
                ChiTietDoUongNoIMG chiTietDoUongNoIMG = new ChiTietDoUongNoIMG();
                chiTietDoUongNoIMG = dao_ChiTietDoUongNoIMG.selectByID(rs.getString("IdChiTietDoUong"));
                lstHoaDonChiTiet.add(new HoaDonChiTietNoIMG(hoaDon, chiTietDoUongNoIMG, rs.getInt("SoLuong"), rs.getString("NgayTao")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHoaDonChiTiet;
    }

    public ArrayList<HoaDonChiTietNoIMG> selectByID(String id) {
        DBConnection1 dbConn = new DBConnection1();
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        DAO_HoaDon dao_HoaDon = new DAO_HoaDon();
        ArrayList<HoaDonChiTietNoIMG> lstHoaDonChiTiet = new ArrayList<>();
        DAO_ChiTietDoUongNoImg dao_ChiTietDoUongNoIMG = new DAO_ChiTietDoUongNoImg();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_BY_SQL, id);
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon = dao_HoaDon.selectByID(rs.getString("IdHoaDon"));
                ChiTietDoUongNoIMG chiTietDoUongNoIMG = new ChiTietDoUongNoIMG();
                chiTietDoUongNoIMG = dao_ChiTietDoUongNoIMG.selectByID(rs.getString("IdChiTietDoUong"));
                lstHoaDonChiTiet.add(new HoaDonChiTietNoIMG(hoaDon, chiTietDoUongNoIMG, rs.getInt("SoLuong"), rs.getString("NgayTao")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHoaDonChiTiet;
    }

    public HoaDonChiTietNoIMG save(HoaDonChiTietNoIMG hoaDonChiTietNoIMG) {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<HoaDonChiTiet> lstHoaDonChiTiet = new ArrayList<>();
        DAO_HoaDon dao_HoaDon = new DAO_HoaDon();
        DAO_ChiTietDoUongNoImg dao_ChiTietDoUongNoIMG = new DAO_ChiTietDoUongNoImg();
        HoaDon hd;
        ChiTietDoUongNoIMG chiTietDoUongNoIMG;
        HoaDonChiTietNoIMG hoaDonChiTietNoIMGAdded = null;
        try {

            if (dbConn.ExcuteSQL(INSERT_SQL, hoaDonChiTietNoIMG.getHoaDon().getId(), hoaDonChiTietNoIMG.getChiTietDoUongNoIMG().getId(), hoaDonChiTietNoIMG.getSoLuong()) == 1) {
                ResultSet rs = dbConn.getDataFromQuery(SELECT_TOP1DESC);
                while (rs.next()) {
                    hd = dao_HoaDon.selectByID(rs.getString("IdHoaDon"));
                    chiTietDoUongNoIMG = dao_ChiTietDoUongNoIMG.selectByID(rs.getString("IdChiTietDoUong"));
                    hoaDonChiTietNoIMGAdded = new HoaDonChiTietNoIMG(hd, chiTietDoUongNoIMG, rs.getInt("SoLuong"), rs.getString("NgayTao"));
                }
            }
            else{
                hoaDonChiTietNoIMGAdded=null;
            }
                
                
        } catch (Exception e) {
            hoaDonChiTietNoIMGAdded = null;
            e.printStackTrace();
        }
        return hoaDonChiTietNoIMGAdded;
    }

    public void update(HoaDonChiTietNoIMG hoaDonChiTiet) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(UPDATE_SQL, hoaDonChiTiet.getSoLuong(), hoaDonChiTiet.getHoaDon().getId(), hoaDonChiTiet.getChiTietDoUongNoIMG().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(HoaDonChiTietNoIMG hoaDonChiTiet) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(DELETE_SQL, hoaDonChiTiet.getHoaDon().getId(), hoaDonChiTiet.getChiTietDoUongNoIMG().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
