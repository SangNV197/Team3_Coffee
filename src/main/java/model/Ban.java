/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 84374
 */
public class Ban {
    private int idBan;
    private String ten;
    private String ngayTao;

    public Ban() {
    }

    public Ban(int idBan, String ten, String ngayTao) {
        this.idBan = idBan;
        this.ten = ten;
        this.ngayTao = ngayTao;
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

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    @Override
    public String toString() {
        return "Ban{" + "idBan=" + idBan + ", ten=" + ten + ", ngayTao=" + ngayTao + '}';
    }


}
