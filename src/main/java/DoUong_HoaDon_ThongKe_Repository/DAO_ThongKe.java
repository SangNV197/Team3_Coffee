/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoUong_HoaDon_ThongKe_Repository;

import DoUong_HoaDon_ThongKe_Model.BieuDoThongKe;
import DoUong_HoaDon_ThongKe_Model.LoaiDoUong;
import java.util.ArrayList;
import ultilities.DbConnection_Sang;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class DAO_ThongKe implements iThongKe {

    private DbConnection_Sang dbConnection_Sang;

    @Override
    public long getTongDoanhThu() {
        String sql = "select  sum((ChiTietDoUong.GiaBan * HoaDonChiTiet.SoLuong)*(1 - CAST(isnull(GiamGia.GiaTri, 0)  AS Float(30))/100)) as 'TongTien' from HoaDonChiTiet\n"
                + "join ChiTietDoUong on HoaDonChiTiet.IdChiTietDoUong = ChiTietDoUong.Id\n"
                + "join HoaDon on HoaDonChiTiet.IdHoaDon = HoaDon.Id\n"
                + "left join GiamGia on GiamGia.MaGiamGia = HoaDon.MaGiamGia\n"
                + "where convert(varchar, hoadon.ngaytao, 105) = (select convert(varchar,getDate(), 105))";
        try (Connection con = dbConnection_Sang.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int getTongHoaDon() {
        String sql = "select count(HoaDon.Ma) from HoaDon\n"
                + "where convert(varchar, hoadon.ngaytao, 105) = (select convert(varchar,getDate(), 105))";
        try (Connection con = dbConnection_Sang.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int getTongSanPham() {
        String sql = "select sum(HoaDonChiTiet.SoLuong) from HoaDonChiTiet "
                + "join HoaDon on HoaDon.Id = HoaDonChiTiet.IdHoaDon\n"
                + "where convert(varchar, hoadon.ngaytao, 105) = (select convert(varchar,getDate(), 105))";
        try (Connection con = dbConnection_Sang.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public long getTongDonhThuTheoNgayChon(Date d1, Date d2) {
        String sql = "select  sum((ChiTietDoUong.GiaBan * HoaDonChiTiet.SoLuong)*(1 - CAST(isnull(GiamGia.GiaTri, 0)  AS Float(30))/100)) as 'TongTien' from HoaDonChiTiet\n"
                + "join ChiTietDoUong on HoaDonChiTiet.IdChiTietDoUong = ChiTietDoUong.Id\n"
                + "join HoaDon on HoaDonChiTiet.IdHoaDon = HoaDon.Id\n"
                + "left join GiamGia on GiamGia.MaGiamGia = HoaDon.MaGiamGia\n"
                + "where hoadon.NgayTao between ? and ?";
        try (Connection con = dbConnection_Sang.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, d1);
            ps.setObject(2, d2);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int getTongHoaDonTheoNgayChon(Date d1, Date d2) {
        String sql = "select count(HoaDon.Ma) from HoaDon\n"
                + "where hoadon.NgayTao between ? and ?";
        try (Connection con = dbConnection_Sang.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, d1);
            ps.setObject(2, d2);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int getTongSanPhamTheoNgayChon(Date d1, Date d2) {
        String sql = "select sum(HoaDonChiTiet.SoLuong) from HoaDonChiTiet "
                + "join HoaDon on HoaDon.Id = HoaDonChiTiet.IdHoaDon\n"
                + "where hoadon.NgayTao between ? and ?";
        try (Connection con = dbConnection_Sang.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, d1);
            ps.setObject(2, d2);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<BieuDoThongKe> getBieuDo() {
        ArrayList<BieuDoThongKe> lstBieuDoThongKe = new ArrayList<>();
        String sql = "select ChiTietDoUong.TenDoUong, sum(HoaDonChiTiet.SoLuong) as 'SoLuong' from HoaDonChiTiet\n"
                + "join ChiTietDoUong on ChiTietDoUong.Id = HoaDonChiTiet.IdChiTietDoUong\n"
                + "join HoaDon on HoaDon.Id = HoaDonChiTiet.IdHoaDon \n"
                + "where convert(varchar, hoadon.ngaytao, 105) = (select convert(varchar,getDate(), 105))\n"
                + "group by ChiTietDoUong.TenDoUong";
        try (Connection con = dbConnection_Sang.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BieuDoThongKe bieuDoThongKe = new BieuDoThongKe();
                bieuDoThongKe.setTenDoUong(rs.getString("TenDoUong"));
                bieuDoThongKe.setSoLuong(rs.getInt("SoLuong"));
                lstBieuDoThongKe.add(bieuDoThongKe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstBieuDoThongKe;
    }

    @Override
    public ArrayList<BieuDoThongKe> getBieuDoTheoNgay(Date d1, Date d2) {
        ArrayList<BieuDoThongKe> lstBieuDoThongKe = new ArrayList<>();
        String sql = "select ChiTietDoUong.TenDoUong, sum(HoaDonChiTiet.SoLuong) as 'SoLuong' from HoaDonChiTiet\n"
                + "join ChiTietDoUong on ChiTietDoUong.Id = HoaDonChiTiet.IdChiTietDoUong\n"
                + "join HoaDon on HoaDon.Id = HoaDonChiTiet.IdHoaDon \n"
                + "where hoadon.NgayTao between ? and ?\n"
                + "group by ChiTietDoUong.TenDoUong";
        try (Connection con = dbConnection_Sang.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, d1);
            ps.setObject(2, d2);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BieuDoThongKe bieuDoThongKe = new BieuDoThongKe();
                bieuDoThongKe.setTenDoUong(rs.getString("TenDoUong"));
                bieuDoThongKe.setSoLuong(rs.getInt("SoLuong"));
                lstBieuDoThongKe.add(bieuDoThongKe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstBieuDoThongKe;
    }

}
