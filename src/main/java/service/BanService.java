/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.Ban;
import repository.DAO_Ban;

/**
 *
 * @author 84374
 */
public class BanService {

    DAO_Ban dao_ban = new DAO_Ban();

    public void saveHoaDon(Ban ban) {
        dao_ban.save(ban);
    }

    public ArrayList<Ban> getListBan() {
        ArrayList<Ban> lstBan = new ArrayList<>();
        lstBan = dao_ban.selectALl();
        return lstBan;
    }

    public Ban getBanByID(int idBan) {
        Ban ban = new Ban();
        ban = dao_ban.selectByID(idBan);
        return ban;
    }

    public void deleteBan(int idBan) {
        dao_ban.delete(idBan);
    }
    
    public int getMax(){
         return dao_ban.getMaxTbl();
    }

//    public void updateHoaDon(HoaDon hoaDon){
//       dao_HoaDon.update(hoaDon);
//    }
}
