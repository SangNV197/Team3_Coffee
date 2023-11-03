/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.view.form_Template;

import java.util.ArrayList;
import java.util.List;
import model.HoaDon;

/**
 *
 * @author 84374
 */
public class Container {

    public Container() {
    }

    public static interface hoaDonModel {

        public static interface Observer {

            public void  valueDidChange(hoaDonModel source, ArrayList<HoaDon> value);
        }

        public  ArrayList<HoaDon> getValue();

        public void addObserver(Observer observer);

        public void removeObserver(Observer observer);
    }

    public static interface MutableHoaDonModel extends hoaDonModel {

        public void setValue(ArrayList<HoaDon> value);
    }

    public static class DefaultHoaDonModel implements MutableHoaDonModel {

        private ArrayList<HoaDon> value;

        private List<hoaDonModel.Observer> observers;

        public DefaultHoaDonModel() {
            this(null);
        }

        public DefaultHoaDonModel(ArrayList<HoaDon> value) {
            this.value = value;
            observers = new ArrayList<hoaDonModel.Observer>(8);
        }

        @Override
        public void setValue(ArrayList<HoaDon> value) {
            this.value = value;
            fireValueDidChange(value);
        }

        @Override
        public ArrayList<HoaDon> getValue() {
            return value;
        }

        @Override
        public void addObserver(hoaDonModel.Observer observer) {
            observers.add(observer);
        }

        @Override
        public void removeObserver(hoaDonModel.Observer observer) {
            observers.remove(observer);
        }

        protected void fireValueDidChange(ArrayList<HoaDon> value) {
            for (hoaDonModel.Observer observer : observers) {
                observer.valueDidChange(this, value);
            }
        }
    }

}
