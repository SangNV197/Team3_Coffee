/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.Time;
import model.ChamCong;
import repository.DAO_ChamCong;

/**
 *
 * @author LENOVO T560
 */
public class ChamCongService {

    private DAO_ChamCong repositoryChamCong;

    public ChamCongService() {
        repositoryChamCong = new DAO_ChamCong();
    }

    public void save(ChamCong chamCong) {
        repositoryChamCong.save(chamCong);
    }

    public void update(String id, ChamCong chamCong) {
        repositoryChamCong.update(id, chamCong);
    }

    public ChamCong selectByID(Time gioVao) {
        return repositoryChamCong.selectByID(gioVao);
    }
}
