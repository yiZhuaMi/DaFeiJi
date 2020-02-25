package pk;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Bullet 
{
	int width=40,height=40;
	Image im;
	int x,y;
	int speed = 12;
	int damage = 100;
	int health = 100;
	
	public Bullet (int x,int y,Image im,int speed) 
	{
		this.x = x;
		this.y = y;
		this.im = im;
		this.speed = speed;
		
	}
	
	public void draw(Graphics g) 
	{
		if (speed>0) 
		{
			g.drawImage(im, x+140, y+86, width, height,null);
		}
		else
		{
			g.drawImage(im, x, y+60, width, height,null);
		}
		
		
		move();
		
	}
	
	public Rectangle getRect() 
	{
		return new Rectangle(x, y, width, height);
		
	}

	public void move() 
	{
		x+=speed;
		if (x>1022) 
		{
			health = 0;
			
		}
		
		
	}
	
}
