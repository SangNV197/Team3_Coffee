/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoUong_HoaDon_ThongKe_Repository111;

import DoUong_HoaDon_ThongKe_Model.ChiTietDoUong;
import DoUong_HoaDon_ThongKe_Model.LoaiDoUong;
import java.sql.ResultSet;
import java.util.ArrayList;
import ultilities.DBConnection1;

/**
 *
 * @author ADMIN
 */
public class DAO_ChiTietDoUong implements iChiTietDoUong {

    final String INSERT_SQL = "INSERT INTO dbo.ChiTietDoUong(idLoaiDoUong,TenDoUong,GiaNhap,GiaBan,HinhAnh,MoTa)VALUES(?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE dbo.ChiTietDoUong SET IdLoaiDoUong=?, TenDoUong=?, GiaNhap=?,GiaBan=?,MoTa=?,HinhAnh=?,Status=? WHERE Id=?";
    final String DELETE_SQL = "DELETE FROM [dbo].[ChiTietDoUong] WHERE [Id] = ?";
    final String SELECT_BY_SQL = "SELECT * FROM [dbo].[ChiTietDoUong] WHERE [Id] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[ChiTietDoUong] order by TenDoUong";
    final String SELECT_BY_UNIID = "SELECT * FROM [dbo].[ChiTietDoUong] WHERE [Id] = ?";
    final String SELECT_BY_MULIPLECONDITION = "DECLARE @tenDoUong AS NVARCHAR(50) = ?, @idLoaiDoUong AS uniqueidentifier =?,@giaBatDau as decimal(20, 0)=?,@giaKeThuc as decimal(20, 0)=?"
            + "SELECT*FROM dbo.ChiTietDoUong \n"
            + "WHERE (@tenDoUong IS NULL OR TenDoUong=@tenDoUong) AND (@IdLoaiDoUong IS NULL OR IdLoaiDoUong=@idLoaiDoUong) AND (@giaBatDau =0 OR GiaBan>=@giaBatDau) AND (@giaKetThuc =0 OR GiaBan<=@giaKetThuc)";
    final String SELECT_BY_MULIPLECONDITION2 = "DECLARE @tenDoUong AS NVARCHAR(50) =?, @idLoaiDoUong AS varchar(50) =?\n"
            + "SELECT*FROM dbo.ChiTietDoUong\n"
            + "WHERE (@tenDoUong IS NULL OR TenDoUong=@tenDoUong) AND (@IdLoaiDoUong IS NULL OR IdLoaiDoUong=@idLoaiDoUong)";

    @Override
    public ArrayList<ChiTietDoUong> selectALl() {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<ChiTietDoUong> lstChiTietDoUong = new ArrayList<>();
        DAO_LoaiDoUong dAO_LoaiDoUong = new DAO_LoaiDoUong();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQL);
            while (rs.next()) {
                LoaiDoUong loaiDoUong = dAO_LoaiDoUong.selectByID(rs.getString("idLoaiDoUong"));
                lstChiTietDoUong.add(new ChiTietDoUong(rs.getString("id"), rs.getString("TenDoUong"), rs.getDouble("GiaNhap"), rs.getDouble("GiaBan"), rs.getString("MoTa"), rs.getBytes("HinhAnh"), loaiDoUong, rs.getInt("Status")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstChiTietDoUong;
    }

    @Override
    public ChiTietDoUong selectByID(String id) {
        DBConnection1 dbConn = new DBConnection1();
        ChiTietDoUong chiTietDoUong = new ChiTietDoUong();
        ArrayList<ChiTietDoUong> lstChiTietDoUong = new ArrayList<>();
        DAO_LoaiDoUong dAO_LoaiDoUong = new DAO_LoaiDoUong();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQL, id);
            while (rs.next()) {
                LoaiDoUong loaiDoUong = dAO_LoaiDoUong.selectByID(rs.getString("idLoaiDoUong"));
                lstChiTietDoUong.add(new ChiTietDoUong(rs.getString("id"), rs.getString("TenDoUong"), rs.getDouble("GiaNhap"), rs.getDouble("GiaBan"), rs.getString("MoTa"), rs.getBytes("HinhAnh"), loaiDoUong, rs.getInt("Status")));
                chiTietDoUong = lstChiTietDoUong.get(0);
                break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietDoUong;
    }

    @Override
    public void save(ChiTietDoUong chiTietDoUong) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(INSERT_SQL, chiTietDoUong.getLoaiDoUong().getId(), chiTietDoUong.getTenDoUong(), chiTietDoUong.getGiaNhap(), chiTietDoUong.getGiaBan(), chiTietDoUong.getHinhAnh(), chiTietDoUong.getMoTa());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ChiTietDoUong chiTietDoUong) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(UPDATE_SQL, chiTietDoUong.getLoaiDoUong().getId(), chiTietDoUong.getTenDoUong(), chiTietDoUong.getGiaNhap(), chiTietDoUong.getGiaBan(), chiTietDoUong.getMoTa(), chiTietDoUong.getHinhAnh(), chiTietDoUong.getStatus(), chiTietDoUong.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(DELETE_SQL, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<ChiTietDoUong> selectByFlexibleCondition(String tenDoUong, String idLoaiDoUong, double giaBatDau, double giaKetThuc) {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<ChiTietDoUong> lstChiTietDoUong = new ArrayList<>();
        DAO_LoaiDoUong dAO_LoaiDoUong = new DAO_LoaiDoUong();
        try {
            System.out.println(idLoaiDoUong);
            ResultSet rs = dbConn.getDataFromQuery(SELECT_BY_MULIPLECONDITION2, tenDoUong, idLoaiDoUong);
            while (rs.next()) {
                LoaiDoUong loaiDoUong = dAO_LoaiDoUong.selectByID(rs.getString("idLoaiDoUong"));
                lstChiTietDoUong.add(new ChiTietDoUong(rs.getString("id"), rs.getString("TenDoUong"), rs.getDouble("GiaNhap"), rs.getDouble("GiaBan"), rs.getString("MoTa"), rs.getBytes("HinhAnh"), loaiDoUong, rs.getInt("Status")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstChiTietDoUong;
    }

}
