package com.jdbc;

import java.sql.Connection;
import java.sql.Statement;

import com.db.DBConn;

public class Test2 {

	public static void main(String[] args) {

		Connection conn = DBConn.getConnection();

		if (conn == null) {
			System.out.println("�����ͺ��̽� ���� ����!!");
			System.exit(0);
		}

		// DB����
		// 1.DriverManager�� Connection�� ����
		// 2.Connection�� Statement�� ����
		// 3.Statement�� Query�� ����

		try {
			// 2.Connection�� Statement�� ����
			// SQL������ �����ϴ� Interface
			Statement stmt = conn.createStatement();
			String sql;

			sql = "insert into score(hak, name, kor, eng, mat) "; // �ݵ�� ��ĭ
			// ����
			// ) " �ض�!
			sql += "values ('112', '�����', 90, 50, 60)"; // ������ �ȶ��� " values
			// ��
			// �ص��ȴ�.

			// sql = "update score set kor=99, eng=99, mat=99";
			// sql += "where hak=111";

			// sql = "delete score where hak=111";

			int result = stmt.executeUpdate(sql); // insert, update, delete��
													// excuteUpdate
													// select �� excuteQuery
			// ������ ����� �Ǹ� result ���� 1
			// ������ ����� �ȵǸ� result ���� 0

			if (result == 1) {
				System.out.println("�߰� ����!!");

			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		// �ݵ�� try�ȿ���
		DBConn.close();

	}

}
