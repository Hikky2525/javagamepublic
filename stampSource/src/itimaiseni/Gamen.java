package itimaiseni;


import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Gamen extends JPanel implements KeyListener{
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1040;
	public static final int font_size = 70;

	protected boolean finish = false;
	public Gamen(){
	    // パネルの推奨サイズを設定、pack()するときに必要
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		// パネルがキー入力を受け付けるようにする
		setFocusable(true);

		// キーイベントリスナーを登録
		addKeyListener(this);

	}

	public void setShow(boolean flg){
		if(flg){
			this.setVisible(true);
			this.requestFocusInWindow();
		}else{
			this.setVisible(false);
		}
	}
	public boolean check(){
		return this.finish;
	}

	//画面表示を記載していくメソッド
	public void display(){
	}
	//キーが押されたらキーの状態を「押された」に変える
    public void keyPressed(KeyEvent e) {
    }
    //キーが離されたらキーの状態を「離された」に変える
    public void keyReleased(KeyEvent e) {
    }
    public void keyTyped(KeyEvent e) {
    }
}

