/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author LENOVO T560
 */
public class GiaoCa {

    private String maGiaoCa;
    private String caLamViec;
    private Date ngayGiaoCa;
    private BigDecimal tongCong;
    private NhanVien nguoiGiao;
    private NhanVien nguoiNhan;
    private Time gioKiemKe;
    private BigDecimal thucKiem;
    private String trangThai;
    private String ghiChu;

    public GiaoCa() {
    }

    public GiaoCa(String maGiaoCa, String caLamViec, Date ngayGiaoCa, BigDecimal tongCong, NhanVien nguoiGiao, NhanVien nguoiNhan, Time gioKiemKe, BigDecimal thucKiem, String trangThai, String ghiChu) {
        this.maGiaoCa = maGiaoCa;
        this.caLamViec = caLamViec;
        this.ngayGiaoCa = ngayGiaoCa;
        this.tongCong = tongCong;
        this.nguoiGiao = nguoiGiao;
        this.nguoiNhan = nguoiNhan;
        this.gioKiemKe = gioKiemKe;
        this.thucKiem = thucKiem;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
    }

    public GiaoCa(String maGiaoCa, String caLamViec, Date ngayGiaoCa, NhanVien nguoiGiao, NhanVien nguoiNhan, BigDecimal tongCong, BigDecimal thucKiem, Time gioThucKiem) {
        this.maGiaoCa = maGiaoCa;
        this.caLamViec = caLamViec;
        this.ngayGiaoCa = ngayGiaoCa;
        this.tongCong = tongCong;
        this.nguoiGiao = nguoiGiao;
        this.nguoiNhan = nguoiNhan;
        this.gioKiemKe = gioKiemKe;
        this.thucKiem = thucKiem;
        this.gioKiemKe = gioThucKiem;
    }

    public NhanVien getNguoiGiao() {
        return nguoiGiao;
    }

    public void setNguoiGiao(NhanVien nguoiGiao) {
        this.nguoiGiao = nguoiGiao;
    }

    public NhanVien getNguoiNhan() {
        return nguoiNhan;
    }

    public void setNguoiNhan(NhanVien nguoiNhan) {
        this.nguoiNhan = nguoiNhan;
    }

    public String getMaGiaoCa() {
        return maGiaoCa;
    }

    public void setMaGiaoCa(String maGiaoCa) {
        this.maGiaoCa = maGiaoCa;
    }

    public Date getNgayGiaoCa() {
        return ngayGiaoCa;
    }

    public void setNgayGiaoCa(Date ngayGiaoCa) {
        this.ngayGiaoCa = ngayGiaoCa;
    }

    public BigDecimal getTongCong() {
        return tongCong;
    }

    public void setTongCong(BigDecimal tongCong) {
        this.tongCong = tongCong;
    }

    public Time getGioKiemKe() {
        return gioKiemKe;
    }

    public void setGioKiemKe(Time gioKiemKe) {
        this.gioKiemKe = gioKiemKe;
    }

    public BigDecimal getThucKiem() {
        return thucKiem;
    }

    public void setThucKiem(BigDecimal thucKiem) {
        this.thucKiem = thucKiem;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getCaLamViec() {
        return caLamViec;
    }

    public void setCaLamViec(String caLamViec) {
        this.caLamViec = caLamViec;
    }

}
