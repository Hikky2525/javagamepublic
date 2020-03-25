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
public class Check extends Gamen {

	public Check() {
		// super
		display();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//待機画面の背景色指定
		Color color = new Color(100,255,100);
		g.setColor(color);
		g.fillRect(0, 0, getWidth(), getHeight());

		Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("check1.png"));
		g.drawImage(image, 150, 400, this);
	}

	public void display() {
		JLabel Head, Head_h;
		JLabel Check, Check_h;
		JLabel flag;
		boolean errorFlag = false;
		boolean nullFlag = false;



		// Threadに名前を付ける。
		this.setName("Check");
		this.setLayout(null);
		this.setSize(WIDTH, HEIGHT);

		// 見出しを表示
		Head = new JLabel("このチェックポイントは通過しています");
		Head.setFont(new Font("MSゴシック", Font.BOLD, font_size));
		Head.setBounds(300, -200, 1500, 600);


		Head_h = new JLabel("つうか");
		Head_h.setFont(new Font("MSゴシック", Font.BOLD, 40));
		Head_h.setBounds(1120, -250, 1500, 600);


		// チェックメッセージ
		Check = new JLabel("チェックポイントの確認");
		Check.setFont(new Font("MSゴシック", Font.BOLD, 50));
		Check.setBounds(150, 60, 1500, 600);
		this.add(Check);

		Check_h = new JLabel("かくにん");
		Check_h.setFont(new Font("MSゴシック", Font.BOLD, 30));
		Check_h.setBounds(620, 20, 1500, 600);
		this.add(Check_h);

		Data_keep dk = Data_keep.getInstance();
		String[] flag_list = dk.check_p();


		try {
			for(int i = 0; i < flag_list.length; i++) {
				if(flag_list[i].equals("E")) {
					errorFlag = true;
				}
			}
		}catch(Exception e) {
			errorFlag = true;
		}finally{
			if( errorFlag == false) {
				// まるばつ表示(正規ルート)
				for(int i = 0; i < flag_list.length; i++) {

					flag = new JLabel(flag_list[i]);
					flag.setFont(new Font("MSゴシック", Font.BOLD, 60));
					flag.setBounds(758, 140 + i * 77, 1500, 600);
					this.add(flag);
				}
				this.add(Head);
				this.add(Head_h);
			} else {
				flag = new JLabel("しっかりとセットされていません！");
				flag.setFont(new Font("MSゴシック", Font.BOLD, font_size));
				flag.setHorizontalAlignment(JLabel.CENTER);
				flag.setBounds(230, -190, 1500, 600);
				this.add(flag);

				flag = new JLabel("もういちどセットしてね");
				flag.setFont(new Font("MSゴシック", Font.BOLD, font_size));
				flag.setHorizontalAlignment(JLabel.CENTER);
				flag.setBounds(250, -100, 1500, 600);
				this.add(flag);
			}
		}
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
