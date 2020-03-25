package dao;

import java.sql.*;

import storage.*;
public class SortDAO {
	public RankingData[] Ranking(){
		Connection conn  = null;
		RankingData[] RankData = new RankingData[10];
		for( int i = 0;i < RankData.length;i++ ){
			RankData[i] = new RankingData();
		}
		try{
			// JDBCドライバのロード - JDBC4.0（JDK1.6）以降は不要
	        Class.forName("com.mysql.jdbc.Driver").newInstance();
	        // MySQLに接続
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
	        Statement stm = conn.createStatement();
	        //実行するSQL文の設定
	        String sql = "SELECT * from Ranking ORDER BY total_score DESC LIMIT 10";
	        ResultSet rst = stm.executeQuery( sql );
		        for( int i = 0;i < RankData.length;i++ ){
		        	if( rst.next() && rst != null ){
						RankData[i].setPassword( rst.getString( "password" ) );
						RankData[i].setScore   ( rst.getInt( "total_score"  ) );
		        	}else{
		        		break;
		        	}
		        }

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
		return RankData;
	}
}
