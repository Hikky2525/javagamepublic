package storage;

import felica.Ranking_Felica;

public class getParameter {
	private static int[] block = { 0x00,0x01,0x02,0x03,0x04,0x05 };
	private static Ranking_Felica reader = new Ranking_Felica();
	//IDmの取得
	public String getIdm(){
		reader.open();
		String idm = reader.getID();
		reader.close();
		return idm;
	}

	public int totalScore(){
		int total = 0;//合計スコア取得
		reader.open();
		for( int i = 0;i < 6;i++ ){
			total += reader.read( block[i] );
		}
		reader.close();
		return total;
	}
}
