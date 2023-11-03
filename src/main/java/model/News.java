/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author 84374
 */
public class News {
    private String idBaiViet;
    private String title;
    private String des;
    private String content;
    private NhanVien  nv;
    private Date date;
    private byte[] hinhAnh;

    public News() {
    }

    public News(String idBaiViet, String title, String des, String content, NhanVien nv, Date date, byte[] hinhAnh) {
        this.idBaiViet = idBaiViet;
        this.title = title;
        this.des = des;
        this.content = content;
        this.nv = nv;
        this.date = date;
        this.hinhAnh = hinhAnh;
    }

    public String getIdBaiViet() {
        return idBaiViet;
    }

    public void setIdBaiViet(String idBaiViet) {
        this.idBaiViet = idBaiViet;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NhanVien getNv() {
        return nv;
    }

    public void setNv(NhanVien nv) {
        this.nv = nv;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    @Override
    public String toString() {
        return "News{" + "idBaiViet=" + idBaiViet + ", title=" + title + ", des=" + des + ", content=" + content + ", nv=" + nv + ", date=" + date + ", hinhAnh=" + hinhAnh + '}';
    }

    
}
