package main;

import java.awt.Dimension;

import javax.swing.JFrame;

import rankingScreen.Complete;
import rankingScreen.GoalWait;
import rankingScreen.MyError;
import rankingScreen.NotComplete;
import common.Common;
import check.CardCheck;

public class Control extends JFrame{

	private static final long serialVersionUID = 1L;
	/* 画面一覧 */
	private Complete       compScreen;//ゴール画面

	private GoalWait       GoalScreen   = new GoalWait();//ゴール待機画面
	private NotComplete notCompScreen ;//未完了画面
	private MyError        ErrorCard;//違うカード画面
	/* その他 */
	private check.CardCheck    Check    = new CardCheck();
	/**
	 * ゴール画面宣言
	 */
	public void GoalDeclaration(){
		compScreen = new Complete();
		this.add( compScreen );
		compScreen.setShow( false );
	}

	/**
	 * 未通過画面宣言
	 */
	public void notCompDeclaration(){
		notCompScreen = new NotComplete();
		this.add( notCompScreen );
		notCompScreen.setShow( false );
	}

	/**
	 * エラー画面宣言
	 */
	public void ErrorDeclaration(){
		ErrorCard = new MyError();
		this.add( ErrorCard );
		ErrorCard.setShow( false );
	}
	/**
	 * コンストラクタ
	 */
	public Control(){
		setPreferredSize( new Dimension( Common.DISP2_WIDTH,Common.DISP2_HEIGHT ) );

		/*遷移用画面の宣言*/
		this.add( GoalScreen );
		GoalScreen.setShow( false );
		this.setTitle( "画面遷移" );
		this.setBounds( 0,0,Common.DISP2_WIDTH,Common.DISP2_HEIGHT );
	}



	/**
	 * 画面遷移の流れの管理
	 */
	public boolean Transition(){
		while( true ){//ループ( 無限 )
			GoalScreen.setShow( true );//待機画面を表示
			if( Check.felica_check() ){//カードがセットされたか
				if( Check.SchoolCardCheck() ){//学校で保持しているカードか
					if( Check.AllCheck() ){//すべてのチェックポイントにフラグが立っているか
						GoalDeclaration();//ゴール完了画面作成
						GoalScreen.setShow( false );//ゴール完了画面に変更
						compScreen.setShow( true  );
						try{
							Thread.sleep( Common.GOAL_TIME_VIEW );//?秒間表示
						}catch ( InterruptedException e ){
							e.printStackTrace();
						}

						compScreen.setShow( false );//待機画面に戻る
						GoalScreen.setShow( true  );
					}else{//すべてにチェックポイントフラグが立っていない
						notCompDeclaration();
						GoalScreen.setShow   ( false );//checkフラグ○×表示(未完了)画面に変更
						notCompScreen.setShow( true  );

						try{
							Thread.sleep( Common.ERROR_TIME_VIEW );//?秒間表示
						}catch( InterruptedException e ){
							e.printStackTrace();
						}

						notCompScreen.setShow( false );//待機画面に戻る
						GoalScreen.setShow   ( true  );
					}
				}else{//学校のカード以外がセットされた
					ErrorDeclaration();
					GoalScreen.setShow( false );
					ErrorCard.setShow ( true  );//エラー画面に遷移

					try{
						Thread.sleep( Common.ERROR_TIME_VIEW );//?秒間表示
					}catch( InterruptedException e ){
						e.printStackTrace();
					}

					ErrorCard.setShow ( false );
					GoalScreen.setShow( true  );
				}
			}
			return true;
		}
	}
}