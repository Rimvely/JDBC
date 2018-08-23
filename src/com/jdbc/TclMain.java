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
					System.out.print("1.�Է� 2.��� 3.����");
					ch = sc.nextInt();
				} while (ch < 1 || ch > 3);

				switch (ch) {
				// �������� �����͸� �ѹ��� �� �ް� �Ѿ���ϱ⶧����
				// �Է� �޾Ƽ� test1, test2, test3 �־���ϱ⶧�Ź���
				// conn.setAutoCommit(false);
				case 1:
					conn.setAutoCommit(false);
					System.out.print("���̵�(����)?");
					id = sc.nextInt();

					System.out.print("�̸�?");
					name = sc.next();

					System.out.print("����(yyyy-mm-dd)?");
					birth = sc.next();

					System.out.print("��ȭ��ȣ?");
					tel = sc.next();

					// ------------------------------------------------------
					sql = String.format(
							"insert into test1(id, name) values (%d, '%s')",
							id, name);

					stmt = conn.createStatement();

					stmt.executeUpdate(sql);

					stmt.close();// ���̺��� �ٸ��⶧���� �Ź� �ݾ�����Ѵ�
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

					conn.commit(); // �׷��� �� �ް� �������� conn.commit(); �ؾ��Ѵ�

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
