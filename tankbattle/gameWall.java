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
    int wallXPosArray[] = {50,100,150,200,250, 50,50,50,50,      250,250,250,250, 100,200, 800,750,700,650,600, 800,800,800,800, 600,600,600,600, 750,650, 250,250,250,250,250, 600,600,600,600,600};
    int wallYPosArray[] = {150,150,150,150,150, 200,250,300,350, 200,250,300,350, 350,350, 150,150,150,150,150, 200,250,300,350, 200,250,300,350, 350,350, 550,600,650,700,750, 550,600,650,700,750};
    int treeXPosArray[] = {350, 500, 350, 430, 550, 500, 400, 500};
    int treeYPosArray[] = {300, 500, 700, 200, 550, 200, 450, 660};
            
    int walls[] = new int[40];
    int trees[] = new int[8];
    
    private ImageIcon wallIcon;
    private ImageIcon treeIcon;
    
    public GameWall()
    {
        wallIcon = new ImageIcon("src\\tankbattle\\images\\wall.jpg");
        for (int i = 0; i < walls.length; i++)
        {
            walls[i] = 1;
        }
        treeIcon = new ImageIcon("src\\tankbattle\\images\\tree.png");
        for (int i = 0; i < trees.length; i++)
        {
            trees[i] = 1;
        }
    }
    
    public void drawWalls(Component c, Graphics g)
    {
        for (int i = 0; i < walls.length; i++)
        {
            if (walls[i] == 1)
            {
                wallIcon.paintIcon(c, g, wallXPosArray[i], wallYPosArray[i]);
            }
        }
    }
    
    public void drawTrees(Component c, Graphics g)
    {
        for (int i = 0; i < trees.length; i++)
        {
            if (trees[i] == 1)
            {
                treeIcon.paintIcon(c, g, treeXPosArray[i], treeYPosArray[i]);
            }
        }
    }
    
    public boolean hasCollidedWall(int x, int y)
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
    
    public boolean hasCollidedTree(int x, int y)
    {
        boolean collided = false;
        for (int i = 0; i < trees.length; i++)
        {
            if (trees[i] == 1)
            {
                if (new Rectangle(x, y, 10, 10).intersects(new Rectangle(treeXPosArray[i], treeYPosArray[i], 50, 50)))
                {
                    trees[i] = 0;
                    collided = true;
                    break;
                }
            }
        }
        return collided;
    }
}
