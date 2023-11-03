/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoUong_HoaDon_ThongKe_Repository;

import DoUong_HoaDon_ThongKe_Model.LoaiDoUong;
import java.sql.ResultSet;
import java.util.ArrayList;
import ultilities.DBConnection;
import ultilities.DBConnection1;

/**
 *
 * @author Sang
 */
public class DAO_LoaiDoUong implements iLoaiDoUong {

    final String INSERT_SQL = "INSERT INTO [dbo].[LoaiDoUong](Ma,Ten)VALUES(?,?)";
    final String UPDATE_SQL = "UPDATE [dbo].[LoaiDoUong] SET Ma=?,Ten=? WHERE Id=?;";
    final String DELETE_SQL = "DELETE FROM [dbo].[LoaiDoUong] WHERE [Id] = ?";
    final String SELECT_BY_SQL = "SELECT * FROM [dbo].[LoaiDoUong] WHERE [Id] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[LoaiDoUong]";
    final String SELECT_BY_UNIID = "SELECT * FROM [dbo].[LoaiDoUong] WHERE [Id] = ?";
    final String SELECT_LOAIDU_KM = "SELECT LoaiDoUong.MA, LoaiDoUong.Ten, ChiTietDoUong.GiaBan, ChiTietDoUong.GiaBan - (ChiTietDoUong.GiaBan * GiamGia.GIATRI / 100) AS GIAMOI\n"
            + "FROM LoaiDoUong\n"
            + "JOIN ChiTietDoUong ON LoaiDoUong.Id = ChiTietDoUong.IdLoaiDoUong\n"
            + "JOIN GiamGia ON ChiTietDoUong.MaGIamGia = GiamGia.MaGiamGia;";

    @Override
    public ArrayList<LoaiDoUong> selectALl() {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<LoaiDoUong> lstLoaiDoUong = new ArrayList<>();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQL);
            while (rs.next()) {
                lstLoaiDoUong.add(new LoaiDoUong(rs.getString("Id"), rs.getString("Ma"), rs.getNString("Ten")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstLoaiDoUong;
    }

    @Override
    public LoaiDoUong selectByID(String id) {
        DBConnection1 dbConn = new DBConnection1();
        LoaiDoUong loaiDoUong = new LoaiDoUong();
        ArrayList<LoaiDoUong> lstLoaiDoUong = new ArrayList<>();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_BY_SQL, id);
            while (rs.next()) {
                lstLoaiDoUong.add(new LoaiDoUong(rs.getString("Id"), rs.getString("Ma"), rs.getString("Ten")));
                loaiDoUong = lstLoaiDoUong.get(0);
                break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return loaiDoUong;
    }
    
    @Override
    public void save(LoaiDoUong loaiDoUong) {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<LoaiDoUong> lstLoaiDoUong = new ArrayList<>();
        try {
            dbConn.ExcuteSQL(INSERT_SQL, loaiDoUong.getMa(), loaiDoUong.getTenLoaiDoUong());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
