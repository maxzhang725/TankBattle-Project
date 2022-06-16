/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tankbattle;

/**
 *
 * @author MaxZhang
 */
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class GameWall {
    int wallXPosArray[] = {50,350,450,550,50,300,350,450,550,150,150,450,550,250,50,100,150,550,250,350,450,550,50,250,350,550,50,150,250,300,350,550,50,150,250,350,450,550,50,250,350,550};
    int wallYPosArray[] = {50,50,50,50,100,100,100,100,100,150,200,200,200,250,300,300,300,300,350,350,350,350,400,400,400,400,450,450,450,450,450,450,500,500,500,500,500,500,550,550,550,550};
    
    int walls[] = new int[42];
    
    private ImageIcon wallIcon;
    
    public GameWall()
    {
        wallIcon = new ImageIcon("src\\tankbattle\\images\\wall.jpg");
        for (int i = 0; i < walls.length; i++)
        {
            walls[i] = 1;
        }
    }
    
    public void draw(Component c, Graphics g)
    {
        for (int i = 0; i < walls.length; i++)
        {
            if (walls[i] == 1)
            {
                wallIcon.paintIcon(c, g, wallXPosArray[i], wallYPosArray[i]);
            }
        }
    }
    
    public boolean hasCollided(int x, int y)
    {
        boolean collided = false;
        for (int i = 0; i < walls.length; i++)
        {
            if (walls[i] == 1)
            {
                if (new Rectangle(x, y, 10, 10).intersects(new Rectangle(wallXPosArray[i], wallYPosArray[i], 50, 50)))
                {
                    walls[i] = 0;
                    collided = true;
                    break;
                }
            }
        }
        return collided;
    }
}
