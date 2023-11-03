/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author LENOVO T560
 */
public class ChamCong {

    private String idChamCong;
    private NhanVien nv;
    private Date ngay;
    private Time gioVao;
    private Time gioRa;
    private Date ngayTao;
    private String ngayFormatted; // Thuộc tính kiểu String để lưu giá trị ngày được định dạng
    private String gioVaoFormatted; // Th

    public ChamCong() {
    }

    public ChamCong(String idChamCong, NhanVien nv, Date ngay, Time gioVao, Time gioRa, Date ngayTao, String ngayFormatted, String gioVaoFormatted) {
        this.idChamCong = idChamCong;
        this.nv = nv;
        this.ngay = ngay;
        this.gioVao = gioVao;
        this.gioRa = gioRa;
        this.ngayTao = ngayTao;
        this.ngayFormatted = ngayFormatted;
        this.gioVaoFormatted = gioVaoFormatted;
    }

    public String getIdChamCong() {
        return idChamCong;
    }

    public void setIdChamCong(String idChamCong) {
        this.idChamCong = idChamCong;
    }

    public NhanVien getNv() {
        return nv;
    }

    public void setNv(NhanVien nv) {
        this.nv = nv;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public void setGioVao(Time gioVao) {
        this.gioVao = gioVao;
    }

    public Time getGioVao() {
        return gioVao;
    }

    public void setGioRa(Time gioRa) {
        this.gioRa = gioRa;
    }

    public Time getGioRa() {
        return gioRa;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public String getNgayFormatted() {
        return ngayFormatted;
    }

    public void setNgayFormatted(String ngayFormatted) {
        this.ngayFormatted = ngayFormatted;
    }

    public String getGioVaoFormatted() {
        return gioVaoFormatted;
    }

    public void setGioVaoFormatted(String gioVaoFormatted) {
        this.gioVaoFormatted = gioVaoFormatted;
    }

}
