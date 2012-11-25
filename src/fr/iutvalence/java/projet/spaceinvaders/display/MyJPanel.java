/**
 * 
 */
package fr.iutvalence.java.projet.spaceinvaders.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import fr.iutvalence.java.projet.spaceinvaders.Coordinates;
import fr.iutvalence.java.projet.spaceinvaders.Movable;

/**
 * @author Gallet Guyon
 *
 */
public class MyJPanel extends JPanel
{
	/**
	 * Array containing all monsters to draw.
	 */
	private Movable[] monsters;

	/**
	 * Array containing all tanks to draw.
	 */
	private Movable[] tanks;

	/**
	 * Array containing all shoots to draw.
	 */
	private Movable[] shoots;
	
	/**
	 * The maximum size of the area.<br/>
	 * No elements can move (so be drown) over this bound.
	 */
	private Coordinates maxSize;

	/**
	 * 
	 */
	private double ratio;
	
	public void paintComponent(Graphics g) 
	{
		int i= 0;
		
		// Fond
		g.setColor(Color.black);
		if(this.maxSize != null)
			g.fillRect(0, 0, (int) (this.maxSize.getX() * this.ratio), (int) (this.maxSize.getY() * this.ratio));
		
		// Tanks
		g.setColor(Color.green);
		if(this.tanks != null)
		{
			for(i = 0; i < this.tanks.length; i++)
			{
				if(this.tanks[i] != null && this.tanks[i].isAlive())
					g.fillRect((int)(this.tanks[i].getArea().getPosition().getX() * this.ratio), 
							(int)(this.getHeight() - ((this.tanks[i].getArea().getSize().getY() + this.tanks[i].getArea().getPosition().getY()) * this.ratio)), 
							(int)(this.tanks[i].getArea().getSize().getX() * this.ratio), 
							(int)(this.tanks[i].getArea().getSize().getY()* this.ratio));
			}
		}
		
		// Monsters
		g.setColor(Color.blue);
		if(this.monsters != null)
		{
			for(i = 0 ; i < this.monsters.length; i++)
			{
				if(this.monsters[i] != null && this.monsters[i].isAlive())
					g.fillRect((int)(this.monsters[i].getArea().getPosition().getX() * this.ratio), 
							(int)(this.getHeight() - ((this.monsters[i].getArea().getSize().getY() + this.monsters[i].getArea().getPosition().getY()) * this.ratio)), 
							(int)(this.monsters[i].getArea().getSize().getX() * this.ratio), 
							(int)(this.monsters[i].getArea().getSize().getY()* this.ratio));
			}
		}
		
		// Shoots
		g.setColor(Color.yellow);
		if(this.shoots != null)
		{
			for(i = 0 ; i < this.shoots.length; i++)
			{
				if(this.shoots[i] != null && this.shoots[i].isAlive())
					g.fillRect((int)(this.shoots[i].getArea().getPosition().getX() * this.ratio), 
							(int)(this.getHeight() - ((this.shoots[i].getArea().getSize().getY() + this.shoots[i].getArea().getPosition().getY()) * this.ratio)), 
							(int)(this.shoots[i].getArea().getSize().getX() * this.ratio), 
							(int)(this.shoots[i].getArea().getSize().getY()* this.ratio));
			}
		}
	}
	
	/**
	 * 
	 * @param monsters
	 * @param tanks
	 * @param shoots
	 */
	public void setTableToPaint(Movable monsters[], Movable tanks[], Movable shoots[], Coordinates maxSize, double ratio)
	{
		this.maxSize = maxSize;
		this.ratio = ratio;
		this.tanks = tanks;
		this.monsters = monsters;
		this.shoots = shoots;
	}
}
