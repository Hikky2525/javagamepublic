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

public class Result extends Gamen {

	public Result() {
		// super
		display();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("result_pic.jpg"));
		g.drawImage(image, 0, 0, this);
		g.drawString("a", 0, 10);

	}

	public void display() {
		JLabel tokuten_n, goukei_n;
		JLabel flag;
		JLabel message, message_h;
		int disp_score, disp_tScore;
		int messageCnt = 0;

		Score score = Score.getInstance();
		Data_keep dk = Data_keep.getInstance();
		//当チェックポイントのスコア取得
		disp_score = score.getScore();
		//合計スコアの取得
		disp_tScore = dk.totalScore();

		// Threadに名前を付ける。
		this.setName("Check");
		this.setLayout(null);
		this.setSize(WIDTH, HEIGHT);

		// 得点(数字)
		tokuten_n = new JLabel(String.valueOf(disp_score));
		tokuten_n.setFont(new Font("MSゴシック", Font.BOLD, 100));
		tokuten_n.setForeground(Color.BLACK);
		tokuten_n.setHorizontalAlignment(JLabel.RIGHT);
		tokuten_n.setBounds(350, 200, 300, 600);
		this.add(tokuten_n);

		// 合計得点(数字)
		goukei_n = new JLabel(String.valueOf(disp_tScore));
		goukei_n.setFont(new Font("MSゴシック", Font.BOLD, 100));
		goukei_n.setForeground(Color.BLACK);
		goukei_n.setHorizontalAlignment(JLabel.RIGHT);
		goukei_n.setBounds(350, 387, 300, 600);
		this.add(goukei_n);

		Data_keep dk_flag = Data_keep.getInstance();
		String[] flag_list = dk_flag.check_p();

		// チェックポイント確認
		for(int i = 0; i < flag_list.length; i++) {

			flag = new JLabel(flag_list[i]);
			flag.setFont(new Font("MSゴシック", Font.BOLD, 60));
			flag.setBounds(1760, 315 + i * 85, 1500, 600);
			this.add(flag);
			if(flag_list[i].equals("○")) {
				messageCnt++;
			}
		}

		//メッセージ表示
		if(messageCnt == 5) {
			message = new JLabel("<html>ポイントがたまったよ！<br>ゴール(2階&nbsp&nbsp&nbsp;視聴覚室)へむかってね！<br>カードをわすれないでね！");
			message_h = new JLabel("かい   しちょうかくしつ");
		}else{
			message = new JLabel("<html>つぎのチェックポイントへむかってね！<br>どこからまわってもいいよ！<br>カードをわすれないでね！");
			message_h = new JLabel("");
		}
		message.setFont(new Font("MSゴシック", Font.BOLD, 40));
		message.setForeground(Color.BLACK);
		message.setBounds(1100, -10, 1500, 600);
		message_h.setFont(new Font("MSゴシック", Font.BOLD, 25));
		message_h.setForeground(Color.BLACK);
		message_h.setBounds(1255, -38, 1500, 600);

		this.add(message);
		this.add(message_h);
	}

	// キーが押されたらキーの状態を「押された」に変える
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_B) {
			this.finish = true;

		}
	}

	// キーが離されたらキーの状態を「離された」に変える
	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

}
