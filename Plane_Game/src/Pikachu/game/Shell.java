package Pikachu.game;

import java.awt.Color;
import java.awt.Graphics;

/**
 * �ڵ��࣬�����ڵ�������
 * @author Pikachu
 *
 */

public class Shell extends GameObject{
	
	//�ڵ����ɵ�λ�ã��ٶȺͷ���Ƕ�
	double degree;
	public Shell() {
		x=100;
		y=100;
		width=10;
		height=10;
		speed=3;
		
		degree=Math.random()*Math.PI*2;
	}
	
	//���ڵ�
	public void draw(Graphics g) {   
		Color c=g.getColor();
		g.setColor(Color.YELLOW);
		
		g.fillOval((int)x, (int)y, width, height);
		
		x+=speed*Math.cos(degree);
		y+=speed*Math.sin(degree);
		
		if(x<0||x>Constant.GAME_WIDTH-width) {
			degree=Math.PI-degree;
		}
			
		if(y<30||y>Constant.GAME_HEIGHT-height) {
			degree=-degree;
		}
			
		g.setColor(c);
	}
	
}
