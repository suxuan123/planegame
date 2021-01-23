package Pikachu.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

/**
 * �ɻ��࣬�����ɻ������ʣ����Ʒɻ��ķ���
 * @author Pikachu
 *
 */

public class Plane extends GameObject{
	
	boolean left,up,right,down,live=true;
	
	//�ɻ��ķ��п���
	public void drawSelf(Graphics g) {
		if(live) {
			g.drawImage(img, (int)x, (int)y, null);
			if(left&&(x-speed)>=0) {
				x-=speed;
			}
			if(right&&(x+speed)<=Constant.GAME_WIDTH-width) {
				x+=speed;
			}
			if(up&&(y-speed)>=height/2) {
				y-=speed;
			}
			if(down&&(y+speed)<=Constant.GAME_HEIGHT-height) {
				y+=speed;
			}
		}
		else {
			
		}
	}
	
	
	//�ɻ����췽��
	public Plane(Image img,double x,double y) {
		this.img=img;
		this.x=x;
		this.y=y;
		this.speed=6;
		this.width=img.getWidth(null);
		this.height=img.getHeight(null);
	}
	
	
	//���̼��������·���������ƶ�
	public void addDirection(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left=true;
			break;
		case KeyEvent.VK_RIGHT:
			right=true;
			break;
		case KeyEvent.VK_UP:
			up=true;
			break;
		case KeyEvent.VK_DOWN:
			down=true;
			break;
		}
	}
	
	//���̼������ɿ����������ֹͣ�ƶ�
	public void minusDirection(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left=false;
			break;
		case KeyEvent.VK_RIGHT:
			right=false;
			break;
		case KeyEvent.VK_UP:
			up=false;
			break;
		case KeyEvent.VK_DOWN:
			down=false;
			break;
		}
	}
	
}
