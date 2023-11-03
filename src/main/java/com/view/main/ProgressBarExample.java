/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.view.main;

/**
 *
 * @author 84374
 */
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

public class ProgressBarExample extends JFrame {

    JProgressBar jb;
    int i = 0, num = 0;

    ProgressBarExample() {
        jb = new JProgressBar(0, 2000);
        jb.setBounds(40, 40, 160, 30);
        jb.setValue(0);
        jb.setStringPainted(true);
        add(jb);
        setSize(250, 150);
        setLayout(null);
    }

    public void iterate() {
        while (i <= 2000) {
            jb.setValue(i);
            i = i + 20;
            try {
                Thread.sleep(150);
            } catch (Exception e) {
            }
        }
    }

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null,"testt");
        ProgressBarExample m = new ProgressBarExample();
        m.setVisible(true);
        m.iterate();
        m.setVisible(false);
        JOptionPane.showMessageDialog(null,"new frame");
    }
}
