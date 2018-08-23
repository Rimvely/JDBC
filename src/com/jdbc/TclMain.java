package com.jdbc;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

import com.db.DBConn;

public class TclMain {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Connection conn = DBConn.getConnection();
		Statement stmt = null;
		String sql;

		int ch, id;
		String name, birth, tel;

		try {

			while (true) {

				do {
					System.out.print("1.입력 2.출력 3.종료");
					ch = sc.nextInt();
				} while (ch < 1 || ch > 3);

				switch (ch) {
				// 여러개의 데이터를 한번에 다 받고 넘어가야하기때문에
				// 입력 받아서 test1, test2, test3 넣어야하기때매문에
				// conn.setAutoCommit(false);
				case 1:
					conn.setAutoCommit(false);
					System.out.print("아이디(숫자)?");
					id = sc.nextInt();

					System.out.print("이름?");
					name = sc.next();

					System.out.print("생일(yyyy-mm-dd)?");
					birth = sc.next();

					System.out.print("전화번호?");
					tel = sc.next();

					// ------------------------------------------------------
					sql = String.format(
							"insert into test1(id, name) values (%d, '%s')",
							id, name);

					stmt = conn.createStatement();

					stmt.executeUpdate(sql);

					stmt.close();// 테이블이 다르기때문에 매번 닫아줘야한다
					// ------------------------------------------------------
					sql = String.format(
							"insert into test2(id, birth) values (%d, '%s')",
							id, birth);

					stmt = conn.createStatement();

					stmt.executeUpdate(sql);

					stmt.close();
					// ------------------------------------------------------
					sql = String.format(
							"insert into test3(id, tel) values (%d, '%s')", id,
							tel);

					stmt = conn.createStatement();

					stmt.executeUpdate(sql);

					stmt.close();

					conn.commit(); // 그래서 다 받고 마지막에 conn.commit(); 해야한다

				case 2:
					break;

				case 3:

					DBConn.close();
					System.exit(0);

				}

			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}
}
