/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

/**
 *
 * @author MSI-G8
 */
public class QLBan {
   private int idBan;
    private String ten;

    public QLBan() {
    }

    public QLBan(int idBan, String ten) {
        this.idBan = idBan;
        this.ten = ten;
    }
    
    public int getIdBan() {
        return idBan;
    }

    public void setIdBan(int idBan) {
        this.idBan = idBan;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @Override
    public String toString() {
        return "QLBan{" + "idBan=" + idBan + ", ten=" + ten + '}';
    }

    
    
}
