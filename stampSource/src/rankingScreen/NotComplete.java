package rankingScreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;

import common.Common;
import felica.Ranking_Felica;

public class NotComplete extends Screen {

	private static final long serialVersionUID = 1L;

	public NotComplete() {
		display();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//待機画面の背景色指定
		Color color = new Color(100,255,100);
		g.setColor(color);
		g.fillRect(0, 0, getWidth(), getHeight());
		Image image = Toolkit.getDefaultToolkit().getImage(
				"C:\\dev\\workspace\\Ranking\\bin\\check1.png");
		g.drawImage(image, 150, 400, this);
	}

	public void display() {
		JLabel Head, Head_h;
		JLabel Check, Check_h;
		// Threadに名前を付ける。
		this.setName("Check");
		this.setLayout(null);
		this.setSize(WIDTH, HEIGHT);

		// 見出しを表示
		Head = new JLabel( "すべてのチェックポイントを終えていません" );
		Head.setFont( new Font("MSゴシック", Font.BOLD,Common.FONT_SIZE_DISP ));
		Head.setBounds( 20, -200, 1500, 600);
		this.add(Head);

		Head_h = new JLabel( "お" );
		Head_h.setFont( new Font("MSゴシック", Font.BOLD, 40));
		Head_h.setBounds( 1000, -250, 1500, 600);
		this.add(Head_h);

		// チェックメッセージ
		Check = new JLabel("チェックポイントの確認");
		Check.setFont(new Font("MSゴシック", Font.BOLD, 50));
		Check.setBounds(150, 60, 1500, 600);
		this.add(Check);

		Check_h = new JLabel("かくにん");
		Check_h.setFont(new Font("MSゴシック", Font.BOLD, 30));
		Check_h.setBounds(620, 20, 1500, 600);
		this.add(Check_h);

		//○×の取得
		String[] check = flgCheck();
		//○×表示
		JLabel[] Flg = new JLabel[5];
		for( int i = 0;i < Flg.length;i++ ){
			Flg[i] = new JLabel();
			Flg[i].setText( check[i] );
			Flg[i].setFont( new Font ( "MSゴシック",Font.BOLD,Common.FONT_SIZE_DISP ) );
			Flg[i].setBounds( 760,145+i*75,1500,600);
			this.add( Flg[i] );
		}
	}

	/**
	 * 通過フラグの読み取り
	 */
	public String[] flgCheck(){
		Ranking_Felica reader = new Ranking_Felica();
		int[] readBlock = new int[5];
		String[] retString = new String[readBlock.length];
		for( int i = 0;i < retString.length;i++ ){
			retString[i] = null;
		}
		readBlock[0] = 0x02;
		readBlock[1] = 0x03;
		readBlock[2] = 0x04;
		readBlock[3] = 0x05;
		readBlock[4] = 0x06;
		reader.open();
		for( int i = 0;i < readBlock.length;i++ ){
			int num = reader.read_p( readBlock[i] );
			if( num == 1 ){
				retString[i] = "〇";
			}else if ( num == 5 ){
				retString[i] = "×";
			}
		}
		return retString;
	}
}
