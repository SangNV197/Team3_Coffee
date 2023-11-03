/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

/**
 *
 * @author 84374
 */
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatingJPanel extends JPanel{

  public UpdatingJPanel() {
    Timer timer = new Timer( 1500, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent e ) {
        UpdatingJPanel.this.add( new JLabel( "A label" ) );
        UpdatingJPanel.this.revalidate();
        UpdatingJPanel.this.repaint();
        if( UpdatingJPanel.this.getComponentCount() == 0 ){
          ( ( Timer ) e.getSource() ).stop();
        }
      }
    } );
    timer.setRepeats( true );
    timer.start();
  }

  public static void main( String[] args ) {
    EventQueue.invokeLater( new Runnable() {
      @Override
      public void run() {
        JFrame testFrame = new JFrame(  );
        testFrame.getContentPane().add( new UpdatingJPanel() );
        //not using pack() as the panel is still empty and I want avoid
        //resizing when adding labels as that triggers a repaint
        testFrame.setSize( 200,200 );
        testFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        testFrame.setVisible( true );
      }
    } );

  }
}
