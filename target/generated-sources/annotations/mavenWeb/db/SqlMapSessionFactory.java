package mavenWeb.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SqlMapSessionFactory {

	private static SqlMapSessionFactory factory = new SqlMapSessionFactory();
	
	private SqlMapSessionFactory() {}
	
	public static SqlMapSessionFactory getInstance() {
		return factory;
	}
	
	public Connection connect() {
		
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "사용자계정", "비밀번호");
					
		}
		catch(Exception ex) {
			System.out.println("오류 발생: " + ex);
		}
		
		return conn;
		
	}
	
	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		
		if ( rs != null ) {
			try {
				rs.close();
			}
			catch(Exception ex) {
				System.out.println("오류 발생: " + ex);
			}
		
			close(conn, ps);	// Recursive 구조 응용(재귀 함수)
			
		}
	}
	
	public void close(Connection conn, PreparedStatement ps) {
		
		if (ps != null ) {
			try {
				ps.close();
			}
			catch(Exception ex) {
				System.out.println("오류 발생: " + ex);
			}
		}
		
		if (conn != null ) {
			try {
				conn.close();
			}
			catch(Exception ex) {
				System.out.println("오류 발생: " + ex);
			}
		}
		
	}
	
	
}
