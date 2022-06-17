/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tankbattle;

/**
 *
 * @author MaxZhang
 */
//WASD and Space to move and shoot for Player 1
//Arrow Keys and Forward Slash to move and shoot for Player 2
import java.awt.Color;
import javax.swing.JFrame;
public class TankBattle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame screen = new JFrame();
        Game game = new Game();
      
        //sets up the ui for game
        screen.setBounds(10, 10, 900, 900);
        screen.setTitle("Tank Battle");
        screen.setBackground(Color.WHITE);
        screen.setResizable(false); //maybe dont need
        screen.add(game);
        screen.setVisible(true);
    }
    
}
