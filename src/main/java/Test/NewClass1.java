/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author 84374
 */
public class NewClass1 {
    public static void main(String[] args) {
        JLabel toe = new JLabel("I'm primary text");
        JFrame cow = new JFrame("Primary Window");
        JPanel cowpanel = new JPanel();
        cowpanel.add(toe);
        cow.setContentPane(cowpanel);
        cow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        cow.pack();
        cow.setVisible(true);
        JButton tow = new JButton("Change");
        tow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toe.setText("Hi!, I'm secondary text!");
            }
        });
        JFrame dog = new JFrame("Secondary Window");
        JPanel dogPanel = new JPanel();
        dog.setContentPane(dogPanel);
        dogPanel.add(tow);
        dog.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        dog.pack();
        dog.setVisible(true);
    }
}
