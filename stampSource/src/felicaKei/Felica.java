package felicaKei;


import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public class Felica {

	private Pointer pasori; // ポート接続用のハンドル

	//final int SYSTEM_CODE = 0x88b4;
	final int SYSTEM_CODE = 0xffff;
	final int SERVICE_CODE = 9;

	final int READ_BLOCK_NUM = 0x00;   //ブロック番号はカクチェックポイントでわけている
	final int READ_BLOCK_LENGTH = 16;

	//
	// JNAによるfelicalibの関数呼び出し用インタフェース
	//
	private interface CLibrary extends Library {

		// 呼び出し用のインスタンス
		static CLibrary INSTANCE =
			 (CLibrary) Native.loadLibrary("felicalib",CLibrary.class);

		// 呼び出す関数の宣言：felicalibの関数
		Pointer pasori_open(char[] c);
		int pasori_init(Pointer pasori);
		void pasori_close(Pointer pasori);
		Pointer felica_polling(Pointer pasori, int sys, byte rfu, byte time);
		void felica_free(Pointer felica);
		void felica_getidm(Pointer felica, byte[] buf);
		int felica_read_without_encryption02(Pointer felica, int sys,int mode,byte  rfu,byte[] buf);
		int felica_write_without_encryption(Pointer felica, int sys, int rfu, byte[] buf);
		Pointer felica_enum_service(Pointer pasori, short a);
	}

	public boolean open() {

		if (isOpened()) return true; // すでに開いている場合

		pasori = CLibrary.INSTANCE.pasori_open(null); // ポート接続

		if (pasori==Pointer.NULL) { // ポート接続失敗
			return false;
		}
		if (CLibrary.INSTANCE.pasori_init(pasori)!=0) { // ポート初期化失敗
			close(); // ポート切断
			return false;
		}
		return true;
	}

	//
	// ポート接続判定
	//
	public boolean isOpened() {

		if (pasori==Pointer.NULL) return false;

		return true;
	}

	//
	// ポート切断
	//
	public void close() {

		if (isOpened())
			CLibrary.INSTANCE.pasori_close(pasori);

		pasori = Pointer.NULL;
	}

	public String getIDm() {

		if (isOpened()==false) return ""; // ポート接続されていなければ終了

		// カードの認識(ポインタを取得)
		Pointer felica = CLibrary.INSTANCE.felica_polling(pasori,(short)0xFFFF,(byte)0,(byte)0);

		if (felica==Pointer.NULL){
			return ""; // カードを認識できなければ終了
		}

		// カードID(バイト値)を取得
		byte [] buf = new byte[8];
		CLibrary.INSTANCE.felica_getidm(felica, buf);

		// ここでカードのポインタを解放
		CLibrary.INSTANCE.felica_free(felica);

		// カードIDを文字列に：16進数表記(英大文字/数字の2文字)×8
		StringBuffer sbuf = new StringBuffer("");
		for (int i=0; i<8; i++) {
			String hex = Integer.toHexString(buf[i]);
			if (hex.length()==1) sbuf.append("0"); // 1文字の場合前に0を付加
			if (hex.length()>2) // 3文字以上の場合は最後の2文字のみ使用
				hex = hex.substring(hex.length()-2);
			sbuf.append(hex);
		}
		return sbuf.toString().toUpperCase(); // 英字は大文字に
	}

	public int read_score( int block) {
		byte[] data = new byte[READ_BLOCK_LENGTH];

		if (isOpened()==false){
			return -1;// ポート接続されていなければ終了
		}

		// カードの認識(ポインタを取得)
		Pointer felica = CLibrary.INSTANCE.felica_polling(pasori,SYSTEM_CODE,(byte)0,(byte)0);

		if (felica==Pointer.NULL){
			return -1;
		}

		CLibrary.INSTANCE.felica_read_without_encryption02( felica, SERVICE_CODE,0,(byte) block,data );
		int ret;
		if( data[1] < 0 ){
			ret = data[1] + 256;
		}else{
			ret = data[1];
		}
		return ret;
	}

	public int read_p( int block) {
		byte[] data = new byte[READ_BLOCK_LENGTH];

		if (isOpened()==false){
			return -1;// ポート接続されていなければ終了
		}

		// カードの認識(ポインタを取得)
		Pointer felica = CLibrary.INSTANCE.felica_polling(pasori,SYSTEM_CODE,(byte)0,(byte)0);

		if (felica==Pointer.NULL){
			return -1;
		}

		CLibrary.INSTANCE.felica_read_without_encryption02( felica, SERVICE_CODE,0,(byte) block,data );

		int ret = data[0];
		return ret;
	}


	public String write( int block,int score ) {

		if (isOpened()==false) return ""; // ポート接続されていなければ終了

		// カードの認識(ポインタを取得)
		Pointer felica = CLibrary.INSTANCE.felica_polling(pasori,SYSTEM_CODE,(byte)0,(byte)0);

		if (felica==Pointer.NULL) return ""; // カードを認識できなければ終了

		byte[] data = new byte[READ_BLOCK_LENGTH];

		data[0] = (byte)1;
		data[1] = (byte)score;

		CLibrary.INSTANCE.felica_write_without_encryption(felica, SERVICE_CODE,(byte) block,data );

		//System.out.println("戻り値" + error1);

		return "OK";
	}
	public void delete(){
		byte[] data = new byte[READ_BLOCK_LENGTH];
		data[0] = 5;
		Pointer felica = CLibrary.INSTANCE.felica_polling(pasori,SYSTEM_CODE,(byte)0,(byte)0);
		CLibrary.INSTANCE.felica_write_without_encryption(felica, SERVICE_CODE,(byte) 0x00,data);
		CLibrary.INSTANCE.felica_write_without_encryption(felica, SERVICE_CODE,(byte) 0x01,data);
		CLibrary.INSTANCE.felica_write_without_encryption(felica, SERVICE_CODE,(byte) 0x02,data);
		CLibrary.INSTANCE.felica_write_without_encryption(felica, SERVICE_CODE,(byte) 0x03,data);
		CLibrary.INSTANCE.felica_write_without_encryption(felica, SERVICE_CODE,(byte) 0x04,data);
		CLibrary.INSTANCE.felica_write_without_encryption(felica, SERVICE_CODE,(byte) 0x05,data);
		CLibrary.INSTANCE.felica_write_without_encryption(felica, SERVICE_CODE,(byte) 0x06,data);
		CLibrary.INSTANCE.felica_write_without_encryption(felica, SERVICE_CODE,(byte) 0x07,data);
		CLibrary.INSTANCE.felica_write_without_encryption(felica, SERVICE_CODE,(byte) 0x08,data);
		CLibrary.INSTANCE.felica_write_without_encryption(felica, SERVICE_CODE,(byte) 0x09,data);
		CLibrary.INSTANCE.felica_write_without_encryption(felica, SERVICE_CODE,(byte) 0x0a,data);
		CLibrary.INSTANCE.felica_write_without_encryption(felica, SERVICE_CODE,(byte) 0x0b,data);
		CLibrary.INSTANCE.felica_write_without_encryption(felica, SERVICE_CODE,(byte) 0x0c,data);
		CLibrary.INSTANCE.felica_write_without_encryption(felica, SERVICE_CODE,(byte) 0x0d,data);
		CLibrary.INSTANCE.felica_write_without_encryption(felica, SERVICE_CODE,(byte) 0x0c,data);
		CLibrary.INSTANCE.felica_write_without_encryption(felica, SERVICE_CODE,(byte) 0x0d,data);
	}

}