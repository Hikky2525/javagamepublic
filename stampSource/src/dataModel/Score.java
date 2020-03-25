package dataModel;



public class Score{
	private static Score score_in;
	private int score = 0;

	public static Score getInstance(){
		if(score_in == null){
			score_in = new Score();
		}

		return score_in;
	}
	//コインを取得したら１足す


	public int getScore() {
		return score;
	}

	public void add( int add ){
		score+=add;
	}
	public void reset(){
		score = 0;
	}

	public String GetToScore(){
		String ten = Integer.toString( getScore() );
		ten = "  " + ten;
		ten = "とくてん " + ten;
		return ten;
	}
}