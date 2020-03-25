package ranking;

import storage.RankingData;

public class RankCheck {
	public int serchRanking( RankingData[] Ranking,String Password ){
		int retNum=0;
		for( int i = 0;i < Ranking.length;i++ ){
			if( Ranking[i].getPassword().equals( Password )){
				retNum = i + 1;
			}
		}
		return retNum;
	}
}
