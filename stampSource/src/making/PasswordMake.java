package making;

import java.util.Random;

import dao.PassCheckDAO;

/**
 * パスワードを自動生成するプログラム
 * 生成したパスワードが登録されているなら新たに作る
 * @author 山本泰成
 *
 */
public class PasswordMake {
	/**
	 * パスワードの生成
	 * @return 作成したパスワードを返す
	 */
	public static String MakePassword(){
		String retPassword = null;
		Random rnd = new Random();
		String password = null;
		String no1 = null;
		String no2 = null;
		String no3 = null;
		String no4 = null;
		String check;
		String[] Ngpass = new String[4];
		Ngpass[0] = "0000";
		Ngpass[1] = "000";
		Ngpass[2] = "00";
		Ngpass[3] = "0";
		while( true ){
			while( true ){
				no1 = Integer.toString( rnd.nextInt( 10 ) );
				no2 = Integer.toString( rnd.nextInt( 10 ) );
				no3 = Integer.toString( rnd.nextInt( 10 ) );
				no4 = Integer.toString( rnd.nextInt( 10 ) );
				check = no1 + no2 + no3 + no4;
				if( !( check.substring( 0,4 ).equals( Ngpass[0] ) && check.substring( 0,3 ).equals( Ngpass[1] ) &&
					check.substring( 0,2 ).equals( Ngpass[2] ) && check.substring( 0,1 ).equals( Ngpass[3]) ) ){

					if( !( check.substring( 0,1 ).equals( Ngpass[3] ) )){
						password = check;
						break;
					}
				}


			}
			System.out.println( "パスワード:" + password );
			PassCheckDAO DAO = new PassCheckDAO();
			boolean flg = DAO.CheckDatabase( password );
			if( !flg ){
				retPassword = password;
				break;
			}

		}
		return retPassword;
	}
}
