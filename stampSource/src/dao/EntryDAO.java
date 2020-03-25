package dao;

import java.sql.*;

public class EntryDAO {
	public void InsertDatabase( int total_score,String Password ){
		Connection conn = null;
		try{
			// JDBCドライバのロード - JDBC4.0（JDK1.6）以降は不要
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // MySQLに接続
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            Statement stm = conn.createStatement();
            //実行するSQL文の設定
            String sql = "INSERT INTO Ranking( total_score,password,time )"
            		+ "values('" + total_score + "'," + Password + ",now())";
            stm.executeUpdate( sql );

		}catch( InstantiationException | IllegalAccessException | ClassNotFoundException e ){
            System.out.println("JDBCドライバのロードに失敗しました。");
		}catch( SQLException e ){
			 System.out.println("MySQLに接続できませんでした。");
		}finally{
			 if ( conn != null) {
	                try {
	                    conn.close();
	                } catch (SQLException e) {
	                    System.out.println("MySQLのクローズに失敗しました。");
	                }
	            }
		}
	}
}
