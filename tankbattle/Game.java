/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tankbattle;

/**
 *
 * @author MaxZhang
 */
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.Timer;
import javax.swing.JPanel;
import java.awt.event.KeyListener;

public class Game extends JPanel implements EventListener{
    private gameWall wall;
    
    //Sets up every instance variable related to player/tank 1
    private ImageIcon tank1;	
    private int p1XCoord = 200;
    private int p1YCoord = 550;	
    private boolean p1FaceRight = false;
    private boolean p1FaceLeft = false;
    private boolean p1FaceDown = false;
    private boolean p1FaceUp = true;	
    private int p1Tanks = 5;
    private boolean p1IsShoot = false;
    private String p1ShellDir = "";
    
    //Sets up every instance variable related to player/tank 2
    private ImageIcon tank2;	
    private int p2XCoord = 200;
    private int p2YCoord = 550;	
    private boolean p2FaceRight = false;
    private boolean p2FaceLeft = false;
    private boolean p2FaceDown = false;
    private boolean p2FaceUp = true;	
    private int p2Tanks = 5;
    private boolean p2IsShoot = false;
    private String p2ShellDir = "";
    
    private Player1Keyboard p1Keyboard;
    private Player2Keyboard p2Keyboard;
    private Player1Shell p1Shell = null;
    private Player2Shell p2Shell = null;

    private Timer timer;
    private int delay = 8;
    private boolean playGame = true;
    
    public Game()
    {
        wall = new gameWall();
        p1Keyboard = new Player1Keyboard();
        p2Keyboard = new Player2Keyboard();
        setFocusable(true); //maybe dont need
        addKeyListener(p1Keyboard);
        addKeyListener(p2Keyboard);
        setFocusTraversalKeysEnabled(false); //idk what this does
        
        timer = new Timer(delay, (ActionListener) this);
        timer.start(); //or this
    }
    
    public void paint(Graphics g)
    {
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, 650, 600);
        
        g.setColor(Color.WHITE);
        g.fillRect(660, 0, 140, 600); //can change to the top of the screen instead
        
        wall.draw(this, g);
        
