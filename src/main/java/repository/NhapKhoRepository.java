/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import java.math.BigDecimal;
import java.util.List;
import model.NhanVien;
import model.NhapKho;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import ultilities.DBConnection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
/**
 *
 * @author MSI-G8
 */
public class NhapKhoRepository {
    public List<NhapKho> getALL() {

        List<NhapKho> nhapKhos = new ArrayList<NhapKho>();
        NhanVienRepository nvRepo = new NhanVienRepository();

        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT Id, IdNV, TenSP, NgayNhap, DonVi, SoLuong,"
                    + " DonGia " + "FROM NhapKho";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString("Id");
                NhanVien idNV = nvRepo.timIDNhanVien(rs.getString("IdNV"));
                String tenSP = rs.getString("TenSP");
                Date ngayNhap = rs.getDate("NgayNhap");
                String donVi = rs.getString("DonVi");
                int soLuong = rs.getInt("SoLuong");
                BigDecimal donGia = rs.getBigDecimal("DonGia");

                NhapKho nhapKho = new NhapKho();
                nhapKho.setId(id);
                nhapKho.setNhanVien(idNV);
                nhapKho.setTenSP(tenSP);
                nhapKho.setNgayNhap(ngayNhap);
                nhapKho.setDonVi(donVi);
                nhapKho.setSoLuong(soLuong);
                nhapKho.setDonGia(donGia);

                nhapKhos.add(nhapKho);

            }
            return nhapKhos;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void them(NhapKho nk) {
//        NhanVienRepository nvRepo = new NhanVienRepository();
        try {
            Connection con = DBConnection.getConnection();
            String query = "INSERT INTO NhapKho " + " (IdNV, TenSP, NgayNhap, DonVi, SoLuong, DonGia) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, nk.getNhanVien().getId());
            st.setString(2, nk.getTenSP());
            st.setDate(3, nk.getNgayNhap());
            st.setString(4, nk.getDonVi());
            st.setInt(5, nk.getSoLuong());
            st.setBigDecimal(6, nk.getDonGia());

            st.execute();
            st.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean xoa(String id) throws Exception {

        Connection con = DBConnection.getConnection();
        String query = "DELETE FROM NhapKho WHERE Id = ? ";
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, id);

        return st.execute();
    }

    public void sua(String id, NhapKho nk) {
//        NhanVienRepository nvRepo = new NhanVienRepository();
        try {
            Connection con = DBConnection.getConnection();
            String query = "UPDATE NhapKho "
                    + "SET  IdNV =?, TenSP =?, NgayNhap = ?, DonVi =?, SoLuong =?, DonGia = ? "
                    + "WHERE  Id = ? ";

            PreparedStatement st = con.prepareStatement(query);

            st.setString(1, nk.getNhanVien().getId());
            st.setString(2, nk.getTenSP());
            st.setDate(3, nk.getNgayNhap());
            st.setString(4, nk.getDonVi());
            st.setInt(5, nk.getSoLuong());
            st.setBigDecimal(6, nk.getDonGia());
            st.setString(7, id);

            st.executeUpdate();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<NhapKho> timTheoTungTruong(String tenSP, Date ngayBatDau, Date ngayKetThuc, int soLuongMin, int soLuongMax, int giaMin, int giaMax) {
        List<NhapKho> nhapKhos = new ArrayList<>();
        String tenSPTimKiem = "%" + tenSP + "%";
        SimpleDateFormat spd = new SimpleDateFormat("dd/MM/yyyy");
        Date ngayBatDauTimKiem = null;
        Date ngayKetThucTimKiem = null;
        try {
            ngayBatDauTimKiem = ngayBatDau == null ? new Date(spd.parse("1/1/1900").getTime()) : ngayBatDau;
            ngayKetThucTimKiem = ngayKetThuc == null ? new Date(spd.parse("1/1/2900").getTime()) : ngayKetThuc;
        } catch (Exception e) {
        }

        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT Id, IdNV,TenSP, NgayNhap, DonVi, SoLuong, DonGia "
                    + "FROM NhapKho "
                    + "WHERE  (tenSP LIKE ?) and (NgayNhap between ? and ?)  and (SoLuong between ? and ?) and (DonGia between ? and ?)";
            System.out.println(query);
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, tenSPTimKiem);
            st.setDate(2, ngayBatDauTimKiem);
            st.setDate(3, ngayKetThucTimKiem);
            st.setInt(4, soLuongMin);
            st.setInt(5, soLuongMax);
            st.setInt(6, giaMin);
            st.setInt(7, giaMax);
            System.out.println(ngayBatDauTimKiem);
            System.out.println(ngayKetThucTimKiem);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
//                
//                NhanVien idNV = nvRepo.timIDNhanVien(rs.getString("IdNV"));
////                Date nNhap = rs.getDate("NgayNhap");
////                int soLuong = rs.getInt("SoLuong");

                NhanVien nv = new NhanVien();
                nv.setId(rs.getString("idNV"));
                NhapKho nhapKho = new NhapKho();
                nhapKho.setId(rs.getString("Id"));
                nhapKho.setNhanVien(nv);
                nhapKho.setTenSP(rs.getString("tenSP"));
                nhapKho.setNgayNhap(rs.getDate("NgayNhap"));
                nhapKho.setDonVi(rs.getString("DonVi"));
                nhapKho.setSoLuong(rs.getInt("SoLuong"));
                nhapKho.setDonGia(rs.getBigDecimal("DonGia"));
                nhapKhos.add(nhapKho);
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhapKhos;
    }
}
