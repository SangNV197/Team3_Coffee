/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import viewModel.QLBan;

/**
 *
 * @author MSI-G8
 */
public interface IBanService {
    public List<QLBan> getALl();
    public void them(QLBan ban);
    public void xoa(int id);
    public void sua(QLBan ban);
}
