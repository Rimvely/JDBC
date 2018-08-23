package com.score6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.db.DBConn;

public class Score {

	String hak, name;
	int kor, eng, mat, tot, avg;

	Scanner sc = new Scanner(System.in);

	// 1.입력
	// 입력을 위한 데이터

	public int insertData() {

		int result = 0;

		Connection conn = DBConn.getConnection();
		Statement stmt = null;
		String sql;

		try {

			System.out.println("학번?");
			hak = sc.next();

			System.out.println("이름?");
			name = sc.next();

			System.out.println("국어?");
			kor = sc.nextInt();

			System.out.println("영어?");
			eng = sc.nextInt();

			System.out.println("수학?");
			mat = sc.nextInt();

			sql = "insert into score (hak, name, kor, eng, mat) values (";
			sql += "'" + hak + "',";
			sql += "'" + name + "',";
			sql += kor + ",";
			sql += eng + ",";
			sql += mat + ")";
			
			//System.out.println(sql); 잘 나오는지 확인
			
			stmt = conn.createStatement();

			result = stmt.executeUpdate(sql);

			stmt.close();

			System.out.println("데이터 입력 완료!!");

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;
	}

	// 2. 수정
	public int updateData() {

		int result = 0;

		Connection conn = DBConn.getConnection();
		Statement stmt = null;
		String sql;

		try {

			System.out.println("학번?");
			hak = sc.next();

			System.out.println("국어?");
			kor = sc.nextInt();

			System.out.println("영어?");
			eng = sc.nextInt();

			System.out.println("수학?");
			mat = sc.nextInt();

			sql = "update score set ";
			sql += "kor=" + kor + ",";
			sql += "eng=" + eng + ",";
			sql += "mat=" + mat;
			sql += " where hak='" + hak + "'"; // 학은 문장이기때문에 ' ' 들어감

			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("수정 완료!!");

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}

	// 3. 삭제
	public int deleteData() {

		int result = 0;

		Connection conn = DBConn.getConnection();
		Statement stmt = null;
		String sql;

		try {
			System.out.print("삭제할 학번?");
			hak = sc.next();

			sql = "delete score where hak='" + hak + "'";

			stmt = conn.createStatement();

			result = stmt.executeUpdate(sql);

			stmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}

	// 4. 전체출력
	// select를 통해 값을 가져오려면 그 값을 담을 그릇인 Interface ResultSet 필요
	
	//select = ResultSet
	//select는 table을 반환하기때문에 excuteQuery 를 사용
	public void selectAll() {

		Connection conn = DBConn.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql;
		String str;

		try {

			sql = "select hak, name, kor, eng, mat,";
			sql += "(kor+eng+mat) tot,";
			sql += "(kor+eng+mat)/3 avg ";
			sql += "from score order by hak";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				hak = rs.getString(1);
				name = rs.getString("name");
				kor = rs.getInt(3);
				eng = rs.getInt("eng");
				mat = rs.getInt(5);
				tot = rs.getInt(6);
				avg = rs.getInt("avg");

				str = String.format("%10s %10s %4d %4d %4d %4d %4d", hak, name,
						kor, eng, mat, tot, avg);

				System.out.println(str);

			}

			rs.close();
			stmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	// 5.이름검색
	public void searchData() {

		Connection conn = DBConn.getConnection();
		Scanner sc = new Scanner(System.in);
		Statement stmt = null;
		ResultSet rs = null;
		String sql;
		String str;
		String name;

		try {
			System.out.print("이름?");
			name = sc.next();
			
			sql = "select hak,name,kor,eng,mat,";
			sql += "(kor+eng+mat) tot,";
			sql += "(kor+eng+mat)/3 avg ";
			sql += "from score where name like '%"+name+"%' order by hak";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				hak = rs.getString(1);
				name = rs.getString("name");
				kor = rs.getInt(3);
				eng = rs.getInt("eng");
				mat = rs.getInt(5);
				tot = rs.getInt(6);
				avg = rs.getInt("avg");

				str = String.format("%10s %10s %4d %4d %4d %4d %4d", hak, name,
						kor, eng, mat, tot, avg);

				System.out.println(str);

			}

			rs.close();
			stmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("이름이 없습니다");
		}

	}

}
