/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoUong_HoaDon_ThongKe_Model;

/**
 *
 * @author Sang
 */
public class LoaiDoUong {
    
    private String id;
    
    private String ma;
    
    private String tenLoaiDoUong;

    public LoaiDoUong() {
    }

    public LoaiDoUong(String id, String ma, String tenLoaiDoUong) {
        this.id = id;
        this.ma = ma;
        this.tenLoaiDoUong = tenLoaiDoUong;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTenLoaiDoUong() {
        return tenLoaiDoUong;
    }

    public void setTenLoaiDoUong(String tenLoaiDoUong) {
        this.tenLoaiDoUong = tenLoaiDoUong;
    }

    @Override
    public String toString() {
        return "LoaiDoUong{" + "id=" + id + ", ma=" + ma + ", tenDoUong=" + tenLoaiDoUong + '}';
    }
    
    
}
