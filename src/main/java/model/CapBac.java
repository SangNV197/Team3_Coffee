/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;

/**
 *
 * @author LENOVO T560
 */
public class CapBac {

    private String idCB;
    private String maCB;
    private String tenCB;
    private BigDecimal luongPastTime;
    private String ngayTao;

    public CapBac() {
    }

    public CapBac(String idCB, String maCB, String tenCB, BigDecimal luongPastTime, String ngayTao) {
        this.idCB = idCB;
        this.maCB = maCB;
        this.tenCB = tenCB;
        this.luongPastTime = luongPastTime;
        this.ngayTao = ngayTao;
    }

    public String getIdCB() {
        return idCB;
    }

    public void setIdCB(String idCB) {
        this.idCB = idCB;
    }

    public String getMaCB() {
        return maCB;
    }

    public void setMaCB(String maCB) {
        this.maCB = maCB;
    }

    public String getTenCB() {
        return tenCB;
    }

    public void setTenCB(String tenCB) {
        this.tenCB = tenCB;
    }

    public BigDecimal getLuongPastTime() {
        return luongPastTime;
    }

    public void setLuongPastTime(BigDecimal luongPastTime) {
        this.luongPastTime = luongPastTime;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    @Override
    public String toString() {
        return "CapBac{" + "idCB=" + idCB + ", maCB=" + maCB + ", tenCB=" + tenCB + ", luongPastTime=" + luongPastTime + ", ngayTao=" + ngayTao + '}';
    }
  
}
