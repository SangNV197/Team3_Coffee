/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import viewModel.QLCapBac;

/**
 *
 * @author MSI-G8
 */
public interface ICapBacService {
    public List<QLCapBac> getALL();
    public void them(QLCapBac cb);
    public void xoa(String id);
    public void sua(QLCapBac cb, String id);
    
}
