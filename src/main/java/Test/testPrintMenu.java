/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import java.util.ArrayList;
import service.ChiTietDoUongNoIMGService;
import viewModel.ChiTietDoUongNoIMG;

/**
 *
 * @author 84374
 */
public class testPrintMenu {
    static ChiTietDoUongNoIMGService service  = new ChiTietDoUongNoIMGService();
    static  ArrayList<ChiTietDoUongNoIMG> lstMenu = new ArrayList<>();
    public static void main(String[] args) {
        lstMenu = service.getListChiTietDoUong();
        
    }
}
