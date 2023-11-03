/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.view.DAO1;

import com.view.model.CapBac;
import java.sql.ResultSet;
import java.util.ArrayList;
import ultilities.DBConnection1;

/**
 *
 * @author Lê Chấn Khang
 */
public class DAO_CapBac {

    final String SELECT_BY_SQL = "SELECT * FROM [dbo].[CapBac] WHERE [Id] = ?";
    
    
    public CapBac selectByID(String id) {
        DBConnection1 dbConn = new DBConnection1();
        CapBac capBac = new CapBac();
        ArrayList<CapBac> listCapBac = new ArrayList<>();
        try {
            ResultSet rs = dbConn.getDataFromQuery(SELECT_BY_SQL, id);
            while (rs.next()) {
                listCapBac.add(new CapBac(rs.getString("Id"), rs.getString("Ten")));
                capBac = listCapBac.get(0);
                break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return capBac;
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
