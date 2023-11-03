/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Ban;
import model.GiamGia;
import model.HoaDon;
import model.KhachHang;
import model.NhanVien;
import ultilities.DBConnection1;

/**
 *
 * @author 84374
 */
public class DAO_HoaDon {

    final String INSERT_SQL = "INSERT INTO dbo.HoaDon(IdBan,IdKH,IdNV,TinhTrangThanhToan,TrangThaiPhaChe,MaGiamGia,Stt)VALUES(?,?,?,0,0,?,?)";
    final String UPDATE_SQL = "UPDATE dbo.HoaDon SET IdBan=?, IdKH=?,IdNV=?,Ma=?,TinhTrangThanhToan=?,TrangThaiPhaChe=?,MaGiamGia=? WHERE Id=?";
    final String DELETE_SQL = "DELETE FROM [dbo].[HoaDon] WHERE [Id] = ?";
    final String SELECT_BY_SQL = "SELECT * FROM [dbo].[HoaDon] WHERE [Id] = ?";
    final String SELECT_ALL_SQLNOW = "SELECT*FROM dbo.HoaDon where Stt = 1 and NgayTao = ? order by NumOrder;";
    final String SELECT_ALL_SQL_HOADONCHONOW = "SELECT*FROM dbo.HoaDon where Stt = 0 and NgayTao = ? order by NumOrder;";
    final String SELECT_ALL_SQL_HOADONDANGPHACHENOW = "SELECT*FROM dbo.HoaDon where TrangThaiPhaChe = 0 and Stt = 1 and NgayTao = ? order by NumOrder;";
    final String SELECT_ALL_SQL = "SELECT*FROM dbo.HoaDon where Stt = 1 order by NumOrder;";
    final String SELECT_ALL_SQL_HOADONCHO = "SELECT*FROM dbo.HoaDon where Stt = 0;";
    final String SELECT_ALL_SQL_HOADONDANGPHACHE = "SELECT*FROM dbo.HoaDon where TrangThaiPhaChe = 0 and Stt = 1;";
    final String UPDATE_STT = "UPDATE dbo.HoaDon SET stt=? WHERE Id=?";
    final String UPDATE_TTPHACHE = "UPDATE dbo.HoaDon SET TrangThaiPhaChe=? WHERE Id=?";
    final String UPDATE_MONEYTAKE = "UPDATE dbo.HoaDon SET SoTienNhanVao=? WHERE Id=?";
    final String UPDATE_CHECKSTT = "UPDATE dbo.HoaDon SET TinhTrangThanhToan=? WHERE Id=?";
    final String UPDATE_DISCOUNT = "UPDATE dbo.HoaDon SET MaGiamGia=? WHERE Id=?";
    final String SELECT_TOP1BYACENDING = "SELECT TOP 1*FROM dbo.HoaDon ORDER BY NumOrder DESC;";
    final String SELECT_ALL_BY_SALE = "SELECT HoaDon.Ma,HOADON.NgayTao,TinhTrangThanhToan,MaGiamGia,HoaDon.IdNV,NhanVien.Ho + ' '+ NhanVien.TenDem + ' ' + NhanVien.Ten AS HOTENNHANVIEN FROM HoaDon\n"
            + "JOIN NhanVien ON HoaDon.IdNV = NhanVien.Id";
    final String SELECT_ALL_BY_MAGIAMGIA = "SELECT HoaDon.Ma,HOADON.NgayTao,TinhTrangThanhToan,MaGiamGia,HoaDon.IdNV,NhanVien.Ho + ' '+ NhanVien.TenDem + ' ' + NhanVien.Ten AS HOTENNHANVIEN FROM HoaDon\n"
            + "JOIN NhanVien ON HoaDon.IdNV = NhanVien.Id where MaGiamGia = ?";

    DAO_Ban dao_Ban = new DAO_Ban();
    DAO_KhachHang dao_KhachHang = new DAO_KhachHang();
    DAO_NhanVien dao_NhanVien = new DAO_NhanVien();
    DAO_GiamGia dao_GiamGia = new DAO_GiamGia();
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    java.util.Date date = new java.util.Date();
    java.sql.Date sqlDateNow = new java.sql.Date(date.getTime());

