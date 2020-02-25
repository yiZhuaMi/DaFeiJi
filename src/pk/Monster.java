package pk;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

public class Monster 
{
//	int width=111,height=150;
	int width,height;
	Image im;
	int health=100;
	int speedY=4;
	int speedX=8;
	int x,y;
	Random ra = new Random();
	
	public Monster(int x,int y,int width,int height,Image im,int speedX,int speedY,int health) 
	{
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.im=im;
		this.speedX=speedX;
		this.speedY=speedY;
		this.health=health;
	}
	
		
	public void draw(Graphics g)//不停刷新 
	{

		if (width==359)
		{
			
			int l = (int) Math.round(health*(0.02));////   l = 剩余血量*(血条长度/总血量)
			g.setColor(Color.red);
			g.fillRect(x+80, y-15, l, 15);
			
			g.setColor(Color.white);
			g.drawRect(x+80, y-15, 200, 15);
			
		}
		
			g.drawImage(im, x, y,width,height, null);
		
		
		if (width==359) {
			
		}
		
		
		move();
		
	}
	public void move() 
	{
		x-=speedX;
		y+=speedY;
	
		
		if(y<=0)
		{
			speedY = -speedY;
		}
		else if (y>=500-height) 
		{
			speedY = -speedY;
		}
	
		
		if (x<-1022-width) 
		{
			health=0;
			
		}
		
		if (height==315&&speedX==1&&x<550) //boss移动 只执行一次 用来改变运动方向
		{
			speedX = 0;
			speedY = 1;
		}
		
	}
	
	public int getWidth() 
	{
		return this.width;
	}
	
	
	public Rectangle getRect() 
	{
		return new Rectangle(x, y, width, height);
		
	}

}
