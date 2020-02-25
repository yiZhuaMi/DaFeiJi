package pk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import org.w3c.dom.css.Rect;

@SuppressWarnings("serial")
public class GameStart extends Frame 
{
	Toolkit tk = Toolkit.getDefaultToolkit();
	Image im_bg1 = tk.getImage(GameStart.class.getClassLoader().getResource("images/bg.gif"));
	Image im_bg2 = tk.getImage(GameStart.class.getClassLoader().getResource("images/bg.gif"));
	Image im_leadingCharacter = tk.getImage(GameStart.class.getClassLoader().getResource("images/zhujuelong.gif"));
	Image im_bullet = tk.getImage(GameStart.class.getClassLoader().getResource("images/huoyan.png"));
//	Image im_monster = tk.getImage(GameStart.class.getClassLoader().getResource("images/monster2.gif"));

	Image im_monster = tk.getImage(GameStart.class.getClassLoader().getResource("images/m2.gif"));
	Image im_boss = tk.getImage(GameStart.class.getClassLoader().getResource("images/bigboss.gif"));
	Image im_M_bullet = tk.getImage(GameStart.class.getClassLoader().getResource("images/M_bullet.gif"));
	Image im_disappear = tk.getImage(GameStart.class.getClassLoader().getResource("images/dis.gif"));
	
	int bgWidth = 1022;
	int bgHight = 500;
	
	int leadingCharacter_x = 120;
	int leadingCharacter_y = 250;
	int leadingCharacter_width = 155;
	int leadingCharacter_height = 140;
	int leadingCharacter_Health = 100;
	int leadingCharacter_Speed = 15;
	int leadingCharacter_ShotFrequency = 4;
	int whether_inc_frequency = -1;
	
	int whether_flash = 1;
	
	double bullet_decrease = 0.1;
	int bullet_nums = 100;
	
	int monster_nums = 0;
	
	int timer = 0;
	Image im_bf = null;
	int ChangeColorTemp = -1;
	
	int bg_x = 0;
	Random ra = new Random();

	
	ArrayList<Bullet> all_bullets = new ArrayList<>();
	ArrayList<Monster> all_monster = new ArrayList<>();
	
	boolean w = false, s = false, a = false, d = false, j = false, k = false;
	boolean bossCame = false;
	



	public void start()
		{
			this.setSize(bgWidth, bgHight);
			this.setTitle("打他");
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent agr0)
					{
						System.exit(1);

					}
			});

			this.addKeyListener(new KeyAdapter() 
			{
				public void keyPressed(KeyEvent e)
					{
						if (e.getKeyCode() == KeyEvent.VK_W) 
						{
							w = true;
							System.out.println("W");
						}
						if (e.getKeyCode() == KeyEvent.VK_S) 
						{
							s = true;
							System.out.println("S");
						}
						if (e.getKeyCode() == KeyEvent.VK_A) 
						{
							a = true;
							System.out.println("A");
						}
						if (e.getKeyCode() == KeyEvent.VK_D) 
						{
							d = true;
							System.out.println("D");
						}
						if (e.getKeyCode() == KeyEvent.VK_J) 
						{
							j = true;
							System.out.println("J");

						}
						if (e.getKeyCode() == KeyEvent.VK_P) 
						{
							System.out.println("P");
							if (bullet_nums>=50) 
							{
								whether_inc_frequency = -whether_inc_frequency;
								inc_bullet_frequencv();
							}
					
						}
						if (e.getKeyCode() == KeyEvent.VK_K) 
						{
							k = true;
							System.out.println("K");
							
						
						
					
						}

					}
				public void keyReleased(KeyEvent e) 
				{
					switch (e.getKeyCode()) 
					{
					case KeyEvent.VK_W:
						w=false;
						break;
					case KeyEvent.VK_S:
						s=false;
						break;
					case KeyEvent.VK_A:
						a=false;
						break;
					case KeyEvent.VK_D:
						d=false;
						break;
					case KeyEvent.VK_J:
						j=false;
						break;
					case KeyEvent.VK_K:
						k=false;
						break;
						default:break;
					
					}
					
				}
			});

			myThread mThread = new myThread();
			mThread.start();
		}

