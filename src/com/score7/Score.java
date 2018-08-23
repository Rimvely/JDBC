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

	// �Է�
	public int insertData() throws Exception {

		int result = 0;

		Connection conn = DBConn.getConnection();
		Statement stmt = null;
		String sql;

		try {

			System.out.println("���̵�?");
			id = sc.next();

			if (checkId(id)) {
				System.out.println("�ߺ��� ���̵� �ֽ��ϴ�.");
				return 0; //false����ϸ� ��������. return 0�� �߿��ϴ�
			}

			System.out.println("��й�ȣ?");
			pw = sc.next();

			System.out.println("�̸�?");
			name = sc.next();

			System.out.println("�������?");
			birth = sc.next();

			System.out.println("��ȭ��ȣ?");
			tel = sc.next();

			System.out.println("��Ÿ����?");
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
			System.out.println("������ �Է� �Ϸ�!!");

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("���̵� �ߺ��� �� �����ϴ�");
		}

		return result;

	}

	// ����
	public int updateData() {

		int result = 0;

		Connection conn = DBConn.getConnection();
		Statement stmt = null;
		String sql;

		try {

			System.out.println("���̵�?");
			id = sc.next();

			System.out.println("�̸�?");
			name = sc.next();

			System.out.println("�������?");
			birth = sc.next();

			System.out.println("��ȭ��ȣ?");
			tel = sc.next();

			System.out.println("��Ÿ����?");
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
			System.out.println("���� �Ϸ�!!");

		} catch (Exception e2) {
			System.out.println(e2.toString());
		}

		return result;

	}

	// ����

	public int deleteData() {

		int result = 0;

		Connection conn = DBConn.getConnection();
		Statement stmt = null;
		String sql;

		try {
			System.out.println("������ ���̵�?");
			id = sc.next();

			sql = "delete member where id='" + id + "'";

			System.out.println("���� ����!!");

			stmt = conn.createStatement();

			result = stmt.executeUpdate(sql);

			stmt.close();

		} catch (Exception e2) {
			System.out.println(e2.toString());
		}

		return result;

	}

	// ��ü���
	// select�� ���� ���� ���������� �� ���� ���� �׸��� Interface ResultSet �ʿ�
	// return �ʿ����
	public void selectAll() {

		Connection conn = DBConn.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql;
		String str;

		try {

			sql = "select id, pwd, name,";
			sql += "to_char(birth, 'YYYY-MM-DD') birth, tel, memo from member order by id";
			// �߿��� ���� to_char() birth�� ������ �����ִ°� ����Ʈ

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				id = rs.getString("id");
				pw = rs.getString("pwd");
				name = rs.getString("name");
				birth = rs.getString("birth");
				tel = rs.getString("tel");
				memo = rs.getString("memo");

				System.out.println("    ���̵�   ��й�ȣ   �̸�  �������   ��ȭ��ȣ   �޸�");
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

	// ���̵� �˻�
	public void searchId() {

		Connection conn = DBConn.getConnection();
		Scanner sc = new Scanner(System.in);
		Statement stmt = null;
		ResultSet rs = null;
		String sql;
		String id, str;

		try {
			System.out.println("�˻��� ���̵�?");
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
