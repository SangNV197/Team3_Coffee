/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import model.BanHung;
import ultilities.DBConnection;
/**
 *
 * @author MSI-G8
 */
public class BanRepository {
    public List<BanHung> getALL(){
        List<BanHung> bans = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT IdBan, Ten "
                    + "FROM Ban";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                BanHung ban = new BanHung();
                ban.setIdBan(rs.getInt("IdBan"));
                ban.setTen(rs.getString("Ten"));
                
                bans.add(ban);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bans;
    }
    
    public boolean checkMa(int id){
        int count = 0;
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT COUNT(*) FROM Ban WHERE IdBan = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                count = rs.getInt(1);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count > 0;
    }
    
    public void them(BanHung ban){
        try {
            Connection con = DBConnection.getConnection();
            String query = "INSERT INTO Ban "
                    + " (IdBan, Ten) "
                    + "VALUES (?, ?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, ban.getIdBan());
            st.setString(2, ban.getTen());
            
            st.execute();
            
            st.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void xoa(int id){
        try {
            Connection con = DBConnection.getConnection();
            String query = "DELETE FROM Ban " + "WHERE  (IdBan = ?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, id);
            
            st.execute();
            
            st.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void sua(BanHung ban){
        try {
            Connection con = DBConnection.getConnection();
            String query = "UPDATE Ban "
                    + "SET Ten =? "
                    + "WHERE  (IdBan = ?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, ban.getTen());
            st.setInt(2, ban.getIdBan());
            
            st.executeUpdate();
            
            st.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
