/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.LoaiDoUong;
import repository.LoaiDoUongRepository;
import viewModel.QLLoaiDoUong;

/**
 *
 * @author MSI-G8
 */
public class LoaiDoUongService implements ILoaiDoUongService{

    private LoaiDoUongRepository loaiDUongRepository = new LoaiDoUongRepository();
    
    @Override
    public List<QLLoaiDoUong> getALL() {
        List<QLLoaiDoUong> ds = new ArrayList<>();
        List<LoaiDoUong> list = loaiDUongRepository.getAll();
        for(LoaiDoUong ld : list){
            QLLoaiDoUong vModel = new QLLoaiDoUong(ld.getId(), ld.getMa(), ld.getTenLoaiDoUong());
            ds.add(vModel);
        }
        return ds;
    }

    @Override
    public void them(QLLoaiDoUong ld) {
        LoaiDoUong domainModel = new LoaiDoUong(ld.getId(), ld.getMa(), ld.getTen());
        if(loaiDUongRepository.checkMa(ld.getMa())){
            JOptionPane.showMessageDialog(null, "Mã đã tồn tại");
            return ;
        }else{
            loaiDUongRepository.them(domainModel);
            JOptionPane.showMessageDialog(null, "Thêm Thành công");
        }
        
        
    
    }

    @Override
    public void xoa(String id) {
         try {
            loaiDUongRepository.xoa(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void sua(QLLoaiDoUong ld) {
        LoaiDoUong domainModel = new LoaiDoUong(ld.getId(), ld.getMa(), ld.getTen());
        if(loaiDUongRepository.checkMa(ld.getMa())){
            loaiDUongRepository.sua(domainModel);
            JOptionPane.showMessageDialog(null, "Sửa thành công");
        }else{
            JOptionPane.showMessageDialog(null, "Mã không thể sửa");
        }
        
    }

    @Override
    public List<QLLoaiDoUong> timKiem(String ma, String ten) {
        List<QLLoaiDoUong> qlLoaiDoUong = new ArrayList<>();
        for(LoaiDoUong ld : loaiDUongRepository.timKiem(ma, ten)){
            QLLoaiDoUong vModel = new QLLoaiDoUong(ld.getId(), ld.getMa(), ld.getTenLoaiDoUong());
            qlLoaiDoUong.add(vModel);
        }
        return qlLoaiDoUong;
        
    }
    
    
}
