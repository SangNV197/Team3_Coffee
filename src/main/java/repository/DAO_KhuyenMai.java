/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import model.ChiTietDoUong;
import model.KhuyenMai;
import model.LoaiDoUong;
import ultilities.DBConnection1;
import java.sql.*;
import java.util.HashSet;
import model.TaiKhoan;
import ultilities.DBConnection;
import ultilities.Utilitys;

/**
 *
 * @author LENOVO T560
 */
public class DAO_KhuyenMai {

    private Connection connection;

    public DAO_KhuyenMai() {
        connection = DBConnection1.openDbConnection();
    }

    final String INSERT_SQL = "INSERT INTO GiamGia(MaGiamGia,TENKHUYENMAI,LOAIKHUYENMAI,GIATRI,NGAYTAO,NGAYKETTHUC,TRANGTHAI)\n"
            + "VALUES (?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE GiamGia SET TENKHUYENMAI = ? ,LOAIKHUYENMAI=?, GIATRI = ?,NGAYTAO = ?,NGAYKETTHUC= ? ,TRANGTHAI= ?\n"
            + "WHERE MaGiamGia = ? ";
    final String DELETE_SQL = "DELETE FROM [dbo].[GiamGia] WHERE [MaGiamGia] =  ?";
    final String SELECT_BY_SQL = "SELECT COUNT(*) FROM GiamGia WHERE MaGiamGia = ?";
    final String SELECT_ALL_BY_MAGIAMGIA = "SELECT * FROM GiamGia WHERE MaGiamGia = ?";
    final String SELECT_ALL_BY_TRANGTHAI = "SELECT * FROM GiamGia WHERE TRANGTHAI = ? ";
    final String SELECT_ALL_SQL = "SELECT MaGiamGia,TENKHUYENMAI,LOAIKHUYENMAI,GIATRI,NgayTao,NGAYKETTHUC,TRANGTHAI FROM GiamGia";

    // Lấy giá trị trong bảng Giảm giá
    public ArrayList<KhuyenMai> selectALl() {
        Utilitys dbConn = new Utilitys();
        ArrayList<KhuyenMai> listKM = new ArrayList<>();
//        DAO_LoaiDoUongMaster dAO_LoaiDoUong = new DAO_LoaiDoUongMaster();
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL_SQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                KhuyenMai km = new KhuyenMai();
                km.setMaKM(rs.getString("MaGiamGia"));
                km.setNgayBatDau(Date.valueOf(rs.getString("NgayTao")));
                km.setTenKM(rs.getString("TENKHUYENMAI"));
                km.setLoaiKM(rs.getString("LOAIKHUYENMAI"));
                km.setGiaTri(rs.getInt("GIATRI"));
                km.setNgayKetThuc(rs.getDate("NGAYKETTHUC"));
                km.setTrangThai(rs.getString("TRANGTHAI"));
                listKM.add(km);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listKM;
    }

    public KhuyenMai selectByID(String id) {
        DBConnection1 dbConn = new DBConnection1();
        KhuyenMai km = new KhuyenMai();
        ArrayList<KhuyenMai> listKM = new ArrayList<>();
        DAO_KhuyenMai dAO_KM = new DAO_KhuyenMai();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_BY_MAGIAMGIA, id);
            while (rs.next()) {
                km.setMaKM(rs.getString("MaGiamGia"));
                km.setNgayBatDau(Date.valueOf(rs.getString("NgayTao")));
                km.setTenKM(rs.getString("TENKHUYENMAI"));
                km.setLoaiKM(rs.getString("LOAIKHUYENMAI"));
                km.setGiaTri(rs.getInt("GIATRI"));
                km.setNgayKetThuc(rs.getDate("NGAYKETTHUC"));
                km.setTrangThai(rs.getString("TRANGTHAI"));
                listKM.add(km);
                km = listKM.get(0);

                break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return km;
    }

    // Lọc dữ liệu theo loại khuyến mại hoặc trạng thái
    public ArrayList<KhuyenMai> selectALLByTrangThai(String trangThai) {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<KhuyenMai> listKM = new ArrayList<>();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_BY_TRANGTHAI, trangThai);
            while (rs.next()) {
                KhuyenMai km = new KhuyenMai();
                km.setMaKM(rs.getString("MaGiamGia"));
                km.setNgayBatDau(Date.valueOf(rs.getString("NgayTao")));
                km.setTenKM(rs.getString("TENKHUYENMAI"));
                km.setLoaiKM(rs.getString("LOAIKHUYENMAI"));
                km.setGiaTri(rs.getInt("GIATRI"));
                km.setNgayKetThuc(rs.getDate("NGAYKETTHUC"));
                km.setTrangThai(rs.getString("TRANGTHAI"));
                listKM.add(km);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listKM;
    }

    public void save(KhuyenMai km) {
        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_SQL);
            ps.setString(1, km.getMaKM());
            ps.setString(2, km.getTenKM());
            ps.setString(3, km.getLoaiKM());
            ps.setInt(4, km.getGiaTri());
            ps.setDate(5, new java.sql.Date(km.getNgayBatDau().getTime()));
            ps.setDate(6, new java.sql.Date(km.getNgayKetThuc().getTime()));
            ps.setString(7, km.getTrangThai());

            int resualt = ps.executeUpdate();
            System.out.println(resualt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(String id, KhuyenMai km) {
        try {
            PreparedStatement ps = connection.prepareStatement(UPDATE_SQL);
            ps.setString(2, km.getLoaiKM());
            ps.setString(1, km.getTenKM());
            ps.setInt(3, km.getGiaTri());
            ps.setDate(4, new java.sql.Date(km.getNgayBatDau().getTime()));
            ps.setDate(5, new java.sql.Date(km.getNgayKetThuc().getTime()));
            ps.setString(6, km.getTrangThai());
            ps.setString(7, id);

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

    // lấy Combobox loại khuyến mại
    public ArrayList<KhuyenMai> selectLoaiKM() {
        ArrayList<KhuyenMai> listKM = new ArrayList<>();
        String sql = "SELECT  DISTINCT LOAIKHUYENMAI FROM GiamGia";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhuyenMai km = new KhuyenMai();
                km.setLoaiKM(rs.getString("LOAIKHUYENMAI"));
                listKM.add(km);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listKM;
    }

    // lấy Combobox loại khuyến mại
    public ArrayList<KhuyenMai> selectTrangThai() {
        ArrayList<KhuyenMai> listKM = new ArrayList<>();
        String sql = "SELECT DISTINCT TRANGTHAI FROM GiamGia";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                KhuyenMai km = new KhuyenMai();
                km.setTrangThai(rs.getString("TRANGTHAI"));
                listKM.add(km);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listKM;
    }

    // Check trùng mã khuyến mại
    // Lấy dữ liệu theo mã khuyến mại
    public int selectById(String maKM) {
        Utilitys dbConn = new Utilitys();
        ArrayList<KhuyenMai> listAcount = new ArrayList<>();
        KhuyenMai km = new KhuyenMai();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_BY_SQL, maKM);
            if (rs.next()) {
                int count = rs.getInt(1);
                return count;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
