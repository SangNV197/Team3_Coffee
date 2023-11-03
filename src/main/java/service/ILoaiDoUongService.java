/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import viewModel.QLLoaiDoUong;

/**
 *
 * @author MSI-G8
 */
public interface ILoaiDoUongService {
     public List<QLLoaiDoUong> getALL();
    public void them(QLLoaiDoUong ld);
    public void xoa(String id);
    public void sua(QLLoaiDoUong ld);
    public List<QLLoaiDoUong> timKiem(String ma, String ten);
}
