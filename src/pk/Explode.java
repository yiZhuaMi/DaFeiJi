package pk;

import java.awt.Graphics;
import java.awt.Image;

public class Explode 
{
	int x;
	int y;
	Image im;
	
	int width=50,height=50;
	int health;


	public Explode(int x,int y,Image im) 
	{
		this.x = x;
		this.y = y;
		this.im = im;
		 
	}
	
	public void draw(Graphics g) 
	{		
		g.drawImage(im, x, y, width, height,null);	
	}
}
