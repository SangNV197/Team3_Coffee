/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.CapBac;
import repository.CapBacRepository;
import viewModel.QLCapBac;

/**
 *
 * @author MSI-G8
 */
public class CapBacService implements ICapBacService{

    private CapBacRepository capBacRepository = new CapBacRepository();
    
    @Override
    public List<QLCapBac> getALL() {
        List<QLCapBac> capBacs = new ArrayList<>();
        List<CapBac> list = capBacRepository.getALL();
        for(CapBac cb : list){
            QLCapBac vModel = new QLCapBac(cb.getIdCB(), cb.getMaCB(), cb.getTenCB(), cb.getLuongPastTime());
            capBacs.add(vModel);
        }
        return capBacs;
    }

    @Override
    public void them(QLCapBac cb) {
        CapBac domainModel = new CapBac("", cb.getMaCB(), cb.getTenCB(), cb.getLuongPastTime(), "");
        if(capBacRepository.checkMa(cb.getIdCB())){
             JOptionPane.showMessageDialog(null, "Mã đã Tồn tại");
             return;
        }else{
            capBacRepository.them(domainModel);
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        }
       
        
    }

    @Override
    public void xoa(String id) {
        capBacRepository.xoa(id);
    }

    @Override
    public void sua(QLCapBac cb, String id) {
         CapBac domainModel = new CapBac(cb.getIdCB(), cb.getMaCB(), cb.getTenCB(), cb.getLuongPastTime(), "");
        if(capBacRepository.checkMa(cb.getMaCB())){
            JOptionPane.showMessageDialog(null, "Sửa thành công");
            capBacRepository.sua(domainModel, id);
        }else{
            JOptionPane.showMessageDialog(null, "Mã không thể sửa");
            return ;
        }
         
    }
    
}
