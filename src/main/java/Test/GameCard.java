/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

/**
 *
 * @author 84374
 */
import java.awt.Color;
import java.awt.Dimension;
 
import javax.swing.JButton;
 
/**
 * Class that represents the colored game card that goes on the game board of
 * concentration.
 * @author Sridhar Narayan
 * @author Mike Horn
 */
public class GameCard extends JButton {
 
    /**actual color of the card, hidden until facing up*/
    private Color myColor;
    /**default color of the card, shown while facing down*/
    private static Color defaultColor = Color.gray;
    /**default size of the card on the game board*/
    private static int width = 100, height = 100;
     
    /**
     * Creates an instance of the GameCard and assigns the color of the card.
     * @param c the color of the card
     */
    public GameCard(Color c) {
        myColor = c;
        setPreferredSize(new Dimension(width, height));
        faceDown();
    }
     
    public void faceUp() {
        // TODO Auto-generated method stub
        setBackground(myColor);
        setOpaque(true);
        paintImmediately(0, 0, getWidth(), getHeight());
 
    }
     
    public void faceDown() {
        setBackground(defaultColor);
        setOpaque(true);
        paintImmediately(0, 0, getWidth(), getHeight());
         
    }
 
    public Color getColor() {
        return myColor;
    }
 
    //returns true if the card is face up, false otherwise
    public boolean isFaceUp() {
        return getBackground() == myColor;
    }
     
    public boolean equals(GameCard other) {
        if(myColor == other.myColor){
            return true;
        }
        return false;
    }
     
     
}
