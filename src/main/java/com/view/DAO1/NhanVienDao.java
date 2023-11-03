/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.view.DAO1;

import com.view.model.CapBac;
import com.view.model.NhanVien;
import ultilities.DBConnection1;
import java.sql.*;
import java.util.ArrayList;
import ultilities.DBConnection;
/**
 *
 * @author Lê Chấn Khang
 */
public class NhanVienDao {

    final String INSERT_SQL = "INSERT INTO dbo.NhanVien(Ten,GioiTinh,NgaySinh,DiaChi,Sdt,TaiKhoan,MatKhau,IdCB,TrangThai,HinhAnh) values(?,?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE dbo.NhanVien set Ten=?,GioiTinh=?,NgaySinh=?,DiaChi=?,Sdt=?,TaiKhoan=?,MatKhau=?,IdCB=?,TrangThai=?,HinhAnh=? where Ma=?";
    final String DELETE_SQL = "DELETE FROM [dbo].[NhanVien] WHERE [ID] = ?";
    final String SELECT_BY_SQL = "SELECT * FROM [dbo].[NhanVien] WHERE [ID] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[NhanVien]";
    
    private DBConnection connection1 = new DBConnection();
    
    public ArrayList<NhanVien> selectALl() {
        DBConnection1 dbConn = new DBConnection1();
        ArrayList<NhanVien> listNhanVien = new ArrayList<>();
        DAO_CapBac dAO_CapBac = new DAO_CapBac();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_ALL_SQL);
            while (rs.next()) {
                CapBac tenCapBac = dAO_CapBac.selectByID(rs.getString("IdCB"));
                listNhanVien.add(new NhanVien(rs.getString("Id"), rs.getString("Ma"), rs.getString("Ten"), rs.getString("GioiTinh"), rs.getString("NgaySinh"), rs.getString("DiaChi"), rs.getString("Sdt"), rs.getString("TaiKhoan"), rs.getString("MatKhau"), tenCapBac, rs.getInt("TrangThai"), rs.getBytes("HinhAnh")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNhanVien;
    }
    
    
    public void save(NhanVien nv) {
        DBConnection dbConn = new DBConnection();
        dbConn.ExcuteDungna(INSERT_SQL, nv.getTen(), nv.getGioiTinh(), 
                nv.getNgaySinh(),nv.getDiaChi(),nv.getSdt(),nv.getTaiKhoan(),nv.getMatKhau(),nv.getCapBac().getId(),nv.getTrangThai(),nv.getImg());
    }
    
    
    public void update(NhanVien nv) {
        DBConnection dbConn = new DBConnection();
        dbConn.ExcuteDungna(UPDATE_SQL, nv.getTen(), nv.getGioiTinh(), 
                nv.getNgaySinh(),nv.getDiaChi(),nv.getSdt(),nv.getTaiKhoan(),nv.getMatKhau(),nv.getCapBac().getId(),nv.getTrangThai(),nv.getImg(),nv.getMa());     }
    
    public NhanVien selectByAccount(String account){
        String sql = "select * from NhanVien where TaiKhoan =?";
        try(Connection con = connection1.getConnection();
                PreparedStatement st = con.prepareStatement(sql)) {
                st.setObject(1, account);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {                
                NhanVien nhanVien = new NhanVien();
                nhanVien.setId(rs.getString(1));
                nhanVien.setMa(rs.getString(2));
                nhanVien.setTen(rs.getString(3));
                nhanVien.setTenDem(rs.getString(4));
                nhanVien.setHo(rs.getString(5));
                nhanVien.setGioiTinh(rs.getString(6));
                nhanVien.setNgaySinh(rs.getString(7));
                nhanVien.setDiaChi(rs.getString(8));
                nhanVien.setSdt(rs.getString(9));
                nhanVien.setTaiKhoan(rs.getString(10));
                nhanVien.setMatKhau(rs.getString(11));
                //nhanVien.setIdCB(rs.getString(12));
                nhanVien.setTrangThai(rs.getInt(13));
                
                return nhanVien;
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }
    
    public Boolean updatePass(String tk,NhanVien nhanVien){
        String sql = "update NhanVien set MatKhau=? where TaiKhoan=?";
        try(Connection con = connection1.getConnection();
                PreparedStatement st = con.prepareStatement(sql)) {
                st.setObject(1, nhanVien.getMatKhau());
                st.setObject(2, tk);
                int result  = st.executeUpdate();
                return result>0;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return false;
    }
    
//    public ArrayList<NhanVien> getList(){
//        ArrayList<NhanVien> list = new ArrayList<>();
//        String sql = "select NhanVien.Ma,NhanVien.Ten,TenDem,Ho,GioiTinh,NgaySinh,DiaChi,Sdt,TaiKhoan,MatKhau,CapBac.Ten,TrangThai,HinhAnh from NhanVien join CapBac on NhanVien.IdCB=CapBac.Id";
//        try(Connection con = connection1.getConnection();
//                PreparedStatement st = con.prepareStatement(sql)) {
//                ResultSet rs = st.executeQuery();
//                while (rs.next()) {                
//                NhanVien nhanVien = new NhanVien();               
//                nhanVien.setMa(rs.getString(1));
//                nhanVien.setTen(rs.getString(2));
//                nhanVien.setTenDem(rs.getString(3));
//                nhanVien.setHo(rs.getString(4));
//                nhanVien.setGioiTinh(rs.getString(5));
//                nhanVien.setNgaySinh(rs.getString(6));
//                nhanVien.setDiaChi(rs.getString(7));
//                nhanVien.setSdt(rs.getString(8));
//                nhanVien.setTaiKhoan(rs.getString(9));
//                nhanVien.setMatKhau(rs.getString(10));
//                nhanVien.setIdCB(rs.getString(11));
//                nhanVien.setTrangThai(rs.getInt(12));
//                nhanVien.setImg(rs.getBytes(13));
//                list.add(nhanVien);
//                }
//        } catch (Exception e) {
//           e.printStackTrace();
//        }
//        return list;
//    }
    
    public ArrayList<CapBac> getCboCapbac(){
        ArrayList<CapBac> lit = new ArrayList<>();
        String sql = "select Id,Ten from CapBac";
        try(Connection con = connection1.getConnection();
                PreparedStatement st = con.prepareStatement(sql)) {
                ResultSet rs = st.executeQuery();
                while (rs.next()) {                
                  CapBac cb = new CapBac();
                  cb.setId(rs.getString("Id"));
                  cb.setTen(rs.getString("Ten"));
                  lit.add(cb);
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return lit;
    }
    
    public Boolean addNew(NhanVien nhanVien){
        String sql = "insert into NhanVien(Ten,GioiTinh,NgaySinh,DiaChi,Sdt,TaiKhoan,MatKhau,IdCB,TrangThai,HinhAnh) values(?,?,?,?,?,?,?,?,?,?)";
        try(Connection con = connection1.getConnection();
                PreparedStatement st = con.prepareStatement(sql)) {
                st.setObject(1, nhanVien.getTen());
                st.setObject(2, nhanVien.getGioiTinh());
                st.setObject(3, nhanVien.getNgaySinh());
                st.setObject(4, nhanVien.getDiaChi());
                st.setObject(5, nhanVien.getSdt());
                st.setObject(6, nhanVien.getTaiKhoan());
                st.setObject(7, nhanVien.getMatKhau());
                st.setObject(8, nhanVien.getCapBac().getId());
                st.setObject(9, nhanVien.getTrangThai());
                st.setObject(10, nhanVien.getImg());
                int result = st.executeUpdate();
                return result>0;
        } catch (Exception e) {
             e.printStackTrace();
        }
        return false;
    }
    
    public Boolean deleteNew(String ma){
        String sql = "delete NhanVien where Ma =?";
        try(Connection con = connection1.getConnection();
                PreparedStatement st = con.prepareStatement(sql)) {
                st.setObject(1, ma);
                int result = st.executeUpdate();
                return result>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<CapBac> getCboCapbacTest(){
        ArrayList<CapBac> lit = new ArrayList<>();
        String sql = "select Id,Ten from CapBac group by Id,Ten";
        try(Connection con = connection1.getConnection();
                PreparedStatement st = con.prepareStatement(sql)) {
                ResultSet rs = st.executeQuery();
                while (rs.next()) {                
                  CapBac cb = new CapBac();
                  cb.setId(rs.getString("Id"));
                  cb.setTen(rs.getString("Ten"));
                  lit.add(cb);
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return lit;
    }
    
    
    public static void main(String[] args) {
        NhanVienDao dao = new NhanVienDao();
        //NhanVien nhanVien = new NhanVien("", "NV03", "Tam", "Nu", "2000-01-01", "Ha Noi", "0143432545", "Tam", "12344", capBac, 0, "");
//        //System.out.println(dao.selectByAccount("son"));
         ArrayList<CapBac> list = dao.getCboCapbacTest();
         for (CapBac o : list) {
             System.out.println(o);
        }
//         System.out.println(list);
    }
    
}
