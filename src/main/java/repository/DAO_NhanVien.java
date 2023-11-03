/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.CapBac;
import model.NhanVien;
import ultilities.DBConnection1;

/**
 *
 * @author 84374
 */
public class DAO_NhanVien {

    final String INSERT_SQL = "INSERT INTO dbo.NhanVien(Ma,Ten,TenDem,Ho,GioiTinh,NgaySinh,DiaChi,Sdt,TaiKhoan,MatKhau,IdCB,TrangThai,HinhAnh)\n"
            + "VALUES( ?,?,?,?,?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE dbo.NhanVien SET Ten=?, TenDem=?,Ho=?,GioiTinh=?,NgaySinh=?,DiaChi=?,Sdt=?,TaiKhoan=?,MatKhau=?,IdCB=?,TrangThai=?,HinhAnh=? WHERE Id=?";
    final String DELETE_SQL = "DELETE FROM [dbo].[NhanVien] WHERE [Id] = ?";
    final String SELECT_BY_SQL = "SELECT * FROM NhanVien JOIN CapBac \n"
            + "            ON NhanVien.IdCB = CapBac.Id\n"
            + "            WHERE NhanVien.Ma = ?";
    final String SELECT_BY_IDNV_SQL = " SELECT * FROM NhanVien WHERE Id = ?";
    final String SELECT_BY_MANV = " SELECT * FROM NhanVien WHERE Ma = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[NhanVien];";
    final String SELECT_CBONV_SQL = "  SELECT Id,Ten AS HOTENNHANVIEN FROM NhanVien";
    final String SELECT_BY_TenNV_SQL = " SELECT Id FROM NhanVien WHERE Ten like ?";

    public DAO_NhanVien() {
    }

    public ArrayList<NhanVien> selectALl() {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<NhanVien> lstNhanVien = new ArrayList<>();
        //get capBac obj
        DAO_CapBac dao_capBac = new DAO_CapBac();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQL);
            while (rs.next()) {
                CapBac capBac = dao_capBac.selectByID(rs.getString("IdCB"));
                lstNhanVien.add(new NhanVien(rs.getString("Id"), rs.getString("Ma"), rs.getNString("Ten"), rs.getNString("TenDem"), rs.getNString("Ho"), rs.getNString("GioiTinh"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("Sdt"), rs.getString("TaiKhoan"), rs.getString("MatKhau"), capBac, rs.getInt("TrangThai"), rs.getBytes("HinhAnh"), rs.getString("NgayTao")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstNhanVien;
    }

    public ArrayList<NhanVien> selectCBONhanVien() {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<NhanVien> lstNhanVien = new ArrayList<>();
        //get capBac obj
        DAO_CapBac dao_capBac = new DAO_CapBac();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_CBONV_SQL);
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId(rs.getString("Id"));
                nv.setTen(rs.getString("HOTENNHANVIEN"));
                lstNhanVien.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstNhanVien;
    }

    public NhanVien selectByID(String id) {
        DBConnection1 dbConn = new DBConnection1();
        NhanVien nhanVien = new NhanVien();
        ArrayList<NhanVien> lstNhanVien = new ArrayList<>();
        DAO_CapBac dao_capBac = new DAO_CapBac();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_BY_IDNV_SQL, id);
            while (rs.next()) {
                CapBac capBac = dao_capBac.selectByID(rs.getString("IdCB"));
                lstNhanVien.add(new NhanVien(rs.getString("Id"), rs.getString("Ma"), rs.getNString("Ten"), rs.getNString("TenDem"), rs.getNString("Ho"), rs.getNString("GioiTinh"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("Sdt"), rs.getString("TaiKhoan"), rs.getString("MatKhau"), capBac, rs.getInt("TrangThai"), rs.getBytes("HinhAnh"), rs.getString("NgayTao")));
                nhanVien = lstNhanVien.get(0);
                System.out.println("dao nhan vien :"+ nhanVien.toString());
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhanVien;
    }
    public NhanVien selectByMa(String ma) {
        DBConnection1 dbConn = new DBConnection1();
        NhanVien nhanVien = new NhanVien();
        ArrayList<NhanVien> lstNhanVien = new ArrayList<>();
        DAO_CapBac dao_capBac = new DAO_CapBac();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_BY_MANV, ma);
            while (rs.next()) {
                CapBac capBac = dao_capBac.selectByID(rs.getString("IdCB"));
                lstNhanVien.add(new NhanVien(rs.getString("Id"), rs.getString("Ma"), rs.getNString("Ten"), rs.getNString("TenDem"), rs.getNString("Ho"), rs.getNString("GioiTinh"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("Sdt"), rs.getString("TaiKhoan"), rs.getString("MatKhau"), capBac, rs.getInt("TrangThai"), rs.getBytes("HinhAnh"), rs.getString("NgayTao")));
                nhanVien = lstNhanVien.get(0);
                break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhanVien;
    }
    

    public NhanVien selectByIDNhanVien(String idNV) {
        DBConnection1 dbConn = new DBConnection1();
        NhanVien nhanVien = new NhanVien();
        ArrayList<NhanVien> lstNhanVien = new ArrayList<>();
        DAO_CapBac dao_capBac = new DAO_CapBac();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_BY_IDNV_SQL, idNV);
            while (rs.next()) {
                CapBac capBac = dao_capBac.selectByID(rs.getString("IdCB"));
                lstNhanVien.add(new NhanVien(rs.getString("Id"), rs.getString("Ma"), rs.getNString("Ten"), rs.getNString("TenDem"), rs.getNString("Ho"), rs.getNString("GioiTinh"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("Sdt"), rs.getString("TaiKhoan"), rs.getString("MatKhau"), new CapBac(), rs.getInt("TrangThai"), rs.getBytes("HinhAnh"), rs.getString("NgayTao")));

                nhanVien = lstNhanVien.get(0);
                break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhanVien;
    }

    public void save(NhanVien nhanVien) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(INSERT_SQL, nhanVien.getMa(), nhanVien.getTen(), nhanVien.getTenDem(), nhanVien.getHo(), nhanVien.getGioiTinh(), nhanVien.getDob(), nhanVien.getDiaChi(),
                    nhanVien.getSdt(), nhanVien.getTaiKhoan(), nhanVien.getMatKhau(), nhanVien.getCapBac().getIdCB(), nhanVien.getTrangThai(), nhanVien.getImg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(NhanVien nhanVien) {
        DBConnection1 dbConn = new DBConnection1();
        try {
            dbConn.ExcuteSQL(UPDATE_SQL, nhanVien.getTen(), nhanVien.getTenDem(), nhanVien.getHo(), nhanVien.getGioiTinh(), nhanVien.getDob(),
                    nhanVien.getDiaChi(), nhanVien.getSdt(), nhanVien.getTaiKhoan(), nhanVien.getMatKhau(), nhanVien.getCapBac(), nhanVien.getTrangThai(), nhanVien.getImg(), nhanVien.getId());
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

    public String selectByTenNhanVien(String tenNV) {
        DBConnection1 dbConn = new DBConnection1();
        NhanVien nhanVien = new NhanVien();
        ArrayList<NhanVien> lstNhanVien = new ArrayList<>();
        DAO_CapBac dao_capBac = new DAO_CapBac();
        String idNhanVien = null;
        try {
            Connection connection = dbConn.openDbConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_BY_TenNV_SQL);
            ps.setString(1, "%" + tenNV + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                idNhanVien = rs.getString("Id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idNhanVien;
    }


}
