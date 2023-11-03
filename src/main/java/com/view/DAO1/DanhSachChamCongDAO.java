/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.view.DAO1;

import com.view.model.ChamCongK;
import com.view.model.LuongK;
import ultilities.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DanhSachChamCongDAO {

    private DBConnection connection1 = new DBConnection();

    public ArrayList<ChamCongK> getDanhSachChamCOng() {
        ArrayList<ChamCongK> list = new ArrayList<>();
        String sql = "SELECT [ChamCong].IdNV,NhanVien.Ten,Ngay,CaLamViec,GioVao,GioRa, DATEDIFF(HOUR, [GioVao], [GioRa]) as SoGio from [ChamCong] join NhanVien on NhanVien.Id=[ChamCong].IdNV";
        try ( Connection con = connection1.getConnection();  PreparedStatement st = con.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ChamCongK chamCongK = new ChamCongK();
                chamCongK.setIdNV(rs.getString(1));
                chamCongK.setTen(rs.getString(2));
                chamCongK.setNgay(rs.getDate(3));
                chamCongK.setCaLamViec(rs.getString(4));
                chamCongK.setGioVao(rs.getString(5));
                chamCongK.setGioRa(rs.getString(6));
                chamCongK.setSoGioLam(rs.getInt(7));
                list.add(chamCongK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<LuongK> getLuong(Date ngayA, Date ngayB) {
        ArrayList<LuongK> listA = new ArrayList<>();
        String sql = "SELECT\n"
                + "[IdNV],[NhanVien].Ten,\n"
                + "    SUM(DATEDIFF(HOUR, [GioVao], [GioRa])) AS TongGio,\n"
                + "	[CapBac].LuongPartime,\n"
                + "	SUM(DATEDIFF(HOUR, [GioVao], [GioRa])) * ([CapBac].LuongPartime) as Luong\n"
                + "FROM [dbo].[ChamCong] \n"
                + "JOIN [dbo].[NhanVien] ON [NhanVien].[Id] = [ChamCong].[IdNV] join CapBac on CapBac.Id=NhanVien.IdCB\n"
                + "where ([Ngay] BETWEEN ? and ?) \n"
                + "group by ChamCong.IdNV,[NhanVien].Ten,[CapBac].LuongPartime";
        try ( Connection con = connection1.getConnection();  PreparedStatement st = con.prepareStatement(sql)) {
            st.setObject(1, ngayA);
            st.setObject(2, ngayB);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                LuongK luongK = new LuongK();
                luongK.setId(rs.getString(1));
                luongK.setTen(rs.getString(2));
                luongK.setTongGio(rs.getInt(3));
                luongK.setLuongPartime(rs.getInt(4));
                luongK.setLuong(rs.getDouble(5));
                listA.add(luongK);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listA;
    }

    public static void main(String[] args) {
        DanhSachChamCongDAO chamCong = new DanhSachChamCongDAO();
        ArrayList<ChamCongK> list = chamCong.getDanhSachChamCOng();
        for (ChamCongK c : list) {
            System.out.println(c);
        }
    }

}
