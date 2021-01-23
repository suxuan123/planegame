package Pikachu.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import javax.swing.JButton;

/**
 * 游戏的最初界面
 * @author Pikachu
 *
 */


public class GUIFrame extends Frame{
	
	boolean GAME_EXIT=true;
	
	public final int STARTX=200;
	public final int STARTY=180;
	
	public final int EXITX=200;
	public final int EXITY=320;
	
	public JButton start;
	public JButton exit;
	
	
	Image bg=GameUtil.getImage("images/bg.jpg");
	
	
	public void paint(Graphics g) { //自动调用，g相当于一只画笔
		Color c=g.getColor();
		Font font=g.getFont();
		
		g.setColor(Color.RED);
		g.setFont(new Font("华文行楷",Font.BOLD,40));
		
		
		g.drawImage(bg,0,0,null);
		g.drawString("小飞机躲子弹", 140, 120);
		
		g.setColor(c);
	}
	
	
	/**
	 * 初始化GUI
	 */
	public void GUIFrame() {
		this.setTitle("Pikachu作品");
		this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		this.setLocation(300,300);
		this.setVisible(true);
		
		start=new JButton("开始游戏");
		this.add(start);
		start.setBounds(STARTX, STARTY, 100, 50);
		start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	PlaneGameFrame f=new PlaneGameFrame();
            	f.lauchFrame();
            }
        });
		
        exit=new JButton("退出游戏");
        this.add(exit);
        exit.setBounds(EXITX, EXITY, 100, 50);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.exit(0);
            }
        });	
        
        //窗口监听，点击退出即可退出
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	
	public static void main(String[] args) {
		GUIFrame G=new GUIFrame();
		G.GUIFrame();
		
	}
	
	private Image offScreenImage=null;
	
	public void update(Graphics g) {
		if(offScreenImage==null) {
			offScreenImage=this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		}
		
		Graphics gOff=offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0,null);
		
	}
}
