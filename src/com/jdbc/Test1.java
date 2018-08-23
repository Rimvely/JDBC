package com.jdbc;

import java.sql.Connection;

import com.db.DBConn;

public class Test1 {

	public static void main(String[] args) {

		// com.db에서 conn 호출
		Connection conn = DBConn.getConnection();

		if (conn == null) {

			System.out.println("데이터베이스 연결 실패!!");
			System.exit(0);

		}

		System.out.println("데이터베이스 연결 성공!!");
		// com.db에서  close() 호출
		DBConn.close();
		
		
		//System.out.println(conn);
		
		//hashcode
		//oracle.jdbc.driver.T4CConnection@27f8302d
		
		

	}

}
//데이터베이스 연결 성공!!이 안뜨는사람은 dbConn안에서 에러 발생이 큼

//연결 성공했으니 연결해보겠습니다
//Test2로 가기
