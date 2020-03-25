package rankingScreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;

import common.Common;

public class MyError extends Screen {

	private static final long serialVersionUID = 1L;
	private Image image;
	public MyError() {
		image =Toolkit.getDefaultToolkit().getImage(getClass().getResource("bikkuri3.jpg"));
		// super
		display();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//待機画面の背景色指定
		Color color = new Color(100,255,100);
		g.setColor(color);
		g.fillRect(0, 0, getWidth(), getHeight());


		g.drawImage(image, 350, 50, this);
	}

	public void display() {
		JLabel Head, Head_h;
		JLabel Message, Message_h;

		// Threadに名前を付ける。
		this.setName("Error");
		this.setLayout(null);
		this.setSize(WIDTH, HEIGHT);

		// 見出しを表示
		Head = new JLabel( "このカードは使えません！" );
		Head.setFont( new Font( "MSゴシック",Font.BOLD,Common.FONT_SIZE_DISP ));
		Head.setBounds( 450,-200,1200,600 );
		this.add(Head);

		Head_h = new JLabel( "つか" );
		Head_h.setFont( new Font( "MSゴシック",Font.BOLD,Common.FONT_SIZE_SUB ));
		Head_h.setBounds( 900,-250,1200,600 );
		this.add( Head_h );

		// 見出しを表示
		Message = new JLabel("受付でスタンプラリーカードをもらってね");
		Message.setFont(new Font("MSゴシック", Font.BOLD,Common.FONT_SIZE_DISP));
		Message.setBounds( 150,-50,2000,600 );
		this.add(Message);

		Message_h = new JLabel("うけつけ");
		Message_h.setFont(new Font("MSゴシック", Font.BOLD,Common.FONT_SIZE_SUB ));
		Message_h.setBounds( 150, -100, 2000, 600);
		this.add(Message_h);
	}

}