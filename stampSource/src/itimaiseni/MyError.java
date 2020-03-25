package itimaiseni;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;

public class MyError extends Gamen {

	public MyError() {
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//待機画面の背景色指定
		Color color = new Color(100,255,100);
		g.setColor(color);
		g.fillRect(0, 0, getWidth(), getHeight());


	}

	public void display(int i) {
		JLabel Head, Head_h;
		JLabel Message, Message_h;
		JLabel Image;

		// Threadに名前を付ける。
		this.setName("Error");
		this.setLayout(null);
		this.setSize(WIDTH, HEIGHT);

		// 見出しを表示
		Head = new JLabel("このカードは使えません！" + i);
		Head.setFont(new Font("MSゴシック", Font.BOLD, font_size));
		Head.setBounds(570, -200, 1200, 600);
		this.add(Head);

		Head_h = new JLabel("つか");
		Head_h.setFont(new Font("MSゴシック", Font.BOLD, 30));
		Head_h.setBounds(1015, -250, 1200, 600);
		this.add(Head_h);

		// 見出しを表示
		Message = new JLabel("受付でスタンプラリーカードをもらってね");
		Message.setFont(new Font("MSゴシック", Font.BOLD, font_size));
		Message.setBounds(250, -50, 2000, 600);
		this.add(Message);

		Message_h = new JLabel("うけつけ");
		Message_h.setFont(new Font("MSゴシック", Font.BOLD, 30));
		Message_h.setBounds(260, -100, 2000, 600);
		this.add(Message_h);
	}

	// キーが押されたらキーの状態を「押された」に変える
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_C) {
			this.finish = true;

		}
	}

	// キーが離されたらキーの状態を「離された」に変える
	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}
}