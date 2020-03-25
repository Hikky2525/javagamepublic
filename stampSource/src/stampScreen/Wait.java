package stampScreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;

import dataModel.*;
import gameObj.*;

public class Wait extends Gamen{
	private Data_keep DataKeep = Data_keep.getInstance();

	public Wait(){
		//super
		display();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//待機画面の背景色指定
		Color color = new Color(100,255,100);
		g.setColor(color);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	public void display(){
		JLabel Head;
		JLabel Name, Name_h;
		JLabel Image;
		JLabel Message;
		//Threadに名前を付ける。
		this.setName( "Wait" );
		this.setLayout( null );
		this.setSize(WIDTH,HEIGHT);

		//見出しを表示
		Head = new JLabel("ICスタンプラリーチェックポイント");
		Head.setFont( new Font("MSゴシック",Font.BOLD,font_size ));
		Head.setBounds( 350, -200, 1200, 600 );
		this.add( Head );


		//◌◌科を表示
		switch(DataKeep.getName()) {
		case "メカトロニクス科":
			Name = new JLabel(DataKeep.getName());
			Name.setFont( new Font("MSゴシック",Font.BOLD,font_size ));
			Name.setBounds( 600, -50, 1800, 600 );
			this.add( Name );

			Name_h = new JLabel("か");
			Name_h.setFont( new Font("MSゴシック",Font.BOLD, 30 ));
			Name_h.setBounds( 1135, -100, 1800, 600 );
			this.add( Name_h );
			break;

		case "機械加工システム科":
			Name = new JLabel(DataKeep.getName());
			Name.setFont( new Font("MSゴシック",Font.BOLD,font_size ));
			Name.setBounds( 600, -50, 1800, 600 );
			this.add( Name );

			Name_h = new JLabel("きかい      かこう                                            か");
			Name_h.setFont( new Font("MSゴシック",Font.BOLD, 30 ));
			Name_h.setBounds( 620, -100, 1800, 600 );
			this.add( Name_h );
			break;

		case "建築科":
			Name = new JLabel(DataKeep.getName());
			Name.setFont( new Font("MSゴシック",Font.BOLD,font_size ));
			Name.setBounds( 800, -50, 1800, 600 );
			this.add( Name );

			Name_h = new JLabel("けんちく   か");
			Name_h.setFont( new Font("MSゴシック",Font.BOLD, 30 ));
			Name_h.setBounds( 810, -100, 1800, 600 );
			this.add( Name_h );
			break;

		case "キャリア・プログラム科":
			Name = new JLabel(DataKeep.getName());
			Name.setFont( new Font("MSゴシック",Font.BOLD,font_size ));
			Name.setBounds( 520, -50, 1800, 600 );
			this.add( Name );

			Name_h = new JLabel("か");
			Name_h.setFont( new Font("MSゴシック",Font.BOLD, 30 ));
			Name_h.setBounds( 1280, -100, 1800, 600 );
			this.add( Name_h );
			break;

		case "総合実務科":
			Name = new JLabel(DataKeep.getName());
			Name.setFont( new Font("MSゴシック",Font.BOLD,font_size ));
			Name.setBounds( 750, -50, 1800, 600 );
			this.add( Name );

			Name_h = new JLabel("そうごう    じつむ      か");
			Name_h.setFont( new Font("MSゴシック",Font.BOLD, 30 ));
			Name_h.setBounds( 760, -100, 1800, 600 );
			this.add( Name_h );
			break;
		}


		//下部メッセージを表示
		Message = new JLabel("スタンプラリーカードをセットしてね");
		Message.setFont( new Font("MSゴシック",Font.BOLD,font_size ));
		Message.setBounds( 300, 520, 1800, 600 );
		this.add( Message );

	}



	//キーが押されたらキーの状態を「押された」に変える
    public void keyPressed(KeyEvent e) {
    	int key = e.getKeyCode();

    	if ( key == KeyEvent.VK_A ) {
    		this.finish=true;


    	}
    }
    //キーが離されたらキーの状態を「離された」に変える
    public void keyReleased(KeyEvent e) {
    }
    public void keyTyped(KeyEvent e) {
    }


}
