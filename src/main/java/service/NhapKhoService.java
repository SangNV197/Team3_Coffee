/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import model.NhanVien;
import model.NhapKho;
import repository.NhanVienRepository;
import repository.NhapKhoRepository;
import viewModel.QLNhapKho;

/**
 *
 * @author MSI-G8
 */
public class NhapKhoService implements INhapKhoService{

    private NhapKhoRepository nhapKhoRepository = new NhapKhoRepository();
    private NhanVienRepository nhanVienRepository = new NhanVienRepository();
    
    @Override
    public List<QLNhapKho> getALL() {
        List<QLNhapKho> ds = new ArrayList<>();
        List<NhapKho> list = nhapKhoRepository.getALL();
        for (NhapKho nk : list) {
            NhanVien idNV = nhanVienRepository.timIDNhanVien(nk.getNhanVien().getId());
            QLNhapKho vModel = new QLNhapKho(nk.getId(), idNV, nk.getTenSP(),
                    nk.getNgayNhap(), nk.getDonVi(), nk.getSoLuong(), nk.getDonGia());
            ds.add(vModel);

        }
        return ds;
    }

    @Override
    public List<NhanVien> getCBBNV() {
        return nhanVienRepository.getCBBTenNV();
    }

    @Override
    public NhanVien timTheoID(String id) {
        return nhanVienRepository.timIDNhanVien(id);
    }

    @Override
    public void them(QLNhapKho nk) {
        NhanVien idNV = nhanVienRepository.timIDNhanVien(nk.getNhanVien().getId());
        NhapKho domainModel = new NhapKho("", idNV, nk.getTenSP(), nk.getNgayNhap(),
                nk.getDonVi(), nk.getSoLuong(), nk.getDonGia());
        nhapKhoRepository.them(domainModel);
    }

    @Override
    public boolean xoa(String id) {
         try {
            nhapKhoRepository.xoa(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void sua(String id, QLNhapKho nk) {
        NhanVien idNV = nhanVienRepository.timIDNhanVien(nk.getNhanVien().getId());//Tìm tên nhân viên theo idnv
        NhapKho domainModel = new NhapKho(id, idNV, nk.getTenSP(), nk.getNgayNhap(),
                nk.getDonVi(), nk.getSoLuong(), nk.getDonGia());
        nhapKhoRepository.sua(id, domainModel);
    }

    @Override
    public List<QLNhapKho> timTheoTungTruong(String tenSP, Date ngayBatDau, Date ngayKetThuc, int soLuongMin, int soLuongMax, int giaMin, int giaMax) {
            
        List<QLNhapKho> lstQLNhapKhos = new ArrayList<>();
        for (NhapKho nhapKho : nhapKhoRepository.timTheoTungTruong(tenSP, ngayBatDau, ngayKetThuc, soLuongMin, soLuongMax, giaMin, giaMax)) {
            QLNhapKho qlNhapKho=new QLNhapKho(nhapKho.getId(), nhapKho.getNhanVien(), nhapKho.getTenSP(), nhapKho.getNgayNhap(),
                    nhapKho.getDonVi(), nhapKho.getSoLuong(), nhapKho.getDonGia());
            lstQLNhapKhos.add(qlNhapKho);
        }
        return lstQLNhapKhos;
    }

    
    
}
