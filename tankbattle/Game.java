/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tankbattle;

/**
 *
 * @author MaxZhang
 */

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.Timer;
import javax.swing.JPanel;
import java.awt.event.KeyListener;

public class Game extends JPanel implements ActionListener{
    private GameWall wall;
    private GameWall tree;
    
    //Sets up every instance variable related to player/tank 1
    private ImageIcon tank1;	
    private int p1XCoord = 150;
    private int p1YCoord = 250;	
    private boolean p1FaceRight = false;
    private boolean p1FaceLeft = false;
    private boolean p1FaceDown = false;
    private boolean p1FaceUp = true;	
    private int p1TankHealth = 100;
    private boolean p1IsShoot = false;
    private String p1ShellDir = "";
    
    //Sets up every instance variable related to player/tank 2
    private ImageIcon tank2;	
    private int p2XCoord = 700;
    private int p2YCoord = 250;	
    private boolean p2FaceRight = false;
    private boolean p2FaceLeft = false;
    private boolean p2FaceDown = false;
    private boolean p2FaceUp = true;	
    private int p2TankHealth = 100;
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
        wall = new GameWall();
        tree = new GameWall();
        p1Keyboard = new Player1Keyboard();
        p2Keyboard = new Player2Keyboard();
        setFocusable(true); //maybe dont need
        addKeyListener(p1Keyboard);
        addKeyListener(p2Keyboard);
        setFocusTraversalKeysEnabled(false); //idk what this does
        
