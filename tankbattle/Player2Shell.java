/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tankbattle;

/**
 *
 * @author MaxZhang
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class Player2Shell {
    private double x;
    private double y;
    
    public Player2Shell (double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void move(String tankDirection)
    {
        if (tankDirection.equals("right"))
        {
            x += 5;
        }
        else if (tankDirection.equals("left"))
        {
            x -= 5;
        }
        else if (tankDirection.equals("down"))
        {
            y += 5;
        }
        else if (tankDirection.equals("up"))
        {
            y -= 5;
        }
    }
    
    public void draw(Graphics g)
    {
        g.setColor(Color.YELLOW);
        g.fillOval((int)x, (int)y, 10, 10);
    }
    
    public int getX()
    {
        return (int)x;
    }
    
    public int getY()
    {
        return (int)y;
    }
    
}
