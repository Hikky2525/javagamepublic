package check;

import felica.Ranking_Felica;

public class CardCheck {
	Ranking_Felica reader = new Ranking_Felica();
	public String[] idm = new String[96];


	public CardCheck(){
		idm[0] = "01270078840B71AA";//3
		idm[1] = "01270078840B5DAA";
		idm[2] = "01270077FAD9A9A9";//5
		idm[3] = "01270078840B89AA";
		idm[4] = "01270078840B88AA";
		idm[5] = "01270077FAD93FA9";
		idm[6] = "01270078840BB4A9";
		idm[7] = "01270078840BA4AA";//10
		idm[8] = "01270077FAD994A9";
		idm[9] = "01270078840B6CA9";
		idm[10] = "01270077FAD9A8A9";
		idm[11] = "01270077FAD952A9";
		idm[12] = "01270078840B6DA9";//15
		idm[13] = "01270077FAD994AA";
		idm[14] = "01270077FAD97DA9";
		idm[15] = "01270077FAD9ABAA";
		idm[16] = "01270078840B73AA";
		idm[17] = "01270077fad993aa";//20
		idm[18] = "01270077fad95fA7";//22
		idm[19] = "01270078840B5FA7";
		idm[20] = "01270077FAD94AA7";
		idm[21] = "01270078840B61A7";//25
		idm[22] = "01270077FAD94BA7";
		idm[23] = "01270078840B90A8";
		idm[24] = "01270077FAD9AFA7";
		idm[25] = "01270077FAD9ADA7";//30
		idm[26] = "01270077FAD936A6";
		idm[27] = "01270078840B2CA6";
		idm[28] = "01270077FAD937A6";
		idm[29] = "01270078840B3EA6";
		idm[30] = "01270077FAD948A6";//35
		idm[31] = "01270078840B9FA7";
		idm[32] = "01270077FAD980A7";
		idm[33] = "01270078840B82A7";
		idm[34] = "01270077FAD965A7";
		idm[35] = "01270078840B2BA6";//40
		idm[36] = "01270077FAD95FA8";
		idm[37] = "01270078840B51A8";
		idm[38] = "01270077FAD95DA6";
		idm[39] = "01270078840V57A6";
		idm[40] = "01270077FAD95CA6";//45
		idm[41] = "01270078840B50A8";
		idm[42] = "01270077FAD960A8";
		idm[43] = "01270078840B39A8";
		idm[44] = "01270078840A4E87";
		idm[46] = "01270078840A6667";//50
		idm[47] = "01270078840B7BA8";
		idm[48] = "01270077FAD9A9A8";
		idm[49] = "01270078840B3DA6";
		idm[50] = "01270077FAD949A6";
		idm[51] = "01270078840A7B67";//55
		idm[52] = "01270078840B7AA8";
		idm[53] = "01270078840B55A6";
		idm[54] = "01270078840A6787";
		idm[55] = "01270078840A6767";
		idm[56] = "01270078840A4987";//60
		idm[57] = "01270077FAD98CA8";
		idm[58] = "01270078840B52A9";
		idm[59] = "01270077FAD930A8";
		idm[60] = "01270078840B3EA9";
		idm[61] = "01270078840B66A8";//65
		idm[62] = "01270077FAD98DA8";
		idm[63] = "01270078840B68A8";
		idm[64] = "01270077FAD93BA9";
		idm[65] = "01270078840B51A9";
		idm[66] = "01270077FAD97AA8";//70
		idm[67] = "01270077FAD9A6A8";
		idm[68] = "01270077FAD946A8";
		idm[69] = "01270078840B38A8";
		idm[70] = "01270077FAD949A8";
		idm[71] = "01270078840B30A9";//75
		idm[72] = "01270077FAD931A8";
		idm[73] = "01270077FAD930A7";
		idm[74] = "01270078840B48A7";
		idm[75] = "01270077FAD931A7";
		idm[76] = "01270078840B49A7";//80
		idm[77] = "01270078840BA3AA";
		idm[78] = "01270078840B81A9";
		idm[79] = "01270077FAD981A7";
		idm[80] = "01270078840B99A7";
		idm[81] = "01270077FAD997A7";//85
		idm[82] = "01270078840BB4A7";
		idm[83] = "01270077FAD999A7";
		idm[84] = "01270078840BB0A8";
		idm[85] = "01270077FAD953A9";
		idm[86] = "01270078840B82A9";//90
		idm[87] = "01270077FAD9ADAA";
		idm[88] = "01270077FAD969A9";
		idm[89] = "01270078840B95A9";//94
		idm[90] = "01270078840BB0A9";//96
		idm[91] = "0127005D1A0B5DD1";
		idm[92] = "0127005D1A0B7DAB";//100
		idm[93] = "01270077FAD995A9";//2
		idm[94] = "01270077FAD95FA7";//20
		idm[95] = "01270077FAD993AA";//22
	}
	/**
	 * 全件回ったかのチェック
	 * @return
	 */
	public boolean AllCheck( ){
		boolean ret = false;
		int[] blockNum = new int[5];
		boolean[] flg  = new boolean[5];
		blockNum[0] = 0x02;
		blockNum[1] = 0x03;
		blockNum[2] = 0x04;
		blockNum[3] = 0x05;
		blockNum[4] = 0x06;
		reader.open();
		for( int i = 0;i< blockNum.length;i++ ){
			@SuppressWarnings("unused")
			int num = 0;
			if( ( num = reader.read_p( blockNum[i] ) ) == 1 ){
				flg[i] = true;
			}else{
				flg[i] = false;
			}
		}

		if( flg[0] == true && flg[1] == true && flg[2] == true && flg[3] == true && flg[4] == true ){
			ret = true;
		}
		reader.close();
		return ret;
	}

	/**
	 * カードがかざされたかの判定
	 * @return　可否
	 */
	public boolean felica_check(){
		boolean ret = false;
		reader.open();

		String ID = null;
		ID = reader.getIDm();
		if( ID != "" && ID != null ){
			ret = true;
		}else{
			ret = false;
		}

		reader.close();
		return ret;
	}
	/**
	 * 学校のカードかチェックする
	 * @return　可否
	 */
	public boolean SchoolCardCheck(){
		boolean ret = false;
		reader.open();
		String ID = reader.getIDm();
		for( int i = 0;i < idm.length;i++ ){
			if( ID.equals( idm[i] )){
				ret = true;
				break;
			}
		}
		reader.close();
		return ret;
	}



}
