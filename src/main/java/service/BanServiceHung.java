/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.BanHung;
import repository.BanRepository;
import viewModel.QLBan;

/**
 *
 * @author MSI-G8
 */
public class BanServiceHung implements IBanService{

    private BanRepository banRepository = new BanRepository();
    
    @Override
    public List<QLBan> getALl() {
        List<QLBan> ds = new ArrayList<>();
        List<BanHung> list = banRepository.getALL();
        for(BanHung b : list){
            QLBan ban = new QLBan(b.getIdBan(), b.getTen());
            ds.add(ban);
        }
        return ds;
    }

    @Override
    public void them(QLBan ban) {
        BanHung domainModel = new BanHung(ban.getIdBan(), ban.getTen());
        if(banRepository.checkMa(ban.getIdBan())){
            JOptionPane.showMessageDialog(null, "Id đã tồn tại");
            return;
        }else{
            JOptionPane.showMessageDialog(null, "Thêm Thành Công");
            banRepository.them(domainModel);
        }
    }

    @Override
    public void xoa(int id) {
        banRepository.xoa(id);
    }

    @Override
    public void sua(QLBan ban) {
        BanHung domainModel = new BanHung(ban.getIdBan(), ban.getTen());
        if(banRepository.checkMa(ban.getIdBan())){
            JOptionPane.showMessageDialog(null, "Sửa Thành Công");
             banRepository.sua(domainModel);
        }else{
            JOptionPane.showMessageDialog(null, "Id không thể sửa");
            return;
        }
    }
    
}
