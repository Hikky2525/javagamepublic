package rankingScreen;

import java.awt.Dimension;

import javax.swing.JPanel;

import common.Common;

public abstract class Screen extends JPanel{

	private static final long serialVersionUID = 1L;
	public Screen(){
		//パネルの推奨サイズの設定
		setPreferredSize( new Dimension( Common.DISP2_WIDTH,Common.DISP2_HEIGHT ) );
		//パネルがキー入力をうけつけるようにする（必要？）
		setFocusable( true );
	}

	//画面の可視性
	public void setShow(boolean flg) {
		if ( flg ) {
			this.setVisible(true);
			this.requestFocusInWindow();
		} else {
			this.setVisible(false);
		}
	}
	// 画面表示を記載していくメソッド
	public abstract void display();

}
