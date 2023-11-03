/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author 84374
 */
public class HoaDon {
    private String id;
    private Ban ban;
    private KhachHang khachHang;
    private NhanVien nhanVien;
    private String ma;
    private Date ngayTao;
    private String thoiGian;
    private int  tinhTrangThanhToan;
    private int  trangThaiPhaChe;
    private GiamGia maGiamGia;
    private int stt;
    private BigDecimal moneyTake;

    public HoaDon() {
    }

    public HoaDon(String id, Ban ban, KhachHang khachHang, NhanVien nhanVien, String ma, Date ngayTao, String thoiGian, int tinhTrangThanhToan, int trangThaiPhaChe, GiamGia maGiamGia, int stt, BigDecimal moneyTake) {
        this.id = id;
        this.ban = ban;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.ma = ma;
        this.ngayTao = ngayTao;
        this.thoiGian = thoiGian;
        this.tinhTrangThanhToan = tinhTrangThanhToan;
        this.trangThaiPhaChe = trangThaiPhaChe;
        this.maGiamGia = maGiamGia;
        this.stt = stt;
        this.moneyTake = moneyTake;
    }

    
    
    
    
    public String getId() {
        return id;
    }

    
    public void setId(String id) {
        this.id = id;
    }

    public Ban getBan() {
        return ban;
    }

    public void setBan(Ban ban) {
        this.ban = ban;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public int getTinhTrangThanhToan() {
        return tinhTrangThanhToan;
    }

    public void setTinhTrangThanhToan(int tinhTrangThanhToan) {
        this.tinhTrangThanhToan = tinhTrangThanhToan;
    }

    public int getTrangThaiPhaChe() {
        return trangThaiPhaChe;
    }

    public void setTrangThaiPhaChe(int trangThaiPhaChe) {
        this.trangThaiPhaChe = trangThaiPhaChe;
    }

    public GiamGia getMaGiamGia() {
        return maGiamGia;
    }

    public void setMaGiamGia(GiamGia maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public BigDecimal getMoneyTake() {
        return moneyTake;
    }

    public void setMoneyTake(BigDecimal moneyTake) {
        this.moneyTake = moneyTake;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "id=" + id + ", ban=" + ban + ", khachHang=" + khachHang + ", nhanVien=" + nhanVien + ", ma=" + ma + ", ngayTao=" + ngayTao + ", thoiGian=" + thoiGian + ", tinhTrangThanhToan=" + tinhTrangThanhToan + ", trangThaiPhaChe=" + trangThaiPhaChe + ", maGiamGia=" + maGiamGia + ", stt=" + stt + ", moneyTake=" + moneyTake + '}';
    }

}
