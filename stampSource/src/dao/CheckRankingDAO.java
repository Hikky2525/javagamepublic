package dao;

import java.sql.*;

import storage.*;
public class CheckRankingDAO {
	public RankingData[] getRanking(){
		Connection conn  = null;
		RankingData[] RankData=null;
		try{
			// JDBCドライバのロード - JDBC4.0（JDK1.6）以降は不要
	        Class.forName("com.mysql.jdbc.Driver").newInstance();
	        // MySQLに接続
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
	        Statement stm = conn.createStatement();
	        //実行するSQL文の設定
	        String sql1 = "SELECT COUNT(*)cnt FROM Ranking";//要素数の取得
	        String sql2 = "SELECT * FROM Ranking ORDER BY total_score DESC";
	        ResultSet rst1 = stm.executeQuery( sql1 );

	        rst1.next();
	        int length = rst1.getInt( "cnt" );
	        RankData = new RankingData[ length ];
	        for( int i = 0;i < RankData.length;i++ ){
				RankData[i] = new RankingData();
			}
	        ResultSet rst2 = stm.executeQuery( sql2 );
		        for( int i = 0;i < RankData.length;i++ ){
		        	if( rst2.next() && rst2 != null ){
						RankData[i].setPassword( rst2.getString( "password" ) );
						RankData[i].setScore   ( rst2.getInt( "total_score"  ) );
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
