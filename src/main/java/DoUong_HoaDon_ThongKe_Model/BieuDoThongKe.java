/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoUong_HoaDon_ThongKe_Model;

/**
 *
 * @author ADMIN
 */
public class BieuDoThongKe {
    
    private String tenDoUong;
    
    private int soLuong;

    public BieuDoThongKe() {
    }

    public BieuDoThongKe(String tenDoUong, int soLuong) {
        this.tenDoUong = tenDoUong;
        this.soLuong = soLuong;
    }

    public String getTenDoUong() {
        return tenDoUong;
    }

    public void setTenDoUong(String tenDoUong) {
        this.tenDoUong = tenDoUong;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return "BieuDoThongKe{" + "tenDoUong=" + tenDoUong + ", soLuong=" + soLuong + '}';
    }
    
    public static void main(String[] args) {
        System.out.println("");
    }
    
    
}
