package main;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Dimension;

import javax.swing.JFrame;
import common.Common;
import main.Control;

import dataModel.*;
import stampScreen.*;

public class StampMainFrame extends JFrame{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static Score score = Score.getInstance();
	int line;  //settergetter atode
	long start;
	private AudioClip sound = Applet.newAudioClip(getClass().getResource("se/pi.wav"));
	private boolean feli_flg = true;
	private static Data_keep DataKeep = Data_keep.getInstance();
	private Wait  wait= new Wait();
	boolean errorFlag = false;

	private MyError error = new MyError();
	private Result result=null;

	private int time = Common.GAMETIME * 1000;
	private int time_taiki = Common.TAIKITIME * 1000;
	private int heldover = Common.HELDOVER * 1000;

	//この3つはnewする場所を変える予定


	private ReInsert reinsert = new ReInsert();

	public StampMainFrame(){
	//	sound = Applet.newAudioClip(getClass().getResource("se/coin03.wav"));
	       // パネルの推奨サイズを設定、pack()するときに必要
        setPreferredSize(new Dimension(Gamen.WIDTH, Gamen.HEIGHT));
        // パネルがキー入力を受け付けるようにする
     //   setFocusable(true);
        // キーイベントリスナーを登録
       // addKeyListener(this);

		this.add(wait);
		wait.setShow(false); //1
		this.add(error);
		error.setShow(false); //3
		this.add(reinsert);
		reinsert.setShow(false); //6

		/*
		 * Check and
		 */

		this.setBounds(0, 0, Gamen.WIDTH, Gamen.HEIGHT);

	}

	public static void main(String[] args) {
		if(args.length != 1){
			System.out.println("引数を1つ指定してください");
			System.exit(0);
		}
		int arg = Integer.parseInt(args[0]);
		if(arg == 10){
			System.out.println("ゴール地点以外使用禁止");
			System.out.println("データベース設定済みなら大丈夫です");
			Control cont = new Control();
			cont.setDefaultCloseOperation( EXIT_ON_CLOSE );
			cont.setVisible( true );
			while( true ){//遷移の無限ループ
				cont.Transition();
			}
		}

		if(arg !=10){
			DataKeep.Name_set(Integer.parseInt(args[0]));
			//= Integer.parseInt(args[0]);
	        StampMainFrame mf = new StampMainFrame();

	        //System.out.println(Data_keep.aa);
	        mf.setDefaultCloseOperation(EXIT_ON_CLOSE);
	        mf.setVisible(true);
	       // mf.requestFocusInWindow(); //必須かもしれないキーイベント
	        while(mf.change_display()){
	        }
		}
	}

	public boolean change_display(){

		while(true){

			wait.setShow(true); //待機画面表示

			DataKeep.open();
			felica_kazashi();//felicaかざすまでループさせている

			DataKeep.close();

			//sound.play();
			//待機画面からゲーム待機画面へ移動


			Before game_taiki = new Before();
			this.add(game_taiki);game_taiki.setShow(false);
			game_taiki.setShow(true);
			wait.setShow(false);
			game_taiki.timeStart();
			score.reset();

			boolean taikiflg = true;
			while(taikiflg){
				System.out.print("");
				switch(game_taiki.worp_Hantei()){
				case 1:
					taikiflg = false;
					System.out.print("チュートリアル終了します\n");
					game_taiki.stop();
					break;
				default:
				}
				continue;
			}

			//今はここでnewをしているが
			//リスタートやリセットメソッドを実装させる予定

			Game game = new Game();
			this.add(game);game.setShow(false);//最後にremove必要

			game.setShow(true);//げーむ開始
			game_taiki.setShow(false);
			game_taiki = null;
			this.start = game.timeStart();//ゲーム時間測定開始
			score.reset();
			OT:
			while(true){
				if(timerhontai() == false){
					continue;
				}
				DataKeep.open();
				if(DataKeep.EntryFinish().equals(DataKeep.EnyIdm())){ //ゲーム時間
					DataKeep.write(score.getScore());
					game.stop();

					result = new Result();
					DataKeep.close();
					this.add(result);result.setShow(false);
					result.setShow(true);
					game.setShow(false);
					break;
				}
				this.start = System.currentTimeMillis();
				while(timer15()){     //ゲーム時間終了しているがカードがかざされていない場合15秒間ループ
					game.stop();


					reinsert.setShow(true);
					game.setShow(false);
					if(DataKeep.EntryFinish().equals(DataKeep.EnyIdm())){
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
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
								//sound.play();
								DataKeep.write(score.getScore());

								result = new Result();
								DataKeep.close();
								this.add(result);result.setShow(false);
								result.setShow(true);//かざされたらリザルト画面移動
								reinsert.setShow(false);
								break OT;
							}else {
								reinsert.setShow(false);
								errorFlag = false;
							}
						}
					}
				}
				DataKeep.close();
				reinsert.setShow(false);  //15秒たつとはじめの画面に移動
				game = null;
				return true;
			}


			DataKeep.open();
			while(true){
				if(DataKeep.getId().equals("")){
				break;
				}
			}
			DataKeep.close();

			result.setShow(false);

			//後処理
			game = null;


		}
	}
	public void felica_kazashi(){

		while(feli_flg){ //かざされるまでループ
			DataKeep.EntryIdm();
			//id = felica.getIDm();
			if("".equals(DataKeep.EntryFinish())){ //かざされなければ無視
			}else{

				sound.play();

				try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(DataKeep.Card()){//学校のカードがかざされているか？
					if(DataKeep.Round() == 5){
					feli_flg = false;
					}else{ //すでにチェックポイントをまわっていた

						Check check = new Check();
						this.add(check);check.setShow(false);
						check.setShow(true);//まわっていますと表示
						wait.setShow(false);
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						check.setShow(false);
						check=null;
						wait.setShow(true);//その後待機画面に戻す
					}
				}else{//学校のかーどではない
					wait.setShow(false);
					error.setShow(true);//エラー画面を表示
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					error.setShow(false);
					wait.setShow(true);//その後待機画面に戻す
				}
			}

		}
		feli_flg = true;

	}

	public void finish(){

	}

	public boolean timerhontai(){//ゲーム時間

		if(time <= System.currentTimeMillis() - start ){
			return true;
		}

		return false;
	}

	public boolean timersub(){//ゲーム時間

		if(time_taiki <= System.currentTimeMillis() - start ){
			return true;
		}

		return false;
	}

	public boolean timer15(){//待機時間
		if(heldover >= System.currentTimeMillis() - start ){
			return true;
		}

		return false;
	}

}


