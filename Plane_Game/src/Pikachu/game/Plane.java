package Pikachu.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

/**
 * 飞机类，描述飞机的性质，控制飞机的飞行
 * @author Pikachu
 *
 */

public class Plane extends GameObject{
	
	boolean left,up,right,down,live=true;
	
	//飞机的飞行控制
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
	
	
	//飞机构造方法
	public Plane(Image img,double x,double y) {
		this.img=img;
		this.x=x;
		this.y=y;
		this.speed=6;
		this.width=img.getWidth(null);
		this.height=img.getHeight(null);
	}
	
	
	//键盘监听，按下方向键代表移动
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
	
	//键盘监听，松开方向键代表停止移动
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
