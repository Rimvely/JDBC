package com.jdbc;

import java.sql.Connection;
import java.sql.Statement;

import com.db.DBConn;

public class Test2 {

	public static void main(String[] args) {

		Connection conn = DBConn.getConnection();

		if (conn == null) {
			System.out.println("데이터베이스 연결 실패!!");
			System.exit(0);
		}

		// DB연결
		// 1.DriverManager가 Connection을 생성
		// 2.Connection이 Statement를 생성
		// 3.Statement가 Query를 실행

		try {
			// 2.Connection이 Statement를 생성
			// SQL구문을 실행하는 Interface
			Statement stmt = conn.createStatement();
			String sql;

			sql = "insert into score(hak, name, kor, eng, mat) "; // 반드시 한칸
			// 띄우고
			// ) " 해라!
			sql += "values ('112', '김수지', 90, 50, 60)"; // 위에를 안띄우면 " values
			// 로
			// 해도된다.

			// sql = "update score set kor=99, eng=99, mat=99";
			// sql += "where hak=111";

			// sql = "delete score where hak=111";

			int result = stmt.executeUpdate(sql); // insert, update, delete는
													// excuteUpdate
													// select 는 excuteQuery
			// 실행이 제대로 되면 result 값이 1
			// 실행이 제대로 안되면 result 값이 0

			if (result == 1) {
				System.out.println("추가 성공!!");

			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		// 반드시 try안에서
		DBConn.close();

	}

}
