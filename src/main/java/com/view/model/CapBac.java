/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.view.model;

/**
 *
 * @author Lê Chấn Khang
 */
public class CapBac {
    private String id;
    private String ten;

    public CapBac() {
    }

    public CapBac(String id, String ten) {
        this.id = id;
        this.ten = ten;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

//    @Override
//    public String toString() {
//        return ten;
//    }
    
    @Override
    public String toString() {
        return "CapBac{" + "id=" + id + ", ten=" + ten + '}';
    }
    
    
}
