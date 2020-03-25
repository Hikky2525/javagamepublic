package dataModel;

import check.CardCheck;
import gameObj.*;
import felica.Stamp_Felica;
public class Data_keep {

	private static final Data_keep insta = new Data_keep();
	public static Data_keep getInstance(){
		if(insta == null){

		}
		return insta;
	}


	private String idm;

	private int Goukei_Score;
	//private boolean Gakkou_Card;
	private String Name; //このプログラムはどこのチェックポイントの名前？
	private int block_num;

	private check.CardCheck    card  = new CardCheck();
	private Stamp_Felica felica = new Stamp_Felica();

	public void open(){
		felica.open();
	}

	public void close(){
		felica.close();
	}

	public String getName() {
		return Name;
	}


	public void Name_set(int line){
		Parts parts = Parts.getInstance();
		block_num = line;
		System.out.println(line + "＝コマンドライン引数");
		switch(line){
			case 1:
				Name = "システム設計科(変更済み)";
			break;
			case 2:
				Name = "メカトロニクス科";
				parts.setMap("map01.dat");
				parts.setMcoin("mcoin_meka.gif");
			break;
			case 3:
				Name = "機械加工システム科";
				parts.setMap("map02.dat");
				parts.setMcoin("mcoin_kikai.gif");
			break;
			case 4:
				Name = "建築科";
				parts.setMap("map03.dat");
				parts.setMcoin("mcoin_ken.gif");
			break;
			case 5:
				Name = "キャリア・プログラム科";
				parts.setMap("map04.dat");
				parts.setMcoin("mcoin_kyari.gif");
			break;
			case 6:
				Name = "総合実務科";
				parts.setMap("map05.dat");
				parts.setMcoin("mcoin_sougou.gif");
			break;
		}
	}



	public int Round(){
		int ret =felica.read_p(block_num);
		return ret;
	}


	//学校のカードかどうかboolean型で返す
	public boolean Card(){

		boolean flg = false ;
		for(int i=0;i<card.idm.length;i++){
			if(this.idm.equals(card.idm[i])){
				flg = true;
			}
		}
		return flg;
	}
	//各チェックポイントが○か×をbooleanで分けてあげる
	public String[] check_p() {
		//int one = felica.read_p(1);
		int two = felica.read_p(2);
		int tre = felica.read_p(3);
		int yon = felica.read_p(4);
		int go  = felica.read_p(5);
		int roku = felica.read_p(6);
		String[] flg = new String[5];

		/*if (one == 0) {
			this.sys = false;
			flg[0] = "×";
		} else {
			this.sys = true;
			flg[0] = "○";
		}*/

		switch(two){
		case 1:
			flg[0] = "○";
			break;
		case 5:
			flg[0] = " ×";
			break;
			default:
				flg[0] = "E";
		}

		switch(tre){
		case 1:
			flg[1] = "○";
			break;
		case 5:
			flg[1] = " ×";
			break;
			default:
				flg[1] = "E";
		}

		switch(yon){
		case 1:
			flg[2] = "○";
			break;
		case 5:
			flg[2] = " ×";
			break;
			default:
				flg[2] = "E";
		}


		switch(go){
		case 1:
			flg[3] = "○";
			break;
		case 5:
			flg[3] = " ×";
			break;
			default:
				flg[3] = "E";
		}

		switch(roku){
		case 1:
			flg[4] = "○";
			break;
		case 5:
			flg[4] = " ×";
			break;
			default:
				flg[4] = "E";
		}

		return flg;
	}

	public String getId(){
		return felica.getIDm();
	}

	//各場所のプログラムが書きこむべき配列にflgとScoreを書く
	public void write(int score){
		System.out.println(felica.write(block_num, score)); //取得した変数を入れる
	}

	//登録済みを返す
	public String EntryFinish() {
		return idm;
	}

	//登録するIdm  Idmがあれば登録なければ""をかえしているだけ
	public void EntryIdm() {
		this.idm = felica.getIDm();
	}

	//かざされているか確認するだけ
	public String EnyIdm(){
		return felica.getIDm();
	}

	//高田案  isOpenedを採用型かも？
	//test
	public int totalScore(){
		Goukei_Score =0;
		//Goukei_Score += felica.read_score(1);
		Goukei_Score += felica.read_score(2);
		Goukei_Score += felica.read_score(3);
		Goukei_Score += felica.read_score(4);
		Goukei_Score += felica.read_score(5);
		Goukei_Score += felica.read_score(6);
		//System.out.println(Goukei_Score);
		return Goukei_Score;
	}

}
