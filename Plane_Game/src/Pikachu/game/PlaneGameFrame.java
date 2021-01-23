package Pikachu.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JFrame;

/**
 * 飞机游戏的主窗口
 * @author Pikachu
 *
 */

public class PlaneGameFrame extends JFrame{
	
	Image planeImg=GameUtil.getImage("images/plane.png");
	Image bg=GameUtil.getImage("images/bg.jpg");
	
	Plane plane=new Plane(planeImg,250,250);
	Shell[] shells=new Shell[40];
	
	Explode bao;
	Date startTime=new Date();
	Date endTime;
	int period;
	
	@Override
	public void paint(Graphics g) { //自动调用，g相当于一只画笔
		Color c=g.getColor();
		g.drawImage(bg,0,0,null);
		plane.drawSelf(g);
		
		for(int i=0;i<shells.length;i++) {
			shells[i].draw(g);
			
			boolean peng=shells[i].getRect().intersects(plane.getRect());
			if(peng) {
				plane.live=false;
				if(bao==null) {
					bao=new Explode(plane.x,plane.y);
					
					endTime=new Date();
					period=(int)((endTime.getTime()-startTime.getTime())/1000);
				}
				
				bao.draw(g);
			}
			
			if(!plane.live) {
				g.setColor(Color.red);
				Font f=new Font("宋体",Font.BOLD,50);
				g.setFont(f);
				g.drawString("生存时间："+period+"秒",90,120);
			}
		}
		
		g.setColor(c);
	}
	
	//帮助我们反复画窗口
	class PaintThread extends Thread{
		@Override
		public void run() {
			while(true) {
				repaint();//重画窗口
				
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	//定义键盘监听的内部类
	class KeyMonitor extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			plane.addDirection(e);
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			plane.minusDirection(e);
		}
	}
	
	/**
	 * 初始化游戏窗口
	 */
	public void lauchFrame() {
		this.setTitle("Pikachu作品");
		this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		this.setLocation(300,300);
		this.setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		new PaintThread().start();//启动重画窗口的线程
		addKeyListener(new KeyMonitor());
		
		//初始化炮弹数量
		for(int i=0;i<shells.length;i++) {
			shells[i]=new Shell();
		}
	}
}


