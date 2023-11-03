/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;
import java.util.List;
import model.NhanVien;

import java.sql.Date;
import viewModel.QLNhapKho;
/**
 *
 * @author MSI-G8
 */
public interface INhapKhoService {
    public List<QLNhapKho> getALL();
    public List<NhanVien> getCBBNV();
    public NhanVien timTheoID(String id);
    public void them(QLNhapKho nk);
    public boolean xoa(String id);
    public void sua(String id,QLNhapKho nk);
    public List<QLNhapKho> timTheoTungTruong(String tenSP, Date ngayBatDau,Date ngayKetThuc, int soLuongMin,int soLuongMax, int giaMin,int giaMax);
}
