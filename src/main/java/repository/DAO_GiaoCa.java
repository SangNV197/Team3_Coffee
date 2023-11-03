/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
//import model.ChiTietDoUong;
import model.KhuyenMai;
//import model.LoaiDoUong;
//import ultilities.DBConnection1;
import java.sql.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import model.ChiTietDoUong;
import model.GiaoCa;
import model.LoaiDoUong;
import model.NhanVien;
import service.NhanVienService;
import ultilities.DBConnection1;
//import ultilities.DBConnection;
import ultilities.Utilitys;
import java.sql.*;

/**
 *
 * @author LENOVO T560
 */
public class DAO_GiaoCa {

//    private Connection connection = Utilitys.openDbConnection();
    final String INSERT_SQL = "INSERT INTO GiaoCa(NgayGiaoCa,NguoiGiao,NguoiNhan,GioKiemKe, SoTienTrenHeThong,SoTienThucKiem,TrangThai,GhiChu,CaLamViec) \n"
            + "VALUES(?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE GIAOCA\n"
            + "SET NGAYGIAOCA = ?,\n"
            + "    NGUOIGIAO = ?,\n"
            + "    NGUOINHAN = ?,\n"
            + "    GIOKIEMKE = ?,\n"
            + "    SOTIENTRENHETHONG = ?,\n"
            + "	   SOTIENTHUCKIEM = ?,\n"
            + "    TRANGTHAI = ?,\n"
            + "	   CALAMVIEC = ?,\n"
            + "    GHICHU = ?\n"
            + "WHERE Ma = ?";
    final String DELETE_SQL = " DELETE GiaoCa WHERE Ma = ?";
    final String SELECT_BY_DATE_SQL = "SELECT Ma, CaLamViec, NgayGiaoCa, NguoiGiao, NguoiNhan, SoTienTrenHeThong, SoTienThucKiem, GioKiemKe, TrangThai, GhiChu\n"
            + "FROM GiaoCa\n"
            + "WHERE CONVERT(VARCHAR, GiaoCa.NgayGiaoCa, 105) BETWEEN ? AND ?";
    final String SELECT_ALL_SQL = "SELECT Ma,CaLamViec, NgayGiaoCa,NguoiGiao,NguoiNhan, SoTienTrenHeThong,SoTienThucKiem,GioKiemKe,TrangThai,GhiChu FROM GiaoCa";

    public ArrayList<GiaoCa> selectALL() {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<GiaoCa> listGiaoCa = new ArrayList<>();
        NhanVienService service = new NhanVienService();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQL);

            while (rs.next()) {
                String nguoiGiaoString = rs.getString("NguoiGiao");
                String nguoiNhanString = rs.getString("NguoiNhan");

                NhanVien nguoiGiao = service.selectByIDNhanVien(nguoiGiaoString);
                NhanVien nguoiNhan = service.selectByIDNhanVien(nguoiNhanString);
                GiaoCa giaoCa = new GiaoCa();
                giaoCa.setMaGiaoCa(rs.getString("Ma"));
                giaoCa.setCaLamViec(rs.getString("CaLamViec"));
                giaoCa.setNgayGiaoCa(rs.getDate("NgayGiaoCa"));
                giaoCa.setNguoiGiao(nguoiGiao);
                giaoCa.setNguoiNhan(nguoiNhan);
                giaoCa.setTongCong(rs.getBigDecimal("SoTienTrenHeThong"));
                giaoCa.setThucKiem(rs.getBigDecimal("SoTienThucKiem"));
                giaoCa.setGioKiemKe(rs.getTime("GioKiemKe"));
                giaoCa.setTrangThai(rs.getString("TrangThai"));
                listGiaoCa.add(giaoCa);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return listGiaoCa;
    }

    public ArrayList<GiaoCa> selectAllByDate(String tuNgay, String denNgay) {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<GiaoCa> listGiaoCa = new ArrayList<>();
        NhanVienService service = new NhanVienService();
        try {
            Connection connection = dbConn.openDbConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_BY_DATE_SQL);
            ps.setString(1, tuNgay);
            ps.setString(2, denNgay);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nguoiGiaoString = rs.getString("NguoiGiao");
                String nguoiNhanString = rs.getString("NguoiNhan");

                NhanVien nguoiGiao = service.selectByIDNhanVien(nguoiGiaoString);
                NhanVien nguoiNhan = service.selectByIDNhanVien(nguoiNhanString);
                GiaoCa giaoCa = new GiaoCa();
                giaoCa.setMaGiaoCa(rs.getString("Ma"));
                giaoCa.setCaLamViec(rs.getString("CaLamViec"));
                giaoCa.setNgayGiaoCa(rs.getDate("NgayGiaoCa"));
                giaoCa.setNguoiGiao(nguoiGiao);
                giaoCa.setNguoiNhan(nguoiNhan);
                giaoCa.setTongCong(rs.getBigDecimal("SoTienTrenHeThong"));
                giaoCa.setThucKiem(rs.getBigDecimal("SoTienThucKiem"));
                giaoCa.setGioKiemKe(rs.getTime("GioKiemKe"));
                giaoCa.setTrangThai(rs.getString("TrangThai"));
                listGiaoCa.add(giaoCa);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return listGiaoCa;
    }