    public ArrayList<HoaDon> selectALLNow() {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<HoaDon> lstHoaDon = new ArrayList<>();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQLNOW, sqlDateNow);
            while (rs.next()) {
                Ban ban = dao_Ban.selectByID(rs.getInt("IdBan"));
//                KhachHang khachHang = dao_KhachHang.selectByID("IdKH");
//                NhanVien nhanVien = dao_NhanVien.selectByID(rs.getString("IdNV"));
//                GiamGia giamGia = dao_GiamGia.selectByID(rs.getString("MaGiamGia"));
                KhachHang khachHang = null;
                NhanVien nhanVien = dao_NhanVien.selectByID(rs.getString("IdNV"));
                GiamGia giamGia = dao_GiamGia.selectByID(rs.getString("MaGiamGia"));
                lstHoaDon.add(new HoaDon(rs.getString("id"), ban, khachHang, nhanVien, rs.getString("Ma"), rs.getDate("NgayTao"),
                        rs.getString("ThoiGian"), rs.getInt("TinhTrangThanhToan"), rs.getInt("TrangThaiPhaChe"), giamGia, rs.getInt("Stt"), rs.getBigDecimal("SoTienNhanVao")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHoaDon;
    }

    public ArrayList<HoaDon> selectHoaDonChoNow() {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<HoaDon> lstHoaDon = new ArrayList<>();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQL_HOADONCHONOW, sqlDateNow);
            while (rs.next()) {
                Ban ban = dao_Ban.selectByID(rs.getInt("IdBan"));
//                KhachHang khachHang = dao_KhachHang.selectByID("IdKH");
//                NhanVien nhanVien = dao_NhanVien.selectByID(rs.getString("IdNV"));
//                GiamGia giamGia = dao_GiamGia.selectByID(rs.getString("MaGiamGia"));
                KhachHang khachHang = null;
                NhanVien nhanVien = dao_NhanVien.selectByID(rs.getString("IdNV"));
                GiamGia giamGia = dao_GiamGia.selectByID(rs.getString("MaGiamGia"));
                lstHoaDon.add(new HoaDon(rs.getString("id"), ban, khachHang, nhanVien, rs.getString("Ma"), rs.getDate("NgayTao"),
                        rs.getString("ThoiGian"), rs.getInt("TinhTrangThanhToan"), rs.getInt("TrangThaiPhaChe"), giamGia, rs.getInt("Stt"), rs.getBigDecimal("SoTienNhanVao")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHoaDon;
    }

    public HoaDon selectByID(String id) {
        DBConnection1 dbConn = new DBConnection1();
        HoaDon hoaDon = new HoaDon();
        ArrayList<HoaDon> lstHoaDon = new ArrayList<>();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_BY_SQL, id);
            while (rs.next()) {
                Ban ban = dao_Ban.selectByID(rs.getInt("IdBan"));
//                KhachHang khachHang = dao_KhachHang.selectByID("IdKH");
//                NhanVien nhanVien = dao_NhanVien.selectByID(rs.getString("IdNV"));
//                GiamGia giamGia = dao_GiamGia.selectByID(rs.getString("MaGiamGia"));
                KhachHang khachHang = null;
                NhanVien nhanVien = dao_NhanVien.selectByID(rs.getString("IdNV"));
                GiamGia giamGia = dao_GiamGia.selectByID(rs.getString("MaGiamGia"));
                lstHoaDon.add(new HoaDon(rs.getString("id"), ban, khachHang, nhanVien, rs.getString("Ma"), rs.getDate("NgayTao"),
                        rs.getString("ThoiGian"), rs.getInt("TinhTrangThanhToan"), rs.getInt("TrangThaiPhaChe"), giamGia, rs.getInt("Stt"), rs.getBigDecimal("SoTienNhanVao")));
                hoaDon = lstHoaDon.get(0);
                break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoaDon;
    }

    public HoaDon save(HoaDon hoaDon) {
        DBConnection1 dbConn = new DBConnection1();
        HoaDon hoaDonAdded = null;
        try {
//            "INSERT INTO dbo.HoaDon(IdBan,IdKH,IdNV,TinhTrangThanhToan,TrangThaiPhaChe,MaGiamGia,Stt)VALUES(?,?,?,0,0,?,?)"
            int check = dbConn.ExcuteSQL(INSERT_SQL, hoaDon.getBan().getIdBan(), null, hoaDon.getNhanVien().getId(), null, hoaDon.getStt());
            if (check == 1) {
                ResultSet rs = dbConn.getDataFromQuery(SELECT_TOP1BYACENDING);
                while (rs.next()) {
                    Ban ban = dao_Ban.selectByID(rs.getInt("IdBan"));
                    KhachHang khachHang = null;
                    NhanVien nhanVien = dao_NhanVien.selectByID(rs.getString("IdNV"));
                    GiamGia giamGia = dao_GiamGia.selectByID(rs.getString("MaGiamGia"));
                    hoaDonAdded = new HoaDon(rs.getString("id"), ban, khachHang, nhanVien, rs.getString("Ma"), rs.getDate("NgayTao"),
                            rs.getString("ThoiGian"), rs.getInt("TinhTrangThanhToan"), rs.getInt("TrangThaiPhaChe"), giamGia, rs.getInt("Stt"), rs.getBigDecimal("SoTienNhanVao"));
                }
                return hoaDonAdded;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(HoaDon hoaDon) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(UPDATE_SQL, hoaDon.getBan().getIdBan(), hoaDon.getKhachHang().getId(), hoaDon.getNhanVien().getId(),
                    hoaDon.getMa(), hoaDon.getTinhTrangThanhToan(), hoaDon.getTrangThaiPhaChe(), hoaDon.getMaGiamGia());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStt(int stt, String id) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(UPDATE_STT, stt, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void delete(String id) {
//        DBConnection1 dbConn = new DBConnection1();
//        try {
//            dbConn.ExcuteSQL(DELETE_SQL, id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public ArrayList<HoaDon> selectByFlexibleCondition(String tenDoUong, String idLoaiDoUong, double giaBatDau, double giaKetThuc) {
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
    public ArrayList<HoaDon> selectHoaDonDangPhaCheNow() {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<HoaDon> lstHoaDonDangPhaChe = new ArrayList<>();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQL_HOADONDANGPHACHENOW, sqlDateNow);
            while (rs.next()) {
                Ban ban = dao_Ban.selectByID(rs.getInt("IdBan"));
//                KhachHang khachHang = dao_KhachHang.selectByID("IdKH");
//                NhanVien nhanVien = dao_NhanVien.selectByID(rs.getString("IdNV"));
//                GiamGia giamGia = dao_GiamGia.selectByID(rs.getString("MaGiamGia"));
                KhachHang khachHang = null;
                NhanVien nhanVien = dao_NhanVien.selectByID(rs.getString("IdNV"));
                GiamGia giamGia = dao_GiamGia.selectByID(rs.getString("MaGiamGia"));
                lstHoaDonDangPhaChe.add(new HoaDon(rs.getString("id"), ban, khachHang, nhanVien, rs.getString("Ma"), rs.getDate("NgayTao"),
                        rs.getString("ThoiGian"), rs.getInt("TinhTrangThanhToan"), rs.getInt("TrangThaiPhaChe"), giamGia, rs.getInt("Stt"), rs.getBigDecimal("SoTienNhanVao")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHoaDonDangPhaChe;

    }

    public void updateTTPhaChe(String id, int i) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(UPDATE_TTPHACHE, i, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UpdateMoneyTake(String LocalId, double localMoneyTake) {
        BigDecimal moneyTake = new BigDecimal(localMoneyTake);
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(UPDATE_MONEYTAKE, moneyTake, LocalId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSttCheckBill(int i, String LocalId) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(UPDATE_CHECKSTT, i, LocalId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateDiscount(String maGiamGia, String id) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(UPDATE_DISCOUNT, maGiamGia, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Hàm lấy hóa đơn trong màn Giảm giá(Sale) theo mã giảm giá
    public ArrayList<HoaDon> selectHoaDonBySale() {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<HoaDon> lstHoaDon = new ArrayList<>();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_BY_SALE);
            while (rs.next()) {
                NhanVien nhanVien = dao_NhanVien.selectByID(rs.getString("IdNV"));
                GiamGia giamGia = dao_GiamGia.selectByID(rs.getString("MaGiamGia"));
                HoaDon hoaDon = new HoaDon();
                hoaDon.setNhanVien(nhanVien);
                hoaDon.setMa(rs.getString("Ma"));
                hoaDon.setNgayTao(rs.getDate("NgayTao"));
                hoaDon.setTinhTrangThanhToan(rs.getInt("TinhTrangThanhToan"));
                hoaDon.setMaGiamGia(giamGia);
                lstHoaDon.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHoaDon;
    }

    // Hàm lấy hóa đơn trong màn Giảm giá(Sale) theo mã giảm giá
    public ArrayList<HoaDon> selectHoaDonByMaGiamGia(String maGiamGia) {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<HoaDon> lstHoaDon = new ArrayList<>();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_BY_MAGIAMGIA, maGiamGia);
            while (rs.next()) {
                NhanVien nhanVien = dao_NhanVien.selectByID(rs.getString("IdNV"));
                GiamGia giamGia = dao_GiamGia.selectByID(rs.getString("MaGiamGia"));
                HoaDon hoaDon = new HoaDon();
                hoaDon.setNhanVien(nhanVien);
                hoaDon.setMa(rs.getString("Ma"));
                hoaDon.setNgayTao(rs.getDate("NgayTao"));
                hoaDon.setTinhTrangThanhToan(rs.getInt("TinhTrangThanhToan"));
                hoaDon.setMaGiamGia(giamGia);
                lstHoaDon.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHoaDon;
    }
}
