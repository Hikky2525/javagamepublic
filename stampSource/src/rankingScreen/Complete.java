package rankingScreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;

import ranking.RankCheck;
import storage.RankingData;
import making.PasswordMake;
import common.Common;
import dao.CheckRankingDAO;
import dao.EntryDAO;
import felica.Ranking_Felica;
/**
 * ゴール完了画面
 * @author 山本泰成
 *
 */
public class Complete extends Screen {
	private static final long serialVersionUID = 1L;
	private Image image;


	public Complete(){
		image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("goal.jpg"));
		display();
	}
	@Override
	//表示関連
	public void display() {

		//スコアラベル
		JLabel Score          = new JLabel();
		//ランク
		JLabel Rank           = new JLabel();
		//識別番号ラベル
		JLabel PassCode       = new JLabel();

		//Threadに名前を付ける,設定
		this.setName( "ゴール!" );
		this.setLayout( null );
		this.setSize( Common.DISP2_WIDTH,Common.DISP2_HEIGHT );
		int    total    = total_Calc();//カードから合計点取得
		String Password = writeDB( total );;//パスワードの生成
		int juni = RankCheck( Password );
		Score.setText( total + "点");
		Rank.setText( juni + "位" );
		PassCode.setText( Password );

		Score.setFont  ( new Font("MSゴシック", Font.BOLD, Common.FONT_SIZE_DISP        ) );
		Rank.setFont( new Font ( "MSゴシック",Font.BOLD,Common.FONT_SIZE_DISP           ) );
		PassCode.setFont  ( new Font("MSゴシック", Font.BOLD, Common.FONT_SIZE_DISP ) );

		Score.setBounds( 900,120,1200,600 );
		Rank.setBounds( 900,280,1200,600 );
		PassCode.setBounds( 900,430,1200,600 );

		this.add( Score );
		this.add( Rank  );
		this.add( PassCode );

		//カードの初期化
		cardDelete();
		System.out.println( "カードを初期化しました" );

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//待機画面の背景色指定
		Color color = new Color(100,255,100);
		g.setColor(color);
		g.fillRect(0, 0, getWidth(), getHeight());


		g.drawImage( image,0,0,this );
	}

	/**
	 * 合計得点の算出
	 */

	public int total_Calc(){
		Ranking_Felica reader = new Ranking_Felica();
		int[] readBlock = new int[5];
		int total = 0;
		readBlock[0] = 0x02;
		readBlock[1] = 0x03;
		readBlock[2] = 0x04;
		readBlock[3] = 0x05;
		readBlock[4] = 0x06;

		reader.open();
		for( int i = 0;i < readBlock.length;i++ ){
			total += reader.read_score( readBlock[i] );
		}
		reader.close();
		return total;
	}

	/**
	 * カードの初期化
	 */
	public void cardDelete(){
		Ranking_Felica reader = new Ranking_Felica();
		reader.open();
		reader.delete5();
		System.out.println( "カードを初期化しました" );
		reader.close();
	}

	/**
	 * データベースへの登録
	 */

	public String writeDB( int total_score ){
		String ret  = PasswordMake.MakePassword();
		EntryDAO DAO = new EntryDAO();//DBに登録する
		DAO.InsertDatabase( total_score,ret );
		return ret;
	}

	/**
	 * 順位の取得
	 */
	public int RankCheck( String Password ){
		CheckRankingDAO  DAO  = new CheckRankingDAO();
		RankingData[] Data = DAO.getRanking();
		RankCheck search = new RankCheck();
		int rank = search.serchRanking( Data,Password );
		return rank;
	}
}
