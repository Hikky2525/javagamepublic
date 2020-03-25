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

public class ReInsert extends Gamen {

	public ReInsert() {
		// super
		display();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//待機画面の背景色指定
		Color color = new Color(100,255,100);
		g.setColor(color);
		g.fillRect(0, 0, getWidth(), getHeight());
		Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("bikkuri3.jpg"));
		g.drawImage(image, 100, 50, this);
	}

	public void display() {
		Score s = Score.getInstance();
		JLabel Head;
		JLabel Message, Message_h;
		JLabel Image;

		// Threadに名前を付ける。
		this.setName("Error");
		this.setLayout(null);
		this.setSize(WIDTH, HEIGHT);

		// 見出しを表示
		Head = new JLabel("スタンプラリーカードがセットされていません！");
		Head.setFont(new Font("MSゴシック", Font.BOLD, font_size));
		Head.setBounds(200, -200, 1800, 600);
		this.add(Head);

		// 見出しを表示
		Message = new JLabel("同じ番号のカードをセットしてね");
		Message.setFont(new Font("MSゴシック", Font.BOLD, font_size));
		Message.setBounds(430, -50, 2000, 600);
		this.add(Message);

		Message_h = new JLabel("おな           ばんごう");
		Message_h.setFont(new Font("MSゴシック", Font.BOLD, 30));
		Message_h.setBounds(435, -100, 2000, 600);
		this.add(Message_h);
	}

	// キーが押されたらキーの状態を「押された」に変える
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_D) {
			this.finish = true;

		}
	}

	// キーが離されたらキーの状態を「離された」に変える
	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}
}