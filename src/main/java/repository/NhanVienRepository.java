/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.NhanVien;
import ultilities.DBConnection;
/**
 *
 * @author MSI-G8
 */
public class NhanVienRepository {
    public List<NhanVien> getCBBTenNV(){
        List<NhanVien> nhanViens = new ArrayList<NhanVien>();
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT Id, Ten, Ma FROM NhanVien";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                String id = rs.getString("Id");
                String ten = rs.getString("Ten");
                String ma = rs.getString("Ma");
//                String tenDem = rs.getString("TenDem");
//                String ho = rs.getString("Ho");
                
                NhanVien nhanVien = new NhanVien();
                nhanVien.setId(id);
                nhanVien.setTen(ten);
                nhanVien.setMa(ma);
//                nhanVien.setTenDem(tenDem);
//                nhanVien.setHo(ho);
               
               
                
                nhanViens.add(nhanVien);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhanViens;
    }
    
    public NhanVien timIDNhanVien(String id){
        List<NhanVien> nhanViens = new ArrayList<NhanVien>();
        NhanVien nv = new NhanVien();
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT Id, Ma, Ten, TenDem, Ho " + "FROM NhanVien WHERE Id = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                nv.setId(rs.getString("Id"));
                nv.setMa(rs.getString("Ma"));
                nv.setTen(rs.getString("Ten"));
                nv.setTenDem(rs.getString("TenDem"));
                nv.setHo(rs.getString("Ho"));
                
                nhanViens.add(nv);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nv;
    }
}
