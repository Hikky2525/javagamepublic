package rankingScreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;

import common.Common;

/**
 * ゴール待機画面
 * @author 山本泰成
 */
public class GoalWait extends Screen {

	private static final long serialVersionUID = 1L;

	public GoalWait(){
		display();
	}

	@Override
	//画面の表示に関する部分
	public void display() {
		JLabel Message     = new JLabel();
		JLabel Message_sub = new JLabel();
		//Threadに名前を付ける,設定
		this.setName( "ゴール待機" );
		this.setLayout( null );
		this.setSize( Common.DISP2_WIDTH,Common.DISP2_HEIGHT );

		//メッセージの表示
		Message.setText(     "<html>すべてのチェックポイントを<br>　　　　回り終えたらカードをセットしてね!" );
		Message_sub.setText( "　　　　　　  　まわ　　お " );
		Message.setFont  ( new Font("MSゴシック", Font.BOLD, Common.FONT_SIZE_DISP ) );
		Message.setBounds( 30,-50,12000,600);
		Message_sub.setFont(   new Font("MSゴシック", Font.BOLD, Common.FONT_SIZE_SUB ) );
		Message_sub.setBounds( 0, -50, 1200, 600);
		this.add( Message );
		this.add( Message_sub );
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//待機画面の背景色指定
		Color color = new Color(100,255,100);
		g.setColor(color);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

}
