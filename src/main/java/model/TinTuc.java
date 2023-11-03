/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Date;
/**
 *
 * @author hungd
 */
public class TinTuc {
    private String id;

    private String tieuDe;
    
    private String moTa;
    
    private String noiDung;
    
    private NhanVien nhanVien;

    private Date ngayTao;

    private byte[] hinhAnh;

    public TinTuc() {
    }

    public TinTuc(String id, String tieuDe, String moTa, String noiDung, NhanVien nhanVien, Date ngayTao, byte[] hinhAnh) {
        this.id = id;
        this.tieuDe = tieuDe;
        this.moTa = moTa;
        this.noiDung = noiDung;
        this.nhanVien = nhanVien;
        this.ngayTao = ngayTao;
        this.hinhAnh = hinhAnh;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    @Override
    public String toString() {
        return "TinTuc{" + "id=" + id + ", tieuDe=" + tieuDe + ", moTa=" + moTa + ", noiDung=" + noiDung + ", nhanVien=" + nhanVien + ", ngayTao=" + ngayTao + ", hinhAnh=" + hinhAnh + '}';
    }

    
    
    
}
