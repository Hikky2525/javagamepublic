package gameObj;

public class Parts {
	private static Parts score_in;

	public static Parts getInstance(){
		if(score_in == null){
			score_in = new Parts();
		}

		return score_in;
	}
	private String map;
	public void setMap(String map) {
		this.map = map;
	}

	public void setMcoin(String mcoin) {
		this.mcoin = mcoin;
	}
	private String mcoin;


	public String getMap() {
		return map;
	}
	public String getMcoin() {
		return mcoin;
	}

}
