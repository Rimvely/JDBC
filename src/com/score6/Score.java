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

	// 1.�Է�
	// �Է��� ���� ������

	public int insertData() {

		int result = 0;

		Connection conn = DBConn.getConnection();
		Statement stmt = null;
		String sql;

		try {

			System.out.println("�й�?");
			hak = sc.next();

			System.out.println("�̸�?");
			name = sc.next();

			System.out.println("����?");
			kor = sc.nextInt();

			System.out.println("����?");
			eng = sc.nextInt();

			System.out.println("����?");
			mat = sc.nextInt();

			sql = "insert into score (hak, name, kor, eng, mat) values (";
			sql += "'" + hak + "',";
			sql += "'" + name + "',";
			sql += kor + ",";
			sql += eng + ",";
			sql += mat + ")";
			
			//System.out.println(sql); �� �������� Ȯ��
			
			stmt = conn.createStatement();

			result = stmt.executeUpdate(sql);

			stmt.close();

			System.out.println("������ �Է� �Ϸ�!!");

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;
	}

	// 2. ����
	public int updateData() {

		int result = 0;

		Connection conn = DBConn.getConnection();
		Statement stmt = null;
		String sql;

		try {

			System.out.println("�й�?");
			hak = sc.next();

			System.out.println("����?");
			kor = sc.nextInt();

			System.out.println("����?");
			eng = sc.nextInt();

			System.out.println("����?");
			mat = sc.nextInt();

			sql = "update score set ";
			sql += "kor=" + kor + ",";
			sql += "eng=" + eng + ",";
			sql += "mat=" + mat;
			sql += " where hak='" + hak + "'"; // ���� �����̱⶧���� ' ' ��

			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("���� �Ϸ�!!");

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}

	// 3. ����
	public int deleteData() {

		int result = 0;

		Connection conn = DBConn.getConnection();
		Statement stmt = null;
		String sql;

		try {
			System.out.print("������ �й�?");
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

	// 4. ��ü���
	// select�� ���� ���� ���������� �� ���� ���� �׸��� Interface ResultSet �ʿ�
	
	//select = ResultSet
	//select�� table�� ��ȯ�ϱ⶧���� excuteQuery �� ���
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

	// 5.�̸��˻�
	public void searchData() {

		Connection conn = DBConn.getConnection();
		Scanner sc = new Scanner(System.in);
		Statement stmt = null;
		ResultSet rs = null;
		String sql;
		String str;
		String name;

		try {
			System.out.print("�̸�?");
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
			System.out.println("�̸��� �����ϴ�");
		}

	}

}
