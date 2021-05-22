package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	Connection con = null;	// JDBC의 정보를 가지는 인터페이스
	Statement stmt = null;	// SQL준비하고 실행시 매개변수 받아와 사용, 쿼리 실행 후 해당 쿼르의 결과물을 받아옴
	ResultSet rs = null;	// statement의 쿼리 실행 결과를 받아서 컬럼 정보 및 로우 정보를 Cutsor를 이용해 관리

	public DBConnection() {
		// TODO Auto-generated constructor stub
	}

	public static Connection DBconnection() throws Exception {
		
		String mysql_driver = "com.mysql.cj.jdbc.Driver"; //JDBC 드라이버 로딩(프젝당 하나만 실행)
		String server = "localhost:3306";
		String database = "UCS";
		String user = "root";
		String password = "1234";

		String url = "jdbc:mysql://" + server + "/" + database;
		
		url += "?useSSL=FALSE&";

		// 데이터베이스 연동시 에러
		// mysql-connector-java 버전 5.1.X 이후 버전부터 KST 타임존을 인식하지 못하는 이슈
		url += "characterEncoding=UTF-8&serverTimezone=UTC";

		Connection con = null;

		// 드라이버 로드
		try {

			Class.forName(mysql_driver); // 1) JDBC드라이버 로딩 ->mysql_driver가 DriverManager에 등록

		} catch (ClassNotFoundException e) {

			System.err.println("Driver Load Error: " + e.getMessage());
			e.printStackTrace();
		}

		// 데이터베이스 연결
		try {
			// 2) DriverManager.getConnection("jdbc:mysql://localhost:3306/DB명", “사용자ID","사용자PASSWORD");
			// Connection 객체 연결 -> DriverManager에 등록된 각 드라이버들을 getConnection(String url) 메소드 사용해서 식별
			// 연결 객체 찾지 못하면 error문구 발생
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Connection Success : " + url + "");

		} catch (SQLException ex) {

			System.err.println("Connection Error :" + ex.getMessage());
			ex.printStackTrace();
		}

		return con;
	}

	public ResultSet SQLExecute(String SQLType, String strSQL) throws Exception {

		try {

			// DB 연결
			con = DBconnection();

			System.out.println(strSQL);

			// 쿼리 실행
			stmt = con.createStatement();

			if (SQLType == "F" || SQLType == "G") {
				// select
				rs = stmt.executeQuery(strSQL);
			} else {
				// insert, update, delete
				stmt.executeUpdate(strSQL);
			}

		} catch (SQLException ex) {

			System.err.println(ex.getStackTrace());

			System.err.println("Error Message :" + ex.getMessage());

		} catch (Exception ex) {

			System.err.println(ex.getStackTrace());

			System.err.println("Error Message :" + ex.getMessage());

		} finally {

		}

		return rs;

	}

	
	//DBConnectionClose()메소드 수행시 에러가 발생하면 Exception에 예외처리를 요청
	public void DBConnectionClose() throws Exception {

		if (rs != null)
			rs.close();

		if (stmt != null)
			stmt.close();

		if (con != null)
			con.close();

	}

}