    public void save(GiaoCa giaoCa) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(INSERT_SQL, giaoCa.getNgayGiaoCa(),
                    giaoCa.getNguoiGiao().getId(),
                    giaoCa.getNguoiNhan().getId(),
                    giaoCa.getGioKiemKe(),
                    giaoCa.getTongCong(), giaoCa.getThucKiem(), giaoCa.getTrangThai(), giaoCa.getGhiChu(), giaoCa.getCaLamViec());
            System.out.println("Thêm thành công");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(String maGiaoCa, GiaoCa giaoCa) {
        DBConnection1 dbConn = new DBConnection1();
        Connection connection = dbConn.openDbConnection();
        try {

            PreparedStatement ps = connection.prepareStatement(UPDATE_SQL);
            ps.setDate(1, new java.sql.Date(giaoCa.getNgayGiaoCa().getTime()));
            ps.setObject(2, giaoCa.getNguoiGiao().getId());
            ps.setObject(3, giaoCa.getNguoiNhan().getId());
            ps.setObject(4, new java.sql.Time(giaoCa.getGioKiemKe().getTime()));
            ps.setObject(5, giaoCa.getTongCong());
            ps.setObject(6, giaoCa.getThucKiem());
            ps.setObject(7, giaoCa.getTrangThai());
            ps.setObject(8, giaoCa.getCaLamViec());
            ps.setObject(9, giaoCa.getGhiChu());
            ps.setObject(10, maGiaoCa);

            int resualt = ps.executeUpdate();
            System.out.println(resualt);
//            dbConn.ExcuteSQL(UPDATE_SQL, giaoCa.getNgayGiaoCa(), giaoCa.getNguoiGiao().getId(), giaoCa.getNguoiNhan().getId(),
//                    giaoCa.getGioKiemKe(), giaoCa.getTongCong(), giaoCa.getThucKiem(),
//                    giaoCa.getTrangThai(), giaoCa.getCaLamViec(), giaoCa.getGhiChu(), giaoCa.getMaGiaoCa());
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

    public BigDecimal getTongDoanhThu(Date ngayTao, String caLamViec) {
        BigDecimal tongDoanhThu = null;
        DBConnection1 dBConnection = new DBConnection1();
        Connection connection = dBConnection.openDbConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT SUM(ChiTietDoUong.GiaBan * HoaDonChiTiet.SoLuong - 0 * (ChiTietDoUong.GiaBan * HoaDonChiTiet.SoLuong) * (CASE WHEN HoaDon.MaGiamGia IS NULL THEN 0 ELSE GiamGia.GiaTri END) / 100) AS 'TongDoanhThu' "
                    + "FROM HoaDonChiTiet "
                    + "LEFT JOIN ChiTietDoUong ON HoaDonChiTiet.IdChiTietDoUong = ChiTietDoUong.Id "
                    + "LEFT JOIN HoaDon ON HoaDonChiTiet.IdHoaDon = HoaDon.Id "
                    + "LEFT JOIN GiamGia ON GiamGia.MaGiamGia = HoaDon.MaGiamGia "
                    + "WHERE HoaDon.TinhTrangThanhToan = 1 "
                    + "AND CONVERT(date, HoaDon.NgayTao) = ? "
                    + "AND ((? = N'Ca Sáng' AND CONVERT(time, HoaDon.ThoiGian) >= '07:00' AND CONVERT(time, HoaDon.ThoiGian) < '12:00') "
                    + "OR (? = N'Ca Chiều' AND CONVERT(time, HoaDon.ThoiGian) >= '13:00' AND CONVERT(time, HoaDon.ThoiGian) < '18:00') "
                    + "OR (? = N'Ca Tối' AND CONVERT(time, HoaDon.ThoiGian) >= '18:00' AND CONVERT(time, HoaDon.ThoiGian) < '23:00')) ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, new java.sql.Date(ngayTao.getTime()));
            // Set caLamViec parameter
            preparedStatement.setString(2, caLamViec);
            preparedStatement.setString(3, caLamViec);
            preparedStatement.setString(4, caLamViec);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                tongDoanhThu = resultSet.getBigDecimal("TongDoanhThu");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return tongDoanhThu;
    }
}