        if(playGame);
        {
            //Orients direction for player 1
            if (p1FaceUp)
            {
                tank1 = new ImageIcon("player1_tank_up.png");
            }
            else if (p1FaceLeft)
            {
                tank1 = new ImageIcon("player1_tank_left.png");
            }
            else if (p1FaceDown)
            {
                tank1 = new ImageIcon("player1_tank_down_png");
            }
            else if (p1FaceRight)
            {
                tank1 = new ImageIcon("player1_tank_right.png");
            }
            tank1.paintIcon(this, g, p1XCoord, p1YCoord);
            
            
            //Orients direction for player 2
            if (p2FaceUp)
            {
                tank2 = new ImageIcon("player2_tank_up.png");
            }
            else if (p2FaceLeft)
            {
                tank2 = new ImageIcon("player2_tank_left.png");
            }
            else if (p2FaceDown)
            {
                tank2 = new ImageIcon("player2_tank_down_png");
            }
            else if (p2FaceRight)
            {
                tank2 = new ImageIcon("player2_tank_right.png");
            }
            tank2.paintIcon(this, g, p2XCoord, p2YCoord);
            
            //player 1 shooting mechanics
            if (p1Shell != null && p1IsShoot)
            {
                if (p1ShellDir.equals(""))
                {
                    if (p1FaceUp)
                    {
                        p1ShellDir = "up";
                    }
                    else if (p1FaceLeft)
                    {
                        p1ShellDir = "left";
                    }
                    else if (p1FaceDown)
                    {
                        p1ShellDir = "down";
                    }
                    else if (p1FaceRight)
                    {
                        p1ShellDir = "right";
                    }
                }
                else
                {
                    p1Shell.move(p1ShellDir);
                    p1Shell.draw(g);
                }
                
                if (new Rectangle(p1Shell.getX(), p1Shell.getY(), 10, 10).intersects(new Rectangle(p2XCoord, p2YCoord, 50, 50)))
                {
                    p2Tanks --;
                    p1Shell = null;
                    p1IsShoot = false;
                    p1ShellDir = "";
                }
                
                //if shell shoots into wall cancel shell
                if (wall.hasCollided(p1Shell.getX(), p1Shell.getY()))
                {
                    p1Shell = null;
                    p1IsShoot = false;
                    p1ShellDir = "";
                }
                
                //if shell shoots out of bounds cancel shell
                if (p1Shell.getX() < 1 || p1Shell.getX() > 630 || p1Shell.getY() < 1 || p1Shell.getY() > 580)
                {
                    p1Shell = null;
                    p1IsShoot = false;
                    p1ShellDir = "";
                }
            }
            
            //player 2 shooting mechanics
            if (p2Shell != null && p2IsShoot)
            {
                if (p2ShellDir.equals(""))
                {
                    if (p2FaceUp)
                    {
                        p2ShellDir = "up";
                    }
                    else if (p2FaceLeft)
                    {
                        p2ShellDir = "left";
                    }
                    else if (p2FaceDown)
                    {
                        p2ShellDir = "down";
                    }
                    else if (p2FaceRight)
                    {
                        p2ShellDir = "right";
                    }
                }
                else
                {
                    p2Shell.move(p2ShellDir);
                    p2Shell.draw(g);
                }
                
                if (new Rectangle(p2Shell.getX(), p2Shell.getY(), 10, 10).intersects(new Rectangle(p1XCoord, p1YCoord, 50, 50)))
                {
                    p1Tanks --;
                    p2Shell = null;
                    p2IsShoot = false;
                    p2ShellDir = "";
                }
                
                //if shell shoots into wall cancel shell
                if (wall.hasCollided(p2Shell.getX(), p2Shell.getY()))
                {
                    p2Shell = null;
                    p2IsShoot = false;
                    p2ShellDir = "";
                }
                
                //if shell shoots out of bounds cancel shell
                if (p2Shell.getX() < 1 || p2Shell.getX() > 630 || p2Shell.getY() < 1 || p2Shell.getY() > 580)
                {
                    p2Shell = null;
                    p2IsShoot = false;
                    p2ShellDir = "";
                }
            }
        }
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 15));
        g.drawString("Tanks Left", 700, 150);
        g.drawString("Player 1: " + p1Tanks, 670, 180);
        g.drawString("Player 2: " + p2Tanks, 670, 210);
        
        if (p1Tanks == 0)
        {
            g.setColor(Color.WHITE);
            g.setFont(new Font("serif", Font.BOLD, 60));
            g.drawString("Game Over", 200, 300);
            g.drawString("Player 2 Wins!", 180, 380);
            playGame = false;
            g.setColor(Color.WHITE);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("(Space to Restart)", 230, 430);
        }
        else if (p2Tanks == 0)
        {
            g.setColor(Color.WHITE);
            g.setFont(new Font("serif", Font.BOLD, 60));
            g.drawString("Game Over", 200, 300);
            g.drawString("Player 1 Wins!", 180, 380);
            playGame = false;
            g.setColor(Color.WHITE);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("(Space to Restart)", 230, 430);
        }
        
        g.dispose();
    }
    
    public void actionPerfromed(ActionEvent e)
    {
        timer.start();
        repaint();
    }
    
    private class Player1Keyboard implements KeyListener
    {
        public void keyTyped(KeyEvent e)
        {
        }
        public void keyReleased(KeyEvent e)
        {
        }
        public void KeyPressed(KeyEvent e)
        {
            //Restart game during game over screen
            if (e.getKeyCode() == KeyEvent.VK_R && (p1Tanks == 0 || p2Tanks == 0))
            {
                wall = new gameWall();
                p1XCoord = 200;
                p1YCoord = 550;
                p1FaceUp = true;
                p1FaceLeft = false;
                p1FaceDown = false;
                p1FaceRight = false;

                p2XCoord = 400;
                p2YCoord = 550;
                p2FaceUp = true;
                p2FaceLeft = false;
                p2FaceDown = false;
                p2FaceRight = false;

                p1Tanks = 5;
                p2Tanks = 5;
                playGame = true;
                repaint();
            }
            
            //Player 1 movements and shooting
            if (e.getKeyCode() == KeyEvent.VK_W)
            {
                p1FaceUp = true;
                p1FaceLeft = false;
                p1FaceDown = false;
                p1FaceRight = false;
                if(!(p1YCoord < 10))
                {
                    p1YCoord -= 10;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_A)
            {
                p1FaceUp = false;
                p1FaceLeft = true;
                p1FaceDown = false;
                p1FaceRight = false;
                if(!(p1XCoord < 10))
                {
                    p1XCoord -= 10;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_S)
            {
                p1FaceUp = false;
                p1FaceLeft = false;
                p1FaceDown = true;
                p1FaceRight = false;
                if(!(p1YCoord > 540))
                {
                    p1YCoord += 10;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_D)
            {
                p1FaceUp = false;
                p1FaceLeft = false;
                p1FaceDown = false;
                p1FaceRight = true;
                if(!(p1XCoord > 590))
                {
                    p1XCoord += 10;
                }
            }
            if(e.getKeyCode() == KeyEvent.VK_SPACE)
            {
                if (!p1IsShoot)
                {
                    if (p1FaceUp)
                    {
                        p1Shell = new Player1Shell(p1XCoord + 20, p1YCoord);
                    }
                    else if (p1FaceLeft)
                    {
                        p1Shell = new Player1Shell(p1XCoord, p1YCoord + 20);
                    }
                    else if (p1FaceDown)
                    {
                        p1Shell = new Player1Shell(p1XCoord + 20, p1YCoord + 40);
                    }
                    else if (p1FaceRight)
                    {
                        p1Shell = new Player1Shell(p1XCoord + 40, p1YCoord + 20);
                    }
                    
                    p1IsShoot = true;
                }
            }
        }  
    }
    
    private class Player2Keyboard implements KeyListener
    {
        public void keyTyped(KeyEvent e)
        {
        }
        public void keyReleased(KeyEvent e)
        {
        }
        public void KeyPressed(KeyEvent e)
        {            
            //Player 2 movements and shooting
            if (e.getKeyCode() == KeyEvent.VK_UP)
            {
                p2FaceUp = true;
                p2FaceLeft = false;
                p2FaceDown = false;
                p2FaceRight = false;
                if (!(p2YCoord < 10))
                {
                    p2YCoord -= 10;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT)
            {
                p2FaceUp = false;
                p2FaceLeft = true;
                p2FaceDown = false;
                p2FaceRight = false;
                if (!(p2XCoord < 10))
                {
                    p2XCoord -= 10;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN)
            {
                p2FaceUp = false;
                p2FaceLeft = false;
                p2FaceDown = true;
                p2FaceRight = false;
                if (!(p2YCoord > 540))
                {
                    p2YCoord += 10;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            {
                p2FaceUp = false;
                p2FaceLeft = false;
                p2FaceDown = false;
                p2FaceRight = true;
                if (!(p2XCoord > 590))
                {
                    p2XCoord += 10;
                }
            }
            if(e.getKeyCode() == KeyEvent.VK_SLASH)
            {
                if (!p2IsShoot)
                {
                    if (p2FaceUp)
                    {
                        p2Shell = new Player2Shell(p2XCoord + 20, p2YCoord);
                    }
                    else if (p2FaceLeft)
                    {
                        p2Shell = new Player2Shell(p2XCoord, p2YCoord + 20);
                    }
                    else if (p2FaceDown)
                    {
                        p2Shell = new Player2Shell(p2XCoord + 20, p2YCoord + 40);
                    }
                    else if (p2FaceRight)
                    {
                        p2Shell = new Player2Shell(p2XCoord + 40, p2YCoord + 20);
                    }
                    
                    p2IsShoot = true;
                }
            }            
        }
    }               
}
