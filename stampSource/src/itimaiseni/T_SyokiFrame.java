package itimaiseni;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.xml.crypto.Data;

public class T_SyokiFrame extends JFrame{

	private boolean feli_flg = true;


	public T_SyokiFrame(){
	//	sound = Applet.newAudioClip(getClass().getResource("se/coin03.wav"));
	       // パネルの推奨サイズを設定、pack()するときに必要
        setPreferredSize(new Dimension(Gamen.WIDTH, Gamen.HEIGHT));

		this.setBounds(0, 0, Gamen.WIDTH, Gamen.HEIGHT);

	}

	public static void main(String[] args) {
        T_SyokiFrame mf = new T_SyokiFrame();

        mf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mf.setVisible(true);
        while(mf.change_display()){
        }
	}

	public boolean change_display(){
		int i=4;
		while(true){

			MyError game_taiki = new MyError();
			game_taiki.display(i);
			this.add(game_taiki);game_taiki.setShow(false);
			game_taiki.setShow(true);
			try {
			Thread.sleep(3000);
			System.out.println();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			game_taiki.setShow(false);
			game_taiki = null;
			i++;
			System.out.println();

		}
	}

}


