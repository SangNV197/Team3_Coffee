/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import model.CapBac;
import java.sql.*;
import ultilities.DBConnection;

/**
 *
 * @author MSI-G8
 */
public class CapBacRepository {
    public List<CapBac> getALL(){
        List<CapBac> capBacs = new ArrayList<>();
        
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT Id, Ma, Ten, LuongPartime " + "FROM CapBac";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                CapBac capBac = new CapBac();
                capBac.setIdCB(rs.getString("Id"));
                capBac.setMaCB(rs.getString("Ma"));
                capBac.setTenCB(rs.getString("Ten"));
                capBac.setLuongPastTime(rs.getBigDecimal("LuongPartime"));
                
                capBacs.add(capBac);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return capBacs;
    }
    
    public void them(CapBac cb){
        try {
            Connection con = DBConnection.getConnection();
            String query = "INSERT INTO CapBac " + " (Ma, Ten, LuongPartime) " +
                                                            "VALUES (?, ?, ?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, cb.getMaCB());
            st.setString(2, cb.getTenCB());
            st.setBigDecimal(3, cb.getLuongPastTime());
            
            st.execute();
            st.close();
            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void xoa(String id){
        try {
            Connection con = DBConnection.getConnection();
            String query = "DELETE FROM CapBac " + "WHERE  (Id = ?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, id);
            
            st.execute();
            st.close();
            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void sua(CapBac cb, String id){
        try {
            Connection con = DBConnection.getConnection();
            String query = "UPDATE CapBac " + "SET Ma = ?, Ten = ?, LuongPartime = ?" + " WHERE  (Id = ?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, cb.getMaCB());
            st.setString(2, cb.getTenCB());
            st.setBigDecimal(3, cb.getLuongPastTime());
            st.setString(4, id);
            
            st.executeUpdate();
            st.close();
            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean checkMa(String ma){
         int count = 0;
         try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT COUNT(*) FROM CapBac WHERE Ma = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, ma);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                count = rs.getInt(1);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
         return count > 0;
    }
    
}
