package com.db;

import java.sql.Connection;
import java.sql.DriverManager;


//연결자 생성과정
public class DBConn {

	private static Connection dbConn;

	public static Connection getConnection() {

		if (dbConn == null) {

			try {
				// 오라클의 기본포트번호 1521
				String url = "jdbc:oracle:thin:@192.168.16.27:1521:TestDB";
				String user = "suzi";
				String pwd = "a123";

				// Class 가 class를 읽는 class이다.
				// oracle 안에 jdbc 안에 driver안에 있는 OracleDriver를 읽어와라
				Class.forName("oracle.jdbc.driver.OracleDriver");

				// dbConn에게 불러오라고할꺼다
				dbConn = DriverManager.getConnection(url, user, pwd);

			} catch (Exception e) {
				System.out.println(e.toString());

			}

		}

		return dbConn;
	}

	public static void close() {

		if (dbConn != null) {

			try {

				if (!dbConn.isClosed()) {
					dbConn.close();
				}

			} catch (Exception e) {
				System.out.println(e.toString());
			}

		}
		//DB를 닫았으면 dbConn = null 로 청소를 해줘야한다. 
		//안그러면 두번째 DB를 연결할때 오류가 생긴다.
		dbConn = null;
	}

}
