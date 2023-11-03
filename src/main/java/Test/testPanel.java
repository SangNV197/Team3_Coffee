/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

/**
 *
 * @author 84374
 */
import java.awt.Graphics;

public class testPanel extends javax.swing.JPanel {

    public boolean draw = true;

    public testPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 603, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 299, Short.MAX_VALUE)
        );
    }// </editor-fold>                        

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (draw == true) {
            g.drawLine(0, 0, 20, 35);
        }
    }

    public void change() {
        draw = !draw;
        this.repaint();

    }

}
