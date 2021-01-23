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
 * �ɻ���Ϸ��������
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
	public void paint(Graphics g) { //�Զ����ã�g�൱��һֻ����
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
				Font f=new Font("����",Font.BOLD,50);
				g.setFont(f);
				g.drawString("����ʱ�䣺"+period+"��",90,120);
			}
		}
		
		g.setColor(c);
	}
	
	//�������Ƿ���������
	class PaintThread extends Thread{
		@Override
		public void run() {
			while(true) {
				repaint();//�ػ�����
				
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	//������̼������ڲ���
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
	 * ��ʼ����Ϸ����
	 */
	public void lauchFrame() {
		this.setTitle("Pikachu��Ʒ");
		this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		this.setLocation(300,300);
		this.setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		new PaintThread().start();//�����ػ����ڵ��߳�
		addKeyListener(new KeyMonitor());
		
		//��ʼ���ڵ�����
		for(int i=0;i<shells.length;i++) {
			shells[i]=new Shell();
		}
	}
}


