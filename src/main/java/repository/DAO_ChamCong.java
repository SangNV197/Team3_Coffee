/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import model.CapBac;
import model.ChamCong;
import model.NhanVien;
import org.bridj.cpp.std.list;
import service.NhanVienService;
import ultilities.DBConnection1;

/**
 *
 * @author LENOVO T560
 */
public class DAO_ChamCong {

    final String INSERT_SQL = "INSERT INTO ChamCong(IdNV,Ngay,GioVao,GioRa) \n"
            + "VALUES (?,?,?,?)";
    final String UPDATE_SQL = "UPDATE ChamCong SET IdNV = ?, Ngay = ?, GioVao = ? , GioRa = ?\n"
            + "WHERE Id = ?";
    final String DELETE_SQL = "";
    final String SELECT_BY_SQL = "SELECT Id,IdNV,Ngay,GioVao,GioRa FROM ChamCong WHERE GioVao LIKE ?";
    final String SELECT_ALL_SQL = "SELECT Id, IdNV,Ngay,GioVao,GioRa FROM ChamCong";

    public DAO_ChamCong() {
        NhanVienService nhanVienService = new NhanVienService();
//        DBConnection1 connection = (DBConnection1) DBConnection1.openDbConnection();
    }

    public ArrayList<ChamCong> selectALl() {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<ChamCong> listChamCong = new ArrayList<>();
        //get capBac obj
        DAO_NhanVien dao_NhanVine = new DAO_NhanVien();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQL);
            while (rs.next()) {
                NhanVien nv = dao_NhanVine.selectByID(rs.getString("IdNV"));
                ChamCong chamCong = new ChamCong();
                chamCong.setNv(nv);
                chamCong.setNgay(rs.getDate("Ngay"));
                chamCong.setGioVao(rs.getTime("GioVao"));
                chamCong.setGioRa(rs.getTime("GioVao"));
                listChamCong.add(chamCong);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listChamCong;
    }

    public ChamCong selectByID(Time gioVao) {
        DBConnection1 dbConn = new DBConnection1();
        Connection connection = dbConn.openDbConnection();
        ChamCong chamCong = new ChamCong();
        ArrayList<ChamCong> lstChamCongs = new ArrayList<>();
        DAO_NhanVien dao_NhanVien = new DAO_NhanVien();
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_BY_SQL);
            ps.setString(1, "%" + String.valueOf(gioVao) + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                NhanVien nv = dao_NhanVien.selectByID(rs.getString("IdNV"));
                chamCong.setNv(nv);
                chamCong.setNgay(rs.getDate("Ngay"));
                chamCong.setGioVao(rs.getTime("GioVao"));
                chamCong.setGioRa(rs.getTime("GioVao"));
                chamCong.setIdChamCong(rs.getString("Id"));
                lstChamCongs.add(chamCong);
                chamCong = lstChamCongs.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return chamCong;
    }

    public void save(ChamCong chamCong) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(INSERT_SQL, chamCong.getNv().getId(), chamCong.getNgay(), chamCong.getGioVao(), chamCong.getGioRa());
            System.out.println("Thêm thành công");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(String id, ChamCong chamCong) {
        DBConnection1 dbConn = new DBConnection1();
        Connection connection = dbConn.openDbConnection();
        try {
//            gioVao = new Time(System.currentTimeMillis());
            PreparedStatement ps = connection.prepareStatement(UPDATE_SQL);
            ps.setString(1, chamCong.getNv().getId());
            ps.setDate(2, new java.sql.Date(chamCong.getNgay().getTime()));
            ps.setTime(3, chamCong.getGioVao());
            ps.setTime(4, chamCong.getGioRa());
            ps.setString(5, id);
            int resualt = ps.executeUpdate();
            System.out.println(resualt);

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
}
