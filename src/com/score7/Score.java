package com.score7;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Scanner;

import com.db.DBConn;

public class Score {

	String id, pw, name, birth, tel, memo;

	Scanner sc = new Scanner(System.in);

	// 입력
	public int insertData() throws Exception {

		int result = 0;

		Connection conn = DBConn.getConnection();
		Statement stmt = null;
		String sql;

		try {

			System.out.println("아이디?");
			id = sc.next();

			if (checkId(id)) {
				System.out.println("중복된 아이디가 있습니다.");
				return 0; //false라고하면 내려간다. return 0이 중요하다
			}

			System.out.println("비밀번호?");
			pw = sc.next();

			System.out.println("이름?");
			name = sc.next();

			System.out.println("생년월일?");
			birth = sc.next();

			System.out.println("전화번호?");
			tel = sc.next();

			System.out.println("기타정보?");
			memo = sc.next();

			sql = "insert into member (id, pwd, name, birth, tel, memo) values (";
			sql += "'" + id + "',";
			sql += "'" + pw + "',";
			sql += "'" + name + "',";
			sql += "'" + birth + "',";
			sql += "'" + tel + "',";
			sql += "'" + memo + "')";

			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("데이터 입력 완료!!");

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("아이디가 중복될 수 없습니다");
		}

		return result;

	}

	// 수정
	public int updateData() {

		int result = 0;

		Connection conn = DBConn.getConnection();
		Statement stmt = null;
		String sql;

		try {

			System.out.println("아이디?");
			id = sc.next();

			System.out.println("이름?");
			name = sc.next();

			System.out.println("생년월일?");
			birth = sc.next();

			System.out.println("전화번호?");
			tel = sc.next();

			System.out.println("기타정보?");
			memo = sc.next();

			sql = "update member set ";
			sql += "name=" + "'" + name + "',";
			sql += "birth=" + "'" + birth + "',";
			sql += "tel=" + "'" + tel + "',";
			sql += "memo=" + "'" + memo + "'";
			sql += " where id='" + id + "'";

			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("수정 완료!!");

		} catch (Exception e2) {
			System.out.println(e2.toString());
		}

		return result;

	}

	// 삭제

	public int deleteData() {

		int result = 0;

		Connection conn = DBConn.getConnection();
		Statement stmt = null;
		String sql;

		try {
			System.out.println("삭제할 아이디?");
			id = sc.next();

			sql = "delete member where id='" + id + "'";

			System.out.println("삭제 성공!!");

			stmt = conn.createStatement();

			result = stmt.executeUpdate(sql);

			stmt.close();

		} catch (Exception e2) {
			System.out.println(e2.toString());
		}

		return result;

	}

	// 전체출력
	// select를 통해 값을 가져오려면 그 값을 담을 그릇인 Interface ResultSet 필요
	// return 필요없음
	public void selectAll() {

		Connection conn = DBConn.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql;
		String str;

		try {

			sql = "select id, pwd, name,";
			sql += "to_char(birth, 'YYYY-MM-DD') birth, tel, memo from member order by id";
			// 중요한 사항 to_char() birth로 가명을 적어주는게 포인트

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				id = rs.getString("id");
				pw = rs.getString("pwd");
				name = rs.getString("name");
				birth = rs.getString("birth");
				tel = rs.getString("tel");
				memo = rs.getString("memo");

				System.out.println("    아이디   비밀번호   이름  생년월일   전화번호   메모");
				System.out.println("========================================");
				str = String.format("%10s %5s %10s %10s %10s %10s", id, pw,
						name, birth, tel, memo);

				System.out.println(str);

			}

			rs.close();
			stmt.close();

		} catch (Exception e2) {
			System.out.println(e2.toString());
		}

	}

	// 아이디 검색
	public void searchId() {

		Connection conn = DBConn.getConnection();
		Scanner sc = new Scanner(System.in);
		Statement stmt = null;
		ResultSet rs = null;
		String sql;
		String id, str;

		try {
			System.out.println("검색할 아이디?");
			id = sc.next();

			sql = "select id, pwd, name,";
			sql += "to_char(birth, 'YYYY-MM-DD') birth, tel, memo from member";
			sql += " where id like '%" + id + "%' order by id";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				id = rs.getString("id");
				pw = rs.getString("pwd");
				name = rs.getString("name");
				birth = rs.getString("birth");
				tel = rs.getString("tel");
				memo = rs.getString("memo");

				str = String.format("%10s %10s %10s %10s %10s %10s", id, pw,
						name, birth, tel, memo);

				System.out.println(str);

			}

			rs.close();
			stmt.close();

		} catch (Exception e2) {
			System.out.println(e2.toString());
		}

	}

	public boolean checkId(String userId) {

		Connection conn = DBConn.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql, id;

		try {
			sql = "select id from member ";
			sql += "where id='" + userId + "'";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {

				id = rs.getString("id");

				if (userId.equals(id)) {
					return true;
				}

			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;

	}

}