        timer = new Timer(delay, this);
        timer.start(); //or this
    }
    
    @Override
    public void paint(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0, 100, 900, 900);
        
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, 900, 100); //can change to the top of the screen instead
        
        wall.drawWalls(this, g);
        tree.drawTrees(this, g);
        
        if(playGame);
        {
            //Orients direction for player 1
            if (p1FaceUp)
            {
                tank1 = new ImageIcon("src\\tankbattle\\images\\player1_tank_up.png");
            }
            else if (p1FaceLeft)
            {
                tank1 = new ImageIcon("src\\tankbattle\\images\\player1_tank_left.png");
            }
            else if (p1FaceDown)
            {
                tank1 = new ImageIcon("src\\tankbattle\\images\\player1_tank_down.png");
            }
            else if (p1FaceRight)
            {
                tank1 = new ImageIcon("src\\tankbattle\\images\\player1_tank_right.png");
            }
            int p1Spawn = (int)(Math.random() * 3);
            tank1.paintIcon(this, g, p1XCoord, p1YCoord);
            
            
            //Orients direction for player 2
            if (p2FaceUp)
            {
                tank2 = new ImageIcon("src\\tankbattle\\images\\player2_tank_up.png");
            }
            else if (p2FaceLeft)
            {
                tank2 = new ImageIcon("src\\tankbattle\\images\\player2_tank_left.png");
            }
            else if (p2FaceDown)
            {
                tank2 = new ImageIcon("src\\tankbattle\\images\\player2_tank_down.png");
            }
            else if (p2FaceRight)
            {
                tank2 = new ImageIcon("src\\tankbattle\\images\\player2_tank_right.png");
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
                    p2TankHealth -= 20;
                    p1Shell = null;
                    p1IsShoot = false;
                    p1ShellDir = "";
                }
                
                //if shell shoots into wall cancel shell
                if (wall.hasCollidedWall(p1Shell.getX(), p1Shell.getY()))
                {
                    p1Shell = null;
                    p1IsShoot = false;
                    p1ShellDir = "";
                }
                
                //if shell shoots into tree cancel shell
                if (tree.hasCollidedTree(p1Shell.getX(), p1Shell.getY()))
                {
                    p1Shell = null;
                    p1IsShoot = false;
                    p1ShellDir = "";
                }
                
                //if shell shoots out of bounds cancel shell
                if (p1Shell.getX() < 1 || p1Shell.getX() > 880 || p1Shell.getY() < 110 || p1Shell.getY() > 880)
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
                    p1TankHealth -= 20;
                    p2Shell = null;
                    p2IsShoot = false;
                    p2ShellDir = "";
                }
                
                //if shell shoots into wall cancel shell
                if (wall.hasCollidedWall(p2Shell.getX(), p2Shell.getY()))
                {
                    p2Shell = null;
                    p2IsShoot = false;
                    p2ShellDir = "";
                }
                
                //if shell shoots into tree cancel shell
                if (tree.hasCollidedTree(p2Shell.getX(), p2Shell.getY()))
                {
                    p2Shell = null;
                    p2IsShoot = false;
                    p2ShellDir = "";
                }
                
                //if shell shoots out of bounds cancel shell
                if (p2Shell.getX() < 1 || p2Shell.getX() > 880 || p2Shell.getY() < 110 || p2Shell.getY() > 880)
                {
                    p2Shell = null;
                    p2IsShoot = false;
                    p2ShellDir = "";
                }
            }
        }
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 15));
        g.drawString("Tank Health", 400, 50);
        g.drawString("Player 1: " + p1TankHealth + " HP", 300, 75);
        g.drawString("Player 2: " + p2TankHealth + " HP", 500, 75);
        
        if (p1TankHealth <= 0)
        {
            g.setColor(Color.WHITE);
            g.setFont(new Font("serif", Font.BOLD, 60));
            g.drawString("Game Over", 300, 450);
            g.drawString("Player 2 Wins!", 265, 525);
            playGame = false;
            g.setColor(Color.WHITE);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("(\"R\" to Restart)", 340, 600);
        }
        else if (p2TankHealth <= 0)
        {
            g.setColor(Color.WHITE);
            g.setFont(new Font("serif", Font.BOLD, 60));
            g.drawString("Game Over", 300, 450);
            g.drawString("Player 1 Wins!", 265, 525);
            playGame = false;
            g.setColor(Color.WHITE);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("(\"R\" to Restart)", 340, 600);
        }
        
        g.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        timer.start();
        repaint();
    }
    
    private class Player1Keyboard implements KeyListener
    {
        @Override
        public void keyTyped(KeyEvent e)
        {
        }
        @Override
        public void keyReleased(KeyEvent e)
        {
        }
        @Override
        public void keyPressed(KeyEvent e)
        {
            //Restart game during game over screen
            if (e.getKeyCode() == KeyEvent.VK_R && (p1TankHealth <= 0 || p2TankHealth <= 0))
            {
                wall = new GameWall();
                tree = new GameWall();
                
                int tank1Spawn = (int)(Math.random() * 3);
                if (tank1Spawn == 0)
                {
                    p1XCoord = 150;
                    p1YCoord = 250;    
                }
                else if (tank1Spawn == 1)
                {
                    p1XCoord = 200;
                    p1YCoord = 600;
                }
                else
                {
                    p1XCoord = 425;
                    p1YCoord = 625;
                }
                p1FaceUp = true;
                p1FaceLeft = false;
                p1FaceDown = false;
                p1FaceRight = false;
                
                int tank2Spawn = (int)(Math.random() * 3);
                if (tank2Spawn == 0)
                {
                    p2XCoord = 700;
                    p2YCoord = 250;    
                }
                else if (tank2Spawn == 1)
                {
                    p2XCoord = 700;
                    p2YCoord = 600;
                }
                else
                {
                    p2XCoord = 425;
                    p2YCoord = 125;
                }
                p2FaceUp = true;
                p2FaceLeft = false;
                p2FaceDown = false;
                p2FaceRight = false;

                p1TankHealth = 100;
                p2TankHealth = 100;
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
                if(!(p1YCoord < 110))
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
                if(!(p1YCoord > 810))
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
                if(!(p1XCoord > 830))
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
        @Override
        public void keyTyped(KeyEvent e)
        {
        }
        @Override
        public void keyReleased(KeyEvent e)
        {
        }
        @Override
        public void keyPressed(KeyEvent e)
        {            
            //Player 2 movements and shooting
            if (e.getKeyCode() == KeyEvent.VK_UP)
            {
                p2FaceUp = true;
                p2FaceLeft = false;
                p2FaceDown = false;
                p2FaceRight = false;
                if (!(p2YCoord < 110))
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
                if (!(p2YCoord > 810))
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
                if (!(p2XCoord > 830))
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
