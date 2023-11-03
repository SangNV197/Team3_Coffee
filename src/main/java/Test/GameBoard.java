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
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
 
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
 
/**
 * Class that models and creates a memory game of Concentration. The user clicks
 * on two cards. If the two cards are a match, the cards remain face up. If they 
 * are not the same color, then the cards turn back down. Using memory, the user
 * must match each set of cards so that they all face up. When all matches are 
 * found and the cards are facing up, the game is over. Each game that is played
 * the cards are put in random order on the game board. This game is built to be
 * a game with a 3x4 board and 12 cards, meaning there are 6 colors: Red, Yellow, 
 * Green, Blue, Magenta, and Orange.
 *  
 * @author Mike Horn
 * @author Sridhar Narayan
 * @date October 14, 2011
 *
 */
public class GameBoard extends JFrame {
 
    /**the main panel the cards are played on*/
    private JPanel mainPanel;
    /**the game controller that turns the cards and sets game progress*/
    private GameController gc;
    /**the deck of GameCards that will be used in the game*/
    private GameCard[] deck;
    private JMenuBar menuBar;
    private JMenu gameMenu;
    private JMenuItem newItem;
    private JMenuItem exitItem;
     
 
 
    /**
     * Creates an instance of the GameBoard with random cards and is visible.
     */
    public GameBoard() {
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Memory Game");
 
        mainPanel = createGamePanel();
         
        Container c = getContentPane();
         
        c.add(mainPanel);
         
        buildMenuBar();
 
        setVisible(true);
 
    }
     
    /**
     * A helper method to create and set up the main panel. 
     * @return returns a JPanel that will become the mainPanel and essentially
     * the game board.
     */
    private JPanel createGamePanel() {
 
        JPanel p = new JPanel();
 
        p.setLayout(new GridLayout(3, 4));
 
        gc = new GameController();
 
        setDeck(); //initialize deck of cards (set colors in random order)
         
        for (int i = 0; i < deck.length; i++) {
            p.add(deck[i]); //add cards to game board
        }
 
        return p;
    }   
     
    /**
     * A helper method to initialize the deck of cards and assigns 2 random cards
     * a color: Red, Yellow, Green, Blue, Magenta, Orange.
     */
    private void setDeck() {
        deck = new GameCard[12];
        Color[] colors = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, 
                Color.MAGENTA, Color.ORANGE}; //colors used in game
        ArrayList<Integer> numList = shuffle(); //random numbers set
 
        for (int i = 0; i < deck.length; i++) { //sets 2 random cards to a color
            deck[numList.get(i).intValue() - 1] = new GameCard(colors[i / 2]);
            deck[numList.get(i).intValue() - 1].addActionListener(gc);
            i++;
            deck[numList.get(i).intValue() - 1] = new GameCard(colors[i / 2]);
            deck[numList.get(i).intValue() - 1].addActionListener(gc);
        }
    }
     
    /**
     * A helper method to create an ArrayList of the numbers 1 through 12 in a
     * random order. This will help create a deck of colored cards in the 
     * random order created here.
     * @return returns an ArrayList of the numbers 1 through 12 in random order
     */
    private ArrayList<Integer> shuffle() {
        Random numGen = new Random(); //create Random
        ArrayList<Integer> numList = new ArrayList<Integer>(); 
        int i = 0;
        while (i < 12) {
            Integer num1 = new Integer(numGen.nextInt(12) + 1);
            if (!numList.contains(num1)) { //checks if random number used yet
                numList.add(num1);
                i++; //if found move on to next number
            }
        }
         
        return numList; //return list of random numbers
    }
     
    /**
     * A helper method to build the menu.
     */
    private void buildMenuBar() {
        menuBar = new JMenuBar();
         
        buildGameMenu();
         
        menuBar.add(gameMenu);
         
        setJMenuBar(menuBar);
    }
     
    /**
     * A helper method that builds the Game Menu.
     */
    private void buildGameMenu() {
        newItem = new JMenuItem("New");
        newItem.setMnemonic(KeyEvent.VK_N);
        newItem.addActionListener(new NewListener());
         
        exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic(KeyEvent.VK_X);
        exitItem.addActionListener(new ExitListener());
         
        gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);
        gameMenu.add(newItem);
        gameMenu.add(exitItem);
    }
     
    /**
     * Main method to run and play the concentration game.
     * @param args
     */
    public static void main(String[] args) {
        GameBoard m = new GameBoard();
    }
     
    /**
     * Inner class that implements the ActionListener interface, which is used to
     * respond to button clicks on the GameBoard and control the overall game play
     * of Concentration. A user can not click the same card twice to get a match.
     * A user can not click cards that are already facing up to get them wrong,
     * which would turn them back over. The click count only increases by 1 when
     * a faced down card is clicked. When the game is over, the click count is 
     * printed out.
     * 
     * @author Michael Horn
     * @author Sridhar Narayan
     * @date October 14, 2011
     *
     */
    private class GameController implements ActionListener {
        private GameCard card1;
        private GameCard card2;
        private int count = 0;
         
        /**
         * Receives the action performed by the user and the game board is 
         * updated accordingly.
         */
        public void actionPerformed(ActionEvent arg0) {
            // TODO Auto-generated method stub
 
            // which card was clicked?
            GameCard pickedCard = (GameCard) arg0.getSource();
 
            if (!pickedCard.isFaceUp()) {
                // turn the card face up
                count++;
                pickedCard.faceUp();
 
                if (card1 == null) { //first card in potential match clicked
                    card1 = pickedCard;
                } else if (card2 == null) { //2nd card in potential match clicked
 
                    if (card1 != pickedCard) { //check same card not clicked twice
                        card2 = pickedCard;
                        // Wait 200 milliseconds
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
 
                if (card2 != null) { //Match is found
                    if (card1.equals(card2)) {
                        card1 = null;
                        card2 = null;
 
                    } else { //Chosen cards are not a match 
                        card1.faceDown();
                        card2.faceDown();
                        card1 = null;
                        card2 = null;
                    }
                }
                if(checkGame()) { //check for game completion
                    System.out.println("Game Over!");
                    System.out.println("You finished with " + count + " clicks!");
                }
                 
            }//if card is face up
        }//action performed method
         
        /**
         * A helper method to check if the game is completed. The game is 
         * completed when all matches are found and the cards are face up.
         * @return
         */
        private boolean checkGame() {
             
            for(int i = 0; i < deck.length; i++) { //go through deck
                if(!deck[i].isFaceUp()) {
                    return false; //face down card found, game not over
                }
            }
            return true; //all cards are face up, game over!
        }
 
    }
     
    private class NewListener implements ActionListener{
 
        public void actionPerformed(ActionEvent arg0) {
             
        }
         
    }
     
    private class ExitListener implements ActionListener {
         
        public void actionPerformed(ActionEvent arg0) {
            System.exit(0);
        }
    }
 
}