//	public void couldMove() 
//	{
//		if (leadingCharacter_y<) 
//		{
//			w=false;
//			
//		}
//		else
//		{
//			move();
//		}
//		
//	}
	
	
	
	public void move()
		{
			if (w == true&&leadingCharacter_y>0) 
			{
				leadingCharacter_y -= leadingCharacter_Speed;

			}
			if (s == true&&leadingCharacter_y<bgHight-leadingCharacter_height) 
			{
				leadingCharacter_y += leadingCharacter_Speed;

			}
			if (a == true&&leadingCharacter_x>0) 
			{
				leadingCharacter_x -= leadingCharacter_Speed;

			}
			if (d == true&&leadingCharacter_x<bgWidth-leadingCharacter_width) 
			{
				leadingCharacter_x += leadingCharacter_Speed;

			}
			if (j == true&&timer>=leadingCharacter_ShotFrequency&&bullet_nums>0) 
			{
				all_bullets.add(new Bullet(leadingCharacter_x, leadingCharacter_y, im_bullet,12));
				
				timer = 0;
				
				bullet_nums-=bullet_decrease;
			}

		}


	public void paint(Graphics g)
		{
			g.drawImage(im_bg1, bg_x-=2, 0, bgWidth, bgHight, null);
			g.drawImage(im_bg2, bgWidth + bg_x, 0, bgWidth, bgHight, null);
			
			//生命
			Font font = new Font("Courier New",Font.PLAIN,20);
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("生命值", 20, 45);
			
			
			
			//填充
			g.setColor(Color.red);
			g.fillRect(84, 31, leadingCharacter_Health, 15);
		
			if (leadingCharacter_Health<20 && leadingCharacter_Health>0 && ChangeColorTemp<0 && whether_flash>0) 
			{
				g.setColor(Color.red);//矩形边界
				g.drawRect(84, 31, 100, 15);
				ChangeColorTemp = -ChangeColorTemp;
			}
			else
			{
				g.setColor(Color.white);//矩形边界
				g.drawRect(84, 31, 100, 15);
				ChangeColorTemp = -ChangeColorTemp;
			}
			
			if (leadingCharacter_Health==0) 
			{
				whether_flash = -1;
			}
			else if (leadingCharacter_Health==100) 
			{
				whether_flash = 1;
			}
			
			
			//火球数
			g.setColor(Color.white);
			g.drawString("火焰值", 20, 75);
//			g.setColor(Color.orange);
			Color c = new Color(240, 210, 0);
			g.setColor(c);
			g.fillRect(84, 61, bullet_nums, 15);
			g.setColor(Color.white);
			g.drawRect(84, 61, 100, 15);
			
			//杀敌数
			g.setColor(Color.white);
			g.drawString("杀敌数", 20, 105);
			g.drawString(String.valueOf(monster_nums-all_monster.size()), 84, 105);
			
			if (whether_inc_frequency>0) 
			{
				g.setColor(Color.red);
				g.drawString("暴走", 194, 76);
			}
			
			if (k) 
			{
				Font f = new Font("Courier New",Font.PLAIN,40);
				g.setFont(f);
				g.drawString("ALL KILLED", bgWidth/2-120, bgHight/2);
				
			}
			
			if (monster_nums-all_monster.size()> 5&& !bossCame) 
			{
				System.out.println("boss ready");
				Monster boss = new Monster(bgWidth, (bgHight-315)/2, 359, 315, im_boss, 1, 0, 10000);
				all_monster.add(boss);
				
				
				bossCame = true;
				
			}
			
			
			
			
			//background revolve
			if (bg_x <= -bgWidth) 
			{
				bg_x = 0;
			}

			g.drawImage(im_leadingCharacter, leadingCharacter_x, leadingCharacter_y, leadingCharacter_width, leadingCharacter_height, null);
			
			//Bullet
			for(int i=0;i<all_bullets.size();i++)//遍历所有子弹
			{
				Bullet b = (Bullet)all_bullets.get(i);
				b.draw(g);
				
				
				Rectangle rec_Lead = new Rectangle(leadingCharacter_x, leadingCharacter_y, leadingCharacter_width, leadingCharacter_height);
				
				
				if (b.speed>0) //筛选人物子弹
				{
					Rectangle rec_b = b.getRect();
					
					for(int j=0;j<all_monster.size();j++)//遍历所有怪物
					{
						Monster m = (Monster)all_monster.get(j);
						
						Rectangle rec_m = m.getRect();
						
						if (rec_b.intersects(rec_m)) 
						{
	
							
							if (m.getWidth()==359&&m.health>0) //打到boss
							{
								m.health-=1;
								System.out.println("boss_health = "+m.health);
								
								if (m.health<=0) 
								{
									all_monster.remove(j);
									
								}
							}
							else
							{
								all_bullets.remove(i);
								
								all_monster.remove(j);
							}
							
								
							Explode ex = new Explode(m.x, m.y, im_disappear);
							ex.draw(g);
						
							
						}
					}
					
					
				}
				else if (b.speed<0) //筛选怪物子弹
				{
					Rectangle rec_BulletofMonster = b.getRect();
					
					if (rec_BulletofMonster.intersects(rec_Lead)&&leadingCharacter_Health>0) //怪物打人
					{
						leadingCharacter_Health-=3;		
						all_bullets.remove(i);
					}
					
				}
				
				
				if (b.health==0) 
				{
					all_bullets.remove(i);			
				}
				
			}
			
			//Monster

			if (ra.nextInt(100)>90)//怪物数量控制
			{
				int speedY = 0;
				if (ra.nextInt(2)>=1) 
				{
					speedY = -(ra.nextInt(2)+1);
					
				}
				else 
				{
					speedY = ra.nextInt(2)+1;
				}
				int iniX = 1200+ra.nextInt(600);
	
				Monster m = new Monster(iniX,ra.nextInt(bgHight-150), 88, 120, im_monster,ra.nextInt(10)+1,speedY,100); //怪物初始化
				
				all_monster.add(m);		
				
				monster_nums++;
				
				
			}
			
			for(int i=0;i<all_monster.size();i++)
			{
				Monster m1 = (Monster)all_monster.get(i);
				m1.draw(g);
				
				Rectangle rec_m = m1.getRect();
				Rectangle rect_lead = new Rectangle(leadingCharacter_x, leadingCharacter_y, leadingCharacter_width, leadingCharacter_height);
				
				if (rec_m.intersects(rect_lead)) //撞怪物
				{
					if (m1.getWidth()==359&&m1.health>0) //撞到boss
					{
						m1.health-=1;
						System.out.println("boss_health = "+m1.health);
						
						if (m1.health<=0) 
						{
							all_monster.remove(j);
							
						}
					}
					else
					{
						all_monster.remove(i);
					}
					
					leadingCharacter_Health--;
					
					
				}
				
				if (ra.nextInt(100)>95) //怪物子弹数量控制
				{
					Bullet b = new Bullet(m1.x, m1.y, im_M_bullet,-12);
					
					all_bullets.add(b);
				}
				

				if (m1.health==0) 
				{
					all_monster.remove(i);
					
				}
			}
			
			//清屏
			if (k) 
			{
				killThemAll();
			}

	}

	class myThread extends Thread {
		public void run()
			{
				while (true) {
					repaint();
					move();
					timer++;
					increaseBullet();
					increaseHealth();

					try {
						Thread.sleep(30);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}

	}

	int temp =0;
	public void increaseBullet() 
	{
		
		if (bullet_nums<100) 
		{
			temp++;
			if(temp>=7)//子弹恢复速度
			{
				bullet_nums+=1;
				temp=0;
			}
		}
		
		if(bullet_nums<=0&&whether_inc_frequency>0)//关闭暴走
		{
			whether_inc_frequency = - whether_inc_frequency;
			leadingCharacter_ShotFrequency = 4;
		}
		
	}
	
	public void increaseHealth() 
	{
		if (leadingCharacter_Health<100) 
		{
			temp++;
			if(temp>=3)//生命恢复速度
			{
				leadingCharacter_Health+=2;
				temp=0;
			}
		}
		
	}
	
	public void inc_bullet_frequencv() 
	{
		if (whether_inc_frequency>0) 
		{
			leadingCharacter_ShotFrequency = 2;
			bullet_decrease = 0.2;
			
		}
		else
		{
			leadingCharacter_ShotFrequency = 4;
			bullet_decrease = 0.1;
		}
		
	}
	
	public void killThemAll() 
	{
		for (int i = 0; i < all_monster.size(); i++) 
		{
			all_monster.remove(i);
		}
		
		for (int i = 0; i < all_bullets.size(); i++) 
		{
			all_bullets.remove(i);
		}
				
	}

	
	public void update(Graphics g)
		{
			if (im_bf == null) 
			{
				im_bf = this.createImage(bgWidth, bgHight);
			}
			Graphics g_buf = im_bf.getGraphics();
			paint(g_buf);
			g.drawImage(im_bf, 0, 0, null);
		}
	
	

	public static void main(String[] args)
		{
			GameStart gs = new GameStart();
			gs.start();
		}
}